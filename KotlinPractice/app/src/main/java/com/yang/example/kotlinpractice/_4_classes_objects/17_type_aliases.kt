package com.yang.example.kotlinpractice._4_classes_objects

import androidx.collection.ArraySet

/**
 * 类型别名
 *  类型别名为现有类型提供替代名称
 *      函数类型
 *      内部类和嵌套类
 *
 */
fun main() {
    val nodeSet = NodeSet<Int>(10).apply {
        add(1)
        add(3)
        add(1)
    }
    println(nodeSet.joinToString())

    A17().AInner()
    BInner()

    val f: (Int) -> Boolean = { it > 0 }
    println(foo17(f))
    println(listOf(1, 2, 0, -2).filter(f))
}


typealias NodeSet<E> = ArraySet<E>

typealias FileTable<K, V> = MutableMap<K, V>

typealias MyHandler = (Int, String, Any) -> Unit

typealias Predicate<T> = (T) -> Boolean

fun foo17(p: Predicate<Int>) = p(42)

class A17 {
    inner class Inner
}

class B17 {
    class Inner
}

typealias AInner = A17.Inner
typealias BInner = B17.Inner

