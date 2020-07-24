package ru.skillbranch.sbdelivery.di.modules

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://sandbox.skill-branch.ru/"

val networkModule = module {

    single<Retrofit> {

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val loggingInterceptor = HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }

        //Надо будет подумать как лучше, добавлять ли хэдер ко всем запросам тут
        //или же в DeliveryService у требуемых запросов
//        val authInterceptor = Interceptor { chain ->
//            val newRequest = chain.request()
//                .newBuilder()
//                .addHeader("SomeName", "SomeValue")
//                .build()
//            chain.proceed(newRequest)
//        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        return@single Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }

}