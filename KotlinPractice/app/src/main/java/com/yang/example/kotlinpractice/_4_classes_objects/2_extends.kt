package com.yang.example.kotlinpractice._4_classes_objects

/**
 * 1. 默认继承 Any, Any 有3个方法: equals, hashCode, toString
 * 2. 默认为 不可继承（final）, 除非声明为 open
 *
 * 派生类要调用父类的构造方法（要初始化父类），无论父类是否有构造参数 ———— 继承自非 Any
 *      1. 无论是在类头 : Base () 或者在次构造方法上使用 : super()
 *      2. 有主和次构造时，可以通过 this() 调用主构造，在主构造上调用父类的 ()
 *
 * 覆盖属性
 *      可以用一个 var 属性覆盖一个 val 属性，但反之则不行。
 *          会构造 set 方法没有没覆盖
 *
 * 覆盖方法
 *
 *
 * 派生类初始化顺序
 *       在构造派生类的新实例的过程中，第一步完成其基类的初始化 （在之前只有对基类构造函数参数的求值），
 *       这意味着它发生在派生类的初始化逻辑运行之前。
 *       设计一个基类时，应该避免在构造函数、属性初始化器或者 init 块中使用 open 成员。
 *
 *
 */
fun main() {

    val empty = Empty()
//    println(empty.equals("11"))
//    println(empty.hashCode())
//    println(empty.toString())

    val d1 = Derived(1, false)
    println(d1)
}

// 继承自 Any
class Empty

abstract class Base() {
    abstract var tee: String

    open var car: String = "carBase"

    init {
        println("Base init")
    }
}

// 主构造调用父类的构造
class Derived() : Base() {
    override var tee: String = ""

    override var car: String = "carDerived"

    init {
        println("Derived init")
    }

    // 次构造调用主构造的
    constructor(p: Int, m: Boolean) : this() {
        println("Derived constructor(p: Int, m: Boolean)")
    }
}

class Derived2(p: Int, override var tee: String) : Base() {

}

//
class Derived3(override var tee: String) : Base() {

}

