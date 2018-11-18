#   基本类型
+   数值
    
        Double	64
        Float	32
        Long	64
        Int	32
        Short	16
        Byte	8
        
        不存在隐式转换数值的精度
        字符在 Kotlin 中不是数值类型
        
+   字面值常量

        --数型: 123 
        --长整型要加大写 L : 123L 
        --16进制：0x0f 
        --二进制：0b00001011
        不支持８进制
        
        浮点数
        -- 默认 Doubles : 123.5 , 123.5e10 
        -- Floats 要添加 f 或 F ：123.5f
        
+   表示，装箱
    
    在 java 平台上，数值被 JVM 虚拟机以字节码的方式物理存储的，除非我们需要做可空标识(比如说 Int?) 或者涉及泛型。在后者中数值是装箱的。
    
    注意装箱过的数值是不保留特征的：
    
        val a: Int = 10000
        print (a === a ) //打印 'true'
        val boxedA: Int? =a
        val anotherBoxedA: Int? = a
        print (boxedA === anotherBoxedA ) //注意这里打印的是 'false'
        print(boxedA == anotherBoxedA) // Prints 'true'
        
+   显式转换

    短类型不是长类型的子类型，短类型是不会隐式转换为长类型的
    
        val a: Int? =1 //一个装箱过的 Int (java.lang.Integer)
        val b: Long? = a // 一个隐式装箱的 Long (java.lang.Long)
        print( a == b )// 很惊讶吧　这次打印出的是 'false' 这是由于 Long 类型的 equals() 只有和 Long 比较才会相同
        
        val b: Byte = 1 // OK, literals are checked statically
        val i: Int = b //ERROR
        val i: Int = b.toInt() // 显式转换
        
        每个数值类型都支持下面的转换：
        
        toByte(): Byte
        toShort(): Short
        toInt(): Int
        toLong(): Long
        toFloat(): Float
        toDouble(): Double
        toChar(): Char
        
+   运算符

+   字符 c.toInt()
    
    字符类型用 Char 表示。不能直接当做数值来使用
    
        fun check(c: Char) {
        	if (c == 1) { //ERROR: 类型不匹配
        		//...
        	}
        }
    字符是由单引号包裹的'1'，特殊的字符通过反斜杠\转义，下面的字符序列支持转义：\t,\b,\n,\r,',",\和$。编码任何其他字符，使用 Unicode 转义语法：\uFF00。
    
    我们可以将字符显示的转义为Int数字：
    
        fun decimalDigitValue(c: Char): Int {
        	if (c !in '0'..'9') 
        		throw IllegalArgumentException("Out of range")
        	return c.toInt() - '0'.toInt() //显示转换为数值类型
        }
    和数值类型一样，需要一个可空引用时，字符会被装箱。特性不会被装箱保留。
    
+   布尔值
    
    布尔值只有 true 或者 false
    
    布尔值的内建操作包括
    
        || – lazy disjunction 
        && – lazy conjunction
        
+   Array
    
    Arrays在 Kotlin 中由 Array 类表示，有 get 和 set 方法(通过运算符重载可以由[]调用)，以及 size 方法，以及一些常用的函数：
    
        class Array<T> private () {
        	fun size(): Int
        	fun get(index: Int): T
        	fun set(Index: Int, value: T): Unit
        	fun iterator(): Iterator<T>
        	//...
        }
        
        //传递每一项的值
        arrayOf(1, 2, 3)  -->  [1, 2, 3] 
        
        //指定Array大小并提供一个迭代器
        Array(5, {i -> (i * i).toString() })  -->  ["0", "1", "4", "9", "16"]
    
    注意：和 java 不一样，arrays 在 kotlin 中是不可变的。这意味这 kotlin 不允许我们把 Array<String> 转为 Array<Any> ,这样就阻止了可能的运行时错误(但你可以使用 Array<outAny> , 参看 Type Projections)
    
    Kotlin 有专门的类来表示原始类型从而避免过度装箱： ByteArray, ShortArray, IntArray 等等。这些类与 Array 没有继承关系，但它们有一样的方法与属性。每个都有对应的库函数：
    
    val x: IntArray = intArrayOf(1, 2, 3)
    x[0] = x[1] + x[2]


+   字符串
    
    字符串是由 String 表示的。字符串是不变的。字符串的元素可以通过索引操作读取: s[i] 。字符串可以用 for 循环迭代：
    
        for (c in str) {
        	println(c)
        }
    Kotlin 有两种类型的 string ：一种是可以带分割符的，很像 java 的 string:
    
        val s = "Hello World!\n"
    一种是可以包含新行以及任意文本的，整行String 是由三个引号包裹的("""),不可以包含分割符但可以包含其它字符：
    
        val text = """
        	for (c in "foo")
        		print(c)
        """
        
    +   模板表达式 ${i}
        
        字符串可以包含模板表达式。一个模板表达式由一个 $ 开始并包含另一个简单的名称：
        
            val i = 10
            val s = "i = $i" // 识别为 "i = 10"
        或者是一个带大括号的表达式：
        
            val s = "abc"
            val str = "$s.length is ${s.length}" //识别为 "abc.length is 3"

#   变量
+   声明常量：
    
        fun main(args: Array<String>) {
          val a: Int = 1  // 立即初始化
          val b = 2   // 推导出Int型
          val c: Int  // 当没有初始化值时必须声明类型
          c = 3       // 赋值
          println("a = $a, b = $b, c = $c")
        }
    
+   变量：

        fun main(args: Array<String>) {
          var x = 5 // 推导出Int类型
          x += 1
          println("x = $x")
        }


+   使用可空变量以及空值检查

        fun parseInt(str: String): Int? {
          return str.toIntOrNull()
        }
        
        fun printProduct(arg1: String, arg2: String) {
          val x = parseInt(arg1)
          val y = parseInt(arg2)
        
          // 直接使用 x*y 会产生错误因为它们中有可能会有空值
          if (x != null && y != null) {
            // x 和 y 将会在空值检测后自动转换为非空值
            println(x * y)
          }
          else {
            println("either '$arg1' or '$arg2' is not a number")
          }    
        }

+   使用is 进行类型检查并自动转换

        fun getStringLength(obj: Any): Int? {
          if (obj is String) {
            // obj 将会在这个分支中自动转换为 String 类型
            return obj.length
          }
        
          // obj 在种类检查外仍然是 Any 类型
          return null
        }


#   包
+   一个源文件以包声明开始：
    
        package foo.bar
        
        fun bza() {}
        
        class Goo {}

+   import

        import foo.Bar              //Bar 现在可以不用条件就可以使用
        
        import foo.*                //foo 中的所有都可以使用
        
        import foo.Bar              // Bar 可以使用
        import bar.Bar as bBar      // bBar 代表 'bar.Bar'
        
        
#   流程控制
+   if : 表达式 , 返回最后执行表达式的值

        //传统用法
        var max = a
        if (a < b)
        	max = b
        
        //带 else 
        var max: Int
        if (a > b)
        	max = a
        else
        	max = b
        
        //作为表达式
        val max = if (a > b) a else b
        
        val max = if (a > b){
        	print("Choose a")
        	a
        }
        else{
        	print("Choose b")
        	b
        }
        
        //如果 if 表达式只有一个分支，或者分支的结果是 Unit , 它的值就是 Unit 。

+   when
    when会对所有的分支进行检查直到有一个条件满足。
    
    在其它分支都不匹配的时候默认匹配 else 分支。
    
    when 可以用做表达式或声明。如果用作表达式的话，那么满足条件的分支就是总表达式
    
    如果把 when 做为表达式的话 else 分支是强制的
    
        when (x) {
        	1 -> print("x == 1")
        	2 -> print("x == 2")
        	else -> { //Note the block
        		print("x is neither 1 nor 2")
        	}
        }
        
        //分支条件可以连在一起
        when (x) {
        	0,1 -> print("x == 0 or x == 1")
        	else -> print("otherwise")
        }
        
        //可以用任意表达式作为分支的条件
        when (x) {
        	parseInt(s) -> print("s encode x")
        	else -> print("s does not encode x")
        }
        
        //可以用 in 或者 !in 检查值是否值在一个集合中
        when (x) {
        	in 1..10 -> print("x is in the range")
        	in validNumbers -> print("x is valid")
        	!in 10..20 -> print("x is outside the range")
        	else -> print("none of the above")
        }
        
        //可以用 is 或者 !is 来判断值是否是某个类型
        val hasPrefix = when (x) {
        	is String -> x.startsWith("prefix")
        	else -> false
        }
        
        //代替 if-else if
        //如果没有任何参数提供，那么分支的条件就是简单的布尔表达式，当条件为真时执行相应的分支
        when {
        	x.isOdd() -> print("x is odd")
        	x.isEven() -> print("x is even")
        	else -> print("x is funny")
        }

+   for
    for 循环通过任何提供的迭代器进行迭代。

        for (item in collection)
        	print(item)
        
        for (item: Int in ints){
        	//...
        }

        //通过 list 或者 array 的索引进行迭代，你可以这样做：
        for (i in array.indices)
        	print(array[i])

+   while 和 do...while

        while (x > 0) {
        	x--
        }
        
        do {
        	val y = retrieveData()
        } while (y != null) // y 在这是可见的
        
+    break 和 continue


#   返回与跳转






