package com.yang.example.kotlinpractice._4_classes_objects

/**
 * 对象声明与对象表达式
 *
 * 伴生对象
 *  companion object
 *      可以看着类内的单例对象，就像这个类的静态方法/属性,
 *      每个类只允许有一个伴生对象
 *      可以省略伴生对象的名称
 *
 *      通过伴生对象名称访问，
 *          MyClass.Factory.  ->   MyClass.
 *      没有名称，则通过 Companion 访问
 *          MyClass.Companion.  ->   MyClass.
 *      都可以通过类名访问
 *
 *      类成员可以访问伴生对象中的私有成员
 *
 *      可以实现接口
 *
 */
fun main() {
    val myClass = MyClass()
    println(myClass)
    println(MyClass.create())
    MyClass.Factory.create()

    val user14 = User14.create("yang")
    println(user14.name)
    User14.Companion.create("yang---")

    // 类成员可以访问伴生对象中的私有成员
    User142("yang").sayHi()

    // 可以实现接口
    println(User1434.create("88888"))

}

//class MyClass private constructor() {
class MyClass {
    companion object Factory {
        fun create(): MyClass = MyClass()
    }
}

class User14(val name: String) {
    companion object {
        fun create(name: String): User14 = User14(name)
    }
}


class User1 {
    // Defines a named companion object
    companion object Named {
        fun show(): String = "User1's Named Companion Object"
    }
}

// References the companion object of User1 using the class name
val reference1 = User1

class User2 {
    // Defines an unnamed companion object
    companion object {
        fun show(): String = "User2's Companion Object"
    }
}

// References the companion object of User2 using the class name
val reference2 = User2

/***********/

class User142(val name: String) {
    companion object {
        private val defaultGreeting = "Hello"
    }

    fun sayHi() {
        println(defaultGreeting)
    }
}


interface Factory1434 {
    fun create(name: String): String
}

class User1434(val name: String) {
    // Defines a companion object that implements the Factory interface
    companion object : Factory1434 {
        override fun create(name: String): String {
            return name
        }

    }
}
