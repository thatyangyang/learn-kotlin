package com.yang.example.kotlinpractice._2_control_flow


/**
 *  Kotlin 有三种结构化跳转表达式： *
 * return 默认从最直接包围它的函数或者匿名函数返回。
 * break 终止最直接包围它的循环。
 * continue 继续下一次最直接包围它的循环。
 *
 * 这些用法与 Java 相同；
 *
 * 与 Java 不同点：
 *
 * 在 Kotlin 中任何表达式都可以用标签来标记。 标签的格式为标识符后跟 @ 符号，
 * 例如：abc@、fooBar@。 要为一个表达式加标签，我们只要在其前加标签即可。
 *
 * Nothing 类型
 *
 * Break 与 Continue 标签
 *
 *
 * return 返回到标签
 *      Kotlin 中函数可以使用函数字面量、局部函数与对象表达式实现嵌套。 标签限定的 return 让你可能从外层函数返回。
 *
 *      最重要的一个用途就是从 lambda 表达式中返回
 *
 * 隐式标签
 *      lambda, 名称与接受该 lambda 的函数同名。
 *      局部函数，返回到调用者，同上标签名与 调用者函数同名
 *      函数，名称与函数名同
 *
 * 函数参数是 函数类型，不能传入 匿名内部类，可以传入 方法 / lambda
 *
 * 函数默认返回位置到最近的位置，函数或者局部函数
 * 返回到标签
 *
 * 当要返一个回值的时候，解析器优先选用标签限定的返回： *
 * return@a 1
 * 这意味着“返回 1 到 @a”，而不是“返回一个标签标注的表达式 (@a 1)”。
 */
fun main() {
    var person = Person()

//    breakLabel()

//    continueLabel()

//    foo()
//    foo2()
    foo3()
    foo4()
}

fun foo() {
    listOf(1, 2, 3, 4, 5).forEach lit@{
        if (it == 3) return@lit // 局部返回到该 lambda 表达式的调用者——forEach 循环
        print("$it ")
    }
    println(" done with explicit label")
}

fun foo2() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return@forEach // 隐式标签 与接受该 lambda 的函数同名。
        print(it)
    }
    print(" done with implicit label")
}

fun foo3() {
    listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
        if (value == 3) return@forEach  // 局部返回到匿名函数的调用者——forEach 循环
        print(value)
    })
    print(" done with anonymous function")
}

fun foo4() {
    run loop@{ // 这里的run 是为了加标签
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop // 从传入 run 的 lambda 表达式非局部返回
            print(it)
        }
    }
    print(" done with nested loop")
}

private fun continueLabel() {
    loop@ for (i in 1..9) {
        println()
        print("i = $i   ")
        innerLoop@ for (j in 1..9) {
            if (j == 3) continue@loop
            print("$j ")
        }
    }
}

private fun breakLabel() {
    loop@ for (i in 1..9) {
        println()
        print("i = $i   ")
        innerLoop@ for (j in 1..9) {
            if (j == 3) break@loop
            print("$j ")
        }
    }
}

fun testNothing() {
    var person = Person()
    val s = person.name ?: return
}

class Person {
    var name: String? = null
}