package uz.gxteam.variantmarket.di.networkModule

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gxteam.variantmarket.BuildConfig
import uz.gxteam.variantmarket.BuildConfig.BASE_URL
import uz.gxteam.variantmarket.network.apiService.ApiService
import uz.gxteam.variantmarket.network.tokenInterceptor.TokenInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private val READ_TIMEOUT = 300
private val WRITE_TIMEOUT = 300
private val CONNECTION_TIMEOUT = 100

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideBaseUrl():String{
       return if(BuildConfig.DEBUG){
            BASE_URL
        }else{
           BASE_URL
        }
    }

    @Singleton
    @Provides
    fun providesRetrofit(baseUrl:String):Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context, tokenInterceptor: TokenInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder().connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(tokenInterceptor)
            .addInterceptor(ChuckerInterceptor(context)).build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit):ApiService = retrofit.create(ApiService::class.java)

}