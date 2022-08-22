package uz.gxteam.variantmarket.models.sliderData

data class SlideData(
    var image:List<Int>,
    var lick:Boolean,
    var bag:String,
    var title:String,
    var message:String,
    var starLevel:String,
    var reviews:String,
    var summa:String,
    var discountSumm:String,
    var diuscount:String,
    var colorProduct:String,
    var productCount:String,
    var listProductImage:List<Int>,
    var sizeList:List<String>,
    var payCard:String,
    var imageType:List<Int>,
    var monthText:String,
    var sizeProduct:List<String>,
    var productInfo:List<InfoData>,
    var filialList:List<Filial>
){

}
