app = QApplication(sys.argv)	# 每个PyQt5应用都必须创建一个应用对象。sys.argv是一组命令行参数的列表。Python可以在shell里运行，这个参数提供对脚本控制的功能。
sys.exit(app.exec_())	# 进入应用的主循环中，事件处理器这个时候开始工作。主循环从窗口上接收事件，并把事件传入到派发到应用控件里。当调用exit()方法或直接销毁主控件时，主循环就会结束。sys.exit()方法能确保主循环安全退出。外部环境能通知主控件怎么结束。


qbtn.clicked.connect(QCoreApplication.instance().quit)