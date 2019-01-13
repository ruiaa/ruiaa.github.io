w = QWidget()   # QWidge控件是一个用户界面的基本控件，它提供了基本的应用构造器。默认情况下，构造器是没有父级的，没有父级的构造器被称为窗口（window）。
w.show()                    # 显示

setFixedSize()
adjustSize()
w.resize(250, 150)                  # 改变控件的大小
w.move(300, 300)                    # 修改控件位置
w.setGeometry(3, 3, 30, 20)         # 屏幕坐标的x、y和窗口大小的宽、高, =resize()+move()

w.setWindowTitle('Simple')          # 添加窗口标题
w.setWindowIcon(QIcon('web.png'))   # 添加窗口图标。先创建一个QIcon对象，然后接受一个路径作为参数显示图标
    
    
    # 居中
    def center(self):
        # 主窗口的大小
        g = self.frameGeometry() 
        # 获取显示器的分辨率，然后得到中心的位置
        c = QDesktopWidget().availableGeometry().center() 
        # 移动中心点
        g.moveCenter(c)
        # 移动窗口
        self.move(g.topLeft())







#   QToolTip 鼠标悬停显示
QToolTip.setFont(QFont('SansSerif', 10))    # 设置提示框的字体
w.setToolTip('This is a widget')            # 创建提示框

#   QPushButton
QPushButton(string text, QWidget parent = None)

#   QMessageBox 消息框
reply = QMessageBox.question(self, 
             'Message',                          # 显示在消息框的标题栏
             "Are you sure to quit?",            # 显示在对话框
             QMessageBox.Yes | QMessageBox.No,   # 消息框按钮
             QMessageBox.No                      # 默认选中的按钮
         )


#   QLabel 标签

#   QLCDNumber 液晶数字屏

#   QSlider 滑块
sld = QSlider(Qt.Horizontal)
sld.setFocusPolicy(Qt.NoFocus)
sld.valueChanged[int].connect(self.changeValue)
def changeValue(self, value):
    pass

#   QProgressBar 进度条
self.pbar = QProgressBar()
self.pbar.setValue(value)

#   QMainWindow
self.statusBar() # 开启底部状态条
self.statusBar().showMessage(sender.text() + ' was pressed')


#   对话框：QInputDialog , QColorDialog , QFontDialog , QFileDialog
text, ok = QInputDialog.getText(self, 'Input Dialog', 'Enter your name:')
col, ok  = QColorDialog.getColor()
font, ok = QFontDialog.getFont()

##  QFileDialog
fileName, filetype = QFileDialog.getOpenFileName(self, 'title', 'path', 'type')

选取文件夹   QFileDialog.getExistingDirectory()
选择文件     QFileDialog.getOpenFileName()
选择多个文件 QFileDialog.getOpenFileNames()
选择保存文件 QFileDialog.getSaveFileName()

文件筛选:
Excel Files (*.xlsx)
All Files (*);;PDF Files (*.pdf);;Text Files (*.txt)

#   QCheckBox 开和关
cb = QCheckBox('Show title')
cb.toggle()

#   QPushButton 按钮
redb = QPushButton('Red', self)
redb.setCheckable(True)
redb.clicked[bool].connect(self.setColor)   # 切换按钮
def setColor(self, pressed):
    pass

#   QFrame

#   QCalendarWidget 日历
cal = QCalendarWidget(self)
cal.setGridVisible(True)
cal.clicked[QDate].connect(self.showDate)

self.lbl = QLabel(self)
date = cal.selectedDate()
self.lbl.setText(date.toString())

def showDate(self, date):     
    self.lbl.setText(date.toString())

#	QPixmap 图片
pixmap = QPixmap('2.jpg')
pixmap.setDevicePixelRatio(pixmap.width() / 100)
lbl = QLabel()
lbl.setPixmap(pixmap)
vbox.addWidget(lbl)

#	QLineEdit 行编辑
qle = QLineEdit(self)
qle.textChanged[str].connect(self.qle_onChanged)
def qle_onChanged(self, text):
    self.setWindowTitle(text)

#	QSplitter 拖拽分割线
from PyQt5.QtWidgets import (QWidget, QHBoxLayout, QFrame, 
    QSplitter, QStyleFactory, QApplication)
from PyQt5.QtCore import Qt
import sys

class Example(QWidget):

    def __init__(self):
        super().__init__()

        self.initUI()


    def initUI(self):      

        hbox = QHBoxLayout(self)

        topleft = QFrame(self)
        topleft.setFrameShape(QFrame.StyledPanel)

        topright = QFrame(self)
        topright.setFrameShape(QFrame.StyledPanel)

        bottom = QFrame(self)
        bottom.setFrameShape(QFrame.StyledPanel)

        splitter1 = QSplitter(Qt.Horizontal)
        splitter1.addWidget(topleft)
        splitter1.addWidget(topright)

        splitter2 = QSplitter(Qt.Vertical)
        splitter2.addWidget(splitter1)
        splitter2.addWidget(bottom)

        hbox.addWidget(splitter2)
        self.setLayout(hbox)

        self.setGeometry(300, 300, 300, 200)
        self.setWindowTitle('QSplitter')
        self.show()


    def onChanged(self, text):

        self.lbl.setText(text)
        self.lbl.adjustSize()        


if __name__ == '__main__':

    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())


#	QComboBox 下拉选框
combo = QComboBox()
combo.addItem("Ubuntu")
combo.addItem("Mandriva")
combo.activated[str].connect(self.combo_onActivated)
vbox.addWidget(combo)
def combo_onActivated(self, text):
    self.setWindowTitle(text)







#	拖拽






