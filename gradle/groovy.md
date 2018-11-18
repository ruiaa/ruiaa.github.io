#   Groovy
+   Groovy是一门jvm语言，它最终是要编译成class文件然后在jvm上执行，所以Java语言的特性Groovy都支持
+   大量的语法糖以及闭包特性

#   语法
+   类型为动态推断，但仍是强类型的语言，类型不匹配会报错
+   通过 def 关键字来声明变量和方法

        def a = 1;
        def b = "hello world";
        def int c = 1;
        
        def hello() {
            println ("hello world");
            return 1;
        }

+   很多东西都是可以省略的：分号，类型，返回，方法调用的括号

        def a = 1
        def b = "hello world"
        def int c = 1
        
        def hello() {
            println "hello world" // 方法调用省略括号
            1;                    // 方法返回值省略return
        }
        def hello(String msg) {
            println (msg)
        }
        
        // 方法省略参数类型
        int hello(msg) {
            println (msg)
            return 1
        }
        
        // 方法省略参数类型
        int hello(msg) {
            println msg
            return 1 // 这个return不能省略
            println "done"
        }

+   字符串的拼接：${i}

        def a = 1
        def b = "hello"
        def c = "a=${a}, b=${b}"
        println c
        
        outputs:
        a=1, b=hello

+   闭包：类似函数指针
        
        {   parameters    ->  code    }
        
        //闭包可以有返回值和参数
        def closure = { int a, String b ->
            println "a=${a}, b=${b}, I am a closure!"
        }
        
        // 这里省略了闭包的参数类型
        def test = { a, b ->
            println "a=${a}, b=${b}, I am a closure!"
        }
        
        def ryg = { a, b ->
            a + b
        }
        
        closure(100, "renyugang")       //a=100, b=renyugang, I am a closure!
        test.call(100, 200)             //a=100, b=200, I am a closure!
        def c = ryg(100,200)            //
        println c                       //300

        
        //如果闭包不指定参数，那么它会有一个隐含的参数 it
        def test = {
            println "find ${it}, I am a closure!"
        }
        
        test(100)       // find 100, I am a closure! 


+   List Map


+   IO


+   Class是一等公民，所有的Class类型，都可以省略.class
        
        def func(Class clazz) {
        }
        
        func(File.class)
        func(File)

+   Getter/Setter和属性默认关联
        
        //两个类完全一致
        class Book {
            private String name
            String getName() { return name }
            void setName(String name) { this.name = name }
        }
        
        class Book {
            String name
        }

+   with操作符：对同一个对象进行操作
        
        Book bk = new Book()
        bk.id = 1
        bk.name = "android art"
        bk.press = "china press"
        
        可以简写为：
        Book bk = new Book() 
        bk.with {
            id = 1
            name = "android art"
            press = "china press"
        }

+   可用性判断

        if (name != null && name.length > 0) {}
        
        //可以替换为：
        if (name) {}


+   三元表达式

        def result = name != null ? name : "Unknown"
        
        // 省略了name
        def result = name ?: "Unknown"

+   非空判断：?

        if (order != null) {
            if (order.getCustomer() != null) {
                if (order.getCustomer().getAddress() != null) {
                System.out.println(order.getCustomer().getAddress());
                }
            }
        }
        
        //可以简写为：
        println order?.customer?.address

+   使用断言：使用assert来设置断言，当断言的条件为false时，程序将会抛出异常

        def check(String name) {
            // name non-null and non-empty according to Gro    ovy Truth
            assert name
            // safe navigation + Groovy Truth to check
            assert name?.size() > 3
        }

+   switch：多类型

        def x = 1.23
        def result = ""
        switch (x) {
            case "foo": result = "found foo"
            // lets fall through
            case "bar": result += "bar"
            case [4, 5, 6, 'inList']: result = "list"
            break
            case 12..30: result = "range"
            break
            case Integer: result = "integer"
            break
            case Number: result = "number"
            break
            case { it > 3 }: result = "number > 3"
            break
            default: result = "default"
        }
        assert result == "number"

+   ==相当于Java的equals，，如果需要比较对个对象是否是同一个，需要使用.is()

        Object a = new Object()
        Object b = a.clone()
        
        assert a == b
        assert !a.is(b)

#   编译、运行Groovy
+   Groovy sdk
+   gradle：
        
        //gradle yugangshuo
        
        task(yugangshuo).doLast {
            println "start execute yuangshuo"
            haveFun()
        }
        
        def haveFun() {
            println "have fun!"
            System.out.println("have fun!");
            1
            def file1 = new File("a.txt")
            def file2 = new File("a.txt")
            assert file1 == file2
            assert !file1.is(file2)
        }
        
        class Book {
            private String name
            String getName() { return name }
            void setName(String name) { this.name = name }
        }










