package com.yang.example.kotlinpractice.common


class Cup {
    var color: String = "red"
    var size: Int = 100

    override fun toString(): String {
        return "Cup: color-${color}, size-$size"
    }
}

fun drinkWater() {
    println("drink some water.")
}

fun drinkTea() {
    println("drink some tea.")
}