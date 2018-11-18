#   gitblit
+   java -version 检测java环境
+   wget http://dl.bintray.com/gitblit/releases/gitblit-1.8.0.tar.gz
+   mkdir -p /opt/gitblit
+   mkdir -p /opt/gitblit/repertory
+   cd /opt/gitblit
+   tar -zxvf gitblit-1.8.0.tar.gz
+   vi ./data/defaults.properties
    +   git.repositoriesFolder=/opt/gitblit/repertory
    +   server.httpPort=3345
    +   server.httpsPort = 0
    +   server.httpsBindInterface=localhost
+   vi ./service-centos.sh
    +   GITBLIT_PATH=/opt/gitblit/gitblit-1.8.0
    +   GITBLIT_BASE_FOLDER=/opt/gitblit/repertory
    +   GITBLIT_HTTP_PORT=3345
    +   GITBLIT_HTTPS_PORT=0
+   直接启动
+		java -jar /opt/gitblit/gitblit-1.8.0/gitblit.jar --baseFolder data
+   后台启动
+   （修改service-centos.sh后需执行一次）./install-service-centos.sh
+   service gitblit start/stop/restart
+   初始管理员账号admin admin
