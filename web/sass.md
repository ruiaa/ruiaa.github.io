通过淘宝的npm镜像来安装。
1.安装cnpm（https://npm.taobao.org/）
npm install -g cnpm --registry=https://registry.npm.taobao.org
2.在项目文件夹下安装node-sass
cnpm install --save-dev node-sass

https://www.haorooms.com/post/sass_css

webpack.base.conf.js的rules里面添加配置
{
  test: /\.sass$/,
  loaders: ['style', 'css', 'sass']
}

变量
$blue : #1875e7;　
div {
   color : $blue;
}

$side : left;
.rounded {
   border-#{$side}-radius: 5px;
}


计算功能
SASS允许在代码中使用算式：
body {
　　　　margin: (14px/2);
　　　　top: 50px + 100px;
　　　　right: $var * 10%;
}
*





嵌套
div h1 {
	color : red;
}
//可以写成：
div {
	hi {
		color:red;
	}
}

//属性也可以嵌套，比如border-color属性，可以写成：
　　p {
　　　　border: {
　　　　　　color: red;
　　　　}
　　}

//注意，border后面必须加上冒号。



在嵌套的代码块内，可以使用$引用父元素。比如a:hover伪类，可以写成：
a {
	&:hover { color: #ffb3ff; }
}










