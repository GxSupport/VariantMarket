package uz.gxteam.variantmarket.models.register.resRegister

data class Success(
    val attempts_count: Int,
    val expire_time: Int,
    val phone: Long,
    val sms_result: SmsResult
)