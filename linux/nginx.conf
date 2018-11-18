
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




#user groupname username;
worker_processes 1;
error_log logs/error.log;
pid logs/nginx.pid;
worker_rlimit_nofile 65535;

events {
	worker_connections 65535;
	use epoll;
}

http {
	include mime.types;
	default_type application/octet-stream;
	log_format main '$remote_addr - $remote_user [$time_local] "$request" ' '$status $body_bytes_sent "$http_referer" ' '"$http_user_agent" "$http_x_forwarded_for"';
   
	access_log off;
 
	sendfile on;
	tcp_nopush on;
	tcp_nodelay on;

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

	gzip on; 
	gzip_vary on;
	gzip_min_length 1k;
	gzip_buffers 4 16k;
	gzip_http_version 1.0;
	gzip_comp_level 2;
	gzip_types text/plain text/xml text/javascript text/css application/json application/xml application/xml+rss application/javascript application/x-javascript;

	include sites/*.conf;
}


#app.conf
server {
    listen 80;
    server_name www.app.com;
    client_max_body_size 10M;

    access_log logs/app.log main;

    #error_page 500 502 503 504 /50x.html;
    #location = /50x.html {
    #    root html;
    #}
    
    location ^~ /static/{
        root /usr/project/myapp;
    }
    
    location / {
        root /var/www/html;
        index index.html index.htm;

        proxy_pass http://127.0.0.1:8888;
        proxy_redirect off;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}





