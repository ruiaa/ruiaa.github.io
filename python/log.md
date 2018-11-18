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