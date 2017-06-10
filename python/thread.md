#	多进程
+	os模块封装了常见的系统调用
+	Unix/Linux ： os.fork()当前进程（称为父进程）复制了一份（称为子进程）,子进程永远返回0，而父进程返回子进程的ID
+	os.getppid()获取父进程的ID

##	multiprocessing模块 跨平台多进程模块
+	Process类来代表一个进程对象
+	创建一个Process实例来创建子进程，传入一个由子进程执行的函数和函数的参数
+	Process.start()启动
+	Process.join()等待子进程结束后再继续往下运行，通常用于进程间的同步


	from multiprocessing import Process
	import os
	
	# 子进程要执行的代码
	def run_proc(name):
	    print('Run child process %s (%s)...' % (name, os.getpid()))
	
	if __name__=='__main__':
	    print('Parent process %s.' % os.getpid())
	    p = Process(target=run_proc, args=('test',))
	    print('Child process will start.')
	    p.start()
	    p.join()
	    print('Child process end.')

###	multiprocessing 进程池Pool
+	apply_async()添加子进程
+	join()方法会等待所有子进程执行完毕
+	调用join()之前必须先调用close()，调用close()之后就不能继续添加新的Process
+
+	Pool的默认大小是4，p = Pool(5)就可以同时跑5个进程


	from multiprocessing import Pool
	import os, time, random
	
	def long_time_task(name):
	    print('Run task %s (%s)...' % (name, os.getpid()))
	    start = time.time()
	    time.sleep(random.random() * 3)
	    end = time.time()
	    print('Task %s runs %0.2f seconds.' % (name, (end - start)))
	
	if __name__=='__main__':
	    print('Parent process %s.' % os.getpid())
	    p = Pool(4)
	    for i in range(5):
	        p.apply_async(long_time_task, args=(i,))
	    print('Waiting for all subprocesses done...')
	    p.close()
	    p.join()
	    print('All subprocesses done.')


##	外部子进程	subprocess模块
+	call
+	communicate()

##	进程间通信 multiprocessing模块:Queue,Pipes


	from multiprocessing import Process, Queue
	import os, time, random
	
	# 写数据进程执行的代码:
	def write(q):
	    print('Process to write: %s' % os.getpid())
	    for value in ['A', 'B', 'C']:
	        print('Put %s to queue...' % value)
	        q.put(value)
	        time.sleep(random.random())
	
	# 读数据进程执行的代码:
	def read(q):
	    print('Process to read: %s' % os.getpid())
	    while True:
	        value = q.get(True)
	        print('Get %s from queue.' % value)
	
	if __name__=='__main__':
	    # 父进程创建Queue，并传给各个子进程：
	    q = Queue()
	    pw = Process(target=write, args=(q,))
	    pr = Process(target=read, args=(q,))
	    # 启动子进程pw，写入:
	    pw.start()
	    # 启动子进程pr，读取:
	    pr.start()
	    # 等待pw结束:
	    pw.join()
	    # pr进程里是死循环，无法等待其结束，只能强行终止:
	    pr.terminate()


##	分布式进程 managers

+	master_process


	# task_master.py
	
	import random, time, queue
	from multiprocessing.managers import BaseManager
	
	# 发送任务的队列:
	task_queue = queue.Queue()
	# 接收结果的队列:
	result_queue = queue.Queue()
	
	# 从BaseManager继承的QueueManager:
	class QueueManager(BaseManager):
	    pass
	
	# 把两个Queue都注册到网络上, callable参数关联了Queue对象:
	QueueManager.register('get_task_queue', callable=lambda: task_queue)
	QueueManager.register('get_result_queue', callable=lambda: result_queue)
	# 绑定端口5000, 设置验证码'abc':
	manager = QueueManager(address=('', 5000), authkey=b'abc')
	# 启动Queue:
	manager.start()
	# 获得通过网络访问的Queue对象:
	task = manager.get_task_queue()
	result = manager.get_result_queue()
	# 放几个任务进去:
	for i in range(10):
	    n = random.randint(0, 10000)
	    print('Put task %d...' % n)
	    task.put(n)
	# 从result队列读取结果:
	print('Try get results...')
	for i in range(10):
	    r = result.get(timeout=10)
	    print('Result: %s' % r)
	# 关闭:
	manager.shutdown()
	print('master exit.')


+	worker_process


	# task_worker.py
	
	import time, sys, queue
	from multiprocessing.managers import BaseManager
	
	# 创建类似的QueueManager:
	class QueueManager(BaseManager):
	    pass
	
	# 由于这个QueueManager只从网络上获取Queue，所以注册时只提供名字:
	QueueManager.register('get_task_queue')
	QueueManager.register('get_result_queue')
	
	# 连接到服务器，也就是运行task_master.py的机器:
	server_addr = '127.0.0.1'
	print('Connect to server %s...' % server_addr)
	# 端口和验证码注意保持与task_master.py设置的完全一致:
	m = QueueManager(address=(server_addr, 5000), authkey=b'abc')
	# 从网络连接:
	m.connect()
	# 获取Queue的对象:
	task = m.get_task_queue()
	result = m.get_result_queue()
	# 从task队列取任务,并把结果写入result队列:
	for i in range(10):
	    try:
	        n = task.get(timeout=1)
	        print('run task %d * %d...' % (n, n))
	        r = '%d * %d = %d' % (n, n, n*n)
	        time.sleep(1)
	        result.put(r)
	    except Queue.Empty:
	        print('task queue is empty.')
	# 处理结束:
	print('worker exit.')



#	多线程 共享内存数据 _thread和threading
+	_thread是低级模块，threading是高级模块
+
+	threading：
+	创建 t=threading.Thread(target=funcname,name='threadname')
+	start()
+	join()
+	current_thread()返回当前线程的实例。主线程实例的名字叫MainThread

##	Lock
+	mylock = threading.Lock()
+	获取锁 mylock.acquire()
+	释放锁 mylock.release()
+	当多个线程同时执行mylock.acquire()时，只有一个线程能成功地获取锁，然后继续执行代码，其他线程就继续等待直到获得锁为止。
+	用try...finally来确保锁一定会被释放

##	ThreadLocal 线程局部变量
+	local_school = threading.local() # 创建全局ThreadLocal对象
+	local_school.student=Student('locstr') # 绑定ThreadLocal的student
+	std=local_school.student # 获取当前线程关联的student







#	协程：在子程序内部可中断，然后转而执行别的子程序，无线程切换
+	多进程+协程

##	generator

	def consumer():
	    r = ''
	    while True:
	        n = yield r
	        if not n:
	            return
	        print('[CONSUMER] Consuming %s...' % n)
	        r = '200 OK'
	
	def produce(c):
	    c.send(None)
	    n = 0
	    while n < 5:
	        n = n + 1
	        print('[PRODUCER] Producing %s...' % n)
	        r = c.send(n)
	        print('[PRODUCER] Consumer return: %s' % r)
	    c.close()
	
	c = consumer()
	produce(c)

consumer函数是一个generator，把一个consumer传入produce后：

1.	首先调用c.send(None)启动生成器；

2.	然后，一旦生产了东西，通过c.send(n)切换到consumer执行；

3.	consumer通过yield拿到消息，处理，又通过yield把结果传回；

4.	produce拿到consumer处理的结果，继续生产下一条消息；

5.	produce决定不生产了，通过c.close()关闭consumer，整个过程结束。

整个流程无锁，由一个线程执行，produce和consumer协作完成任务，所以称为“协程”，而非线程的抢占式多任务。

























