package uz.gxteam.variantmarket.models.searchData

data class SearchDataAll (
    var image:String,
    var isDelivery:Boolean,
    var title:String,
    var discountSumm:Double,
    var allSumm:Double,
    var percentText:String,
    var lavel:String,
    var users:String,
    var discount:Boolean?=false
    )