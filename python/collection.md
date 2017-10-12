##	集合	可变
+	[]	list列表 [value,] 数据类型可以不同，有序，支持多维
	<br>访问 listName[index],倒数访问listName[-index]，个数len(listName)
	<br>添加 listName.append(val),listName.insert(index,val)
	<br>删除 list末尾的元素listName.pop(),listName.pop(index)
	<br>替换 listName[index]=newVal
	<br>分割 [from:toNext]	[from:toNext:every]
	
+   初始化
    
            [n for n in range(5, 10)]
            # [5, 6, 7, 8, 9]
            ['x' for n in range(5)]
            # ['x', 'x', 'x', 'x', 'x']
            ['z']*5
            # ['z', 'z', 'z', 'z', 'z']

+	()	tuple元组 (value,) 一旦初始化就不能修改
	<br>定义一个空的tuple，可以写成()
	<br>对于一个只有1个元素的tuple
	<br>()既可表示tuple，又可表示运算小括号，按小括号进行计算
	<br>(1)-->1
	<br>(1,)-->(1,)


+	{:}	dict字典 {key:value,} 无顺序  必须保证key不可变(非list)
	<br>判断存在 key in dictName	
	<br>取值dictName[key] 如果key不存在，dict就会报错
	<br>dictName.get(key),dictName.get(key,default)如果key不存在,返回None,或者指定的value
	<br>添加dictName[key]=value，一对一，key重复会覆盖原来的
	<br>删除dictName.pop(key)


+	set([])	set 无序，不重复 
	<br>创建 setName=set([1,2,3,4])
	<br>添加 setName.add(value)
	<br>删除 setName.remove(value)


+	迭代

		#	dict
		#	for value in d.values()
		#	for k, v in d.items()
		>>> d = {'a': 1, 'b': 2, 'c': 3}
		>>> for key in d:
		...     print(key)
		...
		a
		c
		b

		>>> for ch in 'ABC':
		...     print(ch)
		...
		A
		B
		C

##	列表生成式 [ for in if]

		>>> [x * x for x in range(1, 11)]
		[1, 4, 9, 16, 25, 36, 49, 64, 81, 100]
		
		#	if 判断
		>>> [x * x for x in range(1, 11) if x % 2 == 0]
		[4, 16, 36, 64, 100]
		
		#	嵌套
		>>> [m + n for m in 'ABC' for n in 'XYZ']
		['AX', 'AY', 'AZ', 'BX', 'BY', 'BZ', 'CX', 'CY', 'CZ']

		
		#	多变量
		>>> d = {'x': 'A', 'y': 'B', 'z': 'C' }
		>>> [k + '=' + v for k, v in d.items()]
		['y=B', 'x=A', 'z=C']


##	生成器 generator 一边循环一边计算

+	只要把一个列表生成式的[]改成()

		>>> L = [x * x for x in range(10)]
		>>> L
		[0, 1, 4, 9, 16, 25, 36, 49, 64, 81]
		>>> g = (x * x for x in range(10))
		>>> g
		<generator object <genexpr> at 0x1022ef630>	
		
		#	使用next获取值
		>>> next(g)
		0
		>>> next(g)
		1
		>>> next(g)
		4
		#	没有更多的元素时，抛出StopIteration的错误。
		
		#	可迭代
		>>> for n in g:
		...     print(n)


+	函数定义 yield

		#	在每次调用next()的时候执行，遇到yield语句返回
		#	再次执行时从上次返回的yield语句处继续执行。
		def fib(max):
		    n, a, b = 0, 0, 1
		    while n < max:
		        yield b
 		       a, b = b, a + b
		        n = n + 1
		    return 'done'


+	获取返回值
<br>用for循环调用generator时，发现拿不到generator的return语句的返回值。如果想要拿到返回值，必须捕获StopIteration错误，返回值包含在StopIteration的value中：

		>>> g = fib(6)
		>>> while True:
		...     try:
		...         x = next(g)
		...         print('g:', x)
		...     except StopIteration as e:
		...         print('Generator return value:', e.value)
		...         break



+	迭代器

	+	可迭代对象Iterable，用于for循环
		<br>list、tuple、dict、set、str、generator
		<br>判断 isinstance(100, Iterable) False

	+	迭代器Iterator，可以被next()函数调用并不断返回下一个值的对象
		<br>generator
		<br>使用iter()函数把list、dict、str等Iterable变成Iterator
		<br>判断 isinstance([], Iterator) False



















