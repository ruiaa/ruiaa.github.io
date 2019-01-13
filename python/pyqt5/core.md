#	QBasicTimer


self.timer = QtCore.QBasicTimer()
self.timer.start(100, self) # 过期时间和事件接收者
self.timer.stop()
def timerEvent(self, e):	# 事件接收者的处理函数
    if self.step >= 100:
        self.timer.stop()
        self.btn.setText('Finished')
        return
    self.step = self.step + 1
    self.pbar.setValue(self.step)