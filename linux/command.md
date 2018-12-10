+   搜索find
    +   find dir -name "pair_of_name" ：根据名称搜索 find / -name "pip*"
*
+   软链接 ln
    +   ln -s from to
    +   添加启动到环境 ln -s /usr/local/python/bin/python3.4 /usr/local/bin/python

+   下载 wget
    +   wget url
    +	http://jythoner.iteye.com/blog/354221

+   解压 tar
    +   tar -zxvf ****.tar.gz 
    +   tar -jxvf ****.tar.bz(或bz2) 
* 
+   netstat -nap #会列出所有正在使用的端口及关联的进程/应用
+   netstat -lnp|grep 88


#	shutdown 关机
	shutdown [-t seconds] [-rkhncfF] time [message]
    -t seconds : 设定在几秒钟之后进行关机程序
    -k         : 并不会真的关机，只是将警告讯息传送给所有只用者
    -r         : 关机后重新开机
    -h         : 关机后停机
    -n         : 不采用正常程序来关机，用强迫的方式杀掉所有执行中的程序后自行关机
    -c         : 取消目前已经进行中的关机动作
    -f         : 关机时，不做 fcsk 动作(检查 Linux 档系统)
    -F         : 关机时，强迫进行 fsck 动作
    time       : 设定关机的时间
    message    : 传送给所有使用者的警告讯息
.













