#   Gunicorn
+   pip3 install gunicorn
+   /usr/local/python3/bin/gunicorn -w 1 -b 0.0.0.0:3349 app:app
	+	-w 表示开启多少个 worker，-b 表示 gunicorn 开发的访问地址