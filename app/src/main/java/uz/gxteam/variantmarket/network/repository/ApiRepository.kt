package uz.gxteam.variantmarket.network.repository

import uz.gxteam.variantmarket.network.apiService.ApiService
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiService: ApiService
){
    suspend  fun <T> methodeGET(fulUrl:String,headerMap:HashMap<String,String>,queryMap:HashMap<String,String>) =
        apiService.methodeGet<T>(fulUrl,headerMap,queryMap)
    suspend fun <T> methodePOST(fulUrl:String,data:Any,headerMap:HashMap<String,String>,queryMap:HashMap<String,String>) =
        apiService.methodePost<T>(fulUrl,data,headerMap,queryMap)
    suspend fun <T> methodePUT(fulUrl:String,data:Any,headerMap:HashMap<String,String>,queryMap:HashMap<String,String>) =
        apiService.methodePut<T>(fulUrl,data,headerMap,queryMap)
    suspend fun <T> methodeDELETE(fulUrl:String,data:Any,headerMap:HashMap<String,String>,queryMap:HashMap<String,String>) =
        apiService.methodeDELETE<T>(fulUrl,data,headerMap,queryMap)
}