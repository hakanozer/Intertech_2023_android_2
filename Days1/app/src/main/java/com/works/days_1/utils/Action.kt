package com.works.days_1.utils

import android.util.Log
import kotlin.math.roundToInt
import kotlin.random.Random

class Action {

    val count = 40

    fun noParams() {
        Log.d("noParams", "noParams: Call ")
    }

    fun sum( num1: Int, num2: Int ) {
        val sm = num1 + num2
        Log.d("sum", sm.toString())
    }

    fun minus( num1: Int, num2: Int ) : Int {
        val min = num1 - num2
        return min
    }

    fun call( set: MutableSet<String> ) : MutableSet<String> {
        val appSet = mutableSetOf<String>()
        for (item in set) {
            //val itm = "a-${item}"
            val itm = item.uppercase()
            appSet.add(itm)
        }
        return appSet
    }

    fun countData( list: MutableList<String> ) : String {
        var item = ""
        var count = 0
        for( data in list ) {
            for (x in list) {
                if (x.count() > count) {
                    item = x
                }
            }
            count = data.count()
        }
        return item
    }

    fun modUser( user: User ) : User {
        val random = Math.random()
        user.password = "${random}${user.password}"
        return user
    }


}