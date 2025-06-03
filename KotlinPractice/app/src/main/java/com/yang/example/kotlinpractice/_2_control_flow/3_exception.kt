package com.yang.example.kotlinpractice._2_control_flow


/**
 * Kotlin 默认不需要 在方法名 throw 向上传递 或者 try-catch
 *
 * require(Boolean) {} 	 检查是否符合条件	IllegalArgumentException
 * check(Boolean) {}     检查对象或变量是否有效，一般做空检查	IllegalStateException
 *      checkNotNull()
 * error() {}	          不接受条件，直接抛，这个一般是在条件内调用 	IllegalStateException
 *
 * require(), check(), 支持智能转换类型
 *
 * try-catch   可以用作表达式，从 try 或 catch 返回最后一个表达式的值
 *
 * Nothing 类型
 * Kotlin 中每一个表达式都有一个类型，throw IllegalArgumentException() 的类型是 Nothing
 * 是一个 build-in 的类别，是所有其它类型的子类
 * 可以用于 return 类别，泛型类别
 *
 * 代表方法或表达式没有成功指向完成，抛异常，无限循环
 *
 * TODO() 方法就是返回 Nothing
 * public inline fun TODO(): Nothing = throw NotImplementedError()
 *
 */
fun main() {

//    getIndices(-10)
    println(getIndices(3))

    var someState: String? = null
    someState = ""
    someState = "non-empty-state"
//    println(getStateValue(null))
//    println(getStateValue(""))
    println(getStateValue("non-empty-state"))


    val user1 = User("Alice", "admin")
    processUserRole(user1)
    val user2 = User("Bob", "guest")
//    processUserRole(user2)

    val num: Int = try {
        // If count() completes successfully, its return value is assigned to num
        count()
    } catch (e: ArithmeticException) {
        // If count() throws an exception, the catch block returns -1,
        // which is assigned to num
        -1
    }
    println("Result: $num")


    val availableFunds = 500.0
    // Change this value to test different scenarios
    val withdrawalAmount = 400.5

    try {
//        processWithdrawal(withdrawalAmount.toDouble(), availableFunds)
        // The order of catch blocks is important!
    } catch (e: InsufficientFundsException) {
        println("Caught an InsufficientFundsException: ${e.message}")
    } catch (e: WithdrawalException) {
        println("Caught a WithdrawalException: ${e.message}")
    }

//    nothingType()


}

fun getStateValue(someState: String?): String {
    val state =
        checkNotNull(someState) { "State must be set beforehand!" }      // IllegalStateException
    check(state.isNotEmpty()) { "State must be non-empty!" }
    return state
}

fun getIndices(count: Int): List<Int> {
    require(count >= 0) { "Count must be non-negative. You set count to $count." }
    return List(count) { it + 1 }
}

class User(val name: String, val role: String)

fun processUserRole(user: User) {
    when (user.role) {
        "admin" -> println("${user.name} is an admin.")
        "editor" -> println("${user.name} is an editor.")
        "viewer" -> println("${user.name} is a viewer.")
        else -> error("Undefined role: ${user.role}")
    }
}

// Simulates a function that might throw ArithmeticException
fun count(): Int {

    // Change this value to return a different value to num
    val a = 0

    return 10 / a
}


open class WithdrawalException(message: String) : Exception(message)
class InsufficientFundsException(message: String) : WithdrawalException(message)

fun processWithdrawal(amount: Double, availableFunds: Double) {
    if (amount > availableFunds) {
        throw InsufficientFundsException("Insufficient funds for the withdrawal.")
    }
    if (amount < 1 || amount % 1 != 0.0) {
        throw WithdrawalException("Invalid withdrawal amount.")
    }
    println("Withdrawal processed")
}

fun nothingType(): Nothing {
    throw Exception()
}
