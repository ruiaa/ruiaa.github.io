+   搜索find
    +   find dir -name "pair_of_name" ：根据名称搜索 find / -name "pip*"

+   软链接 ln
    +   ln -s from to
    +   添加启动到环境 ln -s /usr/local/python/bin/python3.4 /usr/local/bin/python

+   下载 wget
    +   wget url

+   解压 tar
    +   tar -zxvf ****.tar.gz 
    +   tar -jxvf ****.tar.bz(或bz2) 
    
+   netstat -nap #会列出所有正在使用的端口及关联的进程/应用
+   netstat -lnp|grep 88
+   ps -ef | grep java
+   ps 1777 查看进程的详细信息
+   kill -9 1777        #杀掉编号为1777的进程
+   service httpd start #启动apache

#   java环境
+   yum list installed | grep java # 查看自带JDK是否安装
+   yum -y list java* # 查看yum库中java安装包
+   yum -y install java-1.8.0-openjdk*   # 安装java-1.8.0相关java库



#   gitblit
+   wget http://dl.bintray.com/gitblit/releases/gitblit-1.8.0.tar.gz
+   mkdir -p /opt/gitblit
+   mkdir -p /opt/gitblit/repertory
+   cd /opt/gitblit
+   tar -zxvf gitblit-1.8.0.tar.gz
+   vi ./data/defaults.properties
    +   git.repositoriesFolder=/opt/gitblit/repertory
    +   server.httpPort=3345
    +   server.httpsPort = 0
    +   server.httpsBindInterface=localhost
+   vi ./service-centos.sh
    +   GITBLIT_PATH=/opt/gitblit/gitblit-1.8.0
    +   GITBLIT_BASE_FOLDER=/opt/gitblit/repertory
    +   GITBLIT_HTTP_PORT=3345
    +   GITBLIT_HTTPS_PORT=0
+   //java -jar /$GITBLIT/gitblit.jar
+   ./install-service-centos.sh
+   service  gitblit start/stop/restart

#   python3
+   准备编译环境
    +   yum groupinstall 'Development Tools'
    +   yum install zlib-devel bzip2-devel openssl-devel ncurese-devel
+   下载安装
    +   wget https://www.python.org/ftp/python/3.6.2/Python-3.6.2.tar.xz
    +   tar Jxvf Python-3.6.2.tar.xz
    +   cd Python-3.6.2
    +   ./configure --prefix=/usr/local/python3
    +   make && make install
+   软连接
    +   ln -s /usr/local/python3/bin/python3.6 /usr/bin/python3
    +   ln -s /usr/local/python3/bin/pip3 /usr/bin/pip3

#   vsftpd
+   yum install -y vsftpd
+   vi /etc/vsftpd/vsftpd.conf
    +   anonymous_enable=NO 不允许匿名访问
    +   local_enable=YES 允许使用本地帐户进行FTP用户登录验证
    +   chroot_local_user=YES  chroot_list_enable=YES  chroot_list_file=/etc/vsftpd/chroot_list
    
        在/etc/vsftpd.chroot_list文件中列出的用户，可以切换到其他目录；未在文件中列出的用户，不能切换到其他目录。
        
    +   ascii_upload_enable=YES ascii_download_enable=YES 支持ASCII模式的上传和下载
    +   allow_writeable_chroot=YES 配置文件最后添加
+   touch /etc/vsftpd/chroot_list 创建
+   systemctl restart vsftpd.service 重启vsftpd
+   systemctl start vsftpd.service    # 启动服务
+   systemctl status vsftpd.service   # 服务状态查看
+   
+   新建FTP用户 useradd -d /var/ftp/public_root -g ftp -s /sbin/nologin ftpuser
+   修改该FTP用户密码 passwd ftpuser
+   
+   ftp:
    +   open host
    +   ls
    +   put/send LocalFile [RemoteFile]  多个 mput *.txt 
    +   get RemoteFile [LocalFile]  多个 mget *.txt 
    +   lcd 本地目录路径  !chdir 查看当前目录
    +   cd 服务器目录    dir 查看当前目录
    +   bye
    
    
    

#   进程
+   ps 命令用于查看当前正在运行的进程
    +   ps aux | grep name