package com.works.days_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.works.days_1.utils.Action
import com.works.days_1.utils.EApp
import com.works.days_1.utils.User

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Değişkenler
        // var, val
        var name = "Ali"
        name = "Erkan"
        val surname = "Bilsin" // val -> final, const

        // optional
        var address:String? = "İstanbul"
        address?.let {
            Log.d("address", it)
        }

        val num1:Byte = 100
        val num2:Short = 32500
        val num3:Int = 2147000000
        val num4:Long = 934534534534534

        val num5 = 10.5f
        val num6 = 10.5


        // Arrays
        val arr1 = arrayOf("Ali", "Zehra", "Selin", "Kemal", "Kenan")
        val arr = mutableListOf<String>()
        arr.add("İstanbul")
        arr.add("İzmir")
        arr.add("Ankara")
        arr.add("Ankara")
        arr.add("Ankara")
        arr.add("Bursa")
        arr.add("Adana")
        arr.add("Adana")
        arr.add("Adana")
        arr.add("Gaziantep")
        arr.add("Gaziantep")
        arr.add(3, "Samsun")

        arr.set(4, "Eskişehir")
        // delete item
        //arr.removeAt(0)
        //arr.remove("İstanbul")

        Log.d("arr", arr.toString())

        // loop
        for (item in arr) {
            Log.d("item", item)
        }

        val count = arr.count()
        Log.d("count", count.toString())
        for (i in 0..count - 1 ) {
            val item = arr.get(i)
            Log.d("itm", item)
        }

        // Set -> benzersiz nesneleri kendi içinde tutar.
        val set = mutableSetOf<String>()
        set.add("İstanbul")
        set.add("İzmir")
        set.add("Ankara")
        set.add("Ankara")
        set.add("Ankara")
        set.add("Bursa")
        set.add("Adana")
        set.add("Adana")
        set.add("Adana")
        set.add("Gaziantep")
        set.add("Gaziantep")
        Log.d("set", set.toString())

        // Map
        val map = mutableMapOf<String, Any>()
        map.put("name", "Ali")
        map.put("surname", "Bilirim")
        map.put("email", "ali@mail.com")
        map.put("age", 35)
        map.put("status", true)
        map.put("surname", "Bilsin")
        Log.d("map", map.toString())


        val email = map.get("email")
        val agex = map.get("age")
        Log.d("email", email.toString())

        val mapCount = map.count()
        Log.d("mapCount", mapCount.toString())

        agex?.let {
            if ( it is Int ) {
                val cAge = it.toString().toInt()
                val sum = cAge + 50
                Log.d("Sum", sum.toString())
            }
        }

        val keys = map.keys
        for ( key in keys ) {
            val item = "${key} - ${map.get(key)}"
            Log.d("key", item)
        }

        for ((k, v) in map) {
            Log.d(k, v.toString())
        }

        // Enum Map
        val map1 = mutableMapOf<EApp, Any>()
        map1.put(EApp.NAME, "Kemal")
        map1.put(EApp.SURNAME, "Bil")
        map1.put(EApp.EMAIL, "ali@mail.com")
        map1.put(EApp.AGE, 25)

        val mp1Name = map1.get(EApp.EMAIL)
        Log.d("mapEnum", map1.toString())

        // OOP
        val action = Action()
        Log.d("count", action.count.toString())
        action.noParams()

        action.sum(40,55)
        val min = action.minus(77, 55)
        if (min > 10) {
            Log.d("min", "min > 10")
        }else {
            Log.d("min", "min < 10")
        }

        // Set fnc
        val newSet = action.call(set)
        Log.d("newSet", newSet.toString())

        val st = action.countData(arr)
        Log.d("st size", st)

        val u1 = User("Erkan", "Bilirim", "erkan@mail.com", "12345")
        Log.d("u1", u1.name)
        Log.d("u1", u1.surname)
        Log.d("u1", u1.email)
        Log.d("u1", u1.password)

        val u2 = action.modUser(u1);
        Log.d("New User", u2.toString())

        // Data object List
        val u3 = User("Zehra", "Bil", "zehra@mail.com", "12345")
        val u4 = User("Selin", "Bilsin", "selin@mail.com", "12345")
        val ls = mutableListOf<User>()
        ls.add(u1)
        ls.add(u3)
        ls.add(u4)

        for(item in ls) {
            Log.d("User", item.toString())
        }

        //val arrLs = arrayListOf<User>(u1, u2, u3, u4)

    }

}