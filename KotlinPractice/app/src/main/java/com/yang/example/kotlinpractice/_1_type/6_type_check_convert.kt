package com.yang.example.kotlinpractice._1_type

/**
 * 类型检测与类型转换
 * 类型检测    is   !is    =   instanceOf (Java)
 * 类型转换    编译器可以判断类型，之后不需要手动转换
 *      可以用于控制流
 *          when 分支判断,
 *          if 条件
 *              is   或者  !is + return
 *      逻辑运算符
 *          !is || 右侧可以自动转换
 *          is  && 右侧
 *
 * 可能造成空指针，解决办法：
 *      val x: String? = y as String?
 *      val x: String? = y as? String
 */

fun main() {
    /*val obj = "123"
    if (obj is String) {
        print(obj.length)
    }

    if (obj !is String) { // 与 !(obj is String) 相同
        print("Not a String")
    } else {
        print(obj.length)
    }*/

    whenIs(1)
    whenIs("abc")
    whenIs(IntArray(20) { it })

    val kitty = Cat()
    petAnimal(kitty)
    // Purr purr

//    smartCstInLogicalOperators("1234345")
//    smartCstInLogicalOperators(1234)

//    smartCstInInterface()

    safeCast()
}

fun safeCast() {
//    val y = 100
//    val x: String = y as String   // .ClassCastException
//    val x: String? = y as String?    // .ClassCastException

    val y = null
//    val x: String = y as String   // NullPointerException
    val x: String? = y as String?     // null
    println(x)
    val xx: String? = y as? String     // null
    println(xx)

}


/*******************/

fun smartCstInInterface() {
    signalCheck(object : Ok {})
    signalCheck(object : Postponed {})
    signalCheck(object : Declined {})
}

interface Status {
    fun signal() {
        println("single $this")
    }
}

interface Ok : Status
interface Postponed : Status
interface Declined : Status

fun signalCheck(signalStatus: Any) {
    if (signalStatus is Postponed || signalStatus is Declined) {
        // signalStatus is smart-cast to a common supertype Status
        signalStatus.signal()
    }
}

/******************************/

fun smartCstInLogicalOperators(x: Any) {
    // `||` 右侧的 x 自动转换为 String
    if (x !is String || x.length == 0) return

    // `&&` 右侧的 x 自动转换为 String
    if (x is String && x.length > 0) {
        print(x.length) // x 自动转换为 String
    }
}

fun whenIs(x: Any) {
    when (x) {
        is Int -> println(x + 1)
        is String -> println(x.length + 1)
        is IntArray -> println(x.sum())
    }
}

fun demo(x: Any) {
    if (x is String) {
        print(x.length) // x 自动转换为字符串
    }
}

// 编译器甚至足够聪明，能够知道如果反向检测导致返回那么该转换是安全的
fun demo2(x: Any) {
    if (x !is String) return

    print(x.length) // x 自动转换为字符串
}


class Cat {
    fun purr() {
        println("Purr purr")
    }
}

fun petAnimal(animal: Any) {
    val isCat = animal is Cat
    if (isCat) {
        // The compiler can access information about
        // isCat, so it knows that animal was smart-cast
        // to the type Cat.
        // Therefore, the purr() function can be called.
        animal.purr()
    }
}

/***********************/

// != null 的非空判断后，可以不使用 ?.

interface Processor {
    fun process()
}

inline fun inlineAction(f: () -> Unit) = f()

fun nextProcessor(): Processor? = null

fun runProcessor(): Processor? {
    var processor: Processor? = null
    inlineAction {
        // The compiler knows that processor is a local variable and inlineAction()
        // is an inline function, so references to processor can't be leaked.
        // Therefore, it's safe to smart-cast processor.

        // If processor isn't null, processor is smart-cast
        if (processor != null) {
            // The compiler knows that processor isn't null, so no safe call
            // is needed
            processor.process()
        }

        processor = nextProcessor()
    }

    return processor
}
