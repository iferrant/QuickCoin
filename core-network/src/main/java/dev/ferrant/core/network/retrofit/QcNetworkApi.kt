package dev.ferrant.core.network.retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.ferrant.core.network.QcNetworkDataSource
import dev.ferrant.core.network.model.NetworkCoinSearch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private interface QcNetworkApi {

    @GET("search")
    suspend fun search(
        @Query("query") query: String = "",
    ): NetworkResponse<NetworkCoinSearch>

}

private const val QcBaseUrl = "https://api.coingecko.com/api/v3/"

private data class NetworkResponse<T> (
    val data: T
)

@Singleton
class RetrofitQcNetwork @Inject constructor() : QcNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(QcBaseUrl)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                )
                .build()
        )
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            )
        )
        .build()
        .create(QcNetworkApi::class.java)

    override suspend fun search(query: String): NetworkCoinSearch =
        networkApi.search(query = query).data
}
