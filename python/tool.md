#	Tool

##	Python解释器

1.	CPython
<br>官方版本的解释器，在命令行下运行python就是启动CPython解释器。

2.	IPython
<br>基于CPython之上的一个交互式解释器，CPython用>>>作为提示符，而IPython用In [序号]:作为提示符。

3.	PyPy
<br>PyPy采用JIT技术，对Python代码进行动态编译（注意不是解释），所以可以显著提高Python代码的执行速度。*绝大部分Python代码都可以在PyPy下运行，但是PyPy和CPython有一些是不同的*

4.	Jython
<br>Jython是运行在Java平台上的Python解释器，可以直接把Python代码编译成Java字节码执行。

5.	IronPython
<br>运行在微软.Net平台上的Python解释器，可以直接把Python代码编译成.Net的字节码。