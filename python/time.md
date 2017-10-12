[time](http://www.runoob.com/python3/python3-date-time.html)
#   import time;
+   时间戳 time.time()
+   时间元组 

        time.localtime(time.time())
        --> time.struct_time(tm_year=2016, tm_mon=4, tm_mday=7, tm_hour=10, tm_min=28, tm_sec=49, tm_wday=3, tm_yday=98, tm_isdst=0)
        0	tm_year	    2008
        1	tm_mon	    1 到 12
        2	tm_mday	    1 到 31
        3	tm_hour	    0 到 23
        4	tm_min	    0 到 59
        5	tm_sec	    0 到 61 (60或61 是闰秒)
        6	tm_wday	    0到6 (0是周一)
        7	tm_yday	    一年中的第几天，1 到 366
        8	tm_isdst	是否为夏令时，值有：1(夏令时)、0(不是夏令时)、-1(未知)，默认 -1
+   格式化 time.strftime

        time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
        
        %Y 四位数的年份表示（000-9999）   %y 两位数的年份表示（00-99）  
        %m 月份（01-12）
        %d 月内中的一天（0-31）
        %H 24小时制小时数（0-23）       %I 12小时制小时数（01-12）
        %M 分钟数（00-59）
        %S 秒（00-59）
        %a 本地简化星期名称      %A 本地完整星期名称
        %b 本地简化的月份名称    %B 本地完整的月份名称
        %c 本地相应的日期表示和时间表示
        %j 年内的一天（001-366）
        %p 本地A.M.或P.M.的等价符
        %U 一年中的星期数（00-53）星期天为星期的开始
        %w 星期（0-6），星期天为星期的开始
        %W 一年中的星期数（00-53）星期一为星期的开始
        %x 本地相应的日期表示
        %X 本地相应的时间表示
        %Z 当前时区的名称
        %% %号本身

+   api
    
        time.time( )    返回当前时间的时间戳（1970纪元后经过的浮点秒数）。
        time.timezone   当地时区（未启动夏令时）距离格林威治的偏移秒数（>0，美洲;<=0大部分欧洲，亚洲，非洲）。
        
        time.gmtime([secs])     接收时间辍（1970纪元后经过的浮点秒数）并返回格林威治天文时间下的时间元组
        time.localtime([secs]   接收时间辍（1970纪元后经过的浮点秒数）并返回当地时间下的时间元组
        time.mktime(tupletime)  接受时间元组并返回时间辍（1970纪元后经过的浮点秒数）
        
        time.strftime(fmt [,tupletime])  接收以时间元组，并返回以可读字符串表示的当地时间，格式由fmt决定。
        time.strptime(str,fmt='%a %b %d %H:%M:%S %Y')   根据fmt的格式把一个时间字符串解析为时间元组。
        
        time.sleep(secs)        推迟调用线程的运行，secs指秒数。
        
        
        
#   import calendar
