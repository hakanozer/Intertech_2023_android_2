package com.works.project.adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.works.project.R
import com.works.project.models.Product

class ProductAdapter
    ( private val context: Activity, private val list: List<Product>)
    :
    ArrayAdapter<Product>(context, R.layout.productitem, list)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView = context.layoutInflater.inflate(R.layout.productitem, null, true)

        val item = list.get(position)
        val r_title = rootView.findViewById<TextView>(R.id.r_title)
        val r_price = rootView.findViewById<TextView>(R.id.r_price)
        val r_image = rootView.findViewById<ImageView>(R.id.r_img)

        r_title.text = item.title
        r_price.text = "${item.price}₺"
        Glide.with(rootView).load(item.thumbnail).into(r_image)

        return rootView
    }
}