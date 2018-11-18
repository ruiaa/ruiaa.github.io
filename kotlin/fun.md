#   定义
    fun sum(a: Int , b: Int) : Int{
    	return a + b
    }

    //只有一个表达式函数体以及一个自推导型的返回值
    fun sum(a: Int, b: Int) = a + b

    //返回一个没有意义的值(Unit 的返回类型可以省略)：
    fun printSum(a: Int, b: Int): Unit {
      println("sum of $a and $b is ${a + b}")
    }
    fun printSum(a: Int, b: Int) {
      println("sum of $a and $b is ${a + b}")
    }
