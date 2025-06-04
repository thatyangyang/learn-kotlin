package com.yang.example.kotlinpractice._4_classes_objects


/**
 * Kotlin 能够对一个类或接口扩展新功能而无需继承该类或者使用像装饰者这样的设计模式
 *
 * 扩张函数
 *      接收者，可空
 *      函数名前加 接收者.   函数体中使用 this
 *
 *
 * 扩张属性
 *      由于扩展没有实际的将成员插入类中，因此对扩展属性来说幕后字段是无效的。这就是为什么扩展属性不能有初始化器。
 *      他们的行为只能由显式提供的 getter/setter 定义。
 *
 * 伴生对象的扩展
 *
 *
 */
fun main() {
    val list = mutableListOf<Int>(1, 2, 3, 45)
    println(list)
    println(list.indexOne)
    list.swap(1, 2)
    println(list)
    println(list.indexOne)
}

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

val <T> MutableList<T>.indexOne: T
    get() = this[1]

