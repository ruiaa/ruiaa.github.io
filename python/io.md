file.close()关闭文件。关闭后文件不能再进行读写操作。
file.flush()刷新文件内部缓冲，直接把内部缓冲区的数据立刻写入文件, 而不是被动的等待输出缓冲区写入。
file.fileno()返回一个整型的文件描述符(file descriptor FD 整型), 可以用在如os模块的read方法等一些底层操作上。
file.isatty()如果文件连接到一个终端设备返回 True，否则返回 False。
file.next()返回文件下一行。
file.read([size])从文件读取指定的字节数，如果未给定或为负则读取所有。
file.readline([size])读取整行，包括 "\n" 字符。
file.readlines([sizeint])读取所有行并返回列表，若给定sizeint>0，返回总和大约为sizeint字节的行, 实际读取值可能比 sizeint 较大, 因为需要填充缓冲区。
file.seek(offset[, whence])设置文件当前位置
file.tell()返回文件当前位置。
file.truncate([size])从文件的首行首字符开始截断，截断文件为 size 个字符，无 size 表示从当前位置截断；截断之后后面的所有字符被删除，其中 Widnows 系统下的换行代表2个字符大小。
file.write(str)将字符串写入文件，返回的是写入的字符长度。
file.writelines(sequence)向文件写入一个序列字符串列表，如果需要换行则要自己加入每行的换行符。

#	IO

##	文件读写
+	open()	打开文件对象
+	
+	IOError	
+	
+	close()	文件使用完毕后必须关闭
+	with语句自动调用close()方法
+
+	read()	一次读取文件的全部内容，用一个str对象表示
+	read(size)方法，每次最多读取size个字节的内容
+	readline()可以每次读取一行内容
+	readlines()一次读取所有内容并按行返回list
+	
+	write()
	
	
	>>> f = open('/Users/michael/test.txt', 'r')
	#	r:读
	#	rb:读二进制
	#	w:写
	#	wb：写二进制

	try:
	    f = open('/path/to/file', 'r')
	    print(f.read())
	finally:
	    if f:
	        f.close()

	#	自动调用close()方法
	with open('/path/to/file', 'r') as f:
    	print(f.read())



##	字符编码 encoding参数，errors参数
+	open()函数传入encoding参数
+	编码不规范的文件，UnicodeDecodeError
+	open()函数传入errors参数，表示如何处理编码错误

	
	>>> f = open('/Users/michael/gbk.txt', 'r', encoding='gbk')

	#	直接忽略编码错误：
	>>> f = open('/Users/michael/gbk.txt', 'r', encoding='gbk', errors='ignore')


##	StringIO	内存中读写str

+	from io import StringIO
+	getvalue()方法用于获得写入后的str


	>>> from io import StringIO
	>>> f = StringIO()
	>>> f.write('hello')
	5
	>>> f.write(' ')
	1
	>>> f.write('world!')
	6
	>>> print(f.getvalue())
	hello world!

##	BytesIO	内存中读写bytes


##	操作文件和目录
+	直接调用操作系统提供的接口函数 import os 
+	扩充 shutil模块
+	os.environ 环境变量 os.environ.get('key')
+	操作文件和目录的函数一部分放在os模块中，一部分放在os.path模块中
+	目录操作


	# 查看当前目录的绝对路径:
	>>> os.path.abspath('.')
	'/Users/michael'

	# 合并路径:
	>>> os.path.join('/Users/michael', 'testdir')
	'/Users/michael/testdir'
	# 拆分路径
	>>> os.path.split('/Users/michael/testdir/file.txt')
	('/Users/michael/testdir', 'file.txt')
	# 文件扩展名
	>>> os.path.splitext('/path/to/file.txt')
	('/path/to/file', '.txt')


	# 创建一个目录:
	>>> os.mkdir('/Users/michael/testdir')
	# 删掉一个目录:
	>>> os.rmdir('/Users/michael/testdir')

    
    # 桌面路径
    import winreg
    def desktop_path():
        key = winreg.OpenKey(winreg.HKEY_CURRENT_USER,
                             r'Software\Microsoft\Windows\CurrentVersion\Explorer\Shell Folders')
        return winreg.QueryValueEx(key, "Desktop")[0]
 
+	文件操作


	# 对文件重命名:
	>>> os.rename('test.txt', 'test.py')
	# 删掉文件:
	>>> os.remove('test.py')


    
    
    os.listdir() 
    返回指定的文件夹包含的文件或文件夹的名字的列表。这个列表以字母顺序。 它不包括 '.' 和'..' 即使它在文件夹中。

##	序列化 pickle模块

+	dumps()把任意对象序列化成一个bytes
+	dump()把对象序列化后写入一个file-like Object


	>>> f = open('dump.txt', 'wb')
	>>> pickle.dump(d, f)
	>>> f.close()

+	loads()反序列化出对象
+	load()方法从一个file-like Object中直接反序列化出对象


	>>> f = open('dump.txt', 'rb')
	>>> d = pickle.load(f)
	>>> f.close()
	>>> d
	{'age': 20, 'score': 88, 'name': 'Bob'}

##	JSON	json模块

+	{}对应	dict
+	[]对应	list
+	"string"对应	str
+	1234.56对应	int或float
+	true/false对应	True/False
+	null对应	None
+	
+	dumps()返回一个str，内容就是标准的JSON
+	dump()方法可以直接把JSON写入一个file-like Object
+	loads()把JSON的字符串反序列化
+	load()从file-like Object中读取字符串并反序列化：
+	
+	class转Json
	+	default 传入class->json转换函数
	+	object_hook 传入json->class转换函数


	def student2dict(std):
	    return {
	        'name': std.name,
	        'age': std.age,
	        'score': std.score
	    }
	#print(json.dumps(s, default=lambda obj: obj.__dict__))
	>>> print(json.dumps(s, default=student2dict))
	{"age": 20, "name": "Bob", "score": 88}


	def dict2student(d):
	    return Student(d['name'], d['age'], d['score'])
	>>> json_str = '{"age": 20, "score": 88, "name": "Bob"}'
	>>> print(json.loads(json_str, object_hook=dict2student))
	<__main__.Student object at 0x10cd3c190>



#	异步IO asyncio

import threading
    import asyncio
    
    @asyncio.coroutine
    def hello():
        print('Hello world! (%s)' % threading.currentThread())
        yield from asyncio.sleep(1) # waiting for io
        print('Hello again! (%s)' % threading.currentThread())
    
    loop = asyncio.get_event_loop()
    tasks = [hello(), hello()]
    loop.run_until_complete(asyncio.wait(tasks))
    loop.close()

+	@asyncio.coroutine把一个generator标记为coroutine类型
+	yield from doIo() 等待耗时的IO操作
+	当doIo()返回时，线程就可以从yield from拿到返回值，接着执行下一行语句。
+	
+	asyncio.get_event_loop()：从asyncio模块中直接获取一个EventLoop的引用



    import asyncio
    
    @asyncio.coroutine
    def wget(host):
        print('wget %s...' % host)
        connect = asyncio.open_connection(host, 80)
        reader, writer = yield from connect
        header = 'GET / HTTP/1.0\r\nHost: %s\r\n\r\n' % host
        writer.write(header.encode('utf-8'))
        yield from writer.drain()
        while True:
            line = yield from reader.readline()
            if line == b'\r\n':
                break
            print('%s header > %s' % (host, line.decode('utf-8').rstrip()))
        # Ignore the body, close the socket
        writer.close()
    
    loop = asyncio.get_event_loop()
    tasks = [wget(host) for host in ['www.sina.com.cn', 'www.sohu.com', 'www.163.com']]
    loop.run_until_complete(asyncio.wait(tasks))
    loop.close()


##	Python3.5 : async/await

1.	把@asyncio.coroutine替换为async；
2.	把yield from替换为await。


	async def hello():
	    print("Hello world!")
	    r = await asyncio.sleep(1)
	    print("Hello again!")


##	aiohttp
+	asyncio实现了TCP、UDP、SSL等协议，aiohttp则是基于asyncio实现的HTTP框架。
+	pip install aiohttp


    import asyncio
    
    from aiohttp import web
    
    async def index(request):
        await asyncio.sleep(0.5)
        return web.Response(body=b'<h1>Index</h1>')
    
    async def hello(request):
        await asyncio.sleep(0.5)
        text = '<h1>hello, %s!</h1>' % request.match_info['name']
        return web.Response(body=text.encode('utf-8'))
    
    async def init(loop):
        app = web.Application(loop=loop)
        app.router.add_route('GET', '/', index)
        app.router.add_route('GET', '/hello/{name}', hello)
        srv = await loop.create_server(app.make_handler(), '127.0.0.1', 8000)
        print('Server started at http://127.0.0.1:8000...')
        return srv
    
    loop = asyncio.get_event_loop()
    loop.run_until_complete(init(loop))
    loop.run_forever()



















