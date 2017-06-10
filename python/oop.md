# Class

##	定义 class ClassName（extendClass）:
+	在类中定义的函数只有一点不同，就是第一个参数永远是实例变量self，并且，调用时，不用传递该参数
+	实例的变量名如果以两个下划线__开头，就变成了一个私有变量（private）
+	不能直接访问__name是因为Python解释器对外把__name变量改成了_Student__name，所以，仍然可以通过_Student__name来访问__name变量;(不同版本的Python解释器可能会把__name改成不同的变量名)
+	一个下划线开头的实例变量名，比如_name，这样的实例变量外部是可以访问的，但是，按照约定俗成的规定，当你看到这样的变量时，意思就是，“虽然我可以被访问，但是，请把我视为私有变量，不要随意访问”。
		
		class Student(object):
			#	类属性
			identity='student'

			#	初始化，第一个参数self为创建的实例本身，由Python解释器传入
			def __init__(self,name,score):
				self.name=name
				self.score=score
				self.__pri='private'

+	和静态语言不同，Python允许对实例变量绑定任何数据，也就是说，对于两个实例变量，虽然它们都是同一个类的不同实例，但拥有的变量可能有一部分不同（类本身定义的+实例动态添加的）

		>>> bart = Student('Bart Simpson', 59)
		>>> lisa = Student('Lisa Simpson', 87)
		>>> bart.age = 8
		>>> bart.age
		8
		>>> lisa.age
		Traceback (most recent call last):
		  File "<stdin>", line 1, in <module>
		AttributeError: 'Student' object has no attribute 'age'

##	继承

	# Super Class
	class Animal(object):
    	def run(self):
        	print('Animal is running...')
	
	# Subclass
	class Dog(Animal):
    	def run(self):
        	print('Dog is running...')
    	def eat(self):
        	print('Eating meat...')

	
	# 动态语言的“鸭子类型” 
	# 一个对象只要“看起来像鸭子，走起路来像鸭子”，那它就可以被看做是鸭子
	class Timer(object):
    	def run(self):
        	print('Timer is running...')

	
	def run_twice(animal):
    	animal.run()
    	animal.run()
	>>>run_twice(Dog())
	Dog is running...
	Dog is running...
	>>>run_twice(Timer())
	Timer is running...
	Timer is running...
+对于静态语言来说，如果需要传入Animal类型，则传入的对象必须是Animal类型或者它的子类，否则，将无法调用run()方法。
<br>对于Python这样的动态语言来说，则不一定需要传入Animal类型。我们只需要保证传入的对象有一个run()方法就可以了。


##	获取对象信息 

###	import types type() 

	#	判断类型
	>>> type(123)
	<class 'int'>
	>>> type(abs)
	<class 'builtin_function_or_method'>
	
	#	判断函数
	>>> import types
	>>> def fn():
	...     pass
	...
	>>> type(fn)==types.FunctionType
	True
	>>> type(abs)==types.BuiltinFunctionType
	True
	>>> type(lambda x: x)==types.LambdaType
	True
	>>> type((x for x in range(10)))==types.GeneratorType
	True

###	isinstance()

	#	判断继承链
	>>> isinstance(d, Dog) and isinstance(d, Animal)
	True
	
	#	多选项
	>>> isinstance([1, 2, 3], (list, tuple))
	True

###	获得一个对象的所有属性和方法，dir()函数，返回一个包含字符串的list
+	hasattr(obj,'attrname')
+	getattr(obj,'attrname')
+	setattr(obj,'attrname')

##	动态添加属性

	class Student(object):
	    pass
	
	#	动态绑定属性到实例
	>>> s = Student()
	>>> s.name = 'Michael'
	>>> print(s.name)
	Michael
	
	#	绑定方法
	>>> def set_score(self, score):
	...     self.score = score
	...
	#	给实例绑定一个方法 只能被该实例调用
	>>> s.set_age = MethodType(set_age, s) 
	#	给类绑定方法，所有实例可调用
	>>> Student.set_score = set_score

##	限制实例的属性 \_\_slots__
	
	class Student(object):
    	__slots__ = ('name', 'age') # 用tuple定义允许绑定的属性名称


	>>> s.score = 99 # 绑定属性'score'
	Traceback (most recent call last):
	  File "<stdin>", line 1, in <module>
	AttributeError: 'Student' object has no attribute 'score'

+	\_\_slots__定义的属性仅对当前类实例起作用，对继承的子类是不起作用的
<br>除非在子类中也定义\_\_slots__，这样，子类实例允许定义的属性就是自身的\_\_slots__加上父类的\_\_slots__。
	

	>>> class GraduateStudent(Student):
	...     pass
	...
	>>> g = GraduateStudent()
	>>> g.score = 9999


##	@property装饰器 将get/set转换成属性调用

	class Student(object):
	
	    @property
	    def score(self):
	        return self._score
	
	    @score.setter
	    def score(self, value):
	        if not isinstance(value, int):
	            raise ValueError('score must be an integer!')
	        if value < 0 or value > 100:
	            raise ValueError('score must between 0 ~ 100!')
	        self._score = value


	>>> s = Student()
	>>> s.score = 60 # OK，实际转化为s.set_score(60)
	>>> s.score # OK，实际转化为s.get_score()
	60
	>>> s.score = 9999
	Traceback (most recent call last):
	  ...
	ValueError: score must between 0 ~ 100!

+	可以定义只读属性，只定义getter方法，不定义setter方法就是一个只读属性




##	多重继承 MixIn

	class Runnable(object):
	    def run(self):
	        print('Running...')
	
	class Dog(Mammal, Runnable):
	    pass


##	定制类

	__slots__ = ('name', 'age') # 用tuple定义允许绑定的属性名称

	__len__()	#	让class作用于len()函数
	
	__str__()	#	用于print()：print(s)

	__repr__()	#	用于直接调用实例输出：s

	__iter__()	#	用于for ... in循环,返回一个迭代对象
	
	__next__()	#	抛出StopIteration错误退出循环
	
	__getitem__()#	表现得像list那样按照下标取出元素
	
	__getattr__()#	调用不存在的属性时,调用__getattr__(self, 'attr')来尝试获得属性
	
	__call__()	#	直接在实例本身上调用,s()


##	枚举类 from enum import Enum

	Month = Enum('Month', ('Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'))

	for name, member in Month.__members__.items():
    	print(name, '=>', member, ',', member.value)

	#	value属性则是自动赋给成员的int常量，默认从1开始计数



	#	更精确地控制枚举类型，可以从Enum派生出自定义类
	#	@unique装饰器检查保证没有重复值

	from enum import Enum, unique

	@unique
	class Weekday(Enum):
	    Sun = 0 # Sun的value被设定为0
	    Mon = 1
	    Tue = 2
	    Wed = 3
	    Thu = 4
	    Fri = 5
	    Sat = 6


##	动态创建类 type()函数既可以返回一个对象的类型，又可以创建出新的类型

	>>> def fn(self, name='world'): # 先定义函数
	...     print('Hello, %s.' % name)
	...
	#	传入参数:类名，继承，属性
	>>> Hello = type('Hello', (object,), dict(hello=fn)) # 创建Hello class
	>>> h = Hello()
	>>> h.hello()
	Hello, world.
	>>> print(type(Hello))
	<class 'type'>
	>>> print(type(h))
	<class '__main__.Hello'>


##	元类 metaclass 类的模板，必须从`type`类型派生


	# metaclass是类的模板，所以必须从`type`类型派生：
	class ListMetaclass(type):
	    def __new__(cls, name, bases, attrs):
	        attrs['add'] = lambda self, value: self.append(value)
	        return type.__new__(cls, name, bases, attrs)
	
	# 使用metaclass
	class MyList(list, metaclass=ListMetaclass):
	    pass


+	关键字参数metaclass，指示Python解释器在创建MyList时，要通ListMetaclass.\_\_new__()来创建

+	__new__()方法接收到的参数依次是：

	+	当前准备创建的类的对象；

	+	类的名字；

	+	类继承的父类集合；

	+	类的方法集合。

