package com.yang.example.kotlinpractice._4_classes_objects

/**
 * 嵌套类
 *      所有类与接口的组合都是可能的：可以将接口嵌套在类中、将类嵌套在接口中、将接口嵌套在接口中。
 *      Outer.Nested().foo()
 *
 * 内部类 ———— 标记为 inner 的嵌套类
 *      内部类能够访问其外部类的成员。内部类会带有一个对外部类的对象的引用：
 *      内部类必须通过外部类的对象创建，不同通过类名
 *      Outer().Inner().foo2()
 *
 *      外部类是内部类的接收者
 *
 * 匿名内部类
 *      使用对象表达式创建匿名内部类实例
 *      object :      其它与 Java 相同
 *
 *
 */
fun main() {
    val demo = Outer.Nested().foo()
    println(demo)

    Outer().Inner().foo2()

    val interfaceObject = object : OuterInterface {
        override fun call(): String {
            return ""
        }
    }

    val classObject = object : OuterClass(){
        override fun run() {

        }
    }

}


class Outer {
    private val bar: Int = 1

    class Nested {
        fun foo() = 2

        fun foo2() {
//            bar
        }
    }

    inner class Inner {
        fun foo() = bar
        fun foo2() {
            println("this@Outer.bar ${this@Outer.bar}")
            bar
        }
    }
}

interface OuterInterface {
    class InnerClass
    interface InnerInterface

    fun call(): String
}

abstract class OuterClass {
    class InnerClass
    interface InnerInterface
    abstract fun run(): Unit
}

