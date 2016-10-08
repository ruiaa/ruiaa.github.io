##	
+	注释 单行# 多行三对单引号（'''）或者三对双引号（"""）
+	每一行都是一个语句,无须；
+	当语句以冒号:结尾时，缩进的语句视为代码块
+	缩进4个空格
+	大小写敏感

##	基本数据类型  不可变
+	整数：任意大小，包括负整数，十六进制前缀0x
+	浮点数：-9.01，1.23x10^9-->1.23e9
+	复数 1.1 + 2.2j
+	布尔值 True、False(大小写) 用and、or和not运算
+	str字符串:使用一对单引号'或双引号",一对单(双)引号内可以有双(单)引号，
	<br>索引[index]从左往右，从0开始依次增加；从右往左，从-1开始依次减少
	<br>截取[from:toNext]
	<br>转义字符\，串前加r表示\不转义
	<br>用'''...'''的格式表示多行内容
	<br>格式化方式和C语言一致,用%%来表示一个%

		>>> print('''line1
		... line2
		... line3''')
		line1
		line2
		line3
		
		print('''line1
		line2
		line3''')
		
		'%d	整数,%f浮点数,%s字符串,%x十六进制整数' % (1,1.0,'sss',0x1)

+	bytes 带b前缀的单引号或双引号
+	空值 None(大小写)

+	转换 int() float() bool() str() 


##	集合	可变
+	list列表 [value,] 数据类型可以不同，有序，支持多维
	<br>访问 listName[index],倒数访问listName[-index]，个数len(listName)
	<br>添加 listName.append(val),listName.insert(index,val)
	<br>删除 list末尾的元素listName.pop(),listName.pop(index)
	<br>替换 listName[index]=newVal
	<br>分割 [from:toNext]	[from:toNext:every]	
+	tuple元组 (value,) 一旦初始化就不能修改
	<br>定义一个空的tuple，可以写成()
	<br>对于一个只有1个元素的tuple
	<br>()既可表示tuple，又可表示运算小括号，按小括号进行计算
	<br>(1)-->1
	<br>(1,)-->(1,)
+	dict字典 {key:value,} 无顺序  必须保证key不可变(非list)
	<br>判断存在 key in dictName	
	<br>取值dictName[key] 如果key不存在，dict就会报错
	<br>dictName.get(key),dictName.get(key,default)如果key不存在,返回None,或者指定的value
	<br>添加dictName[key]=value，一对一，key重复会覆盖原来的
	<br>删除dictName.pop(key)
+	set 无序，不重复 
	<br>创建 setName=set([1,2,3,4])
	<br>添加 setName.add(value)
	<br>删除 setName.remove(value)

##	编码
+	指定编码 # -*- coding: utf-8 -*-

##	表达式
+	算术 +，-，*，/(浮点，浮点数)，//(取整，整数)，%(取余，整数)，**(乘方)

##	语句和控制流
+	if else elif

		if condition1:
			statement1
		else:
			statement2

		
		if condition1:
			statement1
		elif condition2:
			statement2
		elif condition3:
			statement3
		else:
			statement4	
+	for...in  依次迭代list或tuple中的每个元素

		names=[1,2,3]
		for name in names:
			statement1

+	while 条件判断循环

		while condition1:
			statement1


+	break 提前结束循环 
+	continue 提前结束本轮循环

+	pass 跳过该行


##	函数
+	函数名是指向一个函数对象的引用，可以把函数名赋给一个变量
+	定义 def , 
	<br>参数 传值，复制
	<br>参数顺序：必选参数、默认参数、可变参数、命名关键字参数、关键字参数

		# 默认参数必须指向不变对象,None，(内部变量全局通用)
		def my_method(x,y=0):
    		statement1
			statement2
			return x
		
		# 可变参数  传入list或tuple
		def calc(numbers):
    		sum = 0
    		for n in numbers:
        		sum = sum + n * n
    		return sum
		>>> calc([1, 2, 3])

		# 可变参数 *  params-->list,tuple
		def calc(*numbers):
    		sum = 0
    		for n in numbers:
        		sum = sum + n * n
    		return sum
		>>> calc(1, 2,3)
		>>> nums = [1, 2, 3]
		>>> calc(*nums)
		

		# 关键字参数 ** params-->dict
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







