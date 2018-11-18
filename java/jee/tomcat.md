#	安装运行
+	cd tomcat\bin
+	service install tomcat
+	net start tomcat
+	net stop tomcat

#	配置 tomcat/conf

+	server.xml
默认页
<Host ....>
...
<Context path="" docBase="yourDirIn:webApps" debug="0" reloadable="true" />
...
</Host>

端口
<Connector port="8080" protocol="HTTP/1.1" .../> 

+	web.xml
默认页
<welcome-file-list>
<welcome-file>index.html</welcome-file>
<welcome-file>index.htm</welcome-file>
<welcome-file>index.jsp</welcome-file>
</welcome-file-list>


#	web.xml
+	description ， display-name ， icon

		<display-name>项目名称</display-name>
		<description>项目描述</description>
		<icon> 
			<small-icon>/路径/smallicon.gif</small-icon>
			<large-icon>/路径/largeicon.jpg</large-icon>
		</icon>
		
		small-icon 小图标的路径,16 * 16px,gif或 jpg
		large-icon 大小图标的路径,32* 32px,gif或 jpg

+	context-param 上下文初始化参数
	
		<context-param>  
			<param-name>param_name</param-name>  
			<param-value>param_value</param-value>  
		</context-param>  
		
		JSP网页：${initParam.param_name}
		Servlet：getServletContext().getInitParamter("param_name");


+	filter web容器中的过滤器

在请求和响应对象被servlet处理之前或之后，可以使用过滤器对这两个对象进行操作

		<filter>  
  		<filter-name>setCharacterEncoding</filter-name>  
  		<filter-class>com.SetCharacterEncodingFilter</filter-class>  
  		<init-param>  
     		<param-name>encoding</param-name>  
     		<param-value>GB2312</param-value>  
			</init-param>  
		</filter>  
		
		filter-name元素用来定义过滤器的名称，该名称在整个应用中都必须是惟一的
		ilter-class元素指定过滤 器类的完全限定的名称。
		init-param元素与context-param 元素具有相同的元素描述符

+	filter-mapping 过滤器映射

过滤器可被映射到一个servlet或一个URL模式

过滤是按照部署描述符的filter-mapping元素出现的顺序执行的

		<filter-mapping>  
   		<filter-name>GZIPEncoding</filter-name>  
   		<url-pattern>/*</url-pattern>  
		</filter-mapping>

		<filter-name>Filter的名称</filter-name>
		<url-pattern>URL</url-pattern>
		<servlet-name>Servlet的名称<servlet-name>
		<dispatcher>REQUEST|INCLUDE|FORWARD|ERROR</disaptcher> 
		设定Filter对应的请求方式,默认为REQUEST
		

+	servlet  servlet-mapping

		<servlet>  
  			<servlet-name>ServletName</servlet-name>     
  			<servlet-class>xxxpackage.xxxServlet</servlet-class>   <!--Servlet的类-->  
  			<init-param>                                     <!--初始化一个变量，可看成全局变量，可省略-->  
  				<param-name>参数名称</param-name>            	<!--变量名称-->  
  				<param-value>参数值</param-value>              		<!--变量值-->  
  			</init-param>  
		</servlet>  
		<servlet-mapping>  
  			<servlet-name>ServletName</servlet-name>                 
  			<url-pattern>/aaa/xxx</url-pattern>                   <!--映射的url路径 -->  
		</servlet-mapping>  

+	listener 

		<listener>  
    		<listener-class>com.foo.hello</listener-class>  
		</listener>  


+	session-config  session的有效期限

		<session-config>  
   		<session-timeout>20</session-timeout>  
		</session-config> 
		
		web应用所有session的有效期限.单位为分钟


+	mime-mapping  扩展名与MIME Type做对映

		<mime-mapping>  
   		<extension>doc</extension>  
   		<mime-type>application/vnd.ms-word</mime-type>  
		</mime-mapping>  
		<mime-mapping>  
   		<extension>xls</extension>  
   		<mime-type>application/vnd.ms-excel</mime-type>  
		</mime-mapping>  
		<mime-mapping>  
   		<extension>ppt</extesnion>  
   		<mime-type>application/vnd.ms-powerpoint</mime-type>  
		</mime-mapping>  


+	welcome-file-list  首页列单

		<welcome-file-list>  
    	<welcome-file>index.jsp</welcome-file>  
    	<welcome-file>index.htm</welcome-file>  
		</welcome-file-list>  

+	error-page   错误代码(error-code)或异常(exception-type)的种类对应到web应用资源路径

		<error-page>  
	   <error-code>404</error-code>  
	   <location>/error404.jsp</location>  
		</error-page>  
		
		<error-page>  
	   <exception-type>java.lang.Exception</exception-type>  
	   <location>/except.jsp</location>  
		</error-page>  

+	jsp-config  

		taglib:taglib-uri与taglib-location：设定JSP网页用到的Tag Library路径.
			taglib-uri定义TLD文件的URI,JSP网页的taglib指令可以经由这个URI存取到TLD文件.
			taglib-location：/WEB-INF/lib/xxx.tld， TLD文件对应Web应用的存放位置.
		
		jsp-property-group
			   <description>Description</descrition>    此设定的说明
        <display-name>Name</display-name>        此设定的名称
        <url-pattern>URL</url-pattern>           设定值所影响的范围,如:/CH2 或者/*.jsp
        <el-ignored>true|false</el-ignored>      若为true,表示不支持EL语法.
        <scripting-invalid>true|false</scripting-invalid>    若为true表示不支持<%scription%>语法.
        <page-encoding>encoding</page-encoding>  设定JSP网页的编码
        <include-prelude>.jspf</include-prelude> 设置JSP网页的抬头,扩展名为.jspf
        <include-coda>.jspf</include-coda>       设置JSP网页的结尾,扩展名为.jspf
        
+	resource-ref  利用JNDI取得应用可利用资源

		<description>说明</description> 资源说明
		<rec-ref-name>资源名称</rec-ref-name> 资源名称
		<res-type>资源种类</res-type> 资源种类
		<res-auth>Application|Container</res-auth> 资源由Application或Container来许可
		<res-sharing-scope>Shareable|Unshareable</res-sharing-scope> 资源是否可以共享.默认值为 Shareable

		<resource-ref>  
   		<description>JNDI JDBC DataSource of JSPBook</description>  
   		<res-ref-name>jdbc/sample_db</res-ref-name>  
   		<res-type>javax.sql.DataSoruce</res-type>  
   		<res-auth>Container</res-auth>  
		</resource-ref>  





