package com.yang.example.kotlinpractice._4_classes_objects

/**
 * 接口可以既包含抽象方法的声明也包含实现。
 * 与抽象类不同的是，接口无法保存状态。它可以有属性但必须声明为抽象或提供访问器（就会优化掉）实现。
 *
 * 接口 是 open 的；
 * 接口中的方法，都是默认 open, 无论是否 abstract
 * 接口中的属性，模式是 abstract
 *
 * 继承多个接口同名方法
 *      2个以上的抽象方法，通过 super<Father1>.foo()
 */
fun main() {

}


interface MyInterface {
    val name: String          // 默认抽象，子类必须实现

    val propertyWithImplementation: String
        get() = "foo"       // 提供访问器，不是abstract, 子类不必实现

    fun bar()

    fun foo() {  // 可以看着默认实现

    }
}

class Child(override val name: String) : MyInterface {

    override fun bar() {

    }
}

interface SonInterface : MyInterface {
    val firstName: String
    val lastName: String

    override val name
        get() = "$firstName + $lastName"
}

/**************************************/

interface A {
    fun foo() {
        print("A")
    }

    fun bar()
}

interface B {
    fun foo() {
        print("B")
    }

    fun bar() {
        print("bar")
    }
}

class C : A {
    override fun bar() {
        print("bar")
    }
}

class D : A, B {
    override fun foo() {
        super<A>.foo()
        super<B>.foo()
    }

    override fun bar() {
        super.bar()
    }
}