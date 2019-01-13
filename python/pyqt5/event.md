调用应用的exec_()方法时，应用会进入主循环，主循环会监听和分发事件。
在事件模型中，有三个角色：事件源 -> 事件(signal) -> 事件目标(slot)

widget.envet.connect(widget.fun)


        btn2.clicked.connect(self.buttonClicked)

        self.statusBar()

        self.setGeometry(300, 300, 290, 150)
        self.setWindowTitle('Event sender')
        self.show()



class Example(QMainWindow):

    def __init__(self):
        super().__init__()
        # 绑定：事件源 -> 事件(signal) -> 事件目标(slot)
        btn1.clicked.connect(self.buttonClicked)
        btn2.clicked.connect(self.buttonClicked)     
    
    # 定义slot
    def buttonClicked(self):
        sender = self.sender()  # 获取signal的事件源
        self.statusBar().showMessage(sender.text() + ' was pressed')
   
    
















