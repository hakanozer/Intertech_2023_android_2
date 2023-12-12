package com.works.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val username = intent.getStringExtra("username")
        username?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

    }
}