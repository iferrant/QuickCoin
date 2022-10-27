package dev.ferrant.quickcoin.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ferrant.quickcoin.BuildConfig
import dev.ferrant.core.network.ApiHelper
import dev.ferrant.quickcoin.data.remote.api.ApiHelperImpl
import dev.ferrant.core.network.retrofit.CoinGeckoService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient() =
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        } else {
            OkHttpClient
                .Builder()
                .build()
        }

    @Provides
    @Singleton
    fun provideKotlinJsonAdapterFactory(): KotlinJsonAdapterFactory = KotlinJsonAdapterFactory()

    @Provides
    @Singleton
    fun provideRfc3339DateJsonAdapter(): Rfc3339DateJsonAdapter = Rfc3339DateJsonAdapter()

    @Provides
    @Singleton
    fun provideMoshi(
        kotlinJsonAdapterFactory: KotlinJsonAdapterFactory,
        rfc3339DateJsonAdapter: Rfc3339DateJsonAdapter
    ): Moshi =
        Moshi.Builder()
            .add(kotlinJsonAdapterFactory)
            .add(Date::class.java, rfc3339DateJsonAdapter)
            .build()

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String,
        moshiConverterFactory: MoshiConverterFactory,
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): dev.ferrant.core.network.retrofit.CoinGeckoService = retrofit.create(
        dev.ferrant.core.network.retrofit.CoinGeckoService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): dev.ferrant.core.network.ApiHelper = apiHelper
}
