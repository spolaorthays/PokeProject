package com.pdi.network.di

import android.app.Application
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.pdi.network.di.annotations.ChuckIntercetorQualifier
import com.pdi.network.di.annotations.LoggingIntercetorQualifier
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/") //TODO usar url constant
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    fun provideOkHttp(
            @ChuckIntercetorQualifier chuck: Interceptor,
            @LoggingIntercetorQualifier logging: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
                .addInterceptor(chuck)
                .addInterceptor(logging)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()


    @ChuckIntercetorQualifier //Isso funciona no lugar do @Named
    @Provides
    fun provideChuckInterceptor(application: Application): Interceptor =  //TODO porque não consigo pega o context da classe Context? Para fucionar teve qe vir do Application
        ChuckInterceptor(application.baseContext)

    @LoggingIntercetorQualifier
    @Provides
    fun provideLoggingInterceptor(): Interceptor  {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

}