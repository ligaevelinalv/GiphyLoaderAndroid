package com.example.giphyloader.common

import com.example.giphyloader.network.DataSource
import com.example.giphyloader.network.GifRepository
import com.example.giphyloader.network.GifRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InjectionModule {
    @Provides
    @Singleton
    fun provideKtorClient(): HttpClient {
        return HttpClient(Android) {
            //to map json objects returned from the api to a kotlin data class
            install(ContentNegotiation) {
                json(Json {
                    //ignores json keys we have not included in our data class
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    @Provides
    @Singleton
    fun provideGifRepository(dataSource: DataSource): GifRepository = GifRepositoryImpl(dataSource)
}