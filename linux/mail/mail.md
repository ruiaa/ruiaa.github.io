+   安装Postfix
yum -y install postfix
+


#   mysql
+   mysql -u root -p
+   create database mailserver character set utf8;
+   use mailserver;
+   create user mailserver@'localhost' identified by 'Mailserver139';
+   grant all on mailserver.* to mailserver@'localhost' identified by 'Mailserver139';
+   exit;
+   mysql -u mailserver -p
+   use mailserver;
+

//virtual_domains表，该表是本地服务器用以接收邮件的域名
CREATE TABLE `virtual_domains` (
`id` int(11) NOT NULL auto_increment,
`name` varchar(50) NOT NULL,
PRIMARY KEY (`id`))
ENGINE=InnoDB DEFAULT CHARSET=utf8;

//新建virtual_users表，该表邮件服务器的终端用户表，记录用户的邮件地址及密码
CREATE TABLE `virtual_users` (
`id` int(11) NOT NULL auto_increment,
`domain_id` int(11) NOT NULL,
`password` varchar(106) NOT NULL,
`email` varchar(100) NOT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `email` (`email`),
FOREIGN KEY (domain_id) REFERENCES virtual_domains(id) ON DELETE CASCADE)
ENGINE=InnoDB DEFAULT CHARSET=utf8;


//新建virtual_aliases表，该表是邮件服务器别名表
CREATE TABLE `virtual_aliases` (
`id` int(11) NOT NULL auto_increment,
`domain_id` int(11) NOT NULL,
`source` varchar(100) NOT NULL,
`destination` varchar(100) NOT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY (domain_id) REFERENCES virtual_domains(id) ON DELETE CASCADE)
ENGINE=InnoDB DEFAULT CHARSET=utf8;



insert into virtual_domains(id,name) values(1,'mail.lzrui.net');
insert into virtual_domains(id,name) values(2,'lzrui.net');


insert into virtual_users(id,domain_id,password,email)
values (1,2,ENCRYPT('rui123456', CONCAT('$7$', SUBSTRING(SHA(RAND()), -16))),'rui@lzrui.net');

insert into virtual_users(id,domain_id,password,email)
values (2,2,ENCRYPT('rui123456', CONCAT('$7$', SUBSTRING(SHA(RAND()), -16))),'lzrui@lzrui.net');



insert into virtual_aliases(id,domain_id,source,destination)
values (1,2,'all@mydomain.com','zhangsan@mydomain.com');

insert into virtual_aliases(id,domain_id,source,destination)
values (1,2,'all@mydomain.com','lisi@mydomain.com');
请注意：
通过上述别名表的数据，当有人给all@mydomain.com发送邮件时，系统将自动将邮件转发给zhangsan@mydomain.com和lisi@mydomain.com



#   Postfix
##  /etc/postfix/master.cf
628      inet  n       -       n       -       -       qmqpd
127.0.0.1:7700  inet  n  -  n  -  -  -
127.0.0.1:10025 inet n    -    n    -    -    smtpd
##  /etc/postfix/main.cf

    # 屏蔽Postfix默认的用户验证参数
    # TLS parameters
    #smtpd_tls_cert_file=/etc/ssl/certs/ssl-cert-snakeoil.pem
    #smtpd_tls_key_file=/etc/ssl/private/ssl-cert-snakeoil.key  #smtpd_use_tls=yes
    #smtpd_tls_session_cache_database = btree:${data_directory}/smtpd_scache
    #smtp_tls_session_cache_database = btree:${data_directory}/smtp_scache
    smtpd_tls_cert_file=/etc/dovecot/dovecot.pem
    smtpd_tls_key_file=/etc/dovecot/private/dovecot.pem
    smtpd_use_tls=yes
    smtpd_tls_auth_only = yes
    #Enabling SMTP for authenticated users, and handing off authentication to Dovecot
    smtpd_sasl_type = dovecot
    smtpd_sasl_path = private/auth
    smtpd_sasl_auth_enable = yes
    smtpd_recipient_restrictions =  permit_sasl_authenticated, permit_mynetworks, reject_unauth_destination

    # Postfix能够通过MySQL表中相关数据决定需要接受/发送邮件的域名
    mydestination = localhost

    # Postfix不要使用LDA「Local Delivery Agent」转而使用Dovecot的LMTP完成本地邮件投递
    virtual_transport = lmtp:unix:private/dovecot-lmtp

    # Postfix去MySQL数据库种寻找域名、用户帐号密码及邮件别名等信息
    virtual_mailbox_domains = mysql:/etc/postfix/mysql-virtual-mailbox-domains.cf
    virtual_mailbox_maps = mysql:/etc/postfix/mysql-virtual-mailbox-maps.cf
    virtual_alias_maps = mysql:/etc/postfix/mysql-virtual-alias-maps.cf



    smtpd_banner = $myhostname ESMTP $mail_name (Ubuntu)
    biff = no
    append_dot_mydomain = no
    readme_directory = no

    myhostname = host.mydomain.com
    alias_maps = hash:/etc/aliases
    alias_database = hash:/etc/aliases
    myorigin = /etc/mailname

    relayhost = mynetworks = 127.0.0.0/8 [::ffff:127.0.0.0]/104 [::1]/128
    mailbox_size_limit = 0
    recipient_delimiter = +
    inet_interfaces = all

##  新建/etc/postfix/mysql-virtual-mailbox-domains.cf

    user = mailserver
    password = Mailserver139
    hosts = 127.0.0.1
    dbname = mailserver
    query = SELECT 1 FROM virtual_domains WHERE name='%s'

重启Postfix服务
service postfix restart
测试上述内容是否正确，如果上述内容配置正确，则如下命令执行后返回结果应该为1：
postmap -q mydomain.com mysql:/etc/postfix/mysql-virtual-mailbox-domains.cf

##  新建/etc/postfix/mysql-virtual-mailbox-maps.cf

    user = mailserver
    password = Mailserver139
    hosts = 127.0.0.1
    dbname = mailserver
    query = SELECT 1 FROM virtual_users WHERE email='%s'


重启Postfix服务
service postfix restart
测试上述配置是否正确，如果上述内容配置正确，则如下命令执行后返回结果应该为1：
postmap -q lisi@mydomain.com mysql:/etc/postfix/mysql-virtual-mailbox-maps.cf


##  新建/etc/postfix/mysql-virtual-alias-maps.cf

    user = mailserver
    password = Mailserver139
    hosts = 127.0.0.1
    dbname = mailserver
    query = SELECT destination FROM virtual_aliases WHERE source='%s'


重启Postfix服务
service postfix restart
测试上述配置是否正确，如果上述配置正确，则如下命令执行后返回结果应该是之前添加的别名帐号：
postmap -q all@mydomain.com mysql:/etc/postfix/mysql-virtual-alias-maps.cf

##  /etc/postfix/master.cf

找到submission和smtps所在的两行，并将其注释去掉。这样做的目的是允许Postfix通过587和465端口发送邮件

重启Postfix服务
service postfix restart

#   Dovecot安装及配置
Dovecot将会使用993「IMAP协议」及995「POP协议」

Dovecot的配置
+   /etc/dovecot/dovecot.conf       Dovecot的主配置文件

        !include conf.d/*.conf

        # Enable installed
        protocols!include_try /usr/share/dovecot/protocols.d/*.protocol
        protocols = imap pop3 lmtp


+   /etc/dovecot/conf.d/10-mail.conf        Dovecot将要操作的磁盘路径相关配置信息

        mail_location = maildir:/var/mail/vhosts/%d/%n
        mail_privileged_group = mail

        ls -ld /var/mail
        drwxrwsr-x 2 root mail 4096 May  11 15:08 /var/mail

        创建/var/mail/vhosts/文件夹给每个需要启用的域名：
        mkdir -p /var/mail/vhosts/mydomain.com
        groupadd -g 5000 vmail
        useradd -g vmail -u 5000 vmail -d /var/mail
        chown -R vmail:vmail /var/mail


+   /etc/dovecot/conf.d/10-auth.conf        用户验证相关配置信息

        disable_plaintext_auth = yes
        auth_mechanisms = plain login
        #!include auth-system.conf.ext
        !include auth-sql.conf.ext

+   /etc/dovecot/conf.d/auth-sql.conf.ext       SQL-Type验证相关配置信息

        passdb {
            driver = sql
            args = /etc/dovecot/dovecot-sql.conf.ext
        }

        userdb {
            driver = static
            args = uid=vmail gid=vmail home=/var/mail/vhosts/%d/%n
        }


+   /etc/dovecot/dovecot-sql.conf.ext       Dovecot与数据库连接相关配置信息

        driver = mysql
        connect = host=127.0.0.1 dbname=mailserver user=mailserver password=Mailserver139
        default_pass_scheme = SHA512-CRYPT
        password_query = SELECT email as user, password FROM virtual_users WHERE email='%u';

        在命令行种输入如下内容以修改目录权限：
        chown -R vmail:dovecot /etc/dovecot
        chmod -R o-rwx /etc/dovecot

+   /etc/dovecot/conf.d/10-master.conf      Dovecot本地socket相关配置信息

        # 通过将端口设置为0，以禁用非SSL加密的IMAP和POP3协议
        service imap-login {
            inet_listener imap {
                port = 0
            }
            ...
        }
        service pop3-login {
            inet_listener pop3 {
                port = 0
            }
            ...
        }

        service lmtp {
                unix_listener /var/spool/postfix/private/dovecot-lmtp {
                mode = 0600
                user = postfix
                group = postfix
          }

          # Create inet listener only if you can't use the above UNIX socket
          #inet_listener lmtp {
                #Avoid making LMTP visible for the entire internet
                #address =
                #port =
                #}
         }

         service auth {
             # auth_socket_path points to this userdb socket by default. It's typically
             # used by dovecot-lda, doveadm, possibly imap process, etc. Its default
             # permissions make it readable only by root, but you may need to relax these
             # permissions. Users that have access to this socket are able to get a list
             # of all usernames and get results of everyone's userdb lookups.

             unix_listener /var/spool/postfix/private/auth {
                     mode = 0666
                     user = postfix
                     group = postfix
             }

             unix_listener auth-userdb {
                     mode = 0600
                     user = vmail
                     #group =
             }

             # Postfix smtp-auth
             #unix_listener /var/spool/postfix/private/auth {
             #       mode = 0666
             #}

             # Auth process is run as this user.
             user = dovecot
         }

         service auth-worker {
             # Auth worker process is run as root by default, so that it can access
             # /etc/shadow. If this isn't necessary, the user should be changed to
             # $default_internal_user.

             user = vmail
         }


+   /etc/dovecot/conf.d/10-ssl.conf     关于SSL的相关配置信息


        找到文件中ssl_cert并修改内容如下「请确保dovecot的pem文件已经存在，如果大家使用了自己的SSL文件，请将如下内容修改为相应的路径」：

        ssl_cert = </etc/dovecot/dovecot.pem
        ssl_key = </etc/dovecot/private/dovecot.pem
        # 强制用户使用SSL加密：
        ssl = required


        重新启动Dovecot服务：
        service dovecot restart






