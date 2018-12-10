[邮件服务器](https://www.jianshu.com/p/610d9bf0ae8b)

#	邮件
+	MUA:Mail User Agent.用户邮件代理,用户通过MUA接收发送邮件
+	MTA: Mail Transfer Protocol.邮件传输代理,是SMTP的一种实现.
	常用的MTA有sendmail,Postfix,仅负责邮件的传输
+	MDA: Mail Deliver Agent,邮件分发代理.
	负责将接收到的邮件保存在邮件服务器上.
	sendmail以及Postfix默认使用的MDA是procmail
+	MRA: Mail Receive Agent,邮件接收代理,用来实现IMAP,POP3协议,
	负责与MUA交互,将服务器上的邮件通过IMAP以及POP3传输给客户端.
    常用的MRA是Dovecot.
+	LMTP:Local Mail Transfer Protocol.本地邮件传输协议,是SMTP协议的扩展.
+	Postfix:一个开源的MTA服务器,负责通过SMTP协议管理发送到本机的邮件以及由本机向外发送的邮件.与sendMail相似.现今流行的服务器套件例如Zimbra,IRedMail内部都采用Postfix作为MTA.
+	Dovecot:一个开源的IMAP以及POP3服务器.通常工作是验证用户身份以及邮件的处理.
+	SPF, DKIM, DMARC:验证发件人身份

+	SMTP全称是Simple Mail Transfer Protocol,简单邮件传输协议, 由RFC5321定义.
	主要的工作就是把邮件信息从发件人的邮件服务器中传送到接收人的邮件服务器中,
    偶尔我们使用MUA来发送邮件的话,也承载传输用户邮件到发件服务器的功能, 因而也称作推协议
+	POP3,Post Office Protocol Version 3,邮局协议第三版,由RFC1939进行定义.
	主要服务于用户管理邮件服务器上面的电子邮件.
    具体过程是:当外来邮件发送到收件人的邮件服务器上时,收件人可以使用邮件客户端连接邮件服务器,把未阅读的邮件服务器以及部分信息拉取回本地进行处理.在拉取的过程中,可选择拉取完删除以及拉取完不删除两种方式,默认一般都是拉取完不删除
+	IMAP, Internet Message Access Protocol, 网络信息访问协议.相对于pop3协议所有邮件的管理都需要下载下来进而管理,IMAP提供了用户远程访问邮件服务器的途径,因而通过IMAP,用户可以直接管理邮件服务器上的邮件.

        		
                           DNS 
                          MX记录
                          ↗ ↑
                        ↗   ↑
                2.UDP ↗     ↑ 4.SPF,DKIM,PTR
                    ↗       ↑
                  ↗         ↑
                MTA ----->> MTA ----->> MDA
                 ↑   3.SMTP       5      ↓
                 ↑                       ↓ 6
          1.SMTP ↑                       ↓
                 ↑          MRA ----->> /var/mal/username
                 ↑           ↑    8
                MUA          ↑
                             ↑ 7.IMAP,POP3
                             ↑
                             ↑
                            MUA


	+	邮件服务器接受收件
    
    

#	

+	端口：25, 465, 587, 110, 995, 143, 993
	防火墙

+	移除sendmail：rpm -e sendmail 或者 yum remove sendmail

+	域名解析配置

+	修改hostname：hostnamectl  set-hostname   mail.域名

+	修改MTA（默认邮件传输代理）
alternatives --config mta
alternatives --display mta     # mat - status is manual

#	安装 postfix 
+	yum install postfix
#	配置 postfix 
+	/etc/postfix/main.cf


# DELIVERY TO MAILBOX
#
# The home_mailbox parameter specifies the optional pathname of a
# mailbox file relative to a user's home directory. The default
# mailbox file is /var/spool/mail/user or /var/mail/user.  Specify
# "Maildir/" for qmail-style delivery (the / is required).
#
#home_mailbox = Mailbox
home_mailbox = Maildir/
myhostname

邮局系统的主机名

mydomain

邮局系统的域名

myorigin

从本机发出邮件的域名名称

inet_interfaces

监听的网卡接口

mydestination

可接收邮件的主机名或域名

mynetworks

设置可转发哪些主机的邮件

relay_domains

设置可转发哪些网域的邮件

#	数据库
+	创建mail数据库用以处理邮件相关的业务.并且创建邮件管理员.

create user 'mail_admin'@'localhost' identified by 'mail_13Ad';
GRANT SELECT, INSERT, UPDATE, DELETE ON mail.* TO 'mail_admin'@'localhost' IDENTIFIED BY 'mail_13Ad';
GRANT SELECT, INSERT, UPDATE, DELETE ON mail.* TO 'mail_admin'@'localhost.localdomain' IDENTIFIED BY 'mail_13Ad';
FLUSH PRIVILEGES;

	mail数据库中一共有4个表,分别是虚拟域名, 邮件转发, 用户信息以及传输路径四个表
	虚拟域名表：
	CREATE TABLE domains (domain varchar(50) NOT NULL, PRIMARY KEY (domain) );
    邮件转发：
    CREATE TABLE forwardings (source varchar(80) NOT NULL, destination TEXT NOT NULL, PRIMARY KEY (source) );
    用户表：
    CREATE TABLE users (email varchar(80) NOT NULL, password varchar(20) NOT NULL, PRIMARY KEY (email) );
    传输路径表：
    CREATE TABLE transport ( domain varchar(128) NOT NULL default '', transport varchar(128) NOT NULL default '', UNIQUE KEY domain (domain) );

#	Postfix
虚拟域名配置
创建:/etc/postfix/mysql-virtual_domains.cf
user = mail_admin
password = mys123123
dbname = mail
query = SELECT domain AS virtual FROM domains WHERE domain='%s'
hosts = 127.0.0.1


邮件转发配置
创建:/etc/postfix/mysql-virtual_forwardings.cf
user = mail_admin
password = mys123123
dbname = mail
query = SELECT destination FROM forwardings WHERE source='%s'
hosts = 127.0.0.1


虚拟邮箱配置
创建: /etc/postfix/mysql-virtual_mailboxes.cf
user = mail_admin
password = mys123123
dbname = mail
query = SELECT CONCAT(SUBSTRING_INDEX(email,'@',-1),'/',SUBSTRING_INDEX(email,'@',1),'/') FROM users WHERE email='%s'
hosts = 127.0.0.1


电子邮件与文件映射
创建:/etc/postfix/mysql-virtual_email2email.cf
user = mail_admin
password = mys123123
dbname = mail
query = SELECT email FROM users WHERE email='%s'
hosts = 127.0.0.1
























