python Flask
============



启动
------------
+	app = Flask(__name__)
+	app.run()



路由	装饰器
--------------
+	@app.route('/', methods=['GET', 'POST'])



request数据
--------------
+	request.form['username']


模板
--------------
+	模板的渲染	render_template('model.html', key=value)