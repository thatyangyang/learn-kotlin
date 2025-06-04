package com.yang.example.kotlinpractice._4_classes_objects

/**
 * 主构造
 *      主构造函数在类头中声明
 *      constructor 关键字可以省略,
 *      没有次构造的时候，主构造的 () 不可省略，否则就是没有主构造
 *
 *      如果一个非抽象类没有声明任何（主或次）构造函数，它会有一个生成的不带参数的主构造函数。构造函数的可见性是 public。
 *      如果你不希望你的类有一个公有构造函数，那么声明一个带有非默认可见性的空的主构造函数：
 *
 * 初始化代码块
 *      init {} 可以有多个
 *      在实例初始化期间，初始化块按照它们出现在类体中的顺序执行，与属性初始化器交织在一起
 *
 * 次构造
 *      如果类有一个主构造函数，每个次构造函数需要委托给主构造函数  : this()
 *          如果不这样，可能导致 TODO
 *
 *      初始化块中的代码实际上会成为主构造函数的一部分。对主构造函数的委托发生在访问次构造函数的第一条语句时，
 *      因此所有初始化块与属性初始化器中的代码都会在次构造函数体之前执行。
 *
 *      即使该类没有主构造函数，这种委托仍会隐式发生，并且仍会执行初始化块：
 *
 * 抽象类
 *      类以及其中的某些或全部成员(包括属性)可以声明为 abstract。 不需要用 open 标注抽象类或者函数。
 *      抽象方法不需要写返回值
 *
 *      可以用一个抽象成员覆盖一个非抽象的开放成员
 */
fun main() {
    InitOrderDemo("yang")
    val mutableListOf: MutableList<Pet> = mutableListOf<Pet>()

//    Pet()
//    DontCreateMe()
}


class Person(var name: String, val pets: MutableList<Pet> = mutableListOf<Pet>()) {
    val nickname = name.uppercase()
}


class Pet() {
    constructor(owner: Person) : this() {
        owner.pets.add(this) // adds this pet to the list of its owner's pets
    }
}

class InitOrderDemo(name: String, age: Int = 14) {
    val nickname = name.uppercase()

    val firstProperty = "First property: $name".also(::println)

    constructor(gender: String) : this(name = "adb") {

    }

    init {
        println("First initializer block that prints $firstProperty")
//        name = "yang"
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }
}

class Machine {
    open fun produce() {

    }
}

abstract class Phone {
    abstract var brand: String
    var year: Int = 1999

    abstract fun call(): Unit

    open fun message() {}

    abstract fun produce()
}

class MiPhone : Phone() {
    override var brand: String = "Mi"
    override fun call() {

    }

    override fun message() {

    }

    override fun produce() {

    }

}

