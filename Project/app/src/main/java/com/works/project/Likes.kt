package com.works.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.works.project.adapters.ProductAdapter
import com.works.project.configs.ApiClient
import com.works.project.configs.DB
import com.works.project.models.Product
import com.works.project.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Likes : AppCompatActivity() {

    val arr = mutableListOf<Product>()
    lateinit var dummyService: DummyService
    lateinit var listView: ListView
    lateinit var productAdapter: ProductAdapter
    lateinit var db:DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_likes)

        db = DB(this)
        listView = findViewById(R.id.likesListView)
        dummyService = ApiClient().getClient().create(DummyService::class.java)

        productAdapter = ProductAdapter(this@Likes, arr)
        listView.adapter = productAdapter


        // list item click
        listView.setOnItemClickListener { parent, view, position, id ->
            val item = arr.get(position)
            ProductDetail.item = item
            val intent = Intent(this, ProductDetail::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        arr.clear()
        val ids = db.allLikes()
        for (pid in ids) {
            dummyService.singleProduct(pid).enqueue(object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    val status = response.code()
                    if (status == 200) {
                        val item = response.body()
                        item?.let {
                            arr.add(it)
                            productAdapter.notifyDataSetChanged()
                        }
                    }
                }
                override fun onFailure(call: Call<Product>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

}