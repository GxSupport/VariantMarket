package uz.gxteam.variantmarket.models.local.newsData

data class NewsData(
    val name:String,
    val image:String,
    val title:String,
    val description:String,
    val isDelivery:Boolean?=false,
    val deliverySumma:Double,
    val discount: Boolean? =false,
    val discountPercent:Double,
    val month:Int
)
