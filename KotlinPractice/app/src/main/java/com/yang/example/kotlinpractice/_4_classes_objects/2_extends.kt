package com.yang.example.kotlinpractice._4_classes_objects

/**
 * 1. 默认继承 Any, Any 有3个方法: equals, hashCode, toString
 * 2. 默认为 不可继承（final）, 除非声明为 open
 *
 */
fun main() {

    val empty = Empty()
    println(empty.equals("11"))
    println(empty.hashCode())
    println(empty.toString())

}

open class Empty

class Son : Empty() {

}