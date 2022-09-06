package uz.gxteam.variantmarket.utils.base

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import uz.gxteam.variantmarket.utils.responseState.ResponseState
import java.io.IOException
import javax.inject.Inject


interface ResponseFetcher{
    fun <T> getFlowResponseState(response: Response<T>?): Flow<ResponseState<T?>>
    fun <T> getError(response: Response<T>?):String
    class Base @Inject constructor(): ResponseFetcher {
        override fun <T> getFlowResponseState(response: Response<T>?) = flow {
            var flow = try {
                coroutineScope {
                    if (response?.isSuccessful == true) ResponseState.Success(response.body())
                    else throw HttpException(response)
                }
            }catch (e:IOException){
                ResponseState.Error(e.hashCode(),e.message)
            }catch (e:HttpException){
                ResponseState.Error(e.code(),getError(e.response()))
            } catch (e:Exception){
                ResponseState.Error(e.hashCode(),e.message)
            }
            emit(flow)
        }.flowOn(Dispatchers.IO)

        override fun <T> getError(response: Response<T>?): String {
           return try {
               val jsonObject = JSONObject(response?.errorBody()?.string().toString())
              var errorMessage = ""
              if(jsonObject.has("errors")){
                  val jsonArray = JSONArray(jsonObject.get("errors").toString())
                  for (index in 0 until  jsonArray.length()){
                      if (jsonArray.getJSONObject(index).has("message"))
                          errorMessage += jsonArray.getJSONObject(index).get("message")
                      Log.e("ErrorData", errorMessage)
                  }
              }
               return errorMessage
           }catch (e:Exception){
               e.message?:""
           }
        }

    }
}