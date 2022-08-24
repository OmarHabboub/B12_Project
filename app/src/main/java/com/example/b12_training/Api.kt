package com.example.b12_training

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Response

interface Api {
    @GET("entries")
    suspend fun getPost(): Response<response>


}