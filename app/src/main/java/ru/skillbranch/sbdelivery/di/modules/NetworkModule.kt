package ru.skillbranch.sbdelivery.di.modules

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.skillbranch.sbdelivery.network.DeliveryService
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://sandbox.skill-branch.ru"

val networkModule = module {

    single {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    single {
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create(DeliveryService::class.java)
    }

}

//Надо будет подумать как лучше, добавлять ли хэдер ко всем запросам тут
//или же в DeliveryService у требуемых запросов
//        val authInterceptor = Interceptor { chain ->
//            val newRequest = chain.request()
//                .newBuilder()
//                .addHeader("SomeName", "SomeValue")
//                .build()
//            chain.proceed(newRequest)
//        }