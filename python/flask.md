python Flask
============



创建，启动
------------
+	app = Flask(__name__)
+	app.run(),app.run(host='0.0.0.0')
+   调试 app.debug = True或者app.run(debug=True)



路由	装饰器
--------------
+	@app.route('/', methods=['GET', 'POST'])
+   变量 @app.route('/user/<username>')
+   转换器 @app.route('/post/\<int:post_id>') int, float, path和默认的相似，但也接受斜线

构造/重定向 URL
-----------
+   url_for('route_method_name',key='value')
+   函数名作为第一个参数
+   接受对应URL规则的变量部分的命名参数。未知变量部分会添加到URL末尾作为查询参数(?key=value)
+   重定向redirect(url_for('login')
+   
+   静态文件URL:url_for('static', filename='style.css') --> static/style.css
+   
+   错误请求，放弃请求并返回错误代码abort(errorCode)，定制错误页面errorhandler()装饰器

        @app.errorhandler(404)
        def page_not_found(error):
            return render_template('page_not_found.html'), 404

Jinja2 模板渲染
----------
+	模板的渲染	render_template('model.html', key=value)

request数据
--------------
+	request.form['username']


