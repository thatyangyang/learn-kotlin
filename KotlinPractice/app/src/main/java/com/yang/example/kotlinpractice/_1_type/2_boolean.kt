package com.yang.example.kotlinpractice._1_type

/**
 * 在 JVM 平台，布尔对象的可空引用是装箱的 Java 类，类似于数字。
 */
fun main() {
    val myTrue: Boolean = true
    val myFalse: Boolean = false
    val boolNull: Boolean? = null

    println(myTrue || myFalse)
    // true
    println(myTrue && myFalse)
    // false
    println(!myTrue)
    // false
    println(boolNull)
    // null

    // myTrue && boolNull // 类型不匹配
    // boolNull && myFalse
}