package com.yang.example.kotlinpractice._4_classes_objects

/**
 * 委托模式已经证明是实现继承的一个很好的替代方式
 *  by
 *      class Derived(b: Base) : Base by b
 *
 *  覆盖由委托实现的接口成员
 *      编译器使用 override 的实现，而不是 by 委托的实现
 *      不会影响委托对象中的实现
 */
fun main() {
    val base = BaseImpl(5)
    val derived15 = Derived15(base)
    derived15.print()
    println()
    derived15.printMessage() // abc
    println()
    base.printMessage()  // 5


    println()
    println(derived15.message)
    println()
    println(base.message)
}

interface Base15 {
    val message: String
    fun print()
    fun printMessage()
    fun printMessageLine()
}

class BaseImpl(val x: Int) : Base15 {
    override val message = "BaseImpl: x = $x"
    override fun print() {
        print(x)
    }

    override fun printMessage() {
        print(x)
    }

    override fun printMessageLine() {
        println(x)
    }
}

class Derived15(b: Base15) : Base15 by b {
    override val message = "Message of Derived"
    override fun printMessage() {
        print("abc")
    }
}