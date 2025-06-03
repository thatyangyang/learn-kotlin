package com.yang.example.kotlinpractice._2_control_flow

/**
 * if 是一个表达式：它会返回一个值。 ———— 和你是否接受这个值没有关系
 *      如果各个分支的反正值不一样，那 Any 或最近父类
 *      因此就不需要三元运算符
 *      if ... else if ... else ...
 *
 * when 可以是表达式或语句,
 *      作为表达式，必须匹配其中之一，否则如果没有匹配到，则抛异常
 *          所以最好加上 else 分支，Boolean, enum class, sealed class 一般可以覆盖所有
 *      作为语句，则不必须
 *
 *      when 可以没有主词, 没有主词时，必须有 else 分支
 *
 *      可以用任意表达式（而不只是常量）作为分支条件   in   !in    is   !is
 *
 *      可以合并分支
 *
 *      默认有 break
 *
 */
fun main() {
    val a = 200
    val b = 100
    println(myMax(a, b))

    println(myMax2(23, 23))

    println(myMax3(23, 23))

    val message = when {
        a > b -> "a is greater than b"
        a < b -> "a is less than b"
        else -> "a is equal to b"
    }
    println(message)

    println(myWhen(100))
}

fun myWhen(x: Int): String {
    val message = when (x) {
        is Any -> "branch 1"
        is Int -> "branch 2"
        else -> "branch else"
    }
    return message
}

fun myMax(a: Int, b: Int) = if (a >= b) a else b

fun myMax2(a: Int, b: Int) = if (a > b) 1 else if (a == b) 0 else -1

fun myMax3(a: Int, b: Int): Int = if (a > b) {
    println("a>b")
    1
} else if (a == b) {
    println("a==b")
    0
} else {
    println("a<b")
    -1
}