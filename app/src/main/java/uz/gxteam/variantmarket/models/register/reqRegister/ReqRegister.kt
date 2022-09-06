package uz.gxteam.variantmarket.models.register.reqRegister

import java.io.Serializable

data class ReqRegister(
    val name: String,
    val password: String,
    val password_confirmation: String,
    val phone: String
):Serializable