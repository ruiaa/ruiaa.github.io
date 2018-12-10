#   解压型，启动

+   /usr/local/tomcat/bin/startup.sh
+   /usr/local/tomcat/bin/shutdown.sh

#   目录结构
+   bin ——Tomcat执行脚本目录
    +   catalina.sh 用于启动和关闭tomcat服务器
    +   configtest.sh 用于检查配置文件
    +   startup.sh 启动Tomcat脚本
    +   shutdown.sh 关闭Tomcat脚本
+   conf ——Tomcat配置文件
+   lib ——Tomcat运行需要的库文件（JARS）
+   logs ——Tomcat执行时的LOG文件
    +   localhost_access_log.2013-09-18.txt 访问日志
    +   localhost.2013-09-18.log 错误和其它日志
    +   manager.2013-09-18.log 管理日志
    +   catalina.2013-09-18.log Tomcat启动或关闭日志文件
+   temp ——Tomcat临时文件存放目录
+   webapps ——Tomcat的主要Web发布目录（存放我们自己的JSP,SERVLET,类）
+   work ——Tomcat的工作目录，Tomcat将翻译JSP文件到的Java文件和class文件放在这里。

##  配置文件

+   server.xml

    tomcat的主配置文件，包含service,connectors,engine,realm,valve,hosts等组件

+   web.xml

    遵循Servlet规范标准的配置文件，用于配置servlet，并为所有的Web应用程序提供包括MIME映射等默认配置信息；
    
+   tomcat-user.xml

    Realm认证时用到的相关角色、用户和密码等信息；Tomcat自带的manager默认情况下会用到此文件；在Tomcat中添加/删除用户，为用户指定角色等将通过编辑此文件实现
    
+   catalina.policy

    java相关的安全策略配置文件，在系统资源级别上提供访问控制的能力
    
+   catalina.properties

    Tomcat内部package的定义及访问相关的控制，也包括对通过类装载器装载的内容的控制；Tomcat在启动时会事先读取此文件的相关设置；
    
+   logging.properties

    Tomcat通过自己内部实现的JAVA日志记录器来记录操作相关的日志，此文件即为日志记录器相关的配置信息，可以用来定义日志记录的组件级别以及日志文件的存在位置等
    
+   context.xml

    所有host的默认配置信息
    

##  [server.xml](http://www.jianshu.com/p/602f051918d9)
    
    <Server port="8005" shutdown="SHUTDOWN">
        <Listener className="org.apache.catalina.core.AprLifecycleListener" SSLEngine="on" />
        <Listener className="org.apache.catalina.core.JasperListener" />
        <Listener className="org.apache.catalina.core.JreMemoryLeakPreventionListener" />
        <Listener className="org.apache.catalina.mbeans.GlobalResourcesLifecycleListener" />
        <Listener className="org.apache.catalina.core.ThreadLocalLeakPreventionListener" />
        <GlobalNamingResources>
            <Resource name="UserDatabase" auth="Container"
                      type="org.apache.catalina.UserDatabase"
                      description="User database that can be updated and saved"
                      factory="org.apache.catalina.users.MemoryUserDatabaseFactory"
                      pathname="conf/tomcat-users.xml" />
        </GlobalNamingResources>
        <Service name="Catalina">
            <Connector port="8080" protocol="HTTP/1.1"
                       connectionTimeout="20000"
                       redirectPort="8443" />
            <Connector port="8009" protocol="AJP/1.3" redirectPort="8443" />
            <Engine name="Catalina" defaultHost="localhost">
              <Realm className="org.apache.catalina.realm.LockOutRealm">
                <Realm className="org.apache.catalina.realm.UserDatabaseRealm"
                       resourceName="UserDatabase"/>
              </Realm>
              <Host name="localhost"  appBase="webapps"
                    unpackWARs="true" autoDeploy="true">
                <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
                       prefix="localhost_access_log." suffix=".txt"
                       pattern="%h %l %u %t "%r" %s %b" />
              </Host>
            </Engine>
        </Service>
    </Server>


##  web应用程序
+   页面内容等文件的存放位置：*.html, *.jsp等可以有许多目录层次，由用户的网站结构而定，实现的功能应该是网站的界面，也就是用户主要的可见部分。除了HTML文件、JSP文件外，还有js（JavaScript）文件和css（样式表）文件以及其他多媒体文件等。
+   Web-INF : 包含应用软件所使用的资源，但是WEB-INF却不在公共文档根目录之中。在这个目录中所包含的文件都不能被客户机所访问
    +   Web-INF/web.xml 这是一个Web应用程序的描述文件。这个文件是一个XML文件，描述了Servlet和这个Web应用程序的其他组件信息，此外还包括一些初始化信息和安全约束等等。
    +   Web-INF/classes/ 这个目录及其下的子目录应该包括这个Web应用程序的所有JavaBean及Servlet等编译好的Java类文件（*.class）文件，以及没有被压缩打入JAR包的其他class文件和相关资源。注意，在这个目录下的Java类应该按照其所属的包层次组织目录（即如果该*.class文件具有包的定义，则该*.class文件应该放在.\WEB-
    +   Web-INF/lib Java archive files (JARs)，例如标签库或者Servlets，Beans等类的*.jar文件。如果一个类出现在JAR文件中同时也出现在类的目录中，类加载器会加载位于类目录中的那一个









