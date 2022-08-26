package uz.gxteam.variantmarket.network.apiService

import retrofit2.Response
import retrofit2.http.*
import uz.gxteam.variantmarket.utils.AppConstant.EMPTY

// TODO: This ApiService request and response generic data
interface ApiService {
    // TODO: ApiService Methode Get
    @GET
    suspend fun <T> methodeGet(
        @Url fulUrl:String,
        @HeaderMap mapHeader:HashMap<String,String>?,
        @QueryMap mapQuery:HashMap<String,String>?
    ): Response<T>

    // TODO: ApiService Methode Post
    @POST
    suspend fun <T> methodePost(
        @Url fulUrl:String,
        @Body body:Any?=EMPTY,
        @HeaderMap mapHeader:HashMap<String,String>?,
        @QueryMap mapQuery:HashMap<String,String>?
    ): Response<T>

    // TODO: ApiService Methode Put
    @PUT
    suspend fun <T> methodePut(
        @Url fulUrl:String,
        @Body body:Any?=EMPTY,
        @HeaderMap mapHeader:HashMap<String,String>?,
        @QueryMap mapQuery:HashMap<String,String>?
    ): Response<T>

    // TODO: ApiService Methode Delete
    @DELETE
    suspend fun <T> methodeDELETE(
        @Url fulUrl:String,
        @Body body:Any?=EMPTY,
        @HeaderMap mapHeader:HashMap<String,String>?,
        @QueryMap mapQuery:HashMap<String,String>?
    ): Response<T>
}