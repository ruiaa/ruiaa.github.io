#	apache
安装：

# yum install httpd
设置httpd服务开机启动

[root@yl-web httpd]# /sbin/chkconfig httpd on
Note: Forwarding request to 'systemctl enable httpd.service'.
ln -s '/usr/lib/systemd/system/httpd.service' '/etc/systemd/system/multi-user.target.wants/httpd.service'
启动httpd服务

[root@yl-web httpd]# /sbin/service httpd start
Redirecting to /bin/systemctl start  httpd.service


#   win
+   安装  bin\httpd.exe -k install -n apache
+   启动  ApacheMonitor.exe
+   卸载  sc delete apache
+   命令
        
        httpd -k install 
        将Apache注册为windows服务，可以指定的服务名为"apache"。
         
        httpd -k install -n "服务名"　
        将Apache注册为windows服务，自己指定一个服务名字。
         
        httpd -k install -n "服务名" -f "conf\my.conf"
        将Apache注册为windows服务，自己指定一个服务名字，并且使用特定配置文件。
         
        httpd.exe -k uninstall -n "MyServiceName"
        移除Apache服务，自己制定一个服务名字。
         
        httpd.exe -k start -n "MyServiceName"
        启动Apache服务。
         
        httpd.exe -k stop -n "MyServiceName" 或httpd.exe -k shutdown -n "MyServiceName"
        停止Apache服务。
         
        httpd.exe -k restart -n "MyServiceName"
        重启Apache服务。
         
        httpd.exe -n "MyServiceName" -t
        
        
        参数选项：
        
        -d serverroot
        将ServerRoot指令设置初始值为serverroot。它可以被配置文件中的ServerRoot指令所覆盖。其默认值是/usr/local/apache2 。
        
        -f config
        在启动中使用config作为配置文件。如果config不以"/"开头，则它是相对于ServerRoot的路径。其默认值是conf/httpd.conf 。
        
        -k start|restart|graceful|stop|graceful-stop
        发送信号使httpd启动、重新启动或停止 。
        
        -C directive
        在读取配置文件之前，先处理directive的配置指令。
        
        -c directive
        在读取配置文件之后，再处理directive的配置指令。
        
        -D parameter
        设置参数parameter ，它配合配置文件中的<IfDefine>段，用于在服务器启动和重新启动时，有条件地跳过或处理某些命令。
        
        -e level
        在服务器启动时，设置LogLevel为level 。它用于在启动时，临时增加出错信息的详细程度，以帮助排错。
        
        -E file
        将服务器启动过程中的出错信息发送到文件file 。
        
        -R directory
        当在服务器编译中使用了SHARED_CORE规则时，它指定共享目标文件的目录为directory 。
        
        -h
        输出一个可用的命令行选项的简要说明。
        
        -l
        输出一个静态编译在服务器中的模块的列表。它不会列出使用LoadModule指令动态加载的模块。
        
        -L
        输出一个指令的列表，并包含了各指令的有效参数和使用区域。
        
        -M
        输出一个已经启用的模块列表，包括静态编译在服务器中的模块和作为DSO动态加载的模块。
        
        -S
        显示从配置文件中读取并解析的设置结果(目前仅显示虚拟主机的设置)
        
        -T
        在启动/重启的时候跳过根文件检查 (该参数在Apache 2.2.17及其以后版本有效)
        
        -t
        仅对配置文件执行语法检查。程序在语法解析检查结束后立即退出，或者返回"0"(OK)，或者返回非0的值(Error)。如果还指定了"-D DUMP_VHOSTS"，则会显示虚拟主机配置的详细信息。
        
        -v
        显示httpd的版本，然后退出。
        
        -V
        显示httpd和APR/APR-Util的版本和编译参数，然后退出。
        
        -X
        以调试模式运行httpd 。仅启动一个工作进程，并且服务器不与控制台脱离。
        
        下列参数仅用于Windows平台：
        -k install|config|uninstall
        安装Apache为一个Windows NT的服务；改变Apache服务的启动方式；删除Apache服务。
        
        -n name
        指定Apache服务的名称为name
        
        -w
        保持打开控制台窗口，使得可以阅读出错信息。

+   配置 conf/httpd.conf
        
        Define SRVROOT "A:\apache"
        ServerRoot "${SRVROOT}"
        
        Listen 80
    
        