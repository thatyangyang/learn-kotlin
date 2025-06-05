package com.yang.example.kotlinpractice._5_functions


/**
 * 操作符重载
 *  各个操作符对应固定的函数名称
 *      operator fun get(index: Int)   get() - []
 *
 *  接收者为可空
 *  a !in b	    !b.contains(a)
 *
 *  a == b	    a?.equals(b) ?: (b === null)
 *  a != b	    !(a?.equals(b) ?: (b === null))
 *
 *
 */
fun main() {
    val ordersList = OrdersList()
    val value = ordersList[1]
    println(value)

}

interface IndexedContainer {
    operator fun get(index: Int): Int
}

class OrdersList : IndexedContainer {
    override fun get(index: Int): Int { /*...*/
        return index + 10
    }
}