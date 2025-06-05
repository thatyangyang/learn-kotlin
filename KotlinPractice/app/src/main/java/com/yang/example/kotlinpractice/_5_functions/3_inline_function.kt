package com.yang.example.kotlinpractice._5_functions

/**
 * 内联函数
 * inline
 *      使用高阶函数会带来一些运行时的效率损失
 *      通过内联化 lambda 表达式可以消除这类的开销
 *
 * noinline
 *
 *
 * Returns
 * 在 Kotlin 中，只能对具名或匿名函数使用正常的、非限定的 return 来退出。
 *      要退出一个 lambda 表达式，需要使用一个标签。
 *      在 lambda 表达式内部禁止使用裸 return，因为 lambda 表达式不能使包含它的函数返回：(否则会产生歧义)
 *
 *      但是如果 lambda 表达式传给的函数是内联的，该 return 也可以内联。
 *
 *      这种返回（位于 lambda 表达式中，但退出包含它的函数）称为非局部返回
 *
 * Break and continue
 *
 *
 * 具体化的类型参数
 *  reified
 *      使用 reified 修饰符来限定类型参数使其可以在函数内部访问它， 几乎就像是一个普通的类一样。
 *
 *
 *
 * 内联属性
 *  inline 修饰符可用于没有幕后字段的属性的访问器
 *
 *
 * 公有 API 内联函数的限制
 *      当一个内联函数是 public 或 protected 而不是 private 或 internal 声明的一部分时，
 *      就会认为它是一个模块级的公有 API。可以在其他模块中调用它，并且也可以在调用处内联这样的调用。
 *
 */
fun main() {
    val result = lock(1) { 2 }
    println(result)

    foo()

}

inline fun lock(lock: Int, body: () -> Int): Int {
    return lock + body()
}

fun ordinaryFunction(block: () -> Unit) {
    println("hi!")
}
inline fun inlined(block: () -> Unit) {
    println("hi!")
}
//sampleStart
fun foo() {
    /*ordinaryFunction {
//        return // 错误：不能使 `foo` 在此处返回
//         return@ordinaryFunction   // 加不加这句，没有不同
    }*/
    inlined {
//        return // OK：该 lambda 表达式是内联的
//        return@inlined
        // 没有 return, 有return, 有 return@inlined， 三个的结果一样
    }
    println("foo() end")
}