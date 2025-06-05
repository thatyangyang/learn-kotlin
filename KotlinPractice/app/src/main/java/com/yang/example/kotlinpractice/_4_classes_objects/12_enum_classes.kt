package com.yang.example.kotlinpractice._4_classes_objects

import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator

/**
 * 枚举类
 *  内部可以有属性
 *  可以有一般方法, 枚举常量与方法之间使用 ";" 分隔
 *  可以有抽象方法，必须被实现;
 *
 *  实现接口时, 枚举类和其常量需要实现接口方法
 *
 *  可以按枚举常量的字符串名称取值  valueOf("NAME")
 *
 *  不能被继承
 */
fun main() {

    println(Direction.entries.joinToString())

    println(CColor.entries.joinToString())

    for (item in CColor.entries) {
        println("${item.name}  ${item.rgb}")
    }

    try {
        println(CColor.valueOf("WHITE"))
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }


}

enum class Direction {

    NORTH {
        override fun yan() {
            yang2()
        }
    },
    SOUTH {
        override fun yan() {

        }
    },
    WEST {
        override fun yan() {

        }
    },
    EAST {
        override fun yan() {

        }
    };

    abstract fun yan()

    fun yang2() {

    }

}

enum class CColor(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF),
}

enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    },

    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocolState
}

enum class IntArithmetics : BinaryOperator<Int>, IntBinaryOperator {
    PLUS {
        override fun apply(t: Int, u: Int): Int = t + u
    },
    TIMES {
        override fun apply(t: Int, u: Int): Int = t * u
    };

    override fun applyAsInt(t: Int, u: Int) = apply(t, u)
}
