[CentOS 7 安装 Python3.5](http://www.jianshu.com/p/8bd6e0695d7f)

##  源码包 (.tar, .tar.gz, .tgz, .tar.bz, …)
这种软件包里面都是源程序，没有编译过，需要编译后才能安装。
+   安装: 
    1.  先解压并切换到该文件夹

        tar -zxvf ****.tar.gz 
        
        tar -jxvf ****.tar.bz(或bz2) 

    2.  ./configure  ＃配置＃
    
        指定目录 ./configure --prefix=/usr/local/aaaa
        
        指定软件安装目录以后，当我们不需要这个软件时，直接删除软件的目录
        
    3.  make＃调用make＃ 
    4.  make install ＃安装源代码＃
    5.  make clean 删除安装时产生的临时文件
    
+   卸载: 
    1.  进入安装时的目录 
    2.  make uninstall  ＃卸载＃
    
    
    
##  卸载命令rpm
+   rpm -q -a ：查询到当前系统中安装的所有的软件包
+   rpm -q name ：查询指定软件包
+   rpm -e [package name] ：卸载软件。参数e的作用是使rpm进入卸载模式
+   rpm -e [package name] -nodeps ：  忽略依赖关系的卸载可能会导致系统中其它的一些软件无法使用
+   rpm -ql [package name] ：rpm包安装位置