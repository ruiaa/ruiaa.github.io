#	异常处理

##	try except finally
+	可以在except语句块后面加一个else，当没有错误发生时，会自动执行else语句
+	所有的错误类型都继承自BaseException


	try:
	    print('try...')
	    r = 10 / 0
	    print('result:', r)
	except ZeroDivisionError as e:
	    print('except:', e)
	else:
    	print('no error!')
	finally:
	    print('finally...')
	print('END')

##	记录错误	logging模块

	import logging
	
	logging.exception(e)

##	抛出错误 raise

	class FooError(ValueError):
	    pass
	
	def foo(s):
	    n = int(s)
	    if n==0:
	        raise FooError('invalid value: %s' % s)
	    return 10 / n
	
	foo('0')

+	raise语句如果不带参数，就会把当前错误原样抛出



##	调试

###	断言 assert

	def foo(s):
	    n = int(s)
	    assert n != 0, 'n is zero!'
	    return 10 / n

+	如果断言失败，assert语句本身就会抛出AssertionError
+	启动Python解释器时可以用-O参数来关闭assert 
	+	$ python3 -O err.py



###	logging

	import logging
	logging.basicConfig(level=logging.INFO)
	#	debug，info，warning，error



###	pdb 单步方式运行

	$ python3 -m pdb err.py

+	输入命令l来查看代码
+	输入命令n可以单步执行代码
+	输入命令p 变量名来查看变量
+	输入命令q结束调试，退出程序

###	pdb.set_trace() 断点

	# err.py
	import pdb
	
	s = '0'
	n = int(s)
	pdb.set_trace() # 运行到这里会自动暂停
	print(10 / n)

+	运行代码，程序会自动在pdb.set_trace()暂停并进入pdb调试环境
+	可以用命令p查看变量，或者用命令c继续运行


##	IDE PyCharm

##	单元测试 import unittest

	import unittest
	
	from mydict import Dict
	
	class TestDict(unittest.TestCase):
	
	    def test_init(self):
	        d = Dict(a=1, b='test')
	        self.assertEqual(d.a, 1)
	        self.assertEqual(d.b, 'test')
	        self.assertTrue(isinstance(d, dict))
	
	    def test_key(self):
	        d = Dict()
	        d['key'] = 'value'
	        self.assertEqual(d.key, 'value')
	
	    def test_attrerror(self):
	        d = Dict()
	        with self.assertRaises(AttributeError): # 断言下一步抛出异常
	            value = d.empty

+	测试类，从unittest.TestCase继承

+	unittest.TestCase提供的内置条件判断
	+	assertEqual()
	+	assertTrue()
	+	assertRaises()


###	运行单元测试
+	在mydict_test.py的最后加上两行代码：


	if __name__ == '__main__':
	    unittest.main()

+	在命令行通过参数-m unittest直接运行单元测试


	$ python3 -m unittest mydict_test	


###	setUp() tearDown()分别在每调用一个测试方法的前后分别被执行


##	文档测试(doctest)模块 提取注释中的代码并执行测试


	# mydict2.py
	class Dict(dict):
    	'''
	    Simple dict but also support access as x.y style.
	
	    >>> d1 = Dict()
	    >>> d1['x'] = 100
	    >>> d1.x
	    100
	    >>> d1.y = 200
	    >>> d1['y']
	    200
	    >>> d2 = Dict(a=1, b=2, c='3')
	    >>> d2.c
	    '3'
	    >>> d2['empty']
	    Traceback (most recent call last):
	        ...
	    KeyError: 'empty'
	    >>> d2.empty
	    Traceback (most recent call last):
	        ...
	    AttributeError: 'Dict' object has no attribute 'empty'
	    '''
	    def __init__(self, **kw):
	        super(Dict, self).__init__(**kw)
	
	    def __getattr__(self, key):
	        try:
	            return self[key]
	        except KeyError:
	            raise AttributeError(r"'Dict' object has no attribute '%s'" % key)
	
	    def __setattr__(self, key, value):
	        self[key] = value
	
	if __name__=='__main__':
	    import doctest
	    doctest.testmod()

















