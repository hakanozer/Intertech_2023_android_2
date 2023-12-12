package com.works.project.configs

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    private val base_url = "https://dummyjson.com/"
    private var retrofit: Retrofit? = null

    val clientConfig = OkHttpClient
                        .Builder()
                        .readTimeout(60, TimeUnit.SECONDS)
                        .build()


    fun getClient() : Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit
                .Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientConfig)
                .build()
        }
        return retrofit as Retrofit
    }

}