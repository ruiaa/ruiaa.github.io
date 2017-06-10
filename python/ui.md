#	Tk,wxWidgets,Qt,GTK

##	Tkinter

    #!/usr/bin/env python3
    # -*- coding: utf-8 -*-
    
    import time
    from tkinter import *
    
    class Application(Frame):
        def __init__(self, master=None):
            Frame.__init__(self, master)
            self.pack()
            self.createWidgets()
    
        def createWidgets(self):
            self.helloLabel = Label(self, text='Hello, world!')
            self.helloLabel.pack()
            self.quitButton = Button(self, text='Quit', command=self.quit)
            self.quitButton.pack()
    
    if __name__=='__main__' :
    app = Application()
    # 设置窗口标题:
    app.master.title('Hello World')
    # 主消息循环:
    app.mainloop()