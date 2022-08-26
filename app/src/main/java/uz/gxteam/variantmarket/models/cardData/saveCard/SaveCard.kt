package uz.gxteam.variantmarket.models.cardData.saveCard

import java.io.Serializable

data class SaveCard(
    var name:String,
    var numberCard:String,
    var date:String,
    var cardType:Int,
    var bank:String,
):Serializable
