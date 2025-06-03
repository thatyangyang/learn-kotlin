package com.yang.example.kotlinpractice._7_equality

/**
 * 结构相等  ==       用 equals() 检测, equals() 重载了 == 运算符
 * 引用相等  ===      检测两个引用指向同一对象, 与 Java 中的 == 相同
 *
 * 结构相等, 使用 equals(other: Any?) 判断, 当然可以应用于不同对象
 * 1. 没有重写 equals() 方法, 依靠引用判断; 如果重写了, 则完全依赖 equals() 判断
 * 2. equals 返回 false, 即使引用相等, 也不想等
 * 3. equals 返回 true, 即使相同类型且内部属性完全相同，也相等
 *
 * 引用相等, 不能应用于不同类型的对象
 *
 * null 是一个固定的对象, 两个值为 null 的变量比较: == 和 === 都为 true
 *
 * 像 a == b 这样的表达式会翻译成：
 *  a?.equals(b) ?: (b === null)
 *
 *  当与 null 显式比较时完全没必要优化你的代码： a == null 会被自动转换为 a === null
 *
 *  Value classes and data classes 自动复写了 equals() 方法，所以可以用结构相等
 */
fun main() {


    val point1 = Point(1, 2)
    val point2 = Point(1, 2)
    val point3 = point1
    println("point1 == point2: ${point1 == point2}")
    println("point1 === point2: ${point1 === point2}")
    //
    println("point1 == point3: ${point1 == point3}")
    println("point1 === point3: ${point1 === point3}")

    val point4 = Point(1, 4)
    val user101 = User("jack", 24, false)
    println("point1 == point4: ${point1 == point4}")
    println("point1 === point4: ${point1 === point4}")
    println("point4 == user101: ${point4 == user101}")
    // 引用相等, 不能应用于不同类型的对象
    // println("point4 === user101: ${point4 === user101}")

    // 两个值为 null 的变量, 结构相等
    var a: String? = null
    var b: String? = null
    println("a == b ${a == b}")
    println("a == b ${a === b}")

}

class Point(val x: Int, val y: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return false
        }
        return false
//        if (this === other) return true
//        if (other is User) return true
//        if (other !is Point) return false
//
//        // Compares properties for structural equality
////        return this.x == other.x && this.y == other.y
//        return this.x == other.x
    }
}

class User(val name: String, val age: Int, val isMale: Boolean) {
    /*override fun equals(other: Any?): Boolean {
        if (other != null && other is User) {
            return name == other.name && age == other.age
        }
        return false
    }*/
}