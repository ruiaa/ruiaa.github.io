#   http --> web 服务器 --> WSGI --> python web app

##  Web 服务端
+   Nginx
    +   Nginx （发音为 “engine-x”）是一个web服务器，并是HTTP、SMTP和其他协议的反向代理。 
    +   它由其高性能、相对简洁以及对众多应用服务器（比如WSGI服务器）兼容而著名。
    +   它也拥有便利的特性， 比如负载均衡、基本的认证、流等。Nginx被设计为承载高负载的网站，并逐渐变得广为流行。
    
##  WSGI 服务器
+   WSGI

    Web服务网关接口（Web Server Gateway Interface，简称“WSGI”）是一种在Web服务器和Python Web应用程序或框架之间的标准接口。
    
    通过标准化Web服务器和Python web应用程序 或框架之间的行为和通信，WSGI使得编写可移植的的Python web代码变为可能，使其能够部署在任何符合WSGI的web服务器上。
    
    独立WSGI服务器相比传统web服务器，使用更少的资源，并提供最高的性能
    
+   Gunicorn

    Gunicorn （Green Unicorn，绿色独角兽）是一个纯Python WSGI服务器， 用来支持Python应用。不像其他Python web服务器，它有周全的用户界面，十分易于使用和配置。
    
    Gunicorn具有合理的默认配置。 然而，其他一些像uWSGI这样的服务器相较而言过于可定制化，因此更加难以高效使用。
    
    Gunicorn是如今新Python web应用程序的推荐选择。
    
+   Waitress

    Waitress 是一个纯Python WSGI服务器，声称具备“非常可接受的性能”。 它的文档不是很详细，但它确实提供了一些很好的而Gunicorn没有的功能（例如HTTP请求缓冲）。
    
    Waitress在Python Web开发社区中越来越受欢迎。
    

##  python web app

### python web 框架
+   URL路由（URL Routing）

    将输入的HTTP请求匹配到特定的Python代码用来调用
    
+   请求和响应对象（Request and Response Objects）

    封装来自或发送给用户浏览器的信息
    
+   模板引擎（Template Engine）

    能够将实现应用的Python代码逻辑和其要产生输出的HTML（或其他）分离开
    
+   Web服务器开发（Development Web Server）

    在开发机上运行HTTP服务器，从而快速开发；当文件更新时自动更新服务端代码。
    
+   Django

+   Flask





#   部署

##  托管部署
