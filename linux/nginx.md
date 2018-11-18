#   模型
+   master进程 <--> worker进程... <--> client...
+   操作指令 --> master进程 --> worker进程...  或者  操作指令 --> 启动新的Nginx进程 --> master进程 --> worker进程...
+   连接请求 --> master listen --> worker 注册
+   每个worker里面只有一个主线程，异步非阻塞处理请求
+   事件处理：网络事件、信号、定时器

+   connection tcp连接的封装 
+   worker_connectons 每个进程支持的最大连接数，连接池
+   对于HTTP请求本地资源，能够支持的最大并发数量是worker_connections * worker_processes
+   作为反向代理来说，最大并发数量应该是worker_connections * worker_processes/2。每个并发会建立与客户端的连接和与后端服务的连接，会占用两个连接。
+   多进程间连接的平衡：

+   request  http请求，ngx_http_request_t

#   配置


#   安装
+   安装编译工具及库文件 
    +   yum -y install make zlib zlib-devel gcc-c++ libtool  openssl openssl-devel
+   安装PCRE让Nginx支持Rewrite功能 
    +   cd ~
    +   wget http://downloads.sourceforge.net/project/pcre/pcre/8.41/pcre-8.41.tar.gz
    +   tar zxvf pcre-8.41.tar.gz -C /usr/local
    +   mv /usr/local/pcre-8.41 /usr/local/pcre
    +   cd /usr/local/pcre
    +   ./configure --prefix=/usr/local/pcre
    +   make && make install
    +   pcre-config --version
+   安装zlib库
    +   cd ~
    +   wget http://zlib.net/zlib-1.2.11.tar.gz
    +   tar -zxvf zlib-1.2.11.tar.gz -C /usr/local
    +   mv /usr/local/zlib-1.2.11 /usr/local/zlib
    +   cd /usr/local/zlib
    +   ./configure --prefix=/usr/local/zlib
    +   make && make install
+   安装ssl
    +   cd ~
    +   wget http://www.openssl.org/source/openssl-1.0.1j.tar.gz
    +   tar -zxvf openssl-1.0.1j.tar.gz -C /usr/local
    +   mv /usr/local/openssl-1.0.1j /usr/local/openssl
    +   cd /usr/local/openssl
    +   ./config --prefix=/usr/local/openssl
    +   make && make install
+   安装 Nginx
    +   cd ~
    +   wget http://nginx.org/download/nginx-1.13.6.tar.gz
    +   tar -zxvf nginx-1.13.6.tar.gz
    +   cd nginx-1.13.6
    +   ./configure --prefix=/usr/local/nginx --with-pcre=/usr/local/pcre --with-zlib=/usr/local/zlib
    +   make && make install
#   
+   启动： /usr/local/nginx/sbin/nginx
+   重启： /usr/local/nginx/sbin/nginx –s reload
+   停止： /usr/local/nginx/sbin/nginx –s stop
+   强制关闭：pkill nginx
+   测试配置文件是否正常： /usr/local/nginx/sbin/nginx –t
/usr/local/nginx/sbin/nginx -s reload            # 重新载入配置文件
/usr/local/nginx/sbin/nginx -s reopen            # 重启 Nginx
/usr/local/nginx/sbin/nginx -s stop              # 停止 Nginx

+   win
    
        cd
        nginx                       # 启动
        nginx -s reload             # 重新载入配置文件
        nginx -s stop               # 退出
        nginx -s quit               # 平滑退出
        nginx -s reopen             # 重新打开日志文件
        


+   用户及用户组 
    +   /usr/sbin/groupadd groupname
    +   /usr/sbin/useradd -g groupname username
    
    
+   配置 vi /usr/local/nginx/conf/nginx.conf
[优化](http://www.cnblogs.com/sxlfybb/archive/2011/09/15/2178160.html)

nginx主配置文件分为4部分，main(全局配置)、server(主机配置)、upstream(负载均衡服务器设置)以及location(URL匹配特定位置的设置),这四者的关系是：server继承main,location继承server,upstream既不会继承其它设置也不会被继承


    #运行用户组和用户，默认为nobody
    #user groupname username;
    #启动进程,通常设置成和cpu的数量相等
    worker_processes 1;
    #全局错误日志及PID文件
    error_log logs/error.log;
    pid logs/nginx.pid;
    #一个nginx进程打开的最多文件描述符数目，理论值应该是最多打开文件数（ulimit -n）与nginx 进程数相除
    #但是nginx 分配请求并不是那么均匀，所以最好与ulimit -n 的值保持一致
    worker_rlimit_nofile 65535;
     
     
    #工作模式及连接数上限
    events {
    #单个后台worker process进程的最大并发链接数
    worker_connections 65535;
    #epoll是多路复用IO(I/O Multiplexing)中的一种方式,但是仅用于linux2.6以上内核,可以大大提高nginx的性能
    use epoll;
    }
     
     
    #设定http服务器，利用它的反向代理功能提供负载均衡支持
    http {
    #设定mime类型,类型由mime.types文件定义
    include mime.types;
    #默认设置为二进制流，也就是当文件类型未定义时使用这种方式
    default_type application/octet-stream;
    #设定日志格式
    log_format main '$remote_addr - $remote_user [$time_local] "$request" '
                    '$status $body_bytes_sent "$http_referer" '
                    '"$http_user_agent" "$http_x_forwarded_for"';
     
    access_log off;
     
    #sendfile 指令指定 nginx 是否调用 sendfile函数来输出文件，sendfile为系统调用，直接在内核空间完成文件发送，不需要先read再write，没有上下文切换开销
    #对于普通应用，必须设为on，如果用来进行下载等应用磁盘IO重负载应用，可设置为 off，以平衡磁盘与网络I/O处理速度，降低系统的uptime.
    sendfile on;
    #FreeBSD 的一个 socket 选项，对应 Linux 的 TCP_CORK，数据包会累计到一定大小之后才会发送，减小了额外开销，提高网络效率
    tcp_nopush on;
    #尽快发送数据包，（先填满包，再尽快发送）
    tcp_nodelay on;
    

    #连接超时时间
    keepalive_timeout 60;
    proxy_read_timeout 200;
    proxy_next_upstream error;
     
    fastcgi_connect_timeout 300;
    fastcgi_send_timeout 300;
    fastcgi_read_timeout 300;
    fastcgi_buffer_size 64k;
    fastcgi_buffers 4 64k;
    fastcgi_busy_buffers_size 128k;
    fastcgi_temp_file_write_size 128k;
    
    #开启gzip压缩
    gzip on; 
    #输出Vary响应头
    gzip_vary on;
    gzip_min_length 1k;
    gzip_buffers 4 16k;
    gzip_http_version 1.0;
    gzip_comp_level 2;
    gzip_types text/plain text/xml text/javascript text/css application/json application/xml application/xml+rss application/javascript application/x-javascript;
    
    
    #server虚拟主机的配置，每个文件配置一个server 
    include sites/*.conf;
    }
    
+   /usr/local/nginx/conf/目录下创建一个sites目录，里面存放具体应用的配置文件

    
        #app.conf
        server {
            listen 80;                          #侦听80端口
            server_name www.app.com;          #定义使用www.xx.com访问
            client_max_body_size 10M;

            access_log logs/app.log main;      #设定本虚拟主机的访问日志

            #
            #error_page 500 502 503 504 /50x.html;
            #location = /50x.html {
            #    root html;
            #}
            
            #配置静态文件
            location ^~ /static/{
                root /usr/project/myapp;
            }
            
            #请求转发
            location / {
                root /var/www/html;  #设置虚拟主机的网站根目录
                index index.html index.htm; #设置虚拟主机默认访问的网页
                
                proxy_pass http://127.0.0.1:8888; #请求转向本机ip:8888
                proxy_redirect off;
                proxy_set_header Host $host;
                proxy_set_header X-Real-IP $remote_addr;
                proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            }
        }
        
        
 #  Nginx Location配置总结及基础最佳实践
 语法规则： location [=|~|~*|^~] /url/ { … }
 
 +  = ：表示精确匹配
 +  ^~ ：表示url以某个常规字符串开头，理解为匹配url路径即可。nginx不对url做编码，因此请求为/static/20%/aa，可以被规则^~ /static/ /aa匹配到（注意是空格）。
 +  ~ ：表示区分大小写的正则匹配
 +  ~* ：表示不区分大小写的正则匹配
 +  !~ ：区分大小写的正则不匹配
 +  !~* ：不区分大小写的正则不匹配
 +  / 通用匹配，任何请求都会匹配到。
 +  首先匹配 =，其次匹配^~, 其次是按文件中顺序的正则匹配，最后是交给 / 通用匹配
 
 +  常用匹配规则
 
 
    #直接匹配网站根，通过域名访问网站首页比较频繁，使用这个会加速处理
    #这里是直接转发给后端应用服务器了，也可以是一个静态首页
    #第一个必选规则
    location = / {
        proxy_pass http://tomcat:8080/index
    }
     
    #第二个必选规则是处理静态文件请求，这是nginx作为http服务器的强项
    #有两种配置模式，目录匹配或后缀匹配,任选其一或搭配使用
    location ^~ /static/ {
        root /webroot;
    }
    location ~* \.(gif|jpg|jpeg|png|css|js|ico)$ {
        root /webroot/res/;
    }
     
    #第三个规则就是通用规则，用来转发动态请求到后端应用服务器
    #非静态文件请求就默认是动态请求，自己根据实际把握
    #毕竟目前的一些框架的流行，带.php,.jsp后缀的情况很少了
    location / {
        proxy_pass http://tomcat:8080/
    }
 
 
 +  目录转发
 
 
    location ^~ /outer/ {
        #case A： url最后以/结尾
        proxy_pass http://tomcat:8080/
        #case B： url最后没有/
        #proxy_pass http://tomcat:8080
    }
    #访问localhost/outer/in.html
    #case A 会转发到tomcat:8080/in.html
    #case B 会转发到tomcat:8080/outer/in.html
    
    
+   负载均衡
    
    upstream myserver; {
        ip_hash;
        server 172.16.1.1:8001;
        server 172.16.1.2:8002;
        server 172.16.1.3;
        server 172.16.1.4;
    }
    location / {
        proxy_pass http://myserver;
    }
    我们在 upstream 中指定了一组机器，并将这个组命名为 myserver，这样在 proxypass 中只要将请求转移到 myserver 这个 upstream 中我们就实现了在四台机器的反向代理加负载均衡。其中的 ip_hash 指明了我们均衡的方式是按照用户的 ip 地址进行分配。另外还有轮询、指定权重轮询、fair、url_hash几种调度算法。
    