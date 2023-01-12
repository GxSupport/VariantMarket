package uz.gxteam.variantmarket.models.categoryModel

data class Succes(
    val created_at: String,
    val description_ru: String,
    val description_uz: String,
    val description_uzc: String,
    val id: Int,
    val image: String,
    val level: Int,
    val name_ru: String,
    val name_uz: String,
    val name_uzc: String,
    val parent_id: Int,
    val status: Int,
    val type: Int,
    val updated_at: String
)