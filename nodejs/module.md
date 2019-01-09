一个 Node.js 文件就是一个模块，
这个文件可能是JavaScript 代码、JSON 或者编译过的C/C++ 扩展。
4 类模块（原生模块和3种文件模块）


Node.js 提供了 exports 和 require 两个对象，
其中 exports 是模块公开的接口，
require 用于从外部获取一个模块的接口，即所获取模块的 exports 对象。


#	创建模块

    //hello.js 
    //exports 对象把 world 作为模块的访问接口
    exports.world = function() {
      console.log('Hello World');
    }
    
    
    //对象封装
    module.exports = function() {
      // ...
    }
    //hello.js 
    function Hello() { 
        var name; 
        this.setName = function(thyName) { 
            name = thyName; 
        }; 
        this.sayHello = function() { 
            console.log('Hello ' + name); 
        }; 
    }; 
    module.exports = Hello;
    //main.js 
    var Hello = require('./hello'); 
    hello = new Hello(); 
    hello.setName('BYVoid'); 
    hello.sayHello(); 


#	引入模块
	var hello = require('./hello');
	hello.world();
    //引入了当前目录下的 hello.js 文件（./ 为当前目录，node.js 默认后缀为 js）。






