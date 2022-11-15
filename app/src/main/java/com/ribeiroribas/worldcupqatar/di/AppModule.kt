package com.ribeiroribas.worldcupqatar.di

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.ribeiroribas.worldcupqatar.network.WorldCupApi
import com.ribeiroribas.worldcupqatar.network.WorldCupImpl
import com.ribeiroribas.worldcupqatar.repository.WorldCupQatarRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://worldcupcatar.herokuapp.com"

    private val client by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesObjectMapper(): ObjectMapper {
        val mapper = ObjectMapper()
        mapper.registerModule(JavaTimeModule())
        mapper.registerModule(kotlinModule())
        return mapper
    }

    @Singleton
    @Provides
    fun providesWorldCupMatches(mapper: ObjectMapper): WorldCupApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create(mapper))
            .client(client)
            .build()
            .create(WorldCupApi::class.java)
    }

    @Singleton
    @Provides
    fun providesWorldCupImpl(api: WorldCupApi): WorldCupImpl = WorldCupImpl(api = api)

    @Singleton
    @Provides
    fun providesWorldCupQatarRepository(webClient: WorldCupImpl): WorldCupQatarRepository =
        WorldCupQatarRepository(webClient = webClient)

}