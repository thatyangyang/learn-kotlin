package com.yang.example.kotlinpractice._1_type

/**
 * 在 Kotlin 中，所有东西都是对象，在这个意义上讲可以在任何变量上调用成员函数与属性。
 * 数字和无符号数字、布尔、字符、字符串、数组
 *
 * 三个特殊类型： Any, Nothing, Unit
 *
 * 数字
 * 整数
 *      Byte(8), Short(16), Int(32), Long(64)
 * 浮点数
 *      Float(32), Double(64)
 *
 * 数字类型不支持隐式拓宽转换 Int -> Long
 */

fun main() {
    //integerTest()

//    floatTest()

//    boxing()

    calculations()
    bitCalculations()

    floatCompare()

    unsignedNumbers()

//    val p1: Person? = null
//    val aa: String? = null
//    println(p1 === aa) // null 指向同一地址


}

fun unsignedNumbers() {
    val b: UByte = 1u  // UByte，已提供预期类型
    val s: UShort = 1u // UShort，已提供预期类型
    val l: ULong = 1u  // ULong，已提供预期类型

    val a1 = 42u // UInt：未提供预期类型，常量适于 UInt
    val a2 = 0xFFFF_FFFF_FFFFu // ULong：未提供预期类型，常量不适于 UInt

    data class Color(val representation: UInt)

    val yellow = Color(0xFFCC00CCu)

    val byteOrderMarkUtf8 = ubyteArrayOf(0xEFu, 0xBBu, 0xBFu)
}

// TODO
fun floatCompare() {
//sampleStart
    // 静态类型作为浮点数的操作数
    println(Double.NaN == Double.NaN)                 // false

    // 静态类型并非作为浮点数的操作数
    // 所以 NaN 等于它本身
    println(listOf(Double.NaN) == listOf(Double.NaN)) // true

    // 静态类型作为浮点数的操作数
    println(0.0 == -0.0)                              // true

    // 静态类型并非作为浮点数的操作数
    // 所以 -0.0 小于 0.0
    println(listOf(0.0) == listOf(-0.0))              // false

    println(listOf(Double.NaN, Double.POSITIVE_INFINITY, 0.0, -0.0).sorted())
    // [-0.0, 0.0, Infinity, NaN]
    //sampleEnd

}

fun bitCalculations() {
    // 位运算，使用中缀标识法 / 函数调用，没有位运算符
    val x = 1
    val xShiftedLeft = x shl 2
    println(xShiftedLeft)

    val xAnd = x and 0x000FF000
    println(xAnd)
}

fun calculations() {
    println(1 + 2)
    println(2_500_000_000L - 1L)
    println(3.14 * 2.71)
    println(10.0 / 3)
    println(1.plus(2))
    println(2_500_000_000L.minus(1L))
    println(3.14.times(2.71))
    println(10.0.div(3))

    // 整数除法结果为整数
    val x = 5 / 2
//    x == 2.0 // Operator '==' cannot be applied to 'kotlin.Int' and 'kotlin.Double'.
    println(x == 2)

    val y = 5L / 2
//    y == 2  // Operator '==' cannot be applied to 'kotlin.Long' and 'kotlin.Int'.
    println(y == 2L)

    val z = 5 / 2.toDouble()
    println(z == 2.5)
}

class Person {}

fun boxing() {
    val a: Int = 127
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    // −128 and 127 范围内的数字，装箱时指向同一个对象；在这个范围之外，则指向不同的对象
    println(boxedA === anotherBoxedA) // true
    println(boxedA == anotherBoxedA) // true

    val b: Int = 10000
    val boxedB: Int? = b
    val anotherBoxedB: Int? = b

    println(boxedB === anotherBoxedB) // false
    println(boxedB == anotherBoxedB) // true
}

private fun floatTest() {
    //    val pi: Float = 3.14    // 报错，因为自动推断 3.14 为 Double
//    val one: Double = 1     // 报错，因为自动推断 1 为 Int

    val e = 2.7182818284
    val eFloat = 2.7182818284f // 显示指定为 Float

    fun printDouble(x: Double) {
        print(x)
    }

    val x = 1.0
    val xInt = 1
    val xFloat = 1.0F

    printDouble(x)
//    printDouble(xInt) // 报错：参数类型不匹配；因为数字没有隐式拓宽转换
//    printDouble(xFloat)
}

fun integerTest() {
    val one = 1                     // 默认自动推断为 Int
    val threeBillion = 3000000000   // 自动推断为 Long
    val oneLong = 1L
    val oneByte: Byte = 1            // Byte, Short 必须显示指定

    println(one::class)  // 是类，不是原生类型
    println(threeBillion)
    println(oneLong)
    println(oneByte::class)
}
