package com.yang.example.kotlinpractice._1_type

fun main() {
//    createArray()

    // 向函数传入可变数量的实参
    val lettersArray = arrayOf("c", "d")
    printAllStrings("a", "b", *lettersArray)

//    compareArray()

//    arrayApis()

    val simpleArray = arrayOf("a", "b", "c", "c")
    println(simpleArray.toList())
    // [a, b, c, c]
    println(simpleArray.toSet())
    // [a, b, c]

    // 只有 Pair 元素的数组可以转为 Map
    val pairArray = arrayOf("apple" to 120, "banana" to 150, "cherry" to 90, "apple" to 140)
    println(pairArray.toMap())
    // {apple=140, banana=150, cherry=90}
}

private fun arrayApis() {
    val sumArray = arrayOf(1, 2, 3)
    // Sums array elements
    println(sumArray.sum())
    // 6

    val simpleArray = arrayOf(1, 2, 3)

    // Shuffles elements [3, 2, 1]
    simpleArray.shuffle()
    println(simpleArray.joinToString())

    // Shuffles elements again [2, 3, 1]
    simpleArray.shuffle()
    println(simpleArray.joinToString())
}

private fun createArray() {
    var riversArray = arrayOf("Nile", "Amazon", "Yangtze")
    println("riversArray[1] ${riversArray[1]}")
    riversArray.forEach {
        println(it)
    }
    println(riversArray.joinToString(separator = "-", prefix = "[", postfix = "]"))
    riversArray += "Mississippi"
    println(riversArray.joinToString())

    var copyRiverArray = riversArray
    println(copyRiverArray == riversArray)  // true
    println(copyRiverArray === riversArray) // true

    val simpleArray = arrayOf(1, 2, 3)
    println(simpleArray.joinToString())

    val nullArray = arrayOfNulls<Int>(4)
    println(nullArray.joinToString())

    val emptyArray = emptyArray<Float>()
    println(emptyArray.joinToString())

    // [100,100,100,100]
    val initArray = Array<Int>(4) { 100 }
    println(initArray.joinToString())

    // 使用 lambda 逐个生产元素   [1, 4, 9, 16, 25]
    val ascArray = Array(5) { (it + 1) * (it + 1) }
    println(ascArray.joinToString())

    // Creates a two-dimensional array
    val twoDArray = Array(2) { Array(3) { 0 } }
    println(twoDArray.contentDeepToString())
    // [[0, 0], [0, 0]]

    // Creates a three-dimensional array
    val threeDArray = Array(3) { Array(3) { Array(3) { 0 } } }
    println(threeDArray.contentDeepToString())
}

// 比较数组使用 contentEquals()
fun compareArray() {
    val simpleArray = arrayOf(1, 2, 3)
    val anotherArray = arrayOf(1, 2, 3)

    // Compares contents of arrays
    println(simpleArray.contentEquals(anotherArray))
    // true

    // Using infix notation, compares contents of arrays after an element
    // is changed
    simpleArray[0] = 10
    println(simpleArray contentEquals anotherArray)
    // false
}

fun printAllStrings(vararg strings: String) {
    for (string in strings) {
        print(string)
    }
}