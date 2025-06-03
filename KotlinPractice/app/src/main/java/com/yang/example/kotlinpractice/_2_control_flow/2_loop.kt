package com.yang.example.kotlinpractice._2_control_flow

/**
 * for 循环可以对任何提供迭代器（iterator）的对象进行遍历，
 *      使用关键字 for ( .. in .. ) {}    for (item in collection)
 *      使用扩张函数 foreach {}
 *
 * while 和 do-while 与 Java 中的用法相同
 */
fun main() {
    val arrayOf = arrayOf(1, 2, 3, 4, 5)
    for (item in arrayOf) {
        println(item)
    }

    arrayOf.forEach {
        println(it)
    }

}