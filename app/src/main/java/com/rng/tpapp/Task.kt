package com.rng.tpapp

import com.squareup.moshi.Json

data class Task(
    @Json(name = "id")val id: String ="",
    @Json(name = "title")val title: String ="",
    @Json(name = "description")val description: String = ""
){
}