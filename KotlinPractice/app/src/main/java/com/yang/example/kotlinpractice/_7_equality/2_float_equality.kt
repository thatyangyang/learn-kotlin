package com.yang.example.kotlinpractice._7_equality

/**
 * NaN 非数字, 是计算机科学中数值数据类型的一类值，表示未定义或不可表示的值。常在浮点数运算中使用。
 *  不是指数值类型以外的类型，而是数值类型中的无法表示的指, 如负数的平方根
 */
fun main() {
    //sampleStart
    // 静态类型作为浮点数的操作数
    println(Double.NaN == Double.NaN)                 // false

    // 静态类型并非作为浮点数的操作数
    // 所以 NaN 等于它本身
    println(listOf(Double.NaN) == listOf(Double.NaN)) // true

    // 静态类型作为浮点数的操作数
    println(0.0 == -0.0)                              // true

    // 静态类型并非作为浮点数的操作数
    // 所以 -0.0 小于 0.0
    println(listOf(0.0) == listOf(-0.0))              // false

    println(listOf(Double.NaN, Double.POSITIVE_INFINITY, 0.0, -0.0).sorted())
    // [-0.0, 0.0, Infinity, NaN]
    //sampleEnd
}