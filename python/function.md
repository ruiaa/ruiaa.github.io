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


##	装饰器 decorator 返回函数的高阶函数
+   @语法:将被装饰的函数作为参数传给装饰器
+   调用被装饰的函数时，相当于调用装饰器函数，再由装饰器内部调用被装饰的函数

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

##	偏函数 functools.partial ： 把一个函数的某些参数给固定住（也就是设置默认值），并作为一个新的函数返回

	import functools
	int2 = functools.partial(int, base=2)

	# 等于
	def int2(x, base=2):
	    return int(x, base)
		











#   [内置函数](https://songlee24.github.io/2014/08/30/python-library-01/)
1、abs(x)：返回一个整数或浮点数的绝对值，如果是复数，返回它的模。

2、all(iterable)：当 iterable 中所有元素都为 True 时（或者 iterable 为空），返回 True 。

3、any(iterable)：当 iterable 中有元素为 True 时，则返回 True 。如果 iterable 为空，返回 False 。

4、ascii(object)：类似于repr()，返回一个输入对象的可打印的字符串。

5、bin(x)：将整数 x 转化为一个二进制字符串。当 x 不是 int 对象时，x 必须实现__index__()方法来返回一个整型数值。

1
2
>>> bin(9)
'0b1001'
6、bool(x)：将一个值转换成一个boolean类型的值，省略 x 将返回 False。

1
2
3
4
>>> bool()
False
>>> bool('fedora')
True
7、bytearray()： bytearray的构造函数。bytearray类型是一个可变的整数序列（0 <= 整数 < 256），即字节数组，例如：

1
2
3
>>> a = bytearray(b'after')
>>> list(a)   # convert a bytearray object into a list of integers
[97, 102, 116, 101, 114]
8、bytes()： 字节对象（bytes object）的构造函数。bytes是bytearray的不可变版本：

1
2
3
>>> a = bytes('after', 'UTF-8')
>>> list(a)   # convert a bytes object into a list of integers
[97, 102, 116, 101, 114]
9、callable(object)：判断一个对象是否可调用，如果一个实例的类实现了call()方法，则它是可以调用的。

1
2
3
4
5
6
>>> f = lambda x,y : x+y
>>> callable(f)   # f是函数对象，可调用
True
>>> a = 10   # a 不可调用
>>> callable(a)
False
10、chr(i)：返回编码值 i 对应的字符（str类型），i 的有效值为 0 到 1114111。与ord()正好相反。

1
2
>>> chr(97)
'a'
11、classmethod(function)：返回一个类方法。

12、compile()：编译一个源，返回一个代码对象，该代码对象可以用来作为exec()或者eval()的参数。

13、complex(re, im)：复数的构造函数，re 为返回复数对象的实数部分，im 为虚数部分。

14、delattr(object, name)：删除一个对象的属性，相当于del object.name。

15、dict()：字典类型的构造函数。

16、dir()：返回一个包含了 object 所有属性的列表对象，如果没有参数，则包含当前作用域的所用属性。

17、divmod(a, b)：返回一个元组(a//b, a%b)。

18、enumerate(iterable, start=0)：返回一个可迭代的 enumerate object，对其使用 next() 得到的是包含索引和元素的tuple，通常用于同时遍历索引和元素：

1
2
3
4
5
6
7
8
>>> seasons = ['Spring', 'Summer', 'Fall', 'Winter']
>>> list(enumerate(seasons))
[(0, 'Spring'), (1, 'Summer'), (2, 'Fall'), (3, 'Winter')]

>>> for x in enumerate(seasons):   # 遍历
...     print(x, end=' ')
...
(0, 'Spring') (1, 'Summer') (2, 'Fall') (3, 'Winter')
相当于：

1
2
3
4
5
def enumerate(sequence, start=0):
    n = start
    for elem in sequence:
        yield n, elem
        n += 1
19、eval()：执行一段代码，返回执行的结果。

1
2
3
>>> x = 1
>>> eval('x+1')
2
20、exec()：也是执行一段代码，返回None。

1
2
3
4
>>> x = 1
>>> exec('x += 10')
>>> x
11
21、filter(function, iterable)：过滤器，返回由使函数 function 返回True的 iterable 元素组成的迭代器。

1
2
3
4
>>> lst = [1, 2, 3, 4, 5, 6, 7, 8]
>>> f = lambda x: x%2==0
>>> list(filter(f, lst))
[2, 4, 6, 8]
22、float(x)：返回一个浮点型的对象，无参时返回0.0

23、format(value[, spec])：格式化一个值，当参数是一个自定义对象的时候，该对象需要实现__format__()方法。

1
2
>>> format(3.1415926, '7.3g')
'   3.14'
24、frozenset()： frozenset 的构造函数。顾名思义，frozenset是一种 set 类型，且不可改变（没有add、remove等方法）。

25、getattr(object, name)：获得对象的name属性，当该属性不存在的时候可以使用一个默认值作为返回值。

26、globals()：返回一个包含当前所有全局符号和对应值的字典。

27、hasattr(object, name)：判断对象是否有name属性。

28、hash(object)：返回对象的 hash 值，object 必须是可哈希的。
注意：所有不可变的内置类型都是 hashable 的，比如 string，tuple；所有可变的内置类型都是 unhashable 的，比如 list，dict（即没有__hash__()方法）。

29、help()：查看一个对象的帮助文档。

30、hex(x)：将一个整数转为小写的十六进制字符串（以’0x’开头），如果不是int对象，需要定义 __index__()方法。

31、id()：返回一个对象的 id 身份，可以看作该对象的内存地址。

32、input()：读取一行输入并返回一个字符串。

33、int(x, base=10)：返回相应进制的 int 值。

34、isinstance(object, class)：判断对象 object 是不是类 class 或其派生类的实例。

35、issubclass(class,baseclass)：判断一个类是否是另一个类的子类。

36、iter()：返回一个可迭代的对象。

37、len()：返回一个长度值，与 object 中的__len__()有关。

38、list()： list 的构造函数。

39、locals()：返回一个包含当前局部符号和对应值的字典，与 globals() 对应。

40、map(function, iterable)：映射函数，将 iterable 中的每个元素应用到 function 函数，返回由所有结果组成的迭代器。

1
2
3
>>> it = map(lambda x: x*2, [1,2,3,4])
>>> list(it)
[2, 4, 6, 8]
41、max()：最大值。

42、min()：最小值。

43、memoryview(obj)：返回一个 memory view 对象。

44、next(iterator)：产生下一个生成值，与__next__()有关。

45、object()：略。

46、oct(x)：将一个整数转为一个八进制字符串。如果不是 int 对象，需要定义__index__()方法。

47、open()：打开一个文件，返回对应的文件对象。

48、ord(c)：返回字符 c 的编码值，与chr(i)相反。

1
2
>>> ord('a')
97
49、pow(x, y[, z])：pow(x, y)相当于x**y，pow(x, y, z)相当于pow(x, y) % z。

50、print()：打印输出。

51、property(fget=None, fset=None, fdel=None, doc=None)：函数 property() 的作用就是把类中的方法当作属性来访问。看下面的例子：

1
2
3
4
5
6
7
8
9
10
11
12
13
14
class C:
    def __init__(self):
        self.__x = None

    def getx(self):
        return self.__x

    def setx(self, value):
        self.__x = value

    def delx(self):
        del self.__x

    x = property(getx, setx, delx, "I'm the 'x' property.")
为了操作数据成员 __x，我们需要使用 getx、setx、delx 方法，很麻烦。但是如果通过 property 函数将方法绑定到成员x，那么当获取成员x的值时，就会调用getx函数；当给成员x赋值时，就会调用setx函数；当删除x时，就会调用delx函数：

1
2
3
4
c = C()
print(c.x)    # 相当于c.getx()
c.x = 20      # 相当于c.setx(20)
del c.x       # 相当于c.delx()
这样通过 x 间接调用方法操作 __x 就方便多了。

52、range(start, stop[, step])：返回一个序列。

53、repr(object)：将对象转化为可打印的字符串。

54、reversed()：倒序序列，对象需要实现__reversed__()方法。

55、round(number[, ndigits])：把浮点数转变成指定小数位数的数，ndigits默认为0。

1
2
>>> round(1.75368, 3)
1.754
56、set()： set 的构造函数。

57、setattr(object, name, value)：为一个对象的name属性设置一个value值。

58、slice(start,stop[,step])：切片函数，分割一个可分割的对象，返回其中的一部分。

59、sorted()：排序。

60、staticmethod(function)：返回一个静态的方法。要知道，一个类的静态方法没有隐式的第一个 self 参数，因为静态方法是独立于实例对象的：

1
2
3
class C:
    @staticmethod
    def f(arg1, arg2, ...): ...
61、str()：字符串的构造函数。

62、sum()：求和。

63、super()： super() 常用于继承中调用父类的方法。例如，类的继承中，通常需要调用父类的构造方法，以初始化父类部分，有两种方法能达到这个目的。

方法一：调用未绑定的父类构造方法

1
2
3
4
5
6
7
8
9
10
11
12
13
14
class A:
    def __init__(self):
        self.a = 'A_method'

class B:
    def __init__(self):
        self.b = 'B_method'

class C(A, B):
    def __init__(self):
        A.__init__(self)
        B.__init__(self)
        # ...其他超类
        self.c = 'C_method'
方法二：使用super函数

1
2
3
4
5
6
7
8
9
10
11
12
13
14
class A:
    def __init__(self):
        super().__init__()
        self.a = 'A_method'

class B:
    def __init__(self):
        super().__init__()
        self.b = 'B_method'

class C(A, B):
    def __init__(self):
        super().__init__()    # 等价于super(C,self).__init__()
        self.c = 'C_method'
可以看出，方法一更直观，但是方法二使用 super 函数可以一次初始化所有超类（但要确保所有的超类的构造方法都使用了super函数）。当继承结构很复杂时，方法二明显更适用，当然 super 不仅可以用于构造方法还可以用于其他方法。

使用 super 还有一个好处，就是当改变父类名时，不需要再去修改其他地方，便于代码的维护。

64、tuple()：元组的构造函数。

65、type()：返回一个对象的类型，返回值与object.__class__一样。

66、vars(object)：返回 object 中所有属性与对应值的字典。没有参数时作用和locals()一样。

67、zip()： zip 函数接受一系列可迭代的对象作为参数，将对象中对应的元素打包成一个个tuple（元组）。

68、__import__()：通过import语句调用。






附：reduce函数

在Python 3里，reduce()函数已经被从全局名字空间里移除了，它现在被放置在functools模块里。函数原型如下：

1
reduce(function, iterable[, initializer])
function 必须是二元函数，在省略第三个参数的情况下，函数先对 iterable 中的第1，2个数据进行操作，得到的结果再与第三个数据用 function() 函数运算……依次类推，最后得到一个结果。如果初始值 initializer 给定，第一次调用会是 initializer 和第一个元素而不是序列的头两个元素。

1
2
3
4
5
>>> from functools import reduce
>>> reduce(lambda x,y: x+y, [1,2,3,4,5])
15
>>> reduce(lambda x,y: x+y, [1,2,3,4,5], 10)
25
map用于映射，reduce用于归并。



