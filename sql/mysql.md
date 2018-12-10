CREATE TABLE `address` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `address` varchar(100) NOT NULL,
    `begin` bigint(20) NOT NULL,
    `end` bigint(20) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mail` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `time` bigint(20) NOT NULL,
    `from` varchar(80) NOT NULL,
    `to` varchar(80) NOT NULL,
    `sender` varchar(120) NOT NULL,
    `receiver` varchar(120) NOT NULL,
    `subject` text NOT NULL,
    `content` text NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


### 字段类型
+   整型
    +   bool
    +   bit
    +   tinyint(size):1字节，(-128~127)。在括号中规定最大位数。
    +   smallint:2字节，(-32768~32767)
    +   mediumint:3字节，(-8388608~8388607)
    +   int:4字节，(-2147483648~2147483647)
    +   bigint:8字节
    +   拥有额外的选项 unsigned。通常，整数可以是负数或正数。如果添加 unsigned 属性，那么范围将从 0 开始，而不是某个负数。
+   浮点型
    +   float(size,d):带有浮动小数点的小数字。在括号中规定最大位数。在 d 参数中规定小数点右侧的最大位数。
    +   double(5,2):带有浮动小数点的大数字，参数表示该浮点型数值最多有5位，其中必须有2位小数。
    +   decimal(size,d):作为字符串存储的double类型，允许固定的小数点。
+   二进制
    +   blob:用于 blobs (binary large objects)。存放最多 65,535 字节的数据。
    +   mediumblob:用于 blobs (binary large objects)。存放最多 16,777,215 字节的数据。
    +   longblob:用于 blobs (binary large objects)。存放最多 4,294,967,295 字节的数据。
+   枚举
    +   enum(x,y,z,etc.)	
        +   允许你顺序输入可能值的列表。可以在enum列表中列出最大 65535 个值。如果列表中不存在插入的值，则插入空值。etc:enum('x','y','z')
    +   set	与 enum 类似，set 最多只能包含 64 个列表项，不过 set 可存储一个以上的值。
+   字符串
    +   char(n):字符型，固定长度字符串类型，n<=255。
        +   存入字符数小于n，则以空格补于其后，查询之时再将空格去掉。所以char类型存储的字符串末尾不能有空格
    +   varchar(n):可变长度字符串类型，n<=255，大于 255，则被转换为text类型
    +   tinytext:存放最大长度为 255 个字符的字符串
    +   text:存放最大长度为 65,535 个字符的字符串。
    +   mediumtext:存放最大长度为 16,777,215 个字符的字符串。
    +   longtext:存放最大长度为 4,294,967,295 个字符的字符串。
+   时间日期
    +   year:年份值，格式为:yyyy。4 位格式所允许的值：1901 到 2155。2 位格式所允许的值：70 到 69，表示从 1970 到 2069
    +   data:日期类型，格式为:yyyy-mm-dd。支持的范围是从 '1000-01-01' 到 '9999-12-31'
    +   time:时间类型，格式为:hh:mm:ss。支持的范围是从 '-838:59:59' 到 '838:59:59'
    +   datetime:混合日期和时间值，格式为:yyyy-mm-dd hh:mm:ss。支持的范围是从 '1000-01-01 00:00:00' 到 '9999-12-31 23:59:59
    +   timestamp:时间戳类型，格式：yyyy-mm-dd hh:mm:ss。支持的范围是从 '1970-01-01 00:00:01' utc 到 '2038-01-09 03:14:07' utc
        +   在 insert 或 update 查询中，timestamp 自动把自身设置为当前的日期和时间。
        +   可接受不同的格式，比如 yyyymmddhhmmss、yymmddhhmmss、yyyymmdd 或 yymmdd。

### SQL 命令
+   数据控制语言
    +   grant	赋予用户特权
    +   revoke	收回赋予用户的特权
+   数据定义语言
    +   create	创建新的表、视图或者其他数据库中的对象
    +   alter	修改现存数据库对象，比如一张表
    +   drop	删除表、视图或者数据库中的其他对象
+   数据操纵语言
    +   select	从一张或者多张表中检索特定的数据
    +   insert	创建一条新记录
    +   update	修改记录
    +   delete	删除记录

+   导入sql
    +   create database abc;
    +   use abc;
    +   set names utf8;
    +   source /home/abc/abc.sql;
    
+   导出sql
    +   导出数据和表结构：
        +   mysqldump -u用户名 -p 数据库名 > 数据库名.sql
        +   #/usr/local/mysql/bin/mysqldump -uroot -p abc > abc.sql

    +   只导出表结构
        +   mysqldump -u用户名 -p -d 数据库名 > 数据库名.sql
        +   #/usr/local/mysql/bin/mysqldump -uroot -p -d abc > abc.sql
        
+   允许文件读写

        my.ini
        [mysqld]
        secure-file-priv=e:/mysql

+   导出 文本
    +   csv
        
            select * from activity into outfile 'e:\\mysql\\canteen.csv' fields terminated by ',' optionally enclosed by '' lines terminated by '/n';
            select * from activity into outfile '/tmp/canteen.csv' fields terminated by ',' optionally enclosed by '' lines terminated by '/n';


### 用户管理
+		密码
vi /etc/my.cnf
mysqld --skip-grant-tables
mysql -u root
use mysql;
UPDATE user SET authentication_string=PASSWORD("NEWPASSWORD") WHERE User='root';
FLUSH PRIVILEGES;
+   查看数据库当前引擎 
    +   show create table table_name; 
    +   修改数据库引擎 
    +   alter table table_name engine=myisam | innodb; 
+   创建数据库用户 
    +   一次可以创建多个数据库用户如： 
    +   create user username1 identified by ‘password’ , username2 identified by ‘password’…. 
+   用户的权限控制：grant 
    +   库，表级的权限控制 : 将某个库中的某个表的控制权赋予某个用户 
    +   grant all on db_name.table_name to user_name [ indentified by ‘password’ ]; 

+   创建用户

        create user 'username'@'host' identified by 'password';
        
        username - 将创建的用户名,
        host -  指定该用户在哪个主机(ip)上可以登陆，
                "localhost"是指该用户只能在本地登录，不能在另外一台机器上远程登录，
                如果想远程登录的话，将"localhost"改为"%"，表示在任何一台电脑上都可以登录
        password - 该用户的登陆密码,密码可以为空,如果为空则该用户可以不需要密码登陆服务器。
        
        create user 'read'@'%' identified by 'canteen_1';

+   授权

        grant privileges on databasename.tablename to 'username'@'host'
        grant all privileges on *.* to 'root'@'%'
        flush privileges;
        
        privileges - 用户的操作权限,如select , insert , update 等
                        如果要授予所的权限则使用all
        databasename - 数据库名,
        tablename-表名,如果要授予该用户对所有数据库和表的相应操作权限则可用*表示, 如*.*.
        
        grant select,insert on canteen.* to 'read'@'%';

+   设置与更改用户密码 :set password for 'username'@'host' = password('newpassword');
+   撤销用户权限 : revoke privilege on databasename.tablename from 'username'@'host';
+   删除用户 : drop user 'username'@'host';
+   查看用户的授权 ：show grants for 'username'@'host';

### 数据库/表操作
1.  数据库
    +   创建 : create database db_name; 
    +   选择数据库 : use db_name;
    +   删除 : drop database db_name; 
    +   删除时先判断是否存在: drop database if exits db_name 

2.  表 : 
    +   创建 : create table table_name (字段1 数据类型 , 字段2 数据类型); 
        +   not null
        +   primary key
        +   auto_increment
    +   显示 : show table_name;
    +   show create table table_name;
    +   详细 : describe table_name;
    +   删表 : drop table table_name;

3.  表结构的修改 
    +   增加字段： alter table table_name add column (字段名 字段类型); ----此方法带括号 
    +   指定字段插入的位置： alter table table_name add column 字段名 字段类型 after 某字段； 
    +   删除字段： alter table table_name drop字段名; 
    +   修改字段名称/类型： alter table table_name change 旧字段名 新字段名 新字段的类型; 
    +   改表的名字： alter table table_name rename to new_table_name; 
    +   一次性清空表中的所有数据： truncate table table_name; 此方法也会使表中的取号器(ID)从1开始 

4.  约束
    +   约束可以限制列或者表。列级的约束只限制单一的列，而表级的约束作用于整个表。
    +   not null：保证列中数据不能有 null 值
    +   default value：提供该列数据未指定时所采用的默认值
    +   unique：保证列中的所有数据各不相同
    +   primary key：主键约束，唯一标识数据表中的行/记录，包含一个或者多个字段，可另起声明primary key(列名1，列名2)
    +   forengi key：外键，唯一标识其他表中的一条行/记录，使用references 表2(列名)，或者foreign key (该表列名) references 表2(列名)
    +   check 约束：此约束保证列中的所有值满足某一条件，使用check (age >= 18)
    +   索引：用于在数据库中快速创建或检索数据
    +
    +   增加约束：alter table 表名 add  constraint 约束定义
    +   增加约束：alter table 表名 modify 列名 类型 约束
    +   删除约束：alter table 表名 drop constraint 约束名


10 . 增加主键，外键，约束，索引。。。。(使用方法见17实例) 
① 约束（主键Primary key、唯一性Unique、非空Not Null） 
② 自动增张 auto_increment 
③外键Foreign key-----与reference table_name(col_name列名)配合使用，建表时单独使用 
④ 删除多个表中有关联的数据----设置foreign key 为set null ---具体设置参考帮助文档 

+   主键是数据表的唯一索引，
比如学生表里有学号和姓名，姓名可能有重名的，但学号确是唯一的，
你要从学生表中搜索一条纪录如查找一个人，就只能根据学号去查找，这才能找出唯一的一个，这就是主键;
如：id int(10) not null primary key auto_increment ；自增长的类型 ；

### 增删查改
1. 添加
    +   Insert into 表名 [(字段1 , 字段2 , ….)] values (值1 , 值2 , …..); 
    +   如果向表中的每个字段都插入一个值,那么前面 [ ] 括号内字段名可写也可不写 
    +   例 : insert into mytable (id,username) values (1,’zhangsan’); 

2. 查询
    +   查询所有数据 : select * from table_name; 
    +   查询指定字段的数据 : select 字段1 , 字段2 from table_name; 
    +   例 : select id,username from mytable where id=1 order by desc;多表查询语句------------参照第17条实例 

3. 更新
    +   更新某一个字段的数据（注意，不是更新字段的名字） 
    +   Update table_name set 字段名=’新值’ [, 字段2 =’新值’ , …..][where id=id_num] [order by 字段 顺序] 
    +   例 : update mytable set username=’lisi’ where id=1; 
    +   Order语句是查询的顺序 , 如 : order by id desc(或asc) , 顺序有两种 : desc倒序(100—1,即从最新数据往后查询),asc(从1-100)，Where和order语句也可用于查询select 与删除delete 

4. 删除表中的信息
    +   删除整个表中的信息 : delete from table_name; 
    +   删除表中指定条件的语句 : delete from table_name where 条件语句 ; 条件语句如 : id=3; 

##  视图view
    创建
    create [or replace] [algorithm = {undefined | merge | temptable}] 
    view [db_name.]view_name [(column_list)] 
    as select_statement [with [cascaded | local] check option]
    
       
    修改
    alter view 视图名 as select 查询语句;
    alter view v_article as select a.title, a.content, a.author, c.name as categoryname, a.createdon from article as a join articlecategory as c on a.articlecategoryid=c.articlecategoryid where a.published=true;
    
    删除
    drop view 视图名;
    drop view v_article;


##  多表查询
+   where 主键联系

        SELECT Persons.LastName, Persons.FirstName, Orders.OrderNo
        FROM Persons, Orders
        WHERE Persons.Id_P = Orders.Id_P 

+   join 用于根据两个或多个表中的列之间的关系，从这些表中查询数据

##  WHERE 子句用于规定选择的标准
+   SELECT 列名称 FROM 表名称 WHERE 列 运算符 值
    <br>SQL 使用单引号来环绕文本值（大部分数据库系统也接受双引号）。如果是数值，请不要使用引号。

        操作符	    描述
        =	        等于              WHERE City='Beijing'
        <>	        不等于             
        >	        大于
        <	        小于
        >=	        大于等于
        <=	        小于等于
        BETWEEN	    在某个范围内
        LIKE	    搜索某种模式
        
        
+   LIKE 操作符用于在 WHERE 子句中搜索列中的指定模式
    
        通配符
        %	            替代一个或多个字符
        _	            仅替代一个字符
        [charlist]	    字符列中的任何单一字符
        [^charlist]     不在字符列中的任何单一字符
        [!charlist]     不在字符列中的任何单一字符
        
        WHERE City LIKE 'N%'            //选取以 "N" 开始的城市
        WHERE City LIKE '%g'            //选取以 "g" 结尾的城市
        WHERE City LIKE '%lon%'         //选取包含 "lon" 的城市
        WHERE City NOT LIKE '%lon%'     //选取不包含 "lon" 的城市
        
        WHERE FirstName LIKE '_eorge'   //选取名字的第一个字符之后是 "eorge" 的人
        WHERE City LIKE '[ALN]%'        //选取以 "A" 或 "L" 或 "N" 开头的城市
        WHERE City LIKE '[!ALN]%'       //选取不以 "A" 或 "L" 或 "N" 开头的城市

+   BETWEEN ... AND 操作符在 WHERE 子句中使用，作用是选取介于两个值之间的数据范围

        WHERE LastName BETWEEN 'Adams' AND 'Carter'        //以字母顺序显示介于 "Adams"（包括）和 "Carter"（不包括）之间的人
        WHERE LastName NOT BETWEEN 'Adams' AND 'Carter'     //


#   SQL 函数
+   SELECT function(列) FROM 表
+   Aggregate 合计函数 面向一系列的值，并返回一个单一的值 ，Scalar 函数 面向某个单一的值，并返回基于输入值的一个单一的值

        AVG() 函数  返回数值列的平均值。NULL 值不包括在计算中。
            SELECT AVG(column_name) FROM table_name
        
        SUM 函数返回数值列的总数（总额）
            SELECT SUM(column_name) FROM table_name
        
        COUNT() 函数返回匹配指定条件的行数
            SELECT COUNT(column_name) FROM table_name           返回指定列的值的数目（NULL 不计入）
            SELECT COUNT(*) FROM table_name                     返回表中的记录数
            SELECT COUNT(DISTINCT column_name) FROM table_name  返回指定列的不同值的数目
        
        FIRST() 函数返回指定的字段中第一个记录的值
            SELECT FIRST(column_name) FROM table_name
        
        LAST() 函数返回指定的字段中最后一个记录的值
            SELECT LAST(column_name) FROM table_name
        
        MAX 函数返回一列中的最大值。NULL 值不包括在计算中。
            SELECT MAX(column_name) FROM table_name
        
        MIN 函数返回一列中的最小值。NULL 值不包括在计算中。
            SELECT MIN(column_name) FROM table_name
        
        UCASE 函数把字段的值转换为大写
            SELECT UCASE(column_name) FROM table_name
        
        LCASE 函数把字段的值转换为小写。
            SELECT LCASE(column_name) FROM table_name
        
        MID 函数用于从文本字段中提取字符
            SELECT MID(column_name,start[,length]) FROM table_name
                    column_name	必需。要提取字符的字段。
                    start	必需。规定开始位置（起始值是 1）。
                    length	可选。要返回的字符数。如果省略，则 MID() 函数返回剩余文本。
        
        LEN 函数返回文本字段中值的长度
            SELECT LEN(column_name) FROM table_name
                SELECT LEN(City) as LengthOfCity FROM Persons
        
        ROUND 函数用于把数值字段舍入为指定的小数位数
            SELECT ROUND(column_name,decimals) FROM table_name
                    column_name	必需。要舍入的字段。
                    decimals	必需。规定要返回的小数位数。
                SELECT ProductName, ROUND(UnitPrice,0) as UnitPrice FROM Products
        
        FORMAT 函数用于对字段的显示进行格式化
            SELECT FORMAT(column_name,format) FROM table_name
                    column_name	必需。要格式化的字段。
                    format	必需。规定格式。
                SELECT ProductName, UnitPrice, FORMAT(Now(),'YYYY-MM-DD') as PerDate FROM Products


#   GROUP BY 
+   用于结合合计函数，根据一个或多个列对结果集进行分组。
+   SQL GROUP BY 语法
    
        SELECT column_name, aggregate_function(column_name)
        FROM table_name
        WHERE column_name operator value
        GROUP BY column_name
        
        SELECT Customer,SUM(OrderPrice) FROM Orders GROUP BY Customer
        //结果集根据customer的值分组，sum函数分别对每一组起效


#   ORDER BY 语句用于对结果集进行排序
+   默认按照升序对记录进行排序
+   按照降序对记录进行排序，可以使用 DESC 关键字
+   SELECT Company, OrderNumber FROM Orders ORDER BY Company DESC, OrderNumber ASC

#   INSERT INTO 语句用于向表格中插入新的行
+   INSERT INTO 表名称 VALUES (值1, 值2,....)
+   INSERT INTO table_name (列1, 列2,...) VALUES (值1, 值2,....)

#   DELETE 语句用于修改表中的数据
+   UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值

#   DELETE 语句用于删除表中的行
+   DELETE FROM 表名称 WHERE 列名称 = 值
+   DELETE * FROM table_name 删除所有行