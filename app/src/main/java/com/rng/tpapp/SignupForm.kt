package com.rng.tpapp

import com.squareup.moshi.Json

data class SignupForm(
    @Json(name = "firstname") val firstname : String,
    @Json(name = "lastname") val lastname : String,
    @Json(name = "email") val email : String,
    @Json(name = "password") val password : String,
    @Json(name = "password_confirmation") val password_confirmation : String


)