package com.codenome.cstv.di

import com.codenome.cstv.BuildConfig
import com.codenome.cstv.api.MatchEndpoint
import com.codenome.cstv.api.TeamsEndpoint
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val retrofitModule = module {
    single { GsonBuilder().create() }
    single { createRetrofit() }
    single { retrofitHttpClient() }
    factory { matchEndpointService(get()) }
    factory { teamsEndpointService(get()) }
}

fun retrofitHttpClient() = OkHttpClient.Builder().addInterceptor { chain ->
    val request = chain.request().newBuilder()
        .addHeader("Authorization", "Bearer ${BuildConfig.ACCESS_TOKEN}")
        .build()
    chain.proceed(request)
}.build()

fun Scope.createRetrofit(): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(get())
        .addConverterFactory(GsonConverterFactory.create(get()))
        .build()

fun matchEndpointService(retrofit: Retrofit): MatchEndpoint =
    retrofit.create(MatchEndpoint::class.java)

fun teamsEndpointService(retrofit: Retrofit): TeamsEndpoint =
    retrofit.create(TeamsEndpoint::class.java)