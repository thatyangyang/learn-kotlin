package com.yang.example.kotlinpractice._4_classes_objects


/**
 * 调用超类实现
 * 派生类中的代码可以使用 super 关键字调用其超类的函数与属性访问器的实现：
 *      在一个内部类中访问外部类的超类，可以使用由外部类名限定的 super 关键字来实现：super@Outer
 *
 * 覆盖规则
 *  在 Kotlin 中，实现继承由下述规则规定：如果一个类从它的直接超类继承相同成员的多个实现， 它
 *  必须覆盖这个成员并提供其自己的实现（也许用继承来的其中之一）。
 *
 *  如需表示采用从哪个超类型继承的实现，请使用由尖括号中超类型名限定的 super<Base1>.draw(), super<Base2>.draw()
 *
 *
 */
fun main() {
    println("Constructing the derived class(\"hello\", \"world\")")
    DerivedB("hello", "world")
}

open class BaseB(val name: String) {

    init {
        println("Initializing a base class")
    }

    open val size: Int =
        name.length.also { println("Initializing size in the base class: $it") }
}

class DerivedB(
    name: String,
    val lastName: String,
) : BaseB(name.replaceFirstChar { it.uppercase() }
    .also { println("Argument for the base class: $it") }) {

    init {
        println("Initializing a derived class")
    }

    override val size: Int =
        (super.size + lastName.length).also { println("Initializing size in the derived class: $it") }
}

/*******************/

open class Rectangle {
    open fun draw() {
        println("Drawing a rectangle")
    }

    val borderColor: String get() = "black"
}

//sampleStart
class FilledRectangle : Rectangle() {
    override fun draw() {
        val filler = Filler()
        filler.drawAndFill()
    }

    inner class Filler {
        fun fill() {
            println("Filling")
        }

        fun drawAndFill() {
            super@FilledRectangle.draw() // 调用 Rectangle 的 draw() 实现
            fill()
            println("Drawn a filled rectangle with color ${super@FilledRectangle.borderColor}") // 使用 Rectangle 所实现的 borderColor 的 get()
        }
    }
}

interface Polygon {
    fun draw() {  println("Polygon.draw") } // 接口成员默认就是“open”的
}

// 覆盖规则
// 在 Kotlin 中，实现继承由下述规则规定：如果一个类从它的直接超类继承相同成员的多个实现
class Square() : Rectangle(), Polygon {
    // 编译器要求覆盖 draw()：
    override fun draw() {
        super<Rectangle>.draw() // 调用 Rectangle.draw()
        super<Polygon>.draw() // 调用 Polygon.draw()
    }
}