#	Java web 工程结构
##	工程发布目录结构：servlet容器加载web应用的统一标准目录

	- projectapp
		- index.html
		- othersDirs  				//静态文件资源
			- html
			- css
			- js
			- img
		- WEB-INF						
			- web.xml					//工程配置文件(部署描述符文件)
			- classes
				- pakeage
					- *.class			//工程编译生成的class文件
			- lib						//依赖
				- *.jar				//查找class时优先查找lib目录下的*.jar文件
			- otherConfigfile
		- META-INF
			- MANIFEST.MF			//生成发布版本时根据配置自动生成的配置信息，web版本信息等


##	工程编译目录结构：通过工程构建工具(ant,maven,gradle等)编译生成最终的运行时发布结构

###	Eclipse Dynamic Web Project

	- projectapp
		- src							//具体java逻辑实现部分
			- packageName
				- *.java
		- WebContent					//依赖、工程配置、静态文件部分(默认WebContent根目录对应于发布工程根目录)
			- META-INF
				- MANIFEST.MF
			- WEB-INF
				- web.xml
				- lib
					- *.jar


###	maven构建的工程目录结构

	- projectapp
		- src
			- main
				- java					//-->WEB-INF/classes
					- packagename
						- *.class
				- resources			//-->WEB-INF/classes
					- configfile
				- webapp				//-->/
					- index.html
					- othersDirs
					- WEB-INF
						- web.xml		//-->WEB-INF/web.xml
		- target
			- generated-files
		- pom.xml						//配置工程依赖和编译、发布等版本的控制
										//maven Dependencies --> WEB-INF/lib


















					