package com.yang.example.kotlinpractice._4_classes_objects


/**
 * Kotlin 中所有对属性的读写是通过 get, set 方法的，除了 幕后字段和幕后属性
 *
 * Getters and setters
 * 属性初始化器, get, set
 *      读属性变量的读取和赋值，都会调用 get, set 方法；
 *      自定义这两个方法是，不能在里面读写这个属性，警惕造成 无限循环; 可以使用幕后字段访问读写
 *
 * 幕后字段
 *      field 标识符只能用在属性的访问器内。
 *      如果属性至少一个访问器使用默认实现， 或者自定义访问器通过 field 引用幕后字段，将会为该属性生成一个幕后字段。
 *
 * 幕后属性
 *      private var _name: String = "name"        不提供 set get (提供 get set, 就会被优化掉)
 *      public val name                            jvm 优化，只会生成 _name , 不会生成 name
 *          get() = _name
 *      对内可写可读，对外只读
 *
 * 编译期常量 const
 *      如果只读属性的值在编译期是已知的，那么可以使用 const 修饰符将其标记为编译期常量。
 *
 * lateinit
 *      foo::bar.isInitialized 检测是否已经初始化
 */
fun main() {
    val rectangle = Rectangle2(3, 4)
    println("Width=${rectangle.width}, height=${rectangle.height}, area=${rectangle.area}")
    rectangle.area = 40
    println("Width=${rectangle.width}, height=${rectangle.height}, area=${rectangle.area}")

    val box = Box()
    println("box.name=${box.name}")
    box.customName("bbbbbbbbb")
    println("box.name=${box.name}")
}


//sampleStart
class Rectangle2(val width: Int, val height: Int) {
    var area: Int = 0
        get() {
            println("get")
            return width * height
        }
        set(value) {
//            area = value // ERROR StackOverflow: Using actual name 'counter' would make setter recursive
            field = value
            println("set $value")
        }
}
//sampleEnd

class Box {
    private var _name: String = "name"
    val name: String
        get() = _name

    fun customName(haha: String) {
        _name = haha
//        name = haha
    }
}