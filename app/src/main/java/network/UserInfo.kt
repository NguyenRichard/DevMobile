package network

import android.graphics.Bitmap
import com.squareup.moshi.Json

data class UserInfo(
    @Json(name = "email")
    val email: String,
    @Json(name = "firstname")
    val firstName: String,
    @Json(name = "lastname")
    val lastName: String,
    @Json(name = "avatar")
    val avatar : String?
)