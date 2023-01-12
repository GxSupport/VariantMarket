package uz.gxteam.variantmarket.network.apiService

import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.*
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.EMPTY

// TODO: This ApiService request and response generic data
interface ApiService {
    @GET
    suspend fun methodeGet(
        @Url url:String,
        @QueryMap queryMap:HashMap<String,String>?
    ):Response<JsonElement>

    @POST
    suspend fun methodePost(
        @Url url:String,
        @Body body:Any,
        @QueryMap queryMap:HashMap<String,String>?
    ):Response<JsonElement>

    @PUT
    suspend fun methodePut(
        @Url url:String,
        @Body body:Any,
        @QueryMap queryMap:HashMap<String,String>?
    ):Response<JsonElement>


    @DELETE
    suspend fun methodeDelete(
        @Url url:String,
        @Body body:Any,
        @QueryMap queryMap:HashMap<String,String>?
    ):Response<JsonElement>
}