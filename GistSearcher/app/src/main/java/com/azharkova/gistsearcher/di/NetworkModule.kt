package com.azharkova.gistsearcher.di

import com.azharkova.gistsearcher.services.network.BaseURL
import com.azharkova.gistsearcher.services.network.retrofit.GistApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    @Named("apiClient")
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Singleton
    @Provides
    fun provideGson() = GsonBuilder()
        .serializeNulls()
        .registerTypeAdapter(Date::class.java, JsonDeserializer<Date> { jsonElement, type, context ->
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale("ru"))
            sdf.parse(jsonElement.asString)
        })
        .create()

    @Singleton
    @Provides
    fun provideGsonConverterFactory(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun provideCallAdapter(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    @Singleton
    @Provides
    @Named("retrofit")
    fun provideRetrofit(@Named("apiClient") client: OkHttpClient, converterFactory: Converter.Factory, callAdapterFactory: CallAdapter.Factory) =
        Retrofit.Builder()
            .baseUrl(BaseURL.URL)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .client(client)
            .build()

    @Singleton
    @Provides
    fun provideApi(@Named("retrofit") retrofit: Retrofit) = retrofit.create(GistApi::class.java)
}