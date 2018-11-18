#	SQL注入
一种将SQL代码添加到输入参数中传递到SQL服务器解析并执行的攻击手法

select * from cate where id=-1 or 1=1
输入参数（-1 or 1=1）未经过滤，然后直接拼接到SQL语句当中解析执行；

+	SQL注入漏洞

识别web应用中所有输入点
在web应用中输入主要包含三部分内容：get、post、header；

了解哪些类型的请求会触发异常
get请求时，可能会人为引入特殊字符构造地址栏数据，导致SQL语句错误，从而引起异常数据库报错；
post请求时，可能会人为引入特殊字符构造地址栏数据，导致SQL语句错误，从而引起异常数据库报错；

检测服务器响应中的异常
一般数据库SQL语句错误，线上项目会报500服务器内部错误；

+	确定SQL注入漏洞

错误提示
如果目标Web网站开启了错误显示，攻击者就可以通过反复调整发送的参数、查看页面打印的错误信息，推测出Web网站使用的数据库和开发语言等重要信息。


盲注


+	SQL注入攻击

数字注入
select * from cate where id=-1 or 1=1

字符串注入
SELECT * FROM user where name='张三'#' AND pwd='e10adc3949ba59abbe56e057f20f883e'

SELECT * FROM user where name='张三'-- ' AND pwd='d41d8cd98f00b204e9800998ecf8427e'
处。

+	二次注入

为了预防SQL注入攻击，程序将用户输入进行了转义，但是这些数据却又在“未被转义”的查询中使用。

插入时对特殊字符转义，'转义为\'
insert into userinfo(id,username,password) values (1,'$username','$password')
insert into userinfo(id,username,password) values (1,'admin\' -- ','123456')

内部查询
update users set password = '[new_password]' where username = 'admin' -- '

+	预防SQL注入

严格检查输入变量的类型和格式
数字类型的数据直接用intval()强制转整型函数；
字符串类型的数据用正则表达式来匹配

过滤和转义特殊字符 addslashes

使用预编译语句，绑定变量，SQL语句的语义不会发生改变











盲注：不会根据注入的攻击语句返回错误信息

布尔盲注：根据注入信息返回Ture跟Fales
时间盲注：加入特定的时间函数，通过查看web页面返回的时间差来判断注入的语句是否正确


Length（）函数 返回字符串的长度
Substr（）截取字符串
Ascii（）返回字符的ascii码
sleep(n)：将程序挂起一段时间 n为n秒
if(expr1,expr2,expr3):判断语句 如果第一个语句正确就执行第二个语句如果错误执行第三个语句

