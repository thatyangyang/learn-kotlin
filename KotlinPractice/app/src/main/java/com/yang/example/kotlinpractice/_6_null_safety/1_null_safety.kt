package com.yang.example.kotlinpractice._6_null_safety

/**

 *  a?.equals(b) 如果 a 为空, 则表达式的值为 null 而不是true(即使 b 为空 )/false
 */


// 类型智能判断, 非空接收者

fun main() {


    var a: String? = null
    var b: String? = "abc"
    println("a?.equals(b) ${a?.equals(b)}")  // 结果为 null
}