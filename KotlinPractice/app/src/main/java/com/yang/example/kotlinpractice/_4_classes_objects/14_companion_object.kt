package com.yang.example.kotlinpractice._4_classes_objects

/**
 * 对象声明与对象表达式
 *
 * 对象声明
 *  objects 定义一个类并创建一个实例，用于单例或一次性的对象—————— 声明的过程就是对象表达式
 *
 * 场景：
 *  单例对象
 *  创建工厂方法
 *  临时性/一次性的修改类的行为———匿名实现
 *
 * object MyClassName {}
 * 引用该对象（object），直接使用其名称即可，
 *      调用其方法和属性，就像静态方法和属性一样
 *
 *      object MyObject   没有属性/方法，空类，单例，只有 Any 的方法
 *
 * data object MyDataObject {}
 *      编译器自动生成以下方法：
 *          toString() 返回对象名  如果自己重写了，则不是
 *          equals()/hashCode()
 *      只能使用 ==  不能使用 ===
 *
 *      相比于 data class XXX， 没有 copy(), componentN()
 *
 *      与密封类/接口一起使用
 *
 * 
 * 伴生对象
 *
 *
 *
 * 对象表达式
 *
 *
 *
 *
 */
fun main() {
    DataProviderManager.registerDataProvider(ExampleDataProvider())
    DataProviderManager.registerDataProvider(ExampleDataProvider())
    println(DataProviderManager.allDataProviders.joinToString { it.provideData() })

    DefaultListener.mouseClicked(MouseEvent())
    DefaultListener.mouseEntered(MouseEvent())

    println(MyObject.toString())

    println(MyDataObject)

    println(Number(7))
    // Number(number=7)
    println(EndOfFile)
    // EndOfFile
}


object DataProviderManager {
    private val providers = mutableListOf<DataProvider>()

    // Registers a new data provider
    fun registerDataProvider(provider: DataProvider) {
        providers.add(provider)
    }

    // Retrieves all registered data providers
    val allDataProviders: Collection<DataProvider>
        get() = providers
}

interface DataProvider {
    fun provideData(): String
}

class ExampleDataProvider : DataProvider {
    override fun provideData(): String {
        return "Example data"
    }
}

class MouseEvent {

}

abstract class MouseAdapter {
    abstract fun mouseClicked(e: MouseEvent)
    abstract fun mouseEntered(e: MouseEvent)
}

object DefaultListener : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent) {
        println("mouseClicked")
    }

    override fun mouseEntered(e: MouseEvent) {
        println("mouseEntered")
    }
}


// 空类，单例
object MyObject

/***************/

data object MyDataObject {
    val number: Int = 3

    /*override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }*/
    /*override fun toString(): String {
        return "$number"
    }*/
}


sealed interface ReadResult
data class Number(val number: Int) : ReadResult
data class Text(val text: String) : ReadResult
data object EndOfFile : ReadResult


