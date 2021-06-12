package com.example.testpager3.data.network

import com.example.testpager3.data.Dog
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object{
        const val BASEURL = "https://api.thedogapi.com/"
    }

    @GET("v1/images/search")
    suspend fun getAllDogs(
        @Query ("page") page:Int,
        @Query("limit") limit:Int
    ):List<Dog>
}