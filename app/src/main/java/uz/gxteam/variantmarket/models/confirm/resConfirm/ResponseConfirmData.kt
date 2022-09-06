package uz.gxteam.variantmarket.models.confirm.resConfirm

data class ResponseConfirmData(
    val access_token: String,
    val expires_in: Int,
    val refresh_token: String,
    val token_type: String
)