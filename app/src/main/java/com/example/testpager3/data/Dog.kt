package com.example.testpager3.data

import com.squareup.moshi.Json

data class Dog(
    @Json(name = "url")
    val url:String
)
