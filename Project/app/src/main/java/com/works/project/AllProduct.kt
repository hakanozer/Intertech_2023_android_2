package com.works.project

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.works.project.adapters.ProductAdapter
import com.works.project.configs.ApiClient
import com.works.project.models.Product
import com.works.project.models.Products
import com.works.project.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AllProduct : AppCompatActivity() {

    lateinit var dummyService: DummyService
    var arr = listOf<Product>()
    lateinit var listeView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_product)

        listeView = findViewById(R.id.listView)

        dummyService = ApiClient().getClient().create(DummyService::class.java)
        dummyService.allProducts().enqueue(object : Callback<Products> {
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                val status = response.code()
                if (status == 200) {
                    val products = response.body()
                    products?.let {
                        arr = it.products
                        val productAdapter = ProductAdapter(this@AllProduct, arr)
                        listeView.adapter = productAdapter
                    }
                }
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {
                Toast.makeText(this@AllProduct, "Server Error!", Toast.LENGTH_SHORT).show()
            }
        })

        // list item click
        listeView.setOnItemClickListener { parent, view, position, id ->
            val item = arr.get(position)
            ProductDetail.item = item
            val intent = Intent(this, ProductDetail::class.java)
            startActivity(intent)
        }

    }


    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exit")
        builder.setMessage("Are you sure, exit?")
        builder.setPositiveButton("Yes") { dialog, which ->
            super.onBackPressed()
        }
        builder.setNegativeButton("Cancel") { dialog, which -> }
        val alert = builder.create()
        alert.show()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if ( item.itemId == R.id.m_likes ) {
            val intent = Intent(this@AllProduct, Likes::class.java)
            startActivity(intent)
        }
        if ( item.itemId == R.id.m_logout ) {
            val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove("id")
            editor.remove("name")
            editor.remove("surname")
            editor.remove("token")
            editor.remove("image")
            editor.commit()
            val intent = Intent(this@AllProduct, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        return true
    }

}

