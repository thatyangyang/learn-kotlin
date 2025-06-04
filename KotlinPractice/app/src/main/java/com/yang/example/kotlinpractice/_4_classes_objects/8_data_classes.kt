package com.yang.example.kotlinpractice._4_classes_objects

/**
 * 编译器自动从主构造函数中声明的所有属性导出以下成员
 *      equals() / hashCode()     ==  ->  hashCode     比较主构造内的属性
 *      toString
 *      componentN()   按顺序获取属性   为所有 public 属性
 *                      以在解构声明中使用
 *      copy            只复制主构造的属性，其它属性与新创建对象一样
 *
 * 数据类不能是抽象、开放、密封或者内部的
 *
 * 在类体中声明的属性, 则不会自动生成对应的函数或参与
 *
 *  标准库中的 Pair 与 Triple 类是 data class
 *
 */
fun main() {
    val user = User("yang", 28)
    println(user)
    user.name = "shou"
    user.weatherString = "rainy"
    println("$user  ${user.weatherString}")
    val component1 = user.component1()
//    user.component2()
    val copy = user.copy()
    println("copy $user ${copy.weatherString}")

    val user1 = User("yang", 28).apply { weatherString = "sunny" }
    val user2 = User("yang", 28).apply { weatherString = "cloudy" }

    println(user1 == user2)

    val pair = Pair(1, "red")
    val triple = Triple("yang", 34, false)

}

data class User(var name: String, private val age: Int) {
    var weatherString = "Sunny"
}