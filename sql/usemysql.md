#	安装MySql
1.	卸载旧版本
2.	删除服务：使用管理员权限打开cmd sc delete mysql
3.	在注册表regedit清理mysql
4.	下载MySql ZIP Archive
5.	安装根目录配置my.ini
	<br>5.5 default-character-set需要换为character_set_server
6.	初始化data 安装目录\bin\mysqld --initialize-insecure
7.	将MySQL安装成服务：安装目录\bin\mysqld -install


#	密码
+	配置root用户登录：安装目录\bin\mysqladmin -uroot -p password <新密码>
+	第一次登陆 ERROR 1045 (28000): Access denied for user 'root'@'localhost'
		
	
	1.	跳过密码验证
		my.ini
		[mysqld]
		skip-grant-tables

	2.	重启
		net stop mysql
		net start mysql

	3.	进入mysql数据库：
		mysql -u root -p
		mysql> use mysql;
		Database changed

	4.给root用户设置新密码：
		mysql> update user set authentication_string=password("新密码")where user="root";
		Query OK, 1 rows affected (0.01 sec)
		Rows matched: 1 Changed: 1 Warnings: 0

	5.刷新数据库，退出
		mysql> flush privileges;
		Query OK, 0 rows affected (0.00 sec)
		mysql> quit
		Bye

	6.重启
		[mysqld]
		# skip-grant-tables

	7.使用密码登录

	8.使用任意命令
		error You must reset your password using ALTER USER statement before executing this statement.
		step 1: set password=password('your new password');
		step 2: alter user 'root'@'localhost' password expire never;
		step 3: flush privileges;
	
	9.刷新
	flush privileges;
	quit


#	使用
+	错误日志 \data\*.err
+	[my.ini配置](http://www.cnblogs.com/liuling/p/2013-11-29.html)

#	基本命令
    


    
    select 中加上distinct去除重复字段
    
    
    显示当前mysql版本和当前日期
    
    select version(),current_date;
    
    从文件中读取
    
    mysql -h myhost -u root -p database < sql.txt 

    
    
    
    
    
    
    
    
    
    
    

    
    11 . 查看数据库当前引擎 
    SHOW CREATE TABLE table_name; 
    修改数据库引擎 
    ALTER TABLE table_name ENGINE=MyISAM | InnoDB; 





