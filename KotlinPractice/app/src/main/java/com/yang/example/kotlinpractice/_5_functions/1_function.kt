package com.yang.example.kotlinpractice._5_functions

/**
 * 默认实参
 *      默认参数可以使用其它参数的属性方法
 *          方法：需要在其它参数后面
 *      被覆盖的方法如果有一个默认实参，则override 方法不能有任何默认实参，其使用被覆盖的方法的默认实参
 *
 *      如果一个有默认值参数在一个无默认值的参数之前，那么该默认值只能通过使用具名实参调用该函数来使用—————因为参数按顺序匹配
 *
 *      如果(在默认实参之后的)最后一个参数是 lambda 表达式，那么它既可以作为具名实参在括号内传入，也可以在括号外传入
 *
 * 具名实参
 *      传参时指定参数名称
 *      需要在跳过第一个参数后，对后续的所有参数都使用具名实参：
 *      可变参数，使用数组 传入时，如果使用具名实参，则可以不用展开符合(*)
 *
 *
 * 返回 Unit 的函数
 *      `return Unit` 或者 `return` 是可选的，或者不使用 return
 *      函数声明中的 Unit 可省略
 *
 * 单表达式函数 = 单表达式并返回其值的函数
 *      函数返回值，必须显示调用 return, 除了单表达式函数
 *      { return 10 } ->   = 10
 *
 * 显式返回类型
 *      具有块代码体的函数必须始终显式指定返回类型，除了 Unit
 *          单表达式函数的返回值类型，可以推断，可以省略
 *      Kotlin 不推断具有块代码体的函数的返回类型
 *
 * 可变数量的参数（varargs）
 *      asList(4, 5, 6)
 *      asList(*arrayOf(4, 5, 6))
 *      asList(1, 2, 3, *arrayOf(4, 5, 6), 7)
 *      asList(ts = arrayOf(4, 5, 6))
 *
 *      只有一个参数可以标注为 vararg。
 *      如果 vararg 参数不是列表中的最后一个参数， 必须使用具名实参语法传递其后的参数的值，
 *      或者，如果参数具有函数类型，则通过在括号外部传一个 lambda。
 *
 * 中缀表示法
 *  标有 infix 关键字的函数也可以使用中缀表示法（忽略该调用的点与圆括号）调用
 *      中缀函数必须满足以下要求：
 *          它们必须是成员函数或扩展函数  ———— 要有接收者（函数调用的左边）
 *          它们必须只有一个参数  ————  函数定义中这个参数可以有默认参数，只是在 中缀表示法 中没有用；正常函数调用有用
 *          其参数不得接受可变数量的参数且不能有默认值
 *
 *      receiver infix(func name) arg
 *      中缀函数总是要求指定接收者与参数，在类内使用，需要显示指定 this infix arg
 *
 * 函数作用域
 *      顶层函数、成员函数、扩展函数
 *      局部函数：即一个函数在另一个函数内部：
 *          局部函数可以访问外部函数（闭包）的局部变量。
 *
 * 泛型函数
 *      在函数名前使用尖括号
 *
 * 尾递归函数
 *      对于某些使用循环的算法，可以使用尾递归替代而不会有堆栈溢出的风险。
 *      要符合 tailrec 修饰符的条件的话，函数必须将其自身调用作为它执行的最后一个操作
 *      在递归调用后有更多代码时， 不能使用尾递归，不能用在 try/catch/finally 块中，也不能用于 open 的函数
 *
 */

fun main() {
    val byteArrayOf = byteArrayOf(1, 2, 3)
    read(b = byteArrayOf)

    // 按照没有默认实参的参数的顺序赋值
    foo51(20, baz = 30) { println("foo51") }


//    foo52((*arrayOf("1","2")))
    foo52("1", "2")
    foo52(strings = arrayOf("1", "2"))
    foo52(*arrayOf("1", "2"))

    println(foo53())
    println(foo54())

    asList(4, 5, 6).apply { println(joinToString()) }
    asList(*arrayOf(4, 5, 6)).apply { println(joinToString()) }
    asList(ts = arrayOf(4, 5, 6)).apply { println(joinToString()) }
    asList(1, 2, 3, *arrayOf(4, 5, 6), 7).apply { println(joinToString()) }
    val intArray = intArrayOf(4, 5, 6)
    asList(1, 2, 3, *intArray.toTypedArray(), 7).apply { println(joinToString()) }

    100.jiafa().apply { println(this) }
    (100 jiafa 10).apply { println(this) }

    MyStringCollection() add "fd"

    outerFun(10)

}

/*fun <T> singletonList(item: T): List<T> { *//*……*//*
}*/

fun outerFun(outer: Int) {
    var aa = 100
    fun innerFun(inner: Int): Int {
        aa += 1
        println(aa)
        outerFun(inner - 1)
        return inner - 1
    }

    if (outer > 0) {
        val res = innerFun(outer)
        println("res $res")
    }
}


infix fun Int.jiafa(x: Int = 10): Int {
    return this + x
}

class MyStringCollection {
    infix fun add(s: String) { /*……*/
        println(s)
    }

    fun build() {
        this add "abc"   // 正确
        add("abc")       // 正确
//        add "abc"
    }
}

/*******************/

fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) // ts 是一个数组
        result.add(t)
    return result
}

fun foo53(): Int {
    return 10
}

fun foo54() = "String"

fun foo52(vararg strings: String) { /*……*/
    strings.apply { println("foo52 ${joinToString()}") }
}


fun foo51(
    bar: Int = 10,
    baz: Int = 20,
    qux: () -> Unit,
) { /*……*/
    qux()
}


fun read(
    len: Int = b.size,
    b: ByteArray,
    off: Int = 0,
) { /*……*/
}

fun read(
    len: Int = b.size,
    b: ByteArrayEntity = ByteArrayEntity(),
    off: Int = 0,
) { /*……*/
}

class ByteArrayEntity {
    val size = 0
    fun getLength(): Int {
        return 10
    }
}

open class A1 {
    open fun foo(i: Int = 10, a: Int) { /*……*/
    }
}

class B : A1() {
    override fun foo(i: Int, a: Int) { /*……*/
    }  // 不能有任何默认值
}




