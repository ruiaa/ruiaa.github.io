#	模块Module
+	.py文件
+	目录分层
	+	mycompany
		+	_init_.py (可为空文件，模块名就是mycompany)
		+	module1.py （mycompany.module1）
		+	module2.py


##	使用	import
+	在命令行运行模块时，Python会把该模块的特殊变量_name_赋值为'_main_'，而导入时不会
+	

##	作用域

+	正常的函数和变量名是公开的（public），可以被直接引用，比如：abc，x123，PI等；

+	类似\_\_xxx__这样的变量是特殊变量，可以被直接引用，但是有特殊用途，比如上面的\_\_author__，\_\_name__就是特殊变量，hello模块定义的文档注释也可以用特殊变量__doc__访问，我们自己的变量一般不要用这种变量名；

+	类似_xxx和__xxx这样的函数或变量就是非公开的（private），不应该被直接引用，比如_abc，__abc等；


##	安装第三方模块
+	包管理工具pip
	+	pip install Libname















