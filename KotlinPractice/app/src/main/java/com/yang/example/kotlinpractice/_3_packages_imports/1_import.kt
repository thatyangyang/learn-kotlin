package com.yang.example.kotlinpractice._3_packages_imports

import com.yang.example.kotlinpractice.common.Cup
import com.yang.example.kotlinpractice.common.drinkTea
import com.yang.example.kotlinpractice.common.drinkWater
import com.yang.example.kotlinpractice.common.conflict.Cup as BlueCup

/**
 * 有多个包会默认导入到每个 Kotlin 文件中：
 *
 * kotlin.*
 * kotlin.annotation.*
 * kotlin.collections.*
 * kotlin.comparisons.*
 * kotlin.io.*
 * kotlin.ranges.*
 * kotlin.sequences.*
 * kotlin.text.*
 *
 * 也可以导入一个作用域下的所有内容：包、类、对象等:
 * import org.example.* // “org.example”中的一切都可访问
 */
fun main() {
    // 包默认导入
    val list = mutableListOf<String>()
    val list2 = kotlin.collections.mutableListOf<String>()

    val cup1 = Cup()
//    val cup2 = com.yang.example.kotlinpractice.common.conflict.Cup()
    val cup2 = BlueCup()
    println(cup1)
    println(cup2)

    drinkWater()
    drinkTea()

}