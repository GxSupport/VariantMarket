package uz.gxteam.variantmarket.network.repository

import uz.gxteam.variantmarket.network.apiService.ApiService
import uz.gxteam.variantmarket.utils.base.ResponseFetcher
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiService: ApiService,
    private val responseFetcher: ResponseFetcher.Base
){
    // TODO: Methode Get
    suspend  fun <T> methodeGET(fulUrl:String,headerMap:HashMap<String,String>,queryMap:HashMap<String,String>) =
       responseFetcher.getFlowResponseState(apiService.methodeGet<T>(fulUrl,headerMap,queryMap))
    // TODO: Methode Post
    suspend fun <T> methodePOST(fulUrl:String,data:Any,headerMap:HashMap<String,String>,queryMap:HashMap<String,String>) =
        responseFetcher.getFlowResponseState(apiService.methodePost<T>(fulUrl,data,headerMap,queryMap))
    // TODO: Methode Put
    suspend fun <T> methodePUT(fulUrl:String,data:Any,headerMap:HashMap<String,String>,queryMap:HashMap<String,String>) =
        responseFetcher.getFlowResponseState(apiService.methodePut<T>(fulUrl,data,headerMap,queryMap))
    // TODO: Methode Delete
    suspend fun <T> methodeDELETE(fulUrl:String,data:Any,headerMap:HashMap<String,String>,queryMap:HashMap<String,String>) =
        responseFetcher.getFlowResponseState(apiService.methodeDELETE<T>(fulUrl,data,headerMap,queryMap))
}