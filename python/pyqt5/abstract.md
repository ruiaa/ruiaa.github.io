import sys
from PyQt5.QtWidgets import QApplication, QWidget


app = QApplication(sys.argv)
w = QWidget()
w.resize(250, 150)
w.move(300, 300)
w.setWindowTitle('Simple')
w.show()
sys.exit(app.exec_())




import sys
from PyQt5.QtWidgets import QApplication, QWidget
from PyQt5.QtGui import QIcon


class Example(QWidget):

    def __init__(self):
        super().__init__()
        self.initUI()


    def initUI(self):
        self.setGeometry(300, 300, 300, 220)
        self.setWindowTitle('Icon')
        self.setWindowIcon(QIcon('web.png'))        
        self.show()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())

.
class Example(QWidget):

    def __init__(self):
        super().__init__()

        self.initUI()


    def initUI(self):               

        self.setGeometry(300, 300, 250, 150)        
        self.setWindowTitle('Message box')    
        self.show()


    def closeEvent(self, event):

        reply = QMessageBox.question(self, 'Message',
            "Are you sure to quit?", QMessageBox.Yes | 
            QMessageBox.No, QMessageBox.No)

        if reply == QMessageBox.Yes:
            event.accept()
        else:
            event.ignore()  
