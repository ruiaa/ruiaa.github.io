import logging

log = logging.getLogger('canteendata')
log.setLevel(logging.INFO)

log_formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')

log_file = logging.FileHandler('log.log')
log_file.setLevel(logging.INFO)
log_file.setFormatter(log_formatter)
log.addHandler(log_file)

log_print = logging.StreamHandler()
log_print.setFormatter(log_formatter)
log_print.setLevel(logging.INFO)
log.addHandler(log_print)

log.info('init logger')

日志格式
%(asctime)s 即日志记录时间，精确到毫秒
%(levelname)s 即此条日志级别
%(filename)s 即触发日志记录的python文件名
%(funcName)s 即触发日志记录的函数名
%(lineno)s 即触发日志记录代码的行号
%(message)s 这项即调用如app.logger.info('info log')中的参数，即message










#logging:配置日志级别，输出位置
def logging_to_file():
    '''
    #参数：filename：用指定的文件名创建filedhandler，这样日志会被存储到指定的文件中，如果不指定，则默认输出到控制台
    #参数：filemode默认filemode的默认值"a"，表示append，当然你也可以指定为"w"
    #参数：level，一共5个级别，critical(50)>error(40)>warning(30)>info(20)>debug(10),默认级别为Warning,这里可以用对应的数值表示level
    '''

    #将日志信息只输入到指定的文件
    logging.basicConfig(filename="example.log", level=logging.DEBUG)
    # logging.basicConfig(filename="example.log", level=logging.DEBUG, filemode="w")
    # logging.basicConfig(filename="example.log", level=10, filemode="w")
    logging.debug("debu1g")
    logging.info("info")
    logging.warning("info")
    logging.error("error")
    logging.critical("critical")

    # logging variable data:打印变量
    logging.error("logging variable date： %s,%s,%s","var01","var02","var03")

#logging:配置日志格式
def displaying_format_massages():
    '''
    format参数中可能用到的格式化串
    %(name)s Logger的名字
    %(levelno)s 数字形式的日志级别
    %(levelname)s 文本形式的日志级别
    %(pathname)s 调用日志输出函数的模块的完整路径名，可能没有
    %(filename)s 调用日志输出函数的模块的文件名
    %(module)s 调用日志输出函数的模块名
    %(funcName)s 调用日志输出函数的函数名
    %(lineno)d 调用日志输出函数的语句所在的代码行
    %(created)f 当前时间，用UNIX标准的表示时间的浮 点数表示
    %(relativeCreated)d 输出日志信息时的，自Logger创建以 来的毫秒数
    %(asctime)s 字符串形式的当前时间。默认格式是 “2003-07-08 16:49:45,896”。逗号后面的是毫秒
    %(thread)d 线程ID。可能没有
    %(threadName)s 线程名。可能没有
    %(process)d 进程ID。可能没有
    %(message)s用户输出的消息
    '''

    # format：指定handler使用的日志显示格式。
    logging.basicConfig(format="%(asctime)s %(filename)s[line:%(lineno)d]%(levelname)s - %(message)s")
    logging.warning("warning")

#logging:配置日志时间格式
def displaying_date_message():
    # datefmt：指定日期时间格式,也就是“%(asctime)s”的格式
    logging.basicConfig(format="%(asctime)s %(filename)s[line:%(lineno)d]%(levelname)s - %(message)s",datefmt="%m/%d/%Y %I:%M:%S %p")
    logging.warning("warning")
