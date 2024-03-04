package chapter01

import java.util.*
import kotlin.concurrent.thread
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

var name: String = "Marcin"
var surname: String = "Moskala"
val fullName
    get() = "$name $surname"

/*

fun main() {
    println(fullName) // Marcin Moskala
    name = "Maja"
    println(fullName) // Maja Moskala
}
*/


/*fun calculate(): Int {
    print("Calculating...")
    return 42
}

val fizz = calculate() //계산합니다...
val buzz
    get() = calculate()*/
/*
fun main(){
    println(fizz)   //Calculating...42
    println(fizz)   //42
    println(buzz)   //Calculating...42
    println(buzz)   //Calculating...42
}
*/


interface Element {
    val active: Boolean
}

class ActualElement: Element {
    override var active: Boolean = false
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, delegated property name: '${property.name}'"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$thisRef, delegated property name: '${property.name}' set with value: '$value'")
    }
}

/*class Example {
    // "delegate" 프로퍼티에 Delegate 클래스를 델리게이트로 사용
    var delegate: String by Delegate()
}*/
/*

fun main() {
    val example = Example()

    // getter 호출 (델리게이트의 getValue가 호출됨)
    println(example.delegate) // 출력: "Example@123, delegated property name: 'delegate'"

    // setter 호출 (델리게이트의 setValue가 호출됨)
    example.delegate = "New Value" // 출력: "Example@123, delegated property name: 'delegate' set with value: 'New Value'"

    println(example.delegate)
}*/

/*

class Example2{
    private val name: String? = "maraton"
    private val surname: String = "Braun"

    val fullName: String?
        get() = name?.let { "$it $surname" }

    val fullName2: String? = name?.let { "$it $surname" }
}

fun main(){

    val example2 = Example2()

    if (example2.fullName != null) {    //getter로 정의하였기때문에  스마트 캐스트 x
        println(example2.fullName.length)
    }

    if (example2.fullName2 != null){    //
        println(example2.fullName2.length)
    }

}
*/

/*


class Example {
    // getter로 선언된 프로퍼티
    val nameWithGetter: String
        get() = "John Doe"

    // 직접 변수에 접근되는 프로퍼티
    val nameDirectAccess: String = "Jane Doe"
}

fun main() {
    val example = Example()

    // getter로 선언된 프로퍼티에 대한 스마트 캐스트
    if (example.nameWithGetter != null) {
        println(example.nameWithGetter.length)  // 여기서는 문제 없음
    }

    // 직접 변수에 접근되는 프로퍼티에 대한 스마트 캐스트
    if (example.nameDirectAccess != null) {
        // 컴파일 에러: Variable 'example.nameDirectAccess' must be initialized
        println(example.nameDirectAccess.length)
    }
}

*/

/*

inline fun <T,R> Iterable<T>.map(
    transformation: (T) -> R
): List<R> {
    val list = ArrayList<R>()
    for (elem in this) {
        list.add(transformation(elem))
    }

    return list
}
*/

/*


fun main() {
    val list: List<Int> = listOf(1, 2, 3)

*/
/*    if(list is MutableList){
        list.add(4)
    }*//*

    val mutableList = list.toMutableList()
    mutableList.add(4)
    println(list)
}*/
/*

data class FullName(var firstName: String, var lastName: String) : Comparable<FullName> {
    override fun compareTo(other: FullName): Int {
        return compareValuesBy(this, other, { it.firstName }, { it.lastName })
    }
}

fun main(){
    val names: SortedSet<FullName> = TreeSet()
    val person = FullName("AAA","AAA")
    names.add(person)
    names.add(FullName("Jordan","Hansen"))

    println(names)
    println(person in names)

    person.firstName = "ZZZ"

    println(names)
    println(person in names)
}


*/
/*


data class User(
    val name: String,
    val surname: String
)


fun main(){
    var user = User("재진", "심")
    user = user.copy(surname="박")
    print(user)
}*/

/*
fun main(){
    val list1: MutableList<Int> = mutableListOf()
    var list2: List<Int> = listOf()

    list1.add(1)
    list2 = list2 + 1

    list1 += 1 //list1.plusAssign(1) 로 변경
    list2 += 1 //list2 = list2.plus(1) 로 변경

    println(list1)
    println(list2)
}
*/

/*


fun main()  {
    var list = listOf<Int>()
    val lock = Object()
    for (i in 1..1000 ){
        thread{
            synchronized(lock) {
                list = list + 1
            }

        }
    }
    Thread.sleep(1000)
    println(list.size)
}

*/

/*

fun main() {
    val list = mutableListOf<Int>()
    val lock = Object()

    for (i in 1..1000) {
        thread {
            synchronized(lock) {
                list.add(1)
            }
        }
    }

    Thread.sleep(1000)
    println(list.size)  // 1000
}
*/

/*

fun main() {
    var names by Delegates.observable(listOf<String>()){
        _, old, new ->
        println("Names changed from $old to $new")
    }

    names += "Fabio"
    println(names)
    names += "Bill"
    println(names)
}*/

/*
data class User(
    val name: String
)

class UserRepository{
    private val storedUsers: MutableMap<Int, String> =
        mutableMapOf()

    fun loadAll(): MutableMap<Int, String> {
        return storedUsers
    }
}

fun main() {
    val userRepository = UserRepository()

    val storedUsers = userRepository.loadAll()
    storedUsers[4] = "sss"

    println(userRepository.loadAll())
}
*/
/*

data class MutableUser (
    val name: String
)
class UserHolder {
    private val user =  MutableUser("sim")
    fun get(): MutableUser {
        return user.copy()
    }

}

fun main() {
    val userHolder = UserHolder()
    println(userHolder.get())
}
*/

data class User(val name: String)

class UserRepository {
    private val storedUsers: MutableMap<Int, String> =
        mutableMapOf()

    fun loadAll(): Map<Int,String> {
        return storedUsers
    }
}

fun main(){
    val storedUsers = UserRepository()
    println(storedUsers.loadAll())
}
