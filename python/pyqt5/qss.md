setStyleSheet("QWidget { background-color: %s }" %  self.col.name())

#	选择器
通用类型选择器：*
*所有控件

类别选择器：QPushButton
匹配所有QPushButton的实例和其子类的实例

属性选择器：QPushButton[flat=”false”]
匹配所有QPushButton属性flat为false的实例，属性分为两种，静态的和动态的，静态属性可以通过Q_PROPERTY() 来指定，动态属性可以使用setProperty来指定，如：

QLineEdit *nameEdit = new QLineEdit(this);
nameEdit->setProperty("mandatoryField", true);
如果在设置了qss后Qt属性变动了，必要重新设置qss来使其见效，可以使用先unset再set qss。

1.4 类选择器：.QPushButton
所有QPushButton的实例，但不包括其子类，这相当于：

*[class~="QPushButton"]
~=的含义是测验一个QStringList类别的属性是否包罗给定的QString。
1.5 ID抉择器：QPushButton#okButton
对应Qt里面的object name设置，使用这条CSS之前要先设置对应控件的object name为okButton，如：

Ok->setObjectName(tr(“okButton”));
1.6 后代选择器：QDialog QPushButton
对于所有为QDialog后代（包括儿子，与儿子的儿子的递归）为QPushButton的实例
1.7 子选择器：QDialog > QPushButton
对于所有的QDialog直接子类QPushButton的实例，不包罗儿子的儿子的递归。


