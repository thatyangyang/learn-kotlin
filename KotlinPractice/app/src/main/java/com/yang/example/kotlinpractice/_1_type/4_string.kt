package com.yang.example.kotlinpractice._1_type

fun main() {

    val str = "abcd"
    println(str[0])
    str.forEachIndexed { index, c ->
        println("$c - $index - ${str[index]}")
    }

    println(str.length)
    println(str.padEnd(8, '2'))

    println(str.uppercase())
    println(str)

    println("abc" + 1 + 2)
    println("abc" + 1 + "xyz")

    // 多行字符串
    val text = """
        |for (c in "foo")
        |    print(c)   
    """
    println(text)
    println(text.trimMargin())

    // 字符串模板
    val i = 10
    println("i = $i")
    // i = 10

    val letters = listOf("a", "b", "c", "d", "e")
    println("Letters: ${letters.toString()}")
    // Letters: [a, b, c, d, e]

    // 字符串格式化
    formatString()

}

fun formatString() {
    // Formats an integer, adding leading zeroes to reach a length of seven characters
    val integerNumber = String.format("%07d", 31416)
    println(integerNumber)
    // 0031416

    // Formats a floating-point number to display with a + sign and four decimal places
    val floatNumber = String.format("%+.4f", 1.141592)
    println(floatNumber)
    // +3.1416

    // Formats two strings to uppercase, each taking one placeholder
    val helloString = String.format("%S %s", "hello", "world")
    println(helloString)
    // HELLO WORLD

    // Formats a negative number to be enclosed in parentheses, then repeats the same number in a different format (without parentheses) using `argument_index$`.
    val negativeNumberInParentheses = String.format("%(d means %1\$d", -31416)
    println(negativeNumberInParentheses)
    //(31416) means -31416

}
