package uz.gxteam.variantmarket.repository.apiRepository

import com.google.gson.JsonElement
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import uz.gxteam.variantmarket.utils.responseState.ResponseState

interface ApiRepository {
    // TODO: Methode Repository Get
    suspend fun methodGet(fullUrl:String, queryMap:HashMap<String,String>?): Response<JsonElement>

    // TODO: Methode Repository Post
    suspend fun methodPost(fullUrl:String, data:Any, queryMap:HashMap<String,String>?): Response<JsonElement>

    // TODO: Methode Repository Put
    suspend fun methodPut(fullUrl:String, data:Any, queryMap:HashMap<String,String>?): Response<JsonElement>

    // TODO: Methode Repository Delete
    suspend fun methodeDelete(fullUrl:String, data:Any,queryMap:HashMap<String,String>?): Response<JsonElement>
}