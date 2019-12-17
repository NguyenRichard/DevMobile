package com.rng.tpapp

import com.squareup.moshi.Json

data class LoginForm(
    @Json(name = "email")val  email:String="",
    @Json(name = "password")val password:String=""
)