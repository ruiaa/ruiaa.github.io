#	生命周期
Servlet从被Web服务器加载到它被销毁的整个生命过程： init( ) > service( ) > destory( )
客户端 > request > { init( ) > service( ) > destory( ) } > response > 客户端

1、Servlet容器 ( tomcat ) 创建一个Servlet实例
2、容器调用该实例的init()方法
3、容器向Servlet传递客户端的请求，并且调用此实例的service()方法
4、容器在销毁本实例前调用它的destroy()方法

init()
服务器装入Servlet时执行。可以配置服务器，在启动服务器或客户机首次访问Servlet时装入Servlet

service()
每当一个客户请求一个HttpServlet对象，该对象的service()方法就要被调用，而且传递给这个方法一个"请求"(ServletRequest)对象和一个"响"(ServletResponse)对象作为参数

destroy()
在服务器停止且卸装Servlet时执行该方法。可以将Servlet作为服务器进程的一部分来关闭

GetServletConfig()
返回一个ServletConfig对象，该对象用来返回初始化参数和ServletContext。ServletContext接口提供有关servlet的环境信息

GetServletInfo()
提供有关servlet的信息，如作者、版本、版权等

#	HttpServlet    HTTP协议的Web服务器的Servlet类
+	get请求   --> public void doGet (HttpServletRequest request,HttpServletResponse response)
+	post请求 --> public void doPost(HttpServletRequest request,HttpServletResponse response) 


#	cookie操作  java.servlet.http.Cookie
HttpServletResponse.addCookie()  向浏览器发送Cookie消息
HttpServletRequest.getCookies()   读取浏览器发送的Web服务器的所有Cookie消息

public Cookie ( String name , String value )
MaxAge  Cookie在客户机的有效秒数
Path  当前Cookie的有效Web路径
Domain  当前Cookie的有效域
Comment  当前Cookie的注释部分
Version  当前Cookie的协议版本
Secure  前Cookie是否只能使用安全的协议传输

#	Session 
session对象用来保存每个用户的用户信息和会话状态。session对象由服务器端自动创建，可以跟踪每个用户的操作状态。用户首次登录系统时服务器会自动给用户分配唯一标识的session id，可以用来区分开其他用户。相对于Cookie，session是存储在服务器端的会话

HttpSession
HttpServletRequest.getSession()


#	Filter：Servlet过滤器



#	Servlet监听器




#	HttpServletResponse对象代表服务器的响应

		//发送数据
		getOutputStream()
		getWriter()
		
		//响应头
		addDateHeader,setDateHeader
		addHeader,setHeader
		addIntHeader,setIntHeader
		containsHeader
		
		
		//响应状态码
		setStatus  
		//响应状态码的常量:
		
		
		//以UTF-8的编码进行输出
		setCharacterEncoding("UTF-8");
		//getOutputStream().write("中国".getBytes("UTF-8"));
		//getWriter().write("中国");
		//通过设置响应头控制浏览器以UTF-8的编码显示数据
		setHeader("content-type","text/html;charset=UTF-8");
		//response.getWriter().write("<meta http-equiv='content-type' content='text/html;charset=UTF-8'/>");






#	文件操作

		//绝对路径
		getServletContext().getRealPath("/download/1.JPG");
		
		//设置content-disposition响应头控制浏览器以下载的形式打开文件
		response.setHeader("content-disposition", 
			"attachment;filename="+URLEncoder.encode(fileName, "UTF-8"));
		
		//文件输入输出流
		InputStream in = new FileInputStream(realPath);
		int len = 0;
		byte[] buffer = new byte[1024];
		OutputStream out = response.getOutputStream();
		while ((len = in.read(buffer)) > 0) {
		    out.write(buffer,0,len);
		}
		in.close();    
		
		



#	HttpServletRequest

客户机信息
　　getRequestURL方法返回客户端发出请求时的完整URL。
　　getRequestURI方法返回请求行中的资源名部分。
　　getQueryString 方法返回请求行中的参数部分。
　　getPathInfo方法返回请求URL中的额外路径信息。额外路径信息是请求URL中的位于Servlet的路径之后和查询参数之前的内容，它以“/”开头。
　　getRemoteAddr方法返回发出请求的客户机的IP地址。
　　getRemoteHost方法返回发出请求的客户机的完整主机名。
　　getRemotePort方法返回客户机所使用的网络端口号。
　　getLocalAddr方法返回WEB服务器的IP地址。
　　getLocalName方法返回WEB服务器的主机名。


请求头
　　getHeader(string name)方法:String 
　　getHeaders(String name)方法:Enumeration 
　　getHeaderNames()方法


请求参数(客户端提交的数据)
getParameter(String)方法(常用)
getParameterValues(String name)方法(常用)
getParameterNames()方法(不常用)
getParameterMap()方法(编写框架时常用)





































