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
		
#   字符串内建函数

ljust(width[, fillchar])
返回一个原字符串左对齐,并使用 fillchar 填充至长度 width 的新字符串，fillchar 默认为空格。
rjust(width,[, fillchar])
返回一个原字符串右对齐,并使用fillchar(默认空格）填充至长度 width 的新字符串
center(width, fillchar)
返回一个指定的宽度 width 居中的字符串，fillchar 为填充的字符，默认为空格。
expandtabs(tabsize=8)
把字符串 string 中的 tab 符号转为空格，tab 符号默认的空格数是 8 。	
zfill (width)
返回长度为 width 的字符串，原字符串右对齐，前面填充0


count(str, beg= 0,end=len(string))
返回 str 在 string 里面出现的次数，如果 beg 或者 end 指定则返回指定范围内 str 出现的次数
endswith(suffix, beg=0, end=len(string))
检查字符串是否以 obj 结束，如果beg 或者 end 指定则检查指定的范围内是否以 obj 结束，如果是，返回 True,否则返回 False.
find(str, beg=0 end=len(string))
检测 str 是否包含在字符串中，如果指定范围 beg 和 end ，则检查是否包含在指定范围内，如果包含返回开始的索引值，否则返回-1
index(str, beg=0, end=len(string))
跟find()方法一样，只不过如果str不在字符串中会报一个异常.
rfind(str, beg=0,end=len(string))
类似于 find()函数，不过是从右边开始查找.
rindex( str, beg=0, end=len(string))
类似于 index()，不过是从右边开始.
startswith(str, beg=0,end=len(string))
检查字符串是否是以 obj 开头，是则返回 True，否则返回 False。如果beg 和 end 指定值，则在指定范围内检查。

encode(encoding='UTF-8',errors='strict')
以 encoding 指定的编码格式编码字符串，如果出错默认报一个ValueError 的异常，除非 errors 指定的是'ignore'或者'replace'
bytes.decode(encoding="utf-8", errors="strict")
Python3 中没有 decode 方法，但我们可以使用 bytes 对象的 decode() 方法来解码给定的 bytes 对象，这个 bytes 对象可以由 str.encode() 来编码返回。


join(seq)
以指定字符串作为分隔符，将 seq 中所有的元素(的字符串表示)合并为一个新的字符串


isalnum()
如果字符串至少有一个字符并且所有字符都是字母或数字则返 回 True,否则返回 False
isalpha()
如果字符串至少有一个字符并且所有字符都是字母则返回 True, 否则返回 False
isdigit()
如果字符串只包含数字则返回 True 否则返回 False..
islower()
如果字符串中包含至少一个区分大小写的字符，并且所有这些(区分大小写的)字符都是小写，则返回 True，否则返回 False
isnumeric()
如果字符串中只包含数字字符，则返回 True，否则返回 False
isspace()
如果字符串中只包含空白，则返回 True，否则返回 False.
istitle()
如果字符串是标题化的(见 title())则返回 True，否则返回 False
isupper()
如果字符串中包含至少一个区分大小写的字符，并且所有这些(区分大小写的)字符都是大写，则返回 True，否则返回 False
isdecimal()
检查字符串是否只包含十进制字符，如果是返回 true，否则返回 false。


max(str)
返回字符串 str 中最大的字母。	
min(str)
返回字符串 str 中最小的字母。



capitalize()
将字符串的第一个字符转换为大写
lower()
转换字符串中所有大写字符为小写.
upper()
转换字符串中的小写字母为大写
swapcase()
将字符串中大写转换为小写，小写转换为大写


lstrip()
截掉字符串左边的空格或指定字符。
rstrip()
删除字符串字符串末尾的空格.
strip([chars])
在字符串上执行 lstrip()和 rstrip()



maketrans()
创建字符映射的转换表，对于接受两个参数的最简单的调用方式，第一个参数是字符串，表示需要转换的字符，第二个参数也是字符串表示转换的目标。
title()
返回"标题化"的字符串,就是说所有单词都是以大写开始，其余字母均为小写(见 istitle())
translate(table, deletechars="")
根据 str 给出的表(包含 256 个字符)转换 string 的字符, 要过滤掉的字符放到 deletechars 参数中


len(string)
返回字符串长度
replace(old, new [, max])
把 将字符串中的 str1 替换成 str2,如果 max 指定，则替换不超过 max 次。
split(str="", num=string.count(str))
num=string.count(str)) 以 str 为分隔符截取字符串，如果 num 有指定值，则仅截取 num 个子字符串
splitlines([keepends])
按照行('\r', '\r\n', \n')分隔，返回一个包含各行作为元素的列表，如果参数 keepends 为 False，不包含换行符，如果为 True，则保留换行符。

str = ’0123456789′
print str[0:3] #截取第一位到第三位的字符
print str[:] #截取字符串的全部字符
print str[6:] #截取第七个字符到结尾
print str[:-3] #截取从头开始到倒数第三个字符之前
print str[2] #截取第三个字符
print str[-1] #截取倒数第一个字符
print str[::-1] #创造一个与原字符串顺序相反的字符串
print str[-3:-1] #截取倒数第三位与倒数第一位之前的字符
print str[-3:] #截取倒数第三位到结尾