package com.yang.example.kotlinpractice._4_classes_objects

/**
 * 对象声明与对象表达式
 *
 * 对象表达式————匿名对象
 *  声明类和实例，没有命名；可以继承和实现现存的类和接口（= 匿名内部类）
 *
 * object {}
 * object : SuperClass {}
 *
 * 匿名对象，可以作为返回值
 *      object {}  函数返回 Any, 所以内部属性不可访问
 *      object : SuperType, SuperInterface {}     函数返回 最后一个父类/父接口, 所以内部属性不可访问
 *
 * 可以访问外部属性
 *      对象表达式主体内的代码可以访问来自包含它的作用域的变量
 *
 * 对象声明与对象表达式在初始化行为方面存在差异：
 *      对象表达式是在使用他们的地方立即执行（及初始化）的。
 *      对象声明是在第一次被访问到时延迟初始化的。
 *      伴生对象的初始化是在相应的类被加载（解析）时，与 Java 静态初始化器的语义相匹配 。
 */
fun main() {

    val helloWorld = object {
        val hello = "Hello"
        val world = "World"

        override fun toString() = "$hello $world"
    }

    println(helloWorld)

    val bankAccount = BankAccount(1000)
    specialTransaction(bankAccount)

    UserPreference().printPreferences()

    val notificationManager = NotificationManager()
    notificationManager.getNotification()

    object {
        val theme: String = "Dark"
        val fontSize: Int = 14
    }


}

fun getNotificationddd() = object {
    val message: String = "General notification"
}

open class BankAccount(initialBalance: Int) {
    open val balance: Int = initialBalance
}

// Defines an interface Transaction with an execute() function
interface Transaction {
    fun execute()
}

fun specialTransaction(account: BankAccount) {
    val temporaryAccount = object : BankAccount(account.balance), Transaction {
        override val balance: Int
            get() = super.balance + 500

        override fun execute() {
            println("Executing special transaction. New balance is $balance.")
        }

    }
    temporaryAccount.execute()

}


class UserPreference {
    private fun getPreferences() = object {
        val theme: String = "Dark"
        val fontSize: Int = 14
    }

    fun printPreferences() {
        val preferences = getPreferences()
        println("Theme: ${preferences.theme}, Font Size: ${preferences.fontSize}")
    }
}

/***************************/

//sampleStart
interface Notification {
    fun notifyUser()
}

open class NotificationEntity {
    val name: String = "NotificationEntity"
}

interface DetailedNotification

class NotificationManager {
    fun getNotification() = object {
        val message: String = "General notification"
    }

    // The return type is Notification because the anonymous object implements only one interface
    // The notifyUser() function is accessible because it is part of the Notification interface
    // The message property is not accessible because it is not declared in the Notification interface
    fun getEmailNotification() = object : Notification {
        override fun notifyUser() {
            println("Sending email notification")
        }

        val message: String = "You've got mail!"
    }

    // The return type is DetailedNotification. The notifyUser() function and the message property are not accessible
    // Only members declared in the DetailedNotification interface are accessible
    fun getDetailedNotification(): DetailedNotification =
        object : Notification, DetailedNotification {
            override fun notifyUser() {
                println("Sending detailed notification")
            }

            val message: String = "Detailed message content"
        }

    fun getNotificationEntity(): DetailedNotification =
        object : NotificationEntity(), Notification, DetailedNotification {
            override fun notifyUser() {
                println("Sending detailed notification")
            }

            val message: String = "Detailed message content"
            val time: Int = 1000
        }

    fun test() {
        val any = getNotification()
//        any.message   // no

        val notification = getEmailNotification()
        notification.notifyUser()
//        notification.message  // no

        val detailedNotification = getDetailedNotification()
//        detailedNotification.notifyUser()     // no
//        detailedNotification.message          // no

        val detailedNotification2 = getNotificationEntity()
//        detailedNotification2.n
    }
}

/*****************/

fun countClicks() {
    var clickCount = 0
    var enterCount = 0

    val emp = object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent) {
            clickCount++
        }

        override fun mouseEntered(e: MouseEvent) {
            enterCount++
        }
    }

}

