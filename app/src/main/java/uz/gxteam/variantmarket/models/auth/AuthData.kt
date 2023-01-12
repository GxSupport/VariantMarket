package uz.gxteam.variantmarket.models.auth

import com.google.gson.annotations.SerializedName

data class AuthData(
    @SerializedName("password")
    val password: String,
    @SerializedName("phone")
    val phone: String
)