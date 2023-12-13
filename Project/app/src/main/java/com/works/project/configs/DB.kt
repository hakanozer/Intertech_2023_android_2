package com.works.project.configs

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DB (context: Context): SQLiteOpenHelper(context, "prohect.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val likesSql = "CREATE TABLE \"likes\" (\n" +
                "\t\"lid\"\tINTEGER UNIQUE,\n" +
                "\t\"pid\"\tINTEGER UNIQUE,\n" +
                "\tPRIMARY KEY(\"lid\" AUTOINCREMENT)\n" +
                ");"
        db?.execSQL(likesSql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val likesSql = "drop table if exists likes"
        db?.execSQL(likesSql)
        onCreate(db)
    }


    fun addLike( pid: Long ) : Long {
        try {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put("pid", pid)
            val effectRow = db.insert("likes", null, values)
            db.close()
            return effectRow
        }catch (ex: Exception) {
            return -1
        }
    }

    fun singleLike ( pid: Long ) : Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("select * from likes where pid = $pid", null)
        val status = cursor.moveToNext()
        db.close()
        return status
    }

    fun removeLike ( pid: Long ) : Int {
        val db = this.writableDatabase
        val status = db.delete("likes", "pid = $pid", null )
        db.close()
        return status
    }

    fun allLikes() : List<Long> {
        val db = this.readableDatabase
        val arr = mutableListOf<Long>()
        val cursor = db.query("likes", null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val pid = cursor.getLong(1)
            arr.add(pid)
        }
        db.close()
        return arr
    }


}