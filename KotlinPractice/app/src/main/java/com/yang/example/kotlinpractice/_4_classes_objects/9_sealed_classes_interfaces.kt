package com.yang.example.kotlinpractice._4_classes_objects

import java.io.File
import javax.sql.DataSource

/**
 * 密封类与密封接口
 *  有限的直接子类或实现类, 模块或包外不能继承
 *  默认是 abstract
 *  密封类的构造器默认是 protected
 *
 *  使用密封类的关键好处在于使用 when 表达式的时候，可以穷尽，不用 else
 *      可以用于 UI 的状态管理
 *      Payment method handling       支付方式
 *      API request-response handling 网络请求响应
 */
fun main() {

    val errors = listOf(Error2.NetworkError(), Error2.DatabaseError(), Error2.UnknownError())
    errors.forEach { println(it.message) }
}


sealed class Error2(val message: String) {
    class NetworkError : Error2("Network failure")
    class DatabaseError : Error2("Database cannot be reached")
    class UnknownError : Error2("An unknown error has occurred")
}


// Create a sealed interface
sealed interface Error

// Create a sealed class that implements sealed interface Error
abstract sealed class IOError() : Error

// Define subclasses that extend sealed class 'IOError'
class FileReadError(val file: File) : IOError()
class DatabaseError(val source: DataSource) : IOError()

// Create a singleton object implementing the 'Error' sealed interface
object RuntimeError : Error

//class MYClass : Error

