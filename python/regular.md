#	正则表达式

## 规则
	
###	定义字符类型
+	直接给出字符，就是精确匹配
+	\s可以匹配一个空格（也包括Tab等空白符）
+	\d可以匹配一个数字
+	\w可以匹配一个字母或数字
+	.可以匹配任意字符
+	[]表示范围 [0-9a-zA-Z\\_]可以匹配一个数字、字母或者下划线
+	(A|B)可以匹配A或B
+	^表示行的开头，^\d表示必须以数字开头
+	$表示行的结束，\d$表示必须以数字结束

###	定义字符数量（附加给前面的字符类型）
+	*表示任意个字符（包括0个）
+	?表示0个或1个字符	
+	+表示至少一个字符
+	{n}表示n个字符
+	{n,m}表示n-m个字符

###	转义\



#	re模块

## 转义

	s = 'ABC\\-001' # Python的字符串
	# 对应的正则表达式字符串变成：
	# 'ABC\-001'

+	使用Python的r前缀，就不用考虑转义


	s = r'ABC\-001' # Python的字符串
	# 对应的正则表达式字符串不变：
	# 'ABC\-001'


##	re
+	match('restr','target')如果匹配成功，返回一个Match对象，否则返回None
+	split()切分字符串
+	group()分组 正则表达式中用()表示的就是要提取的分组（Group）



	test = '用户输入的字符串'
	if re.match(r'正则表达式', test):
	    print('ok')
	else:
	    print('failed')
	
	
	>>> re.split(r'[\s\,\;]+', 'a,b;; c  d')
	['a', 'b', 'c', 'd']
	

	>>> m = re.match(r'^(\d{3})-(\d{3,8})$', '010-12345')
	>>> m
	<_sre.SRE_Match object; span=(0, 9), match='010-12345'>
	>>> m.group(0)
	'010-12345'
	>>> m.group(1)
	'010'
	>>> m.group(2)
	'12345'

##	贪婪匹配
+	默认是贪婪匹配，也就是匹配尽可能多的字符
+	加个?就可以让\d+采用非贪婪匹配(尽可能少匹配)


	#	贪婪
	>>> re.match(r'^(\d+)(0*)$', '102300').groups()
	('102300', '')

	#	非贪婪
	>>> re.match(r'^(\d+?)(0*)$', '102300').groups()
	('1023', '00')



##	预编译正则表达式

	>>> import re
	# 编译:
	>>> re_telephone = re.compile(r'^(\d{3})-(\d{3,8})$')
	# 使用：
	>>> re_telephone.match('010-12345').groups()
	('010', '12345')
	>>> re_telephone.match('010-8086').groups()
	('010', '8086')
















