#	函数
+	函数名是指向一个函数对象的引用，可以把函数名赋给一个变量
+	定义 def , 
	<br>参数 传值，复制
	<br>参数顺序：必选参数、默认参数、可变参数、命名关键字参数、关键字参数

		# 默认参数必须指向不变对象,None，(内部变量全局通用)
		# Python函数在定义的时候，默认参数值就被计算出来了
		def my_method(x,y=0):
    		statement1
			statement2
			return x
		
		# 传入list或tuple
		def calc(numbers):
    		sum = 0
    		for n in numbers:
        		sum = sum + n * n
    		return sum
		>>> calc([1, 2, 3])

		# 可变参数  传入多个参数 *params-->list,tuple
		def calc(*numbers):
    		sum = 0
    		for n in numbers:
        		sum = sum + n * n
    		return sum
		>>> calc(1, 2,3)
		>>> nums = [1, 2, 3]
		>>> calc(*nums)
		

		# 关键字参数 ** params-->dict 拷贝
		def person(name, age, **kw):
    		print('name:', name, 'age:', age, 'other:', kw)
		>>> person('Michael', 30)
		name: Michael age: 30 other: {}
		>>> person('Adam', 45, gender='M', job='Engineer')
		name: Adam age: 45 other: {'gender': 'M', 'job': 'Engineer'}
		>>> extra = {'city': 'Beijing', 'job': 'Engineer'}
		>>> person('Jack', 24, **extra)
		name: Jack age: 24 other: {'city': 'Beijing', 'job': 'Engineer'}
		
		# 命名关键字参数
		def person(name, age, *, city, job):
    		print(name, age, city, job)
		>>> person('Jack', 24, city='Beijing', job='Engineer')
		Jack 24 Beijing Engineer
		# 函数定义中已经有了一个可变参数，后面跟着的命名关键字参数就不再需要*
		def person(name, age, *args, city, job):
    		print(name, age, args, city, job)
		# 命名关键字参数可以有缺省值
		def person(name, age, *, city='Beijing', job):
    		print(name, age, city, job)

	return None可以简写为return
	如果没有return语句，函数执行完毕后也会返回结果，只是结果为None
+	递归

##	导入
+	导入函数 from fileName import methodName

##	返回 return
+	return	返回None
+	return None
+	return a,b	返回tuple (a,b)


##	高阶函数：接受函数变量输入

+	
		def add(x, y, f):
		    return f(x) + f(y)
		
		add(-5, 6, abs)
		abs(-5) + abs(6) ==> 11

+	map(func,list)

		>>> def f(x):
		...     return x * x
		...
		>>> r = map(f, [1, 2, 3, 4, 5, 6, 7, 8, 9])
		>>> list(r)
		[1, 4, 9, 16, 25, 36, 49, 64, 81]
		#	list()函数使Iterator把值计算出来并返回list

+	reduce(func,list)结果继续和序列的下一个元素做累积计算

		>>> from functools import reduce
		>>> def add(x, y):
		...     return x + y
		...
		>>> reduce(add, [1, 3, 5, 7, 9])
		25

+	filter(func,list)

+	sorted()
	+	sorted(list)
	+	sorted(list,key=func) 以key的排列顺序作为list的排列顺序
	+	sorted(list,key=func,reverse=True) 反向排序


##	返回函数 闭包（Closure）

		# 内部函数sum可以引用外部函数lazy_sum的参数和局部变量
		def lazy_sum(*args):
 		   def sum():
 		       ax = 0
		        for n in args:
		            ax = ax + n
 		       return ax
		    return sum
		
		>>> f = lazy_sum(1, 3, 5, 7, 9)
		>>> f
		<function lazy_sum.<locals>.sum at 0x101c6ed90>
		
		# 调用返回的函数时，内部函数才会执行
		# 返回的函数在执行时读取引用的外部变量
		>>> f()
		25
		
		# 每次调用都会返回一个新的函数，即使传入相同的参数
		>>> f1 = lazy_sum(1, 3, 5, 7, 9)
		>>> f2 = lazy_sum(1, 3, 5, 7, 9)
		>>> f1==f2
		False


##	匿名函数

+	关键字lambda表示匿名函数，冒号前表示函数参数

		list(map(lambda x: x * x, [1, 2, 3, 4, 5, 6, 7, 8, 9]))
		
		lambda x: x * x
		
		def f(x):
		    return x * x


##	装饰器 decorator 返回函数的高阶函数 @语法

		# __name__属性 函数的名称
		def log(func):
		    def wrapper(*args, **kw):
		        print('call %s():' % func.__name__)
		        return func(*args, **kw)
		    return wrapper
		
		# now = log(now)
		@log
		def now():
		    print('2015-3-25')
		
		>>> now()
		call now():
		2015-3-25


		# decorator本身需要传入参数
		def log(text):
		    def decorator(func):
				@functools.wraps(func)
		        def wrapper(*args, **kw):
		            print('%s %s():' % (text, func.__name__))
		            return func(*args, **kw)
		        return wrapper
		    return decorator

		@log('execute')
		def now():
		    print('2015-3-25')


#	functools模块

##	偏函数 functools.partial

		import functools
		int2 = functools.partial(int, base=2)
		
		# 等于
		def int2(x, base=2):
		    return int(x, base)
		















