package com.charlesmuchene.fetcher.data

import com.charlesmuchene.fetcher.Items
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class ItemsDataSource(private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)) {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://fetch-hiring.s3.amazonaws.com")
        .addConverterFactory(GsonConverterFactory.create())
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