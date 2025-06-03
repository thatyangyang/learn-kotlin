package com.yang.example.kotlinpractice._6_null_safety

/**
 * 为了避免空指针, 分为 非空类型 和 可空类型, 以及
 *  1. 初始化时必须赋值
 *  2. 不能给非空类型赋值为 null 或 可空类型
 *  3. 使用可空类型时
 *      非空判断      --   调用方法
 *      非空断言      --   赋值给非空类型的变量
 *  4. 否则要做类型转换为非空类型
 *
 *
 *
 *  a?.equals(b) 如果 a 为空, 则表达式的值为 null 而不是true(即使 b 为空 )/false
 */


// 类型智能判断, 非空接收者

fun main() {
    var a: String? = null
    var b: String? = "abc"
    println("a?.equals(b) ${a?.equals(b)}")  // 结果为 null

    var aa: String = "aa"
    var bb: String? = "bb"
    aa = bb!!

    println(aa)
}

class User(name: String, gender: String?) {

}