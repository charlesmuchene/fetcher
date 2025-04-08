package com.charlesmuchene.fetcher.data

import com.charlesmuchene.fetcher.Items
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.io.File

class ItemsDataSource(
    cacheDir: File,
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
) {

    private val cache = Cache(File(cacheDir, "cache"), 100 * 1024 /* 100kb */)
    private val client = OkHttpClient().newBuilder().cache(cache).build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://fetch-hiring.s3.amazonaws.com")
        .client(client)
        .build()

    private val api by lazy { retrofit.create(ItemsApi::class.java) }

    suspend fun fetchItems(): Items = scope.async {
        api.items()
    }.await()
}

private interface ItemsApi {
    @GET("hiring.json")
    suspend fun items(): Items
}