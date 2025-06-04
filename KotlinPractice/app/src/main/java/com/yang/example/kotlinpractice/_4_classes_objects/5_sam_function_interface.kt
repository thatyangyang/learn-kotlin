package com.yang.example.kotlinpractice._4_classes_objects

/**
 * 函数式（SAM）接口
 * 只有一个抽象成员函数的接口称为函数式接口或 单一抽象方法（SAM）接口。
 * 函数式接口可以有多个非抽象成员函数，但只能有一个抽象成员函数。
 *
 *      可以用 fun 修饰符在 Kotlin 中声明一个函数式接口
 *
 */
fun main() {

    val isEven = object : IntPredicate {
        override fun accept(i: Int): Boolean {
            return i % 2 == 0
        }
    }
    println(isEven.accept(41))
    val isEven2 = IntPredicate { it % 2 == 0 }
    println(isEven2.accept(40))


    println("Is 7 even? - ${isEven(7)}")

}

fun interface IntPredicate {
    fun accept(i: Int): Boolean
}

// 方法类别？
typealias IntPredicate2 = (i: Int) -> Boolean

val isEven: IntPredicate2 = { it % 2 == 0 }  // java 风格的是 (it) -> it % 2 == 0

