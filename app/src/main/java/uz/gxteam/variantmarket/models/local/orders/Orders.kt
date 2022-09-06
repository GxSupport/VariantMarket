package uz.gxteam.variantmarket.models.local.orders

data class Orders(
    var date:String,
    var month:String,
    var orderName:String,
    var productName:String,
    var summa:String,
    var type:Int,
    var countProduct:Int,
    var image:String
)
