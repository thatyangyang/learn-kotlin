package com.yang.example.kotlinpractice._5_functions


/**
 * 高阶函数和 lambda
 *
 *  Kotlin 函数都是头等的，这意味着它们可以存储在变量与数据结构中，并可以作为参数传给其他高阶函数以及从其他高阶函数返回。
 *  可以像操作任何其他非函数值一样对函数进行操作。
 *
 *  高阶函数
 *  高阶函数是将函数用作参数或返回值的函数。
 *
 *  函数类型
 *      所有函数类型都有一个圆括号括起来的参数类型列表以及一个返回类型：
 *      (A, B) -> C
 *
 *      函数类型可以有一个额外的接收者类型，它在表示法中的点之前指定： 类型
 *      A.(B) -> C
 *
 *      挂起函数属于函数类型的特殊种类，它的表示法中有一个 suspend 修饰符 ，例如
 *      suspend () -> Unit 或者 suspend A.(B) -> C
 *
 *      可以选择性地包含函数的参数名：
 *      (x: Int, y: Int) -> Point
 *
 *      如需将函数类型指定为可空，请使用圆括号，如下所示：
 *      ((Int, Int) -> Int)?
 *
 *      还可以通过使用类型别名给函数类型起一个别称：
 *      typealias ClickHandler = (Button, ClickEvent) -> Unit
 *
 *
 * 函数类型实例化
 *      使用函数字面值的代码块 ———— 没有函数名的函数实现
 *          lambda 表达式: { a, b -> a + b }   可以加参数类型，禁止用 return, 函数体部分禁止用 {}
 *          匿名函数: fun(s: String): Int { return s.toIntOrNull() ?: 0 }
 *
 *      使用已有声明的可调用引用
 *          顶层、局部、成员、扩展函数：::isOdd、 String::toInt
 *          顶层、成员、扩展属性：List::size
 *          构造函数：::Regex
 *              这包括指向特定实例成员的绑定的可调用引用：foo::toString
 *
 *      使用实现函数类型接口的自定义类的实例
 *          接口方法名 invoke()
 *
 *      带与不带接收者的函数类型非字面值可以互换，其中接收者可以替代第一个参数，反之亦然。例如，
 *      (A, B) -> C 类型的值可以传给或赋值给期待 A.(B) -> C 类型值的地方
 *
 *      函数类型的实例，必须用赋值给变量，或者传递给高阶函数（有一个变量接收），才能使用；字面值不能直接使用
 *
 *  函数类型实例调用    这一章都是匿名函数,都是使用函数实例的名称  foo 为实例的名称
 *      foo.invoke(x) 或者直接 foo(x)
 *
 *      带接收者的类型: 1. invoke  接收者作为第一个参数
 *                   2. 1.foo(2)  接收者对象调用函数
 *
 */
fun main() {
    val items = listOf(1, 2, 3, 4, 5)

    items.fold(0) { acc: Int, i: Int ->
        print("acc = $acc, i = $i, ")
        val result = acc + i
        println("result = $result")
        // lambda 表达式中的最后一个表达式是返回值：
        result
    }

    val joinedToString = items.fold("Elements: ") { acc, i -> "$acc $i" }
    val product = items.fold(1, Int::times)
    println(joinedToString)
    println(product)

    val repeatFun: String.(Int) -> String = { times -> this.repeat(times) }
    val twoParams: (String, Int) -> String = repeatFun
    val two: (String, Int) -> String = { str, times -> str.repeat(times) }
    fun runTransformation(f: (String, Int) -> String): String {
        return f("hello", 3)
    }

    val result = runTransformation(repeatFun)
    println("result = $result")

    val a = { i: Int -> { i + 1 } } // (Int) -> () -> Int

    val stringPlus: (String, String) -> String = String::plus
    val intPlus: Int.(Int) -> Int = Int::plus

    println(stringPlus.invoke("<-", "->"))
    println(stringPlus.invoke("Hello, ", "world!"))
    println(intPlus.invoke(1, 1))
    println(intPlus(1, 2))
    println(2.intPlus(3))

    println(IntTransformer()(4))
    val transformer = IntTransformer()
    println(transformer(5))
    println(transformer.invoke(6))
    println(intFunction(5))
    println(intFunction.invoke(6))

    // 函数字面值 作为 高阶函数的实参， 这样有一个变量接收
    val func2 = fun(s: (Int) -> Int, a: Int): Int { return s.invoke(a) }
    val res = func2({ a: Int -> a + 100 }, 10)
    println(res)

}


// 实现函数类型接口的自定义类
class IntTransformer : (Int) -> Int {
    override fun invoke(p1: Int): Int {
        return p1
    }

}

val intFunction: (Int) -> Int = IntTransformer()

val a = { i: Int -> i + 1 }

