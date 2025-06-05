package com.yang.example.kotlinpractice._5_functions


/**
 * 高阶函数和 lambda
 *   lambda 表达式语法缺少一个东西——指定函数的返回类型的能力。在大多数情况下， 这是不必要的。因为返回类型可以自动推断出来。
 *   然而，如果确实需要显式指定， 可以使用另一种语法： 匿名函数
 *
 * 匿名函数
 *  匿名函数，明确的指定了返回值类型和参数类型
 *      当可以推导出参数类型和返回值类的时候，可以省略
 *      当函数体中不适用参数时，参数名可以用 _ 代替
 *      匿名函数的返回类型推断机制与正常函数一样：对于具有表达式函数体的匿名函数将自动推断返回类型，但具有代码块函数体的返回类型必须显式指定
 *
 * Lambda表达式与匿名函数之间的另一个区别是非局部返回的行为。
 *  一个不带标签的 return 语句总是在用 fun 关键字声明的函数中返回。这意味着 lambda 表达式中的 return 将从包含它的函数返回，
 *  而匿名函数中的 return 将从匿名函数自身返回。
 *
 * 闭包
 *  Lambda 表达式或者匿名函数（以及局部函数和对象表达式） 可以访问其闭包 ，其中包含在外部作用域中声明的变量。
 *
 * 带有接收者的函数字面值(lambda, 匿名函数)
 *      带有接收者的函数类型，例如 A.(B) -> C，可以用特殊形式的函数字面值实例化—— 带有接收者的函数字面值。
 *      val sum: Int.(Int) -> Int = { other -> plus(other) }
 *
 *      在这样的函数字面值内部，传给调用的接收者对象成为隐式的this，以便访问接收者对象的成员而无需任何额外的限定符，
 *      亦可使用 this 表达式 访问接收者对象。
 *
 *      扩展函数也允许在函数体内部访问接收者对象的成员。
 *
 *      匿名函数语法允许你直接指定函数字面值的接收者类型
 *
 *
 *
 */
fun main() {
    val nonFun01: (Int, Int) -> Int = fun(_, _): Int = 4
    val nonFun0 = fun(x: Int, y: Int): Int = x + y
    val nonFun1: (Int, Int) -> Int = fun(x, y) = x + y
    val nonFun2 = fun(x: Int, y: Int): Int {
        return x + y;
    }
    val nonFun3: (Int, Int) -> Int = fun(x, y): Int {
        return x + y;
    }
    val nonFun4: (Int, Int) -> Int = fun(_, _): Int {
        return 4;
    }
    println(nonFun1(2, 3))

    // 带有接收者的 lambda
    val sum: Int.(Int) -> Int = { other -> plus(other) }
    // 带有接收者的匿名函数
    val sum2 = fun Int.(other: Int): Int = this + other

    html {       // 带接收者的 lambda 由此开始
        body()   // 调用该接收者对象的一个方法
    }

}

class HTML {
    fun body() {
        println("body() invoked")
    }
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()  // 创建接收者对象
    html.init()        // 将该接收者对象传给该 lambda
    // 等同于 init(html)
    // init.invoke(html)
    return html
}


