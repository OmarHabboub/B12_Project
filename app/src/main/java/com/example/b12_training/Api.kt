package com.example.b12_training

import retrofit2.http.GET
import retrofit2.Call
interface Api {
    @GET("entries")
    fun getPost(): Call<response>


}