#   安装
+   yum install mysql
+   yum install mysql-devel
+   mysql-server(#yum install mysql-server:CentOS 7 版本将MySQL数据库软件从默认的程序列表中移除，用分支mariadb代替)
    +   wget https://dev.mysql.com/get/mysql57-community-release-el7-11.noarch.rpm (from https://dev.mysql.com/downloads/repo/yum/)
    +   rpm -ivh mysql57-community-release-el7-11.noarch.rpm
    +   yum install mysql-server
+   chkconfig mysqld on
+   service mysqld start

#   初始密码
+   systemctl start mysqld.service      (centos6 service mysqld start)
+   grep "password" /var/log/mysqld.log  从mysql进程日志中获取root用户的初始密码
+   mysql -u root -p 登录

+   use mysql;
+   update user set password=password('111111') where user='root';
    ? update user set authentication_string=password("111111") where user="root";
+   flush privileges;


#   远程访问
+   mysql -u root -p
+   use mysql;
+   update user set host = '%' where user ='root';
+   select host, user from user;

#   启动
+   service mysqld restart  (systemctl start mysqld)(systemctl start  mysqld.service)
+   状态：systemctl status mysqld (systemctl status mysqld.service)

