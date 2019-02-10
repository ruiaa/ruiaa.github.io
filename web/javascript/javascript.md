#   Javascript

##  引入位置
+   HTML 中的脚本必须位于 \<script> 与 \</script> 标签之间。
+   脚本可被放置在 HTML 页面的 \<body> 和 \<head> 部分中。
+   外部 JavaScript 文件的文件扩展名是 .js，在\<script> 标签的 "src" 属性中设置该 .js 文件

##  输出
+   操作 HTML 元素：document.getElementById(id)

##  语句
+   分号用于分隔 JavaScript 语句
+   代码块由左花括号开始，由右花括号结束。
+   JavaScript 对大小写是敏感的
+   可以在文本字符串中使用反斜杠对代码行进行换行
+   单行注释以 //
+   多行注释以 /* 开始，以 */ 结尾
+   
+   if...else 语句 if(.){...} else if(.){...}else{...}
+   switch 语句  switch(i){ case n:...;break; }
+   for 循环 for(var i=0;i<n;i++){...}
+   for/in 遍历 for(obj in objs){...}
+   while 循环 while(.){...}
+   do/while 循环 do{..}while(.);
+   
+   break 跳出switch()语句，跳出当前循环
+   continue 中断当前迭代，继续循环中的下一个迭代
+   label 标签 label:语句
    +   break;break 语句（不带标签引用），只能用在循环或 switch 中。
    +   break labelname;通过标签引用，break 语句可用于跳出任何 JavaScript 代码块：
    +   continue labelname;     continue 语句（带有或不带标签引用）只能用在循环中。
+   异常  try{...} catch(err){...}
+   throw 创建或抛出异常 throw exception; //异常可以是 JavaScript 字符串、数字、逻辑值或对象。
+   
+   with 设置代码在特定对象中的作用域，with(obj) {...//在obj的定义域下执行}


##  变量
+   声明：var
+   未使用值来声明的变量，其值实际上是 undefined
+   如果重新声明 JavaScript 变量，该变量的值不会丢失
+   数据类型
    +   字符串，用双引号或单引号包围这个值。
    +   数字：只有一种数字类型，可以带小数点。指数计数法来：123e5
        +   如果前缀为 0，则 JavaScript 会把数值常量解释为八进制数，如果前缀为 0x ，则解释为十六进制数。

                属性：
                MAX VALUE
                MIN VALUE
                NEGATIVE INFINITIVE
                POSITIVE INFINITIVE
                NaN
                prototype
                constructor
                
                方法：
                toExponential()
                toFixed()
                toPrecision()
                toString()
                valueOf()

    +   布尔：true 或 false
    +   数组：创建new Array(o,o...)，或者[o,o...];访问[i]
        +   new Array(i);如果只传入一个数字参数，则会创建一个长度为参数的数组
    +   对象：对象由花括号包裹。在括号内部，对象的属性以名称和值对的形式name:value来定义。属性由逗号分隔
        +   var person={firstname:"Bill", lastname:"Gates", id:5566};
        +   属性访问：name=person.lastname;或者name=person["lastname"];
        +   对象构造器
            
                function person(firstname,lastname,age,eyecolor)
                {
                this.firstname=firstname;
                this.lastname=lastname;
                this.age=age;
                this.eyecolor=eyecolor;
                
                this.changeName=changeName;
                function changeName(name)
                {
                this.lastname=name;
                }
                }

    +   null：
    +   undefined：表示变量不含有值
    
+   声明变量类型
    +   声明新变量时，可以使用关键词 "new" 来声明其类型
    +   var carname=new String;
    +   var x=      new Number;
    +   var y=      new Boolean;
    +   var cars=   new Array;
    +   var person= new Object;
+   JavaScript 变量均为对象。声明一个变量时，就创建了一个新的对象
+   
+   变量的生存期
    +   函数内部声明的变量(var)是局部变量，只能在函数内部访问
    +   在函数外声明的变量是全局变量，网页上的所有脚本和函数都能访问
    +   局部变量会在函数运行以后被删除。
    +   全局变量会在页面关闭后被删除。
+   向未声明的变量来分配值时，该变量将被自动作为全局变量声明，即使它在函数内执行。

#   Js 对象
##  Array 对象
+   创建

        new Array();        //数组为空，length 字段为 0。
        new Array(size);    //length为size，元素为undefined 
        new Array(element0, element1, ..., elementn);

+   属性

        constructor 返回对创建此对象的数组函数的引用。
        length  设置或返回数组中元素的数目。
        prototype   使您有能力向对象添加属性和方法。object.prototype.name=value

+   方法

        concat()    连接两个或更多的数组，并返回结果。
        join()  把数组的所有元素放入一个字符串。元素通过指定的分隔符进行分隔。
        pop()   删除并返回数组的最后一个元素
        push()  向数组的末尾添加一个或更多元素，并返回新的长度。
        reverse()   颠倒数组中元素的顺序。
        shift() 删除并返回数组的第一个元素
        slice() 从某个已有的数组返回选定的元素
        sort()  对数组的元素进行排序
        splice()    删除元素，并向数组添加新元素。
        toSource()  返回该对象的源代码。
        toString()  把数组转换为字符串，并返回结果。
        toLocaleString()    把数组转换为本地数组，并返回结果。
        unshift()   向数组的开头添加一个或更多元素，并返回新的长度。
        valueOf()   返回数组对象的原始值

#   ECMAScript 原始类型
 +  Undefined、Null、Boolean、Number 和 String
 
        对变量或值调用 typeof 运算符将返回下列值之一：
        undefined - 如果变量是 Undefined 类型的
        boolean - 如果变量是 Boolean 类型的
        number - 如果变量是 Number 类型的
        string - 如果变量是 String 类型的
        object - 如果变量是一种引用类型或 Null 类型的 （null 被认为是对象的占位符，但从技术上来说，它仍然是原始值。）
 
 +  Undefined 类型
    +   Undefined 类型只有一个值，即 undefined
    +   当声明的变量未初始化时，该变量的默认值是 undefined
    +   当函数无明确返回值时，返回的也是值 undefined
    +   值undefined（已声明，未初始化，从而默认赋予）并不同于未定义的值（从未声明）
        +   typeof 运算符并不真正区分这两种值，都输出"undefined"
        +   其他运算符只能用于已声明的变量上，用于未定义的值将引发错误
+   Null 类型
    +   只有一个值 null
    +   值 undefined 实际上是从值 null 派生来的，ECMAScript 把它们定义为相等的。null == undefined
+   Boolean 类型
+   Number 类型
    +   直接输入的（而不是从另一个变量访问的）任何数字都被看做 Number 类型的字面量
    +   八进制字面量的首数字必须是 0
    +   十六进制字面量的开头必须是 0x
    +   浮点值必须包括小数点和小数点后的一位数字，（用它进行计算前，真正存储的是字符串）
    +   所有整数都可以表示为八进制或十六进制的字面量，但所有数学运算返回的都是十进制结果
    +   Number.MAX_VALUE  Number.MIN_VALUE Number.POSITIVE_INFINITY  Number.NEGATIVE_INFINITY
    +   无穷大Infinity： isFinite() 
    +   非数NaN：isNaN()
+   String 类型

#   ECMAScript 类型转换
+   Boolean Number String的原始值是伪对象，实际上具有属性和方法。
+   parseInt() parseFloat() 把非数字的原始值转换成数字
    +   只能对 String 类型调用，对其他类型返回的都是 NaN
    +   首字符不是有效数字将返回 NaN，否则将首个非有效数字字符之前的字符串转换成数字
    +   parseInt() 
        +   对于整数来说，小数点是无效字符
        +   十进制数包含前导 0，那么最好采用基数 10，这样才不会意外地得到八进制的值
        
                var iNum1 = parseInt("0xA");    //返回 10
                var iNum1 = parseInt("A", 16);  //返回 10
                var iNum2 = parseInt("10", 8);  //返回 8
                var iNum1 = parseInt("010");    //返回 8
                var iNum3 = parseInt("010", 10);    //返回 10

    +   parseFloat()
        +   第一个出现的小数点是有效字符。如果有两个小数点，第二个小数点将被看作无效的
        +   字符串必须以十进制形式表示浮点数，而不是用八进制或十六进制。
        +   该方法会忽略前导 0，
        +   八进制数 0102 将被解析为 102。
        +   十六进制数 0xA，该方法将返回 NaN(具体的浏览器实现可能会返回 0)

+   强制类型转换
    +   Boolean(value) - 把给定的值转换成 Boolean 型；
        +   当要转换的值是至少有一个字符的字符串、非0数字或对象时，Boolean()函数将返回 true
    +   Number(value) - 把给定的值转换成数字（可以是整数或浮点数）；
        +   转换整个值： "1.2"-->1.2 , "1.2.3"-->NaN
        +   fasle-->0 , true-->1 , undefined-->NaN , null-->0 , new object()-->NaN
    +   String(value) - 把给定的值转换成字符串
        +   调用作为参数传递进来的值的 toString() 
        +   对 null 和 undefined 值强制类型转换可以生成字符串而不引发错误 
        +   String(null);   //"null"

#   ECMAScript 引用类型
+   引用类型通常叫做类（class），也就是说，遇到引用值，所处理的就是对象。
+   对象是由 new 运算符加上要实例化的对象的名字创建的
+   Object 对象
    +   ECMAScript 中的所有对象都由Object对象继承而来
    +   属性
    
            constructor
            对创建对象的函数的引用（指针）。对于 Object 对象，该指针指向原始的 Object() 函数。
            Prototype
            对该对象的对象原型的引用。对于所有的对象，它默认返回 Object 对象的一个实例。
            
    +   方法
            
            hasOwnProperty(property)
            判断对象是否有某个特定的属性。必须用字符串指定该属性。（例如，o.hasOwnProperty("name")）
            IsPrototypeOf(object)
            判断该对象是否为另一个对象的原型。
            PropertyIsEnumerable
            判断给定的属性是否可以用 for...in 语句进行枚举。
            ToString()
            返回对象的原始字符串表示。对于 Object 对象，ECMA-262 没有定义这个值，所以不同的 ECMAScript 实现具有不同的值。
            ValueOf()
            返回最适合该对象的原始值。对于许多对象，该方法返回的值都与 ToString() 的返回值相同。

+   Boolean 对象
    +   new Boolean(true);  Boolean 对象是 Boolean 原始类型的引用类型
    +   注意：强制转换Boolean(new Boolean(false)) 返回true；
    +   new Boolean(false) && true; //输出 true

+   Number 对象
    +   new Number(68);   Number 对象是 Number 原始类型的引用类型
    +   toFixed() 方法返回的是具有指定位数小数的数字的字符串表示 
    +   toExponential() 返回的是用科学计数法表示的数字的字符串形式。
    +   toPrecision() 方法根据最有意义的形式来返回数字的预定形式或指数形式，有一个参数用于表示数的数字总数（不包括指数）

+   String 对象
    +   new String("hello world");  String 对象是 String 原始类型的引用类型
    +   length 属性：字符串中的字符个数
    +	trim() 方法会删除一个字符串两端的空白字符。
    +   charAt() 方法返回包含指定位置处的字符的字符串
    +   charCodeAt()  返回指定位置处的字符代码
    +   concat() 方法，用于把一个或多个字符串连接到 String 对象的原始值上。该方法返回的是 String 原始值，保持原始的 String 对象不变：
    +   indexOf() 和 lastIndexOf() 方法返回的都是指定的子串在另一个字符串中的位置，如果没有找不到子串，则返回 -1。
    +   localeCompare() 
        
            如果 String 对象按照字母顺序排在参数中的字符串之前，返回负数。
            如果 String 对象等于参数中的字符串，返回 0
            如果 String 对象按照字母顺序排在参数中的字符串之后，返回正数。
    +   slice()  substring()
    
            返回的都是要处理的字符串的子串，不改变 String 对象自身的值。它们只返回原始的 String 值
            接受一个或两个参数。
            第一个参数是要获取的子串的起始位置
            第二个参数（如果使用的话）是要获取子串终止前的位置（也就是说，获取终止位置处的字符不包括在返回的值内）。
            如果省略第二个参数，终止位就默认为字符串的长度。
            对于负数参数，
                slice() 方法会用字符串的长度加上参数，
                substring() 方法则将其作为 0 处理（也就是说将忽略它）

    +   toLowerCase()、toLocaleLowerCase()、toUpperCase() 和 toLocaleUpperCase()
    
            toLowerCase() 和 toUpperCase() 方法是原始的，是以 java.lang.String 中相同方法为原型实现的。
            toLocaleLowerCase() 和 toLocaleUpperCase() 方法是基于特定的区域实现的（与 localeCompare() 方法相同）
            不过，有几种语言对 Unicode 大小写转换应用了特定的规则（例如土耳其语），因此必须使用区域特定的方法才能进行正确的转换
            一般来说，如果不知道在以哪种编码运行一种语言，则使用区域特定的方法比较安全。

+   instanceof 运算符
    +   typeof 运算符时采用引用类型存储值会出现一个问题，无论引用的是什么类型的对象，它都返回 "object"
    +   instanceof 方法要求开确地确认对象为某特定类型

#   ECMAScript 函数
+   声明：关键字 function、函数名、一组参数，以及置于括号中的待执行代码。
+   调用：名字加上括号中的参数
+   返回值
+   arguments对象，arguments[0]即第一个参数的值，arguments.length即函数调用时传入的参数个数
    +   ECMAScript 不验证传递给函数的参数个数是否等于函数定义的参数个数。开发者定义的函数都可以接受任意个数的参数
    +   任何遗漏的参数都会以 undefined 传递给函数，多余的函数将忽略。
    +   用 arguments 对象判断传递给函数的参数个数，即可模拟函数重载：
+   
+   函数实际上是功能完整的对象
    +   Function 类可以表示开发者定义的任何函数
    
            //用 Function 类直接创建函数的语法
            var function_name = new function(arg1, arg2, ..., argN, function_body)
            
            
            function sayHi(sName, sMessage) {
              alert("Hello " + sName + sMessage);
            }
            //即
            var sayHi = new Function("sName", "sMessage", "alert(\"Hello \" + sName + sMessage);");
            
            //函数覆盖
            function doAdd(iNum) {
              alert(iNum + 20);
            }
            function doAdd(iNum) {
              alert(iNum + 10);
            }
            doAdd(10);  //输出 "20"
            //即
            var doAdd = new Function("iNum", "alert(iNum + 20)");
            var doAdd = new Function("iNum", "alert(iNum + 10)");
            doAdd(10);
    
    +   属性 length 声明了函数期望的参数个数
    +   alueOf() 方法和 toString() 方法。这两个方法返回的都是函数的源代码        
+   
+   闭包（closure）
    +   闭包，指的是词法表示包括不被计算的变量的函数，也就是说，函数可以使用函数之外定义的变量。
    
            //使用全局变量
            var sMessage = "hello world";
            function sayHelloWorld() {
              alert(sMessage);
            }
            sayHelloWorld();
            
            //使用外部函数的参数 
            var iBaseNum = 10;
            function addNum(iNum1, iNum2) {
              function doAdd() {
                return iNum1 + iNum2 + iBaseNum;
              }
              return doAdd();
            }

#   ECMAScript 面向对象
+   object：属性的无序集合，每个属性存放一个原始值、对象或函数
+   对象由特性（attribute）构成，
    +   特性可以是原始值，也可以是引用值。
    +   如果特性存放的是函数，它将被看作对象的方法（method），否则该特性被看作对象的属性（property）
+   
+   创建：关键字 new 后面跟上实例化的类的名字
+   引用：不能访问对象的物理表示，只能访问对象的引用。每次创建对象，存储在变量中的都是该对象的引用，而不是对象本身。
+   废除：当再没有对对象的引用时，称该对象被废除（dereference）了
    +   每当函数执行完它的代码，无用存储单元收集程序都会运行，释放所有的局部变量
    +   把对象的所有引用都设置为 null，可以强制性地废除对象
+   绑定：把对象的接口与对象实例结合在一起的方法
    +   早绑定（early binding）是指在实例化对象之前定义它的属性和方法，这样编译器或解释程序就能够提前转换机器代码
    +   晚绑定（late binding）指的是编译器或解释程序在运行前，不知道对象的类型。使用晚绑定，无需检查对象的类型，只需检查对象是否支持属性和方法即可

##  ECMAScript 对象类型
+   本地对象、内置对象和宿主对象
+   本地对象（native object）：独立于宿主环境的 ECMAScript 实现提供的对象 
    +   简单来说，本地对象就是 ECMA-262 定义的类（引用类型）
    +   Object , Function , Array , String , Boolean , Number , Date , RegExp , Error , EvalError , RangeError , ReferenceError , SyntaxError , TypeError , URIError
+   内置对象（built-in object）: 由 ECMAScript 实现提供的、独立于宿主环境的所有对象，在 ECMAScript 程序开始执行时出现
    +   这意味着开发者不必明确实例化内置对象，它已被实例化了
    +   Global 和 Math （它们也是本地对象，根据定义，每个内置对象都是本地对象）
+   宿主对象
    +   所有非本地对象都是宿主对象（host object），即由 ECMAScript 实现的宿主环境提供的对象。
    +   所有 BOM 和 DOM 对象都是宿主对象。

##  作用域
+   ECMAScript 只有公用作用域
    +   规约：前后加下划线的属性和方法应该被看做私有的
+   ECMAScript 没有静态作用域
+   关键字 this
    +   在对象的方法中， this 总是指向调用该方法的对象

##  ECMAScript 定义类或对象
+   原始的方式：实例化Object，动态定义attr

        var oCar = new Object;
        oCar.color = "blue";
        oCar.doors = 4;
        oCar.mpg = 25;
        oCar.showColor = function() {
          alert(this.color);
        };

+   工厂方式

        function createCar() {
          var oTempCar = new Object;
          oTempCar.color = "blue";
          oTempCar.doors = 4;
          oTempCar.mpg = 25;
          //每次调用函数 createCar()，都要创建新函数 showColor()，意味着每个对象都有自己的 showColor() 版本。
          //而事实上，每个对象都共享同一个函数。
          oTempCar.showColor = function() {
            alert(this.color);
          };
          return oTempCar;
        }
        
        var oCar1 = createCar();
        var oCar2 = createCar();

+   在工厂函数外定义对象的方法

        function showColor() {
          alert(this.color);
        }
        
        function createCar(sColor,iDoors,iMpg) {
          var oTempCar = new Object;
          oTempCar.color = sColor;
          oTempCar.doors = iDoors;
          oTempCar.mpg = iMpg;
          oTempCar.showColor = showColor;
          return oTempCar;
        }
        
        var oCar1 = createCar("red",4,23);
        var oCar2 = createCar("blue",3,25);
        
        oCar1.showColor();      //输出 "red"
        oCar2.showColor();      //输出 "blue"

+   构造函数方式

        function Car(sColor,iDoors,iMpg) {
          this.color = sColor;
          this.doors = iDoors;
          this.mpg = iMpg;
          this.showColor = function() {
            alert(this.color);
          };
        }
        
        var oCar1 = new Car("red",4,23);
        var oCar2 = new Car("blue",3,25);
    +   首先在构造函数内没有创建对象，而是使用 this 关键字。
    +   使用 new 运算符构造函数时，在执行第一行代码前先创建一个对象，只有用 this 才能访问该对象。然后可以直接赋予 this 属性
    +   默认情况下this是构造函数的返回值（不必明确使用 return 运算符）。

+   原型方式

        function Car() {
        }
        
        //共享一切
        Car.prototype.color = "blue";
        Car.prototype.doors = 4;
        Car.prototype.mpg = 25;
        Car.prototype.showColor = function() {
          alert(this.color);
        };
        //对于引用值，不同的实例的drivers属性将引用到同一个Array对象
        Car.prototype.drivers = new Array("Mike","John");
        
        var oCar1 = new Car();
        var oCar2 = new Car();
 
 +  混合的构造函数/原型方式
    +   用构造函数定义对象的所有非函数属性，用原型方式定义对象的函数属性（方法）
 
 +
 
        function Car(sColor,iDoors,iMpg) {
          this.color = sColor;
          this.doors = iDoors;
          this.mpg = iMpg;
          this.drivers = new Array("Mike","John");
        }
        
        Car.prototype.showColor = function() {
          alert(this.color);
        };
        
        var oCar1 = new Car("red",4,23);
        var oCar2 = new Car("blue",3,25);
        
        oCar1.drivers.push("Bill");
        
        alert(oCar1.drivers);   //输出 "Mike,John,Bill"
        alert(oCar2.drivers);   //输出 "Mike,John"

+   动态原型方法

        function Car(sColor,iDoors,iMpg) {
          this.color = sColor;
          this.doors = iDoors;
          this.mpg = iMpg;
          this.drivers = new Array("Mike","John");
          
          if (typeof Car._initialized == "undefined") {
            Car.prototype.showColor = function() {
              alert(this.color);
            };
            
            Car._initialized = true;
          }
        }

#   ECMAScript 继承机制实现
+   对象冒充
    +   构造函数使用 this 关键字给所有属性和方法赋值（即采用类声明的构造函数方式）。
    +   因为构造函数只是一个函数，所以可使 ClassA 构造函数成为 ClassB 的方法，然后调用它。
    +   ClassB 就会收到 ClassA 的构造函数中定义的属性和方法
    
+
        function ClassA(sColor) {
            this.color = sColor;
            this.sayColor = function () {
                alert(this.color);
            };
        }
        
        function ClassB(sColor, sName) {
            this.newMethod = ClassA;
            this.newMethod(sColor);//不使用new，直接调用方法，ClassB.this--->ClassA.this
            delete this.newMethod;
        
            this.name = sName;
            this.sayName = function () {
                alert(this.name);
            };
        }
        
        var objA = new ClassA("blue");
        var objB = new ClassB("red", "John");
        objA.sayColor();    //输出 "blue"
        objB.sayColor();    //输出 "red"
        objB.sayName();     //输出 "John"
        
+   call() 方法
    +   Function 对象的方法，第一个参数obj用作 函数内 this 的对象。其他参数都直接传递给函数自身。

+
        function ClassB(sColor, sName) {
            //this.newMethod = ClassA;
            //this.newMethod(color);
            //delete this.newMethod;
            ClassA.call(this, sColor);
        
            this.name = sName;
            this.sayName = function () {
                alert(this.name);
            };
        }
    
+   apply() 方法
    +   Function 对象的方法，有两个参数，用作 this 的对象和要传递给函数的参数的数组    
    
+
        function ClassB(sColor, sName) {
            //this.newMethod = ClassA;
            //this.newMethod(color);
            //delete this.newMethod;
            ClassA.apply(this, new Array(sColor));
        
            this.name = sName;
            this.sayName = function () {
                alert(this.name);
            };
        }
    
    
+   原型链（prototype chaining）

        function ClassA() {
        }
        
        ClassA.prototype.color = "blue";
        ClassA.prototype.sayColor = function () {
            alert(this.color);
        };
        
        function ClassB() {
        }
        
        //把ClassA的全部prototype赋予ClassB
        ClassB.prototype = new ClassA();
        
        ClassB.prototype.name = "";
        ClassB.prototype.sayName = function () {
            alert(this.name);
        };

+   混合方式
    +   用对象冒充继承构造函数的属性，用原型链继承 prototype 对象的方法
    
+
        function ClassA(sColor) {
            this.color = sColor;
        }
        
        ClassA.prototype.sayColor = function () {
            alert(this.color);
        };
        
        function ClassB(sColor, sName) {
            //继承属性
            ClassA.call(this, sColor);
            this.name = sName;
        }
        
        //继承方法nb
        ClassB.prototype = new ClassA();
        
        ClassB.prototype.sayName = function () {
            alert(this.name);
        };


#   函数
+   函数就是包裹在花括号中的代码块，前面使用了关键词 function：

        function functionname(argument1,argument2)
        {
            //函数内部声明的变量(var)是局部变量，只能在函数内部访问
            //在函数外声明的变量是全局变量，网页上的所有脚本和函数都能访问
            ...
            return ;
        }


#   运算符
+   赋值运算符：= 
+   算术运算符：+，-，*，/，%，++，--          *
+   字符串的 + 运算符：把文本值或字符串变量加起来（连接起来）
+   比较运算符：==(等于)，===(全等 值和类型)，!=，>，<，>=，<=
+   逻辑运算符：&&，||，!
+   条件运算符：(true) ? value1 : value2



#   html dom
+   HTML DOM 树

        文档
        --根元素<html>
            --元素<head>
                --元素<title>
                    --内容
            --元素<body>
                --元素<a>
                    --内容
                    --属性：id,class,href,...

+   查找HTML元素
    +   通过 id 找到 HTML 元素：document.getElementById("aid");
    +   通过标签名找到 HTML 元素：getElementsByTagName("p");
    +   通过类名找到 HTML 元素
    
+   改变HTML元素
    +   document.write() 可用于直接向 HTML 输出流写内容
    +   document.createElement("p"); 创建元素
    +   element.appendChild(p);向已存在的元素追加新子元素
    +   parent.removeChild(child);删除已有的元素
    +   element.parentNode  父元素
    +   
    +   元素内容：element.innerHTML
    +   元素属性：element.attribute
    +   元素样式：element.style.property
    +   元素事件：element.event
        +   onload 和 onunload 事件会在用户进入或离开页面时被触发。
        +   onfocus  当输入字段获得焦点
        +   onchange 当用户改变输入字段的内容时被触发
        +   onmouseover和onmouseout事件可用于在用户的鼠标移至HTML元素上方或移出元素时触发
        +   onmousedown, onmouseup 以及 onclick 构成了鼠标点击事件的所有部分。
            +   点击鼠标按钮时，触发onmousedown，当释放鼠标按钮时，触 onmouseup，最后，当完成鼠标点击时，会触发onclick。
            
            
#   JavaScript Window - 浏览器对象模型
+   window  表示浏览器窗口
+   所有全局对象、函数以及变量均自动成为 window 对象的成员。
+   Window 尺寸：

        对于Internet Explorer、Chrome、Firefox、Opera 以及 Safari：
        window.innerHeight - 浏览器窗口的内部高度
        window.innerWidth - 浏览器窗口的内部宽度
        对于 Internet Explorer 8、7、6、5：
        document.documentElement.clientHeight
        document.documentElement.clientWidth
        或者
        document.body.clientHeight
        document.body.clientWidth
        
        
        实用的 JavaScript 方案（涵盖所有浏览器）：
        var w=window.innerWidth
        || document.documentElement.clientWidth
        || document.body.clientWidth;
        
        var h=window.innerHeight
        || document.documentElement.clientHeight
        || document.body.clientHeight;
        
+   window.open() - 打开新窗口
+   window.close() - 关闭当前窗口
+   window.moveTo() - 移动当前窗口
+   window.resizeTo() - 调整当前窗口的尺寸
+   
+   window.screen：用户屏幕
    +   screen.availWidth - 可用的屏幕宽度
    +   screen.availHeight - 可用的屏幕高度
+   window.location 对象用于获得当前页面的地址 (URL)，并把浏览器重定向到新的页面。
    +   location.hostname 返回 web 主机的域名
    +   location.pathname 返回当前页面的路径和文件名
    +   location.port 返回 web 主机的端口 （80 或 443）
    +   location.protocol 返回所使用的 web 协议（http:// 或 https://）
    +   location.href 属性返回当前页面的 URL。
    +   location.assign("url") 方法加载新的文档。
+   window.history 对象包含浏览器的历史。
    +   history.back() - 与在浏览器点击后退按钮相同
    +   history.forward() - 与在浏览器中点击按钮向前相同
+   window.navigator 对象包含有关访问者浏览器的信息。
+   消息框
    +   警告框 alert("文本")
    +   确认框 confirm("文本") 点击确定或者取消按钮后返回 true或false。
    +   提示框 prompt("文本","默认值") 用户需要输入某个值，然后点击确认或取消按钮才能继续操纵。确认将返回输入的值。取消将返回 null。
    
+   计时
    +   setInterval() - 间隔指定的毫秒数不停地执行指定的代码。
    +   setTimeout() - 暂停指定的毫秒数后执行指定的代码

+   
        var myI=setInterval(function(){alert("Hello")},3000);
        clearInterval(myI);
        
        var t=setTimeout("javascript语句",毫秒);
        clearTimeout(t)
    
+   cookie
    +   document.cookie
    
    
#   JSON：JavaScript 对象表示法（JavaScript Object Notation）






#   存储
+   localStorage - 没有时间限制的数据存储
+   sessionStorage - 针对一个 session 的数据存储，当用户关闭浏览器窗口后，数据会被删除

相同浏览器的不同页面间可以共享相同的localStorage（页面属于相同域名和端口），但是不同页面或标签页间无法共享sessionStorage的信息。

+   document.cookie - 自动添加到http请求





#   事件
+   元素事件：element.event
    +   onload 和 onunload 事件会在用户进入或离开页面时被触发。
    +   onfocus  当输入字段获得焦点
    +   onchange 当用户改变输入字段的内容时被触发
    +   onmouseover和onmouseout事件可用于在用户的鼠标移至HTML元素上方或移出元素时触发
    +   onmousedown, onmouseup 以及 onclick 构成了鼠标点击事件的所有部分。

window.onload=function(){
      //do something
}
//或者经常用到的图片
document.getElementById("imgID").onload=function(){
     //do something
}



$(document).ready(function(){
     //do something
})
})
})


#   自定义属性

    for (let i = 0; i < mails.length; i++) {
        ......
        "<li><a data-index='" + i + "' onclick='showMailDetail(this)'>"
        ......
    }
    
    
    function showMailDetail(a) {
        let mail=mails[a.dataset.index];
    }
    
    




