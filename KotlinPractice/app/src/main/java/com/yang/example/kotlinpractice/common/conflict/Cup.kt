package com.yang.example.kotlinpractice.common.conflict

class Cup {
    var color: String = "blue"
    var size: Int = 200
    override fun toString(): String {
        return "Cup: color-${color}, size-$size"
    }
}