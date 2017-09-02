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
+	str字符串:使用一对单引号'或双引号",一对单(双)引号内可以有双(单)引号
+	bytes 带b前缀的单引号或双引号
+	空值 None(大小写)

+	转换 int() float() bool() str() 


##	集合	可变
+	list列表 [value,] 数据类型可以不同，有序，支持多维
+	tuple元组 (value,) 一旦初始化就不能修改
+	dict字典 {key:value,} 无顺序  必须保证key不可变(非list)
+	set 无序，不重复 


##	变量

##	常量
+	通常用全部大写的变量名表示常量
+	但事实上仍然是一个变量，Python根本没有任何机制保证不会被改变


##	编码
+	指定编码 # -*- coding: utf-8 -*-

##	标准文件模板

		#!/usr/bin/env python3
		# -*- coding: utf-8 -*-
		
		'annotation for this module'
		
		_author_='rui'

+	第1行用于在Unix/Linux/Mac下声明为py文件，可直接运行
+	第2行注释表示.py文件本身使用标准UTF-8编码
+	任何模块代码的第一个字符串都被视为模块的文档注释
+	__author__变量把作者写进去

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

+	pass 占位符 跳过该行


+	isinstance（变量,(类型1,类型2)）	类型判断


#	字符串
+	格式化 ("I'm %s. I'm %d year old" % ('Vamei', 99))
	+	使用字符串作为模板，有格式符为真实值预留位置，并说明格式
	+	%号分隔，代表了格式化操作
	+	tuple将多个值传递给模板，每个值对应一个格式符
	+	
	+	("I'm %(name)s. I'm %(age)d year old" % {'name':'Vamei', 'age':99})
	+	可以对格式符进行命名，每个命名对应词典的一个key


###	格式符
+   %s    字符串 (采用str()的显示)
+   %r    字符串 (采用repr()的显示)
+   %c    单个字符
+   %b    二进制整数
+   %d    十进制整数
+   %i    十进制整数
+   %o    八进制整数
+   %x    十六进制整数
+   %e    指数 (基底写为e)
+   %E    指数 (基底写为E)
+   %f    浮点数
+   %F    浮点数，与上相同
+   %g    指数(e)或浮点数 (根据显示长度)
+   %G    指数(E)或浮点数 (根据显示长度)
+   %%    字符"%"

###	精准格式
+	%[(name)][flags][width].[precision]typecode
+   (name)为命名
+   flags可以有+,-,' '或0。
	+	+表示右对齐。
	+	-表示左对齐。
	+	' '为一个空格，表示在正数的左侧填充一个空格，从而与负数对齐。
	+	0表示使用0填充。
+   width表示显示宽度
+   precision表示小数点后精度