package com.rng.tpapp

import com.squareup.moshi.Json

data class Task(
    @Json(name = "id")val id: String ="",
    @Json(name = "title") var title: String ="",
    @Json(name = "description") var description: String = ""
)