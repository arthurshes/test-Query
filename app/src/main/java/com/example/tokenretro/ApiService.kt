package com.example.tokenretro

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @Headers("appkey:1234")
    @POST("/superjob/api/signup/")
    suspend fun getAuth(@Body auth: Auth):Message

    @Headers("appkey:1234","{token}")
    @GET("superjob/api/user/")
    suspend fun getTest(@Path("token") token:String):Response
}