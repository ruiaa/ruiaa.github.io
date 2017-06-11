#	web

##	WSGI：Web Server Gateway Interface

+	WSGI服务器:wsgiref纯Python编写的WSGI服务器的参考实现
+	WSGI处理函数:生成Http，由WSGI服务器来调用


	# environ：一个包含所有HTTP请求信息的dict对象；
	# start_response：一个发送HTTP响应头的函数，只能调用一次
	def application(environ, start_response):

		#    HTTP响应码
		#    list表示的HTTP Header，每个Header用一个包含两个str的tuple表示。
	    start_response('200 OK', [('Content-Type', 'text/html')])

		#	函数的返回值作为HTTP响应的Body发送给浏览器
	    return [b'<h1>Hello, web!</h1>']

+	启动WSGI服务器


    # 实现Web应用程序的WSGI处理函数
    # hello.py
    def application(environ, start_response):
        start_response('200 OK', [('Content-Type', 'text/html')])
        return [b'<h1>Hello, web!</h1>']
    	
    	
    
    # 负责启动WSGI服务器，加载application()函数
    # server.py
    # 从wsgiref模块导入:
    from wsgiref.simple_server import make_server
    # 导入我们自己编写的application函数:
    from hello import application
    
    # 创建一个服务器，IP地址为空，端口是8000，处理函数是application:
    httpd = make_server('', 8000, application)
    print('Serving HTTP on port 8000...')
    # 开始监听HTTP请求:
    httpd.serve_forever()




##	Web框架

+	Flask
+	Django：全能型Web框架；
+	web.py：一个小巧的Web框架；
+	Bottle：和Flask类似的Web框架；
+	Tornado：Facebook的开源异步Web框架。


##	HTML模板技术 MVC：Model-View-Controller，“模型-视图-控制器”
+	jinja2

+	Mako：用<% ... %>和${xxx}的一个模板；

+	Cheetah：也是用<% ... %>和${xxx}的一个模板；

+	Django：














