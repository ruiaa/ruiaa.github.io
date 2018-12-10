https://www.jianshu.com/p/0a83ccb4c388


+	export:用于对外输出本模块（一个文件可以理解为一个模块）变量的接口。

+	import:用于在一个模块中加载另一个含有export接口的模块。

      //a.js
      var name="LXN";
      var echo=function(value){
        console.log(value)
      }
      export {name, echo}
      //通过向大括号中添加name和echo 变量，将它们export输出
      //以上的export {name}不能写成export name
      
      //b.js
      //通过import获取a.js文件的内部变量
      import {name, echo} from "./a.js"
      console.log(name)  //LXN
      echo(name) //LXN
      
+	export default:默认输出，不需要知道所要加载模块的变量名

      //a.js
      var name="LXN";
      export default name  
      //name不能加大括号，注意与基本用法区分
      //一个文件只能用一个export default
      //此处相当于为name变量值“LXN”起了一个系统默认的变量名default
      //export default命令实际上只是输出一个叫做default的变量，所以它后面不能跟变量声明语句，而export需要跟变量声明或者大括号作为输出。
      
      //b.js
      //其实，a.js文件的export default输出一个叫做default的变量，然后系统允许为他取任意名字，所以可以为import的模块起任何变量名，且不需要用大括号包含
      import any from "./a.js"
      import anyname from "./a.js"
      console.log(any,anyname) //LXN,LXN















