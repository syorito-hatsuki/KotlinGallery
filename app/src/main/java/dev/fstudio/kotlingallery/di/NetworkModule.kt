package dev.fstudio.kotlingallery.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import dev.fstudio.kotlingallery.ui.fragment.nasa.impl.ApodAPI
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

private const val URL = "https://api.nasa.gov"

@ExperimentalSerializationApi
val networkModule = module {
    single { provideOkHttp(get(), get()) }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
    single { provideChuckInterceptor(get()) }
}

private fun provideOkHttp(context: Context, chuckInterceptor: ChuckInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .callTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .cache(Cache(context.cacheDir, 10 * 1024 * 1024))
        .followRedirects(true)
        .followSslRedirects(true)
        .addInterceptor(Interceptor {
            val request = it.request()
            val url = request.url.newBuilder()
                .addQueryParameter("api_key", "fTGMsl3DTWhyL1AR9pdHvtm6hgXB3BiXtCnyLr71").build()
            it.proceed(request.newBuilder().url(url).build())
        })
        .addInterceptor(chuckInterceptor)
        .build()
}

@ExperimentalSerializationApi
private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    val contentType = "application/json".toMediaType()
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(URL)
        .addConverterFactory(Json.asConverterFactory(contentType))
        .build()
}

private fun provideApiService(retrofit: Retrofit): ApodAPI {
    return retrofit.create(ApodAPI::class.java)
}

private fun provideChuckInterceptor(context: Context): ChuckInterceptor {
    return ChuckInterceptor(context)
}