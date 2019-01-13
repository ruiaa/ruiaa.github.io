#   绝对定位
w.move(x, y)


#   盒布局 : QHBoxLayout,QVBoxLayout

	# 右下角
    hbox = QHBoxLayout()
    hbox.addStretch(1)          # 弹性空间
    hbox.addWidget(okButton)
    hbox.addWidget(cancelButton)
    vbox = QVBoxLayout()
    vbox.addStretch(1)
    vbox.addLayout(hbox)
    
    # 使用顶层布局
    self.setLayout(vbox)    


#	栅格布局 : QGridLayout

grid = QGridLayout()
self.setLayout(grid)
grid.addWidget(button, row,col)
grid.addWidget(reviewEdit, row, col,  multiple_rows,  multiple_cols)

grid.setSpacing(10) 


















