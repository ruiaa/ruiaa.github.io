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

+	字符串格式化运算 %

		>>> 'Hello, %s' % 'world'
		'Hello, world'
		>>> 'Hi, %s, you have $%d.' % ('Michael', 1000000)
		'Hi, Michael, you have $1000000.'

ord()函数获取字符的整数表示，chr()函数把编码转换为对应的字符

以Unicode表示的str通过encode()方法可以编码为指定字符集的bytes

		>>> 'ABC'.encode('ascii')
		b'ABC'
		>>> '中文'.encode('utf-8')
		b'\xe4\xb8\xad\xe6\x96\x87'

要把bytes变为str，就需要用decode()方法

		>>> b'ABC'.decode('ascii')
		'ABC'
		>>> b'\xe4\xb8\xad\xe6\x96\x87'.decode('utf-8')
		'中文'

len()函数计算str的字符数，bytes的字节数

		>>> len(b'ABC')
		3
		>>> len(b'\xe4\xb8\xad\xe6\x96\x87')
		6
		>>> len('中文'.encode('utf-8'))
		6