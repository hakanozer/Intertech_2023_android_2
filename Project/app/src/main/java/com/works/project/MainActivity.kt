package com.works.project

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.works.project.configs.ApiClient
import com.works.project.models.JWTUser
import com.works.project.models.SendUser
import com.works.project.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var txtUserName: EditText
    lateinit var txtPassword: EditText
    lateinit var btnLogin: Button
    lateinit var btnRegister: Button

    lateinit var dummyService: DummyService
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        txtUserName = findViewById(R.id.txtUserName)
        txtPassword = findViewById(R.id.txtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnRegister)

        dummyService = ApiClient().getClient().create(DummyService::class.java)
        btnLogin.setOnClickListener {
            val username = txtUserName.text.toString()
            val password = txtPassword.text.toString()

            if (username == "") {
                Toast.makeText(this, "Username Empty!", Toast.LENGTH_LONG).show()
                txtUserName.requestFocus()
            }else if ( password == "" ) {
                Toast.makeText(this, "Password Empty!", Toast.LENGTH_SHORT).show()
                txtPassword.requestFocus()
            }else {
                // Form Send
                val sendUser = SendUser(username, password)
                dummyService.login(sendUser).enqueue( object : Callback<JWTUser> {
                    override fun onResponse(call: Call<JWTUser>, response: Response<JWTUser>) {
                        val statusCode = response.code()
                        if (statusCode == 200) {
                            val jwt = response.body()
                            jwt?.let {
                                // user.xml data write
                                editor.putLong("id", it.id)
                                editor.putString("name", it.firstName)
                                editor.putString("surname", it.lastName)
                                editor.putString("token", it.token)
                                editor.putString("image", it.image)
                                editor.commit()

                                val intent = Intent(this@MainActivity, AllProduct::class.java)
                                startActivity(intent)

                                // This Actvity Finish
                                finish()
                            }
                        }else {
                            Toast.makeText(this@MainActivity, "Username or Password Fail!", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<JWTUser>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Server or Internet Connection Error!", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }

        // Register Button Click
        btnRegister.setOnClickListener {
            val username = txtUserName.text.toString()
            val intent = Intent(MainActivity@this, Register::class.java)
            intent.putExtra("username", username)
            startActivity(intent)
        }

    }


}