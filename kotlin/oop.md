#   类
+   类名 类头 类主体
+   构造函数：一个主构造函数，多个二级构造函数
    
        主构造函数不能包含任意代码。初始化代码可以放在以 init 做前缀的初始化块内
        class Customer constructor(name: String) {
        	init {
        		logger,info("Customer initialized with value ${name}")
        	}
        }
        
        主构造函数的参数可以用在初始化块内，也可以用在类的属性初始化声明处：
        class Customer(name: String) {
        	val customerKry = name.toUpperCase()
        }
        class Person(val firstName: String, val lastName: String, var age: Int) {
        }
        
        
        //二级构造函数，需要加前缀 constructor:
        class Person {
        	constructor(parent: Person) {
        		parent.children.add(this)
        	}
        }
        
        //如果类有主构造函数，每个二级构造函数都要，或直接或间接通过另一个二级构造函数代理主构造函数。在同一个类中代理另一个构造函数使用 this 关键字：
        class Person(val name: String) {
        	constructor (name: String, paret: Person) : this(name) {
        		parent.children.add(this)
        	}
        }

+   创建实例
    
        val customer = Customer("Joe Smith")

+   属性和字段
    +   var 关键字声明可变属性， val 关键字声明只读属性
    +   Getters 和 Setters
    
            var <propertyName>: <PropertyType> [ = <property_initializer> ]
            	<getter>
            	<setter>

+   抽象类

        open class Base {
        	open fun f() {}
        }
        
        abstract class Derived : Base() {
        	override abstract fun f()
        }

+   密封类

        interface MyInterface {
        	fun bar()
        	fun foo() {
        		//函数体是可选的
        	}
        }
        
        //实现
        class Child : MyInterface {
        	fun bar () {
        		//函数体
        	}
        }


+   数据类
    
        data class User(val name: String, val age: Int)
        
        编译器会自动根据主构造函数中声明的所有属性添加如下方法：
        equals()/hashCode 函数
        toString 
            "User(name=john, age=42)"
        [compontN()functions] (http://kotlinlang.org/docs/reference/multi-declarations.html) 对应按声明顺序出现的所有属性
        copy() 函数
            fun copy(name: String = this.name, age: Int = this.age) = User(name, age)


+   嵌套类

        class Outer {
        	private val bar: Int = 1
        	class Nested {
        		fun foo() = 2
        	}
        }
        
        val demo = Outer.Nested().foo() //==2
        
+   内部类 ：嵌套类标记为inner就可以访问外部类的成员，内部类拥有外部类的一个对象引用

        class Outer {
        	private val bar: Int = 1
        	inner class Inner {
        		fun foo() = bar
        	}
        }
        
        val demo = Outer().Inner().foo() //==1

+   匿名内部类 : 通过 对象表达式 创建

        window.addMouseListener(object: MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) {
                // ...
            }
                                                                                                                    
            override fun mouseEntered(e: MouseEvent) {
                // ...
            }
        }
        
        //如果对象是函数式的 java 接口的实例（比如只有一个抽象方法的 java 接口），你可以用一个带接口类型的 lambda 表达式创建它。
        val listener = ActionListener { println("clicked") }

+   对象表达式
    
        父类有构造函数，则必须传递相应的构造参数。多个父类可以用逗号隔开，跟在冒号后面：
        
        open class A(x: Int) {
        	public open val y: Int = x
        }
        
        interface B { ... }
        
        val ab = object : A(1), B {
        	override val y = 14
        }
        有时候我们只是需要一个没有父类的对象，我们可以这样写：
        
        val adHoc = object {
        	var x: Int = 0
        	var y: Int = 0
        }

+   对象声明

        object DataProviderManager {
            fun registerDataProvider(provider: DataProvider) {
                // ...
            }
        
            val allDataProviders: Collection<DataProvider>
                get() = // ...
        }
        这叫做对象声明，跟在 object 关键字后面是对象名。和变量声明一样，对象声明并不是表达式，而且不能作为右值用在赋值语句。
        
        想要访问这个类，直接通过名字来使用这个类：
        
        DataProviderManager.registerDataProvider(...)


+   伴随对象

        class MyClass {
        	companion object Factory {
        		fun create(): MyClass = MyClass()
        	}
        }
        伴随对象的成员可以通过类名做限定词直接使用：
        
        val instance = MyClass.create()
        在使用了 companion 关键字时，伴随对象的名字可以省略：
        
        class MyClass {
        	companion object {
        
        	}
        }


+   代理

        interface Base {
        	fun print()
        }
        
        class BaseImpl(val x: Int) : Base {
        	override fun print() { printz(x) }
        }
        
        class Derived(b: Base) : Base by b
        
        fun main() {
        	val b = BaseImpl(10)
        	Derived(b).print()
        }
        在 Derived 的父类列表中的 by 从句会将 b 存储在 Derived 内部对象，并且编译器会生成 Base 的所有方法并转给 b。


















#   继承
    Kotlin 中所有的类都有共同的父类 Any
    
    open class Base(p: Int)
    
    class Derived(p: Int) : Base(p)
    如果类有主构造函数，则基类可以而且是必须在主构造函数中使用参数立即初始化。
    
    如果类没有主构造函数，则必须在每一个构造函数中用 super 关键字初始化基类，或者在代理另一个构造函数做这件事。注意在这种情形中不同的二级构造函数可以调用基类不同的构造方法：
    
    class MyView : View {
    	constructor(ctx: Context) : super(ctx) {
    	}
    	constructor(ctx: Context, attrs: AttributeSet) : super(ctx,attrs) {
    	}
    }
 
 +  复写方法
 
        open class Base {
        	open fun v() {}
        	fun nv() {}
        }
        
        class Derived() : Base() {
        	override fun v() {}
        }
        
        //标记为override的成员是open的，它可以在子类中被复写。如果你不想被重写就要加 final:
        open class AnotherDerived() : Base() {
        	final override fun v() {}
        }

+   复写属性
    
    复写属性与复写方法类似，在一个父类上声明的属性在子类上被重新声明，必须添加override，并且它们必须具有兼容的类型。每个被声明的属性都可以被一个带有初始化器的属性或带有getter方法的属性覆盖
    
        open class Foo {
          open val x: Int get { ... }
        }
        
        class Bar1 : Foo() {
          override val x: Int = ...
        }
    您还可以使用var属性覆盖一个val属性，但反之则不允许。这是允许的，因为val属性本质上声明了一个getter方法，并将其重写为var，另外在派生类中声明了setter方法。
    
    注意，可以在主构造函数中使用override关键字作为属性声明的一部分。
    
        interface Foo {
            val count: Int
        }
        
        class Bar1(override val count: Int) : Foo
        
        class Bar2 : Foo {
            override var count: Int = 0
        }

+   复写规则

    如果一个类从它的直接父类继承了同一个成员的多个实现，那么它必须复写这个成员并且提供自己的实现(或许只是直接用了继承来的实现)。为表示使用父类中提供的方法我们用 super<Base>表示:
    
        open class A {
        	open fun f () { print("A") }
        	fun a() { print("a") }
        }
        
        interface B {
        	fun f() { print("B") } // 接口的成员变量默认是 open 的
        	fun b() { print("b") }
        }
        
        class C() : A() , B {
        	// 编译器会要求复写f()
        	override fun f() {
        		super<A>.f() // 调用 A.f()
        		super<B>.f() // 调用 B.f()
        	}
        }


#   泛型
    class Box<T>(t: T){
        var value = t
    }
    val box: Box<Int> = Box<Int>(1)
    val box = Box(1)//1是 Int 型，推导出调用的是 Box<Int>
    
+   声明变型和类型投影，代替通配符类型
+   声明变型：out(仅输出父类) in(仅输入子类)

        //类型参数 T 被声明为 out 时，只能出现在输出位置
        abstract class Source<out T> {
            abstract fun nextT(): T
        }
        fun demo(strs: Source<String>) {
            val objects: Source<Any> = strs // This is OK, since T is an out-parameter
            // ...
        }
        
        //类型参数 T 被声明为 in 时，只能出现在输入位置
        abstract class Comparable<in T> {
            abstract fun compareTo(other: T): Int
        }
        fun demo(x: Comparable<Number>) {
            x.compareTo(1.0) // 1.0 has type Double, which is a subtype of Number
            // Thus, we can assign x to a variable of type Comparable<Double>
            val y: Comparable<Double> = x // OK!
        }

+   类型投影：调用处变型

        fun copy(from: Array<out Any>, to: Array<Any>) {
            //from不是一个简单的 array， 而是一个投影，只能调用返回类型参数 T 的方法
            assert(from.size == to.size)
            for (i in from.indices)
                to[i] = from[i]
        }
        
        fun fill(dest: Array<in String>, value: String) {
            //  Array<in String> 对应 Java 中的 Array<? super String>
        }

+   范型函数

        fun <T> singletonList(item: T): List<T> {
            // ...
        }
        
        fun <T> T.basicToString() : String {  // extension function
            // ...
        }
    
        val l = singletonList<Int>(1)

+   范型约束
    
    +   上界(upper bound) : 在 Java 中对应 extends关键字
    
            fun <T : Comparable<T>> sort(list: List<T>) {
                // ...
            }
            
            sort(listOf(1, 2, 3)) // OK. Int is a subtype of Comparable<Int>
            sort(listOf(HashMap<Int, String>())) // Error: HashMap<Int, String> is not a subtype of Comparable<HashMap<Int, String>>


















