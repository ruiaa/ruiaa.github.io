#   安装
+   yum install mysql
+   yum install mysql-devel
+   mysql-server(#yum install mysql-server:CentOS 7 版本将MySQL数据库软件从默认的程序列表中移除，用分支mariadb代替)
    +   wget https://dev.mysql.com/get/mysql57-community-release-el7-11.noarch.rpm (from https://dev.mysql.com/downloads/repo/yum/)
    +   rpm -ivh mysql57-community-release-el7-11.noarch.rpm
    +   yum install mysql-server
chkconfig mysqld on
service mysqld start

#   初始密码
+   systemctl start mysqld.service      (centos6 service mysqld start)
+   grep "password" /var/log/mysqld.log  从mysql进程日志中获取root用户的初始密码
+   mysql -uroot -p 登录
+   alter user 'root'@'localhost' identified by '1111';

+   5.1,空初始密码
+   use mysql;
+   update user set password=password('1111') where user='root';
+   flush privileges;


#   启动
+   service mysqld restart  (systemctl start mysqld)(systemctl start  mysqld.service)
+   状态：systemctl status mysqld (systemctl status mysqld.service)


#	远程访问
mysql -u root -pvmwaremysql>usemysql;mysql>update user set host = '%' where user ='root';mysql>select host, user from user;