package com.yang.example.kotlinpractice._7_equality

/**
 * 数组的结构相等, 使用 contentEquals() ———— 使用 java.util.Arrays.equals
 * 因为 Array 没有重写 equals() 默认使用引用相等判断
 *
 * operator 只能应用于成员方法
 */
fun main() {
    //sampleStart
    val simpleArray = arrayOf(1, 2, 3)
    val anotherArray = arrayOf(1, 2, 3)

    // Compares contents of arrays
    println(simpleArray.contentEquals(anotherArray))
    // true

    println(simpleArray == anotherArray)
    // false

    // Using infix notation, compares contents of arrays after an element
    // is changed
    simpleArray[0] = 10
    println(simpleArray contentEquals anotherArray)
    // false
    //sampleEnd
}
