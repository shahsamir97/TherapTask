package com.apps.therapassignment.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import com.apps.therapassignment.utils.isNetworkAvailable
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ServiceGenerator {

    companion object{
        private const val BASE_URL = "https://api.github.com/orgs/"
        private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        fun getGitApiService(context: Context): GitRepoApiService {
            val cacheSize = (5 * 1024 * 1024).toLong() //5MB cache size
            val dataCache = Cache(context.cacheDir, cacheSize)

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor { chain ->
                    var request = chain.request()
                    request = if (isNetworkAvailable(context)){
                        request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                    }else{
                        request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60*60*24*7).build()
                    }
                    chain.proceed(request)
                }
                .cache(dataCache)
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build()

           return retrofit.create(GitRepoApiService::class.java)
        }
    }
}
