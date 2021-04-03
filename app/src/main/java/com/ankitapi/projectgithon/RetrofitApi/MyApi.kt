package com.ankitapi.projectgithon.RetrofitApi

import com.ankitapi.projectgithon.DemoClass
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.http.POST
import javax.security.auth.callback.Callback

interface MyApi {
    @POST("getCourse.php")
    fun getCourses():Call<DemoClass>

//    private fun makeService(okHttpClient: OkHttpClient) : {
//
//    }
}