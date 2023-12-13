package com.works.project.services

import com.works.project.models.JWTUser
import com.works.project.models.Product
import com.works.project.models.Products
import com.works.project.models.SendUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DummyService {

    @POST("auth/login")
    fun login( @Body sendUser: SendUser ) : Call<JWTUser>

    @GET("products")
    fun allProducts() : Call<Products>

    @GET("products/{pid}")
    fun singleProduct(@Path("pid") pid:Long) : Call<Product>

}