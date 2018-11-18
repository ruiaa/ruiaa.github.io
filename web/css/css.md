#	引入
+	外部：<head><link rel="stylesheet" href="url.css"/></head>
+	嵌入：<head><style>selector{property: value;}</style></head>
+	内联：<元素名 style="property:value;"/>

#	构造块
+	样式规则 

		selector { 
		    property:value value value; 
		    property:value value value; 
		}

+	继承
	+	强制继承 inherit
	+	不可继承属性 border,padding,width height

+	层叠
	+	优先级 : #id > .class > otherclass.class > 元素名
	+	晚出现的优先级高
	+	!important

+	媒体类型


#	选择器
+	元素类型, .class, #id, [属性], [属性~="值"]
+	两个选择器间 : 逗号--组; 空格--后代关系; 无空格--并关系; > -- 父子; + -- 相邻同胞; ~ -- 普通同胞
+	伪类或伪元素 
:first-child 第一个子元素
:last-child 最后一个子元素
:first-letter 元素的第一个字母
:first-line 元素的第一行
:link 从未被激活或指向
:visited 访问者已激活过
:focus 通过键盘选择、处于活跃状态
:hover 光标指向链接
:active 激活时
:after 在元素原本的内容之后添加新内容

#	属性
##	文本
+	文本:word-spacing,letter-spacing,text-indent,text-align,text-transform,text-decoration
+	字体:font-family,font-style,font-weight,font-variant,font-size,line-height
+	背景:background-color,image,repeat,attachment,position,size,clip,origin 
+	颜色:color:#rrggbb,rgba(r,g,b,a),colorname
+	空白属性 white-space 
##	布局,框模型 w+h --> padding --> margin --> border
+	display:block,inline,inline-block,list-item,none
+	visibility
+	流动：float:left;clear:both;overflow:auto,hidden
+	position:static,relative,absolute,fixed
+	vertical-align

#	边框
+	border-style：
dotted: 定义一个点线边框
dashed: 定义一个虚线边框
solid: 定义实线边框
double: 定义两个边框。 两个边框的宽度和 border-width 的值相同
groove: 定义3D沟槽边框。效果取决于边框的颜色值
ridge: 定义3D脊边框。效果取决于边框的颜色值
inset:定义一个3D的嵌入边框。效果取决于边框的颜色值
outset: 定义一个3D突出边框。 效果取决于边框的颜色值


+	border-width：指定长度值，或者 thick 、medium（默认值） 和 thin
+	border-color：
name - 指定颜色的名称，如 "red"
RGB - 指定 RGB 值, 如 "rgb(255,0,0)"
Hex - 指定16进制值, 如 "#ff0000"

+	单独设置各边
border-top-style:dotted;
border-right-style:solid;
border-bottom-style:dotted;
border-left-style:solid;


+	border-radius 属性用于创建圆角

+	box-shadow 用于向方框添加阴影

+	border-image  使用图片来创建边框










