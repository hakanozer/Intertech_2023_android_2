package com.works.project

import android.content.res.ColorStateList
import android.graphics.BlendMode
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.works.project.configs.DB
import com.works.project.models.Product

class ProductDetail : AppCompatActivity() {

    companion object {
        var item: Product? = null
    }

    lateinit var db:DB
    lateinit var d_img: ImageView
    lateinit var d_title: TextView
    lateinit var d_price: TextView
    lateinit var d_detail: TextView
    lateinit var d_ratingBar: RatingBar
    lateinit var btn_like: ImageButton

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }
        db = DB(this)



        d_img = findViewById(R.id.d_img)
        d_title = findViewById(R.id.d_title)
        d_price = findViewById(R.id.d_price)
        d_detail = findViewById(R.id.d_detail)
        d_ratingBar = findViewById(R.id.d_ratingBar)
        btn_like = findViewById(R.id.btn_like)

        item?.let {
            var likesStatus = db.singleLike(it.id)
            if (likesStatus == true) {
                btn_like.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            }else {
                btn_like.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
            }

            Glide.with(this).load(it.thumbnail).into(d_img)
            d_title.text = it.title
            d_price.text = "${it.price}₺"
            d_detail.text = it.description
            d_ratingBar.rating = it.rating.toFloat()

            val pidx = it.id
            btn_like.setOnClickListener {
                if (likesStatus == true) {
                        // Remove
                        val status = db.removeLike(pidx)
                        btn_like.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                        likesStatus = false

                }else {
                        val status = db.addLike(pidx)
                        btn_like.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                        likesStatus = true

                }
            }

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return true
    }

}