import cgitb


cgitb.enable(display=1, logdir=None, context=5, format="html")
    display 1，发送至浏览器；0， 不发送 
    logdir 如果有的话，写到该目录下
    context 显示错误代码周围的代码行数
    format 是否显示为HTML，除了'html'之外的所有值，都会显示为纯文本,text
    ...cgitb.enable(logdir=os.getcwd(), format='text')

cgitb.handle(info=None)
info 应当是含有异常类型、异常值和traceback对象的三元组，——如同sys.exc_info()返回的那样。
如果不提供info，则从sys.exc_info中获取。