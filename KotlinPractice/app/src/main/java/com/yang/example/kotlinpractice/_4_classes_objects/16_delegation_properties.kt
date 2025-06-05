package com.yang.example.kotlinpractice._4_classes_objects

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * 属性委托
 *  场景
 *      延迟属性（lazy properties）: 其值只在首次访问时计算。
 *      可观察属性（observable properties）: 监听器会收到有关此属性变更的通知
 *      把多个属性储存在一个映射（map）中，而不是每个存在单独的字段中
 *
 *  val/var <属性名>: <类型> by <表达式>
 *      在 by 后面的表达式是该 委托， 因为属性对应的 get()（与 set()）会被委托给它的 getValue() 与 setValue() 方法。
 *      属性的委托不必实现接口，但是需要提供一个 getValue() 函数（对于 var 属性还有 setValue()）。
 *
 *  属性的委托就是实现 getValue(), setValue() 方法
 *
 *  标准委托
 *  1. 延迟属性
 *      by lazy {}     只能用于 val
 *      Lazy 是一个接口，有一个抽象属性和方法
 *
 *  2. 可观察属性
 *      by Delegates.observable("") {}
 *
 *  3. 委托给另一个属性
 *      by this::memberInt
 *      by ::topLevelInt
 *      by anotherClassInstance::anotherClassInt
 *
 *      一个属性可以把它的 getter 与 setter 委托给另一个属性。这种委托对于顶层和类的属性（成员和扩展）都可用。该委托属性可以为：
 *          顶层属性
 *          同一个类的成员或扩展属性
 *          另一个类的成员或扩展属性
 *
 *  4. 将属性储存在映射中
 *      在一个映射（map）里存储属性的值
 *      val name: String by map
 *
 *  5. 局部委托属性
 *      需要 () -> PropertyType 的函数对象
 *
 *  6. 属性委托要求
 *      operator fun getValue(thisRef: Owner, property: KProperty<*>): Resource {}
 *      operator fun setValue(thisRef: Owner, property: KProperty<*>, value: Any?) {}
 *
 *  7. 提供委托
 *      通过定义 provideDelegate 操作符，可以扩展创建属性实现所委托对象的逻辑。
 *
 */
fun main() {

    val example = Example()
    println(example.p)
    example.p = "hello shu ge"

    val lazyValue: String by lazy {
        println("computed!")
        "Hello"
    } // 执行这个语句的时候，没有打印；第一次访问这个属性的时候，才打印
    println(lazyValue)
    println(lazyValue)
//    lazyValue = "hello world"  没有 setValue 方法， by lzy {} 只能用于 val

    val user162 = User162()
    println("user162.name: ${user162.name}")  // 默认值
    user162.name = "first"
    println("user162.name: ${user162.name}")
    user162.name = "second"
    println("user162.name: ${user162.name}")

    println()

    val classWithDelegate = ClassWithDelegate(1000)
    val myClass16 = MyClass16(topLevelInt, classWithDelegate)
    println(myClass16.delegatedToMember)
    println(myClass16.delegatedToTopLevel)
    println(myClass16.extDelegated)
    println(myClass16.delegatedToAnotherClass)

    topLevelInt = 9
//    classWithDelegate.anotherClassInt = 9999
    println(myClass16.delegatedToMember)
    println(myClass16.delegatedToTopLevel)
    println(myClass16.extDelegated)
    println(myClass16.delegatedToAnotherClass)

    val myClass = MyClass162()
    // 通知：'oldName: Int' is deprecated.
    // Use 'newName' instead
    myClass.oldName = 42
    println(myClass.newName) // 42
    println(myClass.oldName) // 42
    myClass.newName = 66
    println(myClass.newName) // 42
    println(myClass.oldName) // 42

    val user1623 = User1623(
        mutableMapOf(
            "name" to "yang",
            "age" to 32
        )
    )
    println(user1623.name)
    println(user1623.age)


    val foooo = {
        object : Foo {
            override fun doSomething() {
                println("doSomething")
            }

        }
    }
//    example(foooo)
    example {
        val fooObject = object : Foo {
            override fun doSomething() {
                println("doSomething")
            }
        }
        println("create fooObject")
        fooObject
    }

}


class Resource

class Owner {
    var varResource: Resource by ResourceDelegate()
}

class ResourceDelegate(private var resource: Resource = Resource()) {
    operator fun getValue(thisRef: Owner, property: KProperty<*>): Resource {
        return resource
    }

    operator fun setValue(thisRef: Owner, property: KProperty<*>, value: Any?) {
        if (value is Resource) {
            resource = value
        }
    }
}

/*****************/


fun interface Foo {
    fun doSomething()
    fun isValid(): Boolean {
        return true
    }
}

var someCondition = false

fun example(computeFoo: () -> Foo) {
    val memoizedFoo by lazy(computeFoo) // 只有第一次访问 memoizedFoo 时，才会调用方法创建 Foo

    if (someCondition && memoizedFoo.isValid()) {
        memoizedFoo.doSomething()
    }
}

/******************/

class User1623(val map: MutableMap<String, Any>) {
    var name: String by map
    var age: Int by map
}

// newName 为什么会受 oldName 影响? 因为 getValue 和 setValue 都委托给 newName; 写oldName,就写了 newName
class MyClass162 {
    var newName: Int = 0

    @Deprecated("Use 'newName' instead", ReplaceWith("newName"))
    var oldName: Int by this::newName
}

/********/

var topLevelInt: Int = 0

class ClassWithDelegate(val anotherClassInt: Int)

class MyClass16(var memberInt: Int, val anotherClassInstance: ClassWithDelegate) {
    var delegatedToMember: Int by this::memberInt
    var delegatedToTopLevel: Int by ::topLevelInt

    val delegatedToAnotherClass: Int by anotherClassInstance::anotherClassInt
}

// 扩张属性
var MyClass16.extDelegated: Int by ::topLevelInt


/****************/

class User162 {
    //    var name: String by Delegates.observable("") { prop, old, new ->
//        println("$old -> $new")
//    }
    val observable = Delegates.observable("") { prop, old, new ->
        println("${prop.name}   $old -> $new")
    }
    var name: String by observable
//    var gender by observable
}


class Example {
    var p: String by Delegate()

}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}