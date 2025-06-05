package com.yang.example.kotlinpractice._5_functions


/**
 * 高阶函数和 lambda
 *
 * Lambda 表达式与匿名函数
 *  lambda 表达式与匿名函数是函数字面值，函数字面值即没有声明而是立即做为表达式传递的函数。
 *
 * Lambda 表达式语法
 *  变量 (: 函数类型) = { x: Int, y: Int -> x + y }
 *  val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
 *  val sum2:(Int, Int) -> Int = { x: Int, y: Int -> x + y; x + y }
 *  1. 必须在花括号内
 *  2. 参数部分不加圆括号，使用"," 分隔; 如果没有参数，可以箭头左侧为空，也可省略箭头
 *      如果可以推导出函数签名，则参数类型可以省略
 *  3. 函数体部分在 -> 后，
 *      可以有多个语句，使用 ";" 分隔
 *      不能有 return, 默认返回最后一个表达式的值，如果函数的实例的类型部分的返回值为 Unit，则不返回
 *          return@filter shouldFilter 除了带返回标签的情况
 *  4. lambda 表达式的函数类型的决定，考虑：
 *      1. 等号左侧写明的类型， 2. 动推到的类型
 *      优先1，如果没有写1则2；
 *      所以 lambda 表达式内部（即函数体部分）也可以有{}, 会默认推导为高阶函数；如果左侧1不是高阶函数，则看着函数体整体部分；
 *
 * 传递末尾的 lambda 表达式
 *      如果函数的最后一个参数是函数，那么作为相应参数传入的 lambda 表达式可以放在圆括号之外
 *      如果该 lambda 表达式是调用时唯一的参数，那么圆括号可以完全省略
 *
 * it：单个参数的隐式名称
 *      如果编译器可以推断出函数签名（函数类型）只有一个参数，则可以省略 参数和-> ， 在函数体部分使用 it 代表这个参数
 *      推断的条件，就是变量右边的函数类型，或高阶函数的函数参数的类型
 *
 * 从 lambda 表达式中返回一个值
 *      return@filter shouldFilter  =   shouldFilter
 *
 * 下划线用于未使用的变量
 *  如果 lambda 表达式的参数未使用，那么可以用下划线取代其名称
 *      map.forEach { (_, value) -> println("$value!") }
 *      map.forEach { _, value -> println("$value!") }
 *      val ampfun: (Int, Int) -> Unit = { _: Int, value: Int -> println("$value!") }
 *      val ampfun2: (Int, Int) -> Unit = { _, value -> println("$value!") }
 *
 *      参数列表的圆括号，目前只见到可以在 高阶函数的实参处使用，在lambda 实例中无法使用
 *
 * 在 lambda 表达式中解构
 *
 *
 */
fun main() {
    val sum_2 = { { 1;2 } }
    val sum_1 = { 1;2 }
    val sum0 = { -> 1 }
    val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
    val sum2: (Int, Int) -> Int = { x: Int, y: Int -> x + y; x + y; }
    val sum3: (Int, Int) -> Unit = { x: Int, y: Int -> x + y; x + y; }
    val sum4: (Int, Int) -> Unit = { x: Int, y: Int -> { x + y; x + y } }
    val sum5 = { x: Int, y: Int -> { x: Int, y: Int -> x + y; x + y } }
    val sum6 = { x: Int, y: Int -> { x + y; x + y } } // 访问外部的变量
    println(sum2(1, 1))  // 2
    println(sum3(1, 1))  // kotlin.Unit
    println(sum4(1, 1))
    println(sum0())

    // val sum7: (Int, Int) -> (() -> Int) = { x: Int, y: Int -> 1 } // 访问外部的变量

//    { x: Int, y: Int -> x + y; x + y }
    // 1. 通过函数实例的类型推导函数签名
    val sum11: (Int) -> Int = { it }

    // 1. 通过高阶函数的函数参数的类型推导函数签名
    fun hyperFun(a: (Int) -> Int, b: Int): Int {
        return a.invoke(b)
    }

    val result = hyperFun({ it + 100 }, 10)
    println(result)

    // 默认返回到调用者——高阶函数
    val ints = listOf(23, 12, -1, 0, 300)
    ints.filter {
        val shouldFilter = it > 0
        shouldFilter
    }.apply { println(this.joinToString()) }

    ints.filter {
        val shouldFilter = it > 0
        return@filter shouldFilter
    }.apply { println(this.joinToString()) }

    val map = mapOf(
        "1" to 1,
        "2" to 2
    )
    map.forEach { _, value -> println("$value!") }
    map.forEach { (_, value) -> println("$value!") }

    val ampfun: (Int, Int) -> Unit = { _: Int, value: Int -> println("$value!") }
    val ampfun2: (Int, Int) -> Unit = { _, value -> println("$value!") }

}
