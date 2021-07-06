package com.pdi.network.di

import android.app.Application
import android.os.Build
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.pdi.network.BuildConfig
import com.pdi.network.di.annotations.BaseUrlQualifier
import com.pdi.network.di.annotations.ChuckInterceptorQualifier
import com.pdi.network.di.annotations.LoggingInterceptorQualifier
import com.pdi.network.utils.Constants
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
        okHttpClient: OkHttpClient,
        @BaseUrlQualifier baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @BaseUrlQualifier
    @Singleton
    @Provides
    fun provideBaseUrl(): String = Constants.BASE_URL

    @Provides
    fun provideOkHttp(
            @ChuckInterceptorQualifier chuck: Interceptor,
            @LoggingInterceptorQualifier logging: Interceptor
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        if (BuildConfig.BUILD_TYPE != Constants.TYPE_RELEASE) {
            okHttpClient.addInterceptor(chuck)
            okHttpClient.addInterceptor(logging)
        }
        okHttpClient.apply {
            this.readTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
            this.connectTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
            this.writeTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
        }
        return okHttpClient.build()
    }

    @ChuckInterceptorQualifier //Isso funciona no lugar do @Named
    @Provides
    fun provideChuckInterceptor(application: Application): Interceptor =
        ChuckInterceptor(application.baseContext)

    @LoggingInterceptorQualifier
    @Provides
    fun provideLoggingInterceptor(): Interceptor  {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

}