package com.yang.example.kotlinpractice._0_basic

fun main() {
//    standardInput()

    println(sum(19, 23))

}

private fun standardInput() {
    println("Enter any word:")
    val word = readln()
    println("You entered the word:")
    println(word)
}

// 函数体可以是（一个）表达式
fun sum(a: Int, b: Int) = a + b

val PI = 3.14
var x = 0
fun incrementX() {
    x += 1
}