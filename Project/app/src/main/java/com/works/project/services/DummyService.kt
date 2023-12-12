package com.works.project.services

import com.works.project.models.JWTUser
import com.works.project.models.Products
import com.works.project.models.SendUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DummyService {

    @POST("auth/login")
    fun login( @Body sendUser: SendUser ) : Call<JWTUser>

    @GET("products")
    fun allProducts() : Call<Products>

}