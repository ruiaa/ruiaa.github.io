#   安装
+   yum install mysql
+   yum install mysql-devel
+   mysql-server(#yum install mysql-server:CentOS 7 版本将MySQL数据库软件从默认的程序列表中移除，用分支mariadb代替)
    +   wget https://dev.mysql.com/get/mysql57-community-release-el7-11.noarch.rpm (from https://dev.mysql.com/downloads/repo/yum/)
    +   rpm -ivh mysql57-community-release-el7-11.noarch.rpm
    +   yum install mysql-server

#   初始密码
+   systemctl start  mysqld.service
+   grep "password" /var/log/mysqld.log  从mysql进程日志中获取root用户的初始密码
+   mysql -uroot -p 登录
+   alter user 'root'@'localhost' identified by 'Working_mysql139';


#   启动
+   service mysqld restart  (systemctl start mysqld)(systemctl start  mysqld.service)
+   状态：systemctl status mysqld (systemctl status mysqld.service)