package uz.gxteam.variantmarket.usesCase.apiUsesCase

import com.google.gson.JsonElement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import uz.gxteam.variantmarket.repository.apiRepository.ApiRepository
import uz.gxteam.variantmarket.utils.base.ResponseFetcher
import uz.gxteam.variantmarket.utils.extensions.gsonData
import uz.gxteam.variantmarket.utils.responseState.ResponseState
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

class ApiUsesCase @Inject constructor(
    private val apiRepository: ApiRepository,
    private val responseFetcher: ResponseFetcher.Base) {
    suspend fun methodeGet(url:String,queryMap:HashMap<String,String>)
            = responseFetcher.getFlowResponseState(apiRepository.methodGet(url,queryMap))

    suspend fun methodePost(url:String,body:Any,queryMap:HashMap<String,String>)
            = responseFetcher.getFlowResponseState(apiRepository.methodPost(url,body,queryMap))

    suspend fun methodePut(url:String,body:Any,queryMap:HashMap<String,String>)
            = responseFetcher.getFlowResponseState(apiRepository.methodPut(url,body,queryMap))

    suspend fun methodeDelete(url:String,body:Any,queryMap:HashMap<String,String>)
            = responseFetcher.getFlowResponseState(apiRepository.methodeDelete(url,body,queryMap))

    suspend fun getHomeAllData(
        urlCategory:String,
        categoryQueryMap:HashMap<String,String>
    ) = flow {
        val flow = try {
            coroutineScope {
                val listResponse = ArrayList<JsonElement?>()
                val responseCategory = async { apiRepository.methodGet(urlCategory, categoryQueryMap) }.await()
                if (responseCategory.isSuccessful) listResponse.add(responseCategory.body())
                else throw HttpException(responseCategory)
                ResponseState.Success(listResponse)
            }
        }catch (e:HttpException){
             ResponseState.Error(e.code(),e.response()?.body().toString())
        }catch (e:IOException){
            ResponseState.Error(e.hashCode(),e.message)
        } catch (e:Exception){
            ResponseState.Error(e.hashCode(),e.message)
        }
        emit(flow)
    }.flowOn(Dispatchers.IO)
}