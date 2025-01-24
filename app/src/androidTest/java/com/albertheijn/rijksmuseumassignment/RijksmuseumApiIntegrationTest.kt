package com.albertheijn.rijksmuseumassignment

import com.albertheijn.rijksmuseumassignment.application.di.NetworkModule
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Test

class RijksmuseumApiIntegrationTest {
    private val client = NetworkModule.provideOkHttpClient()
    private val retrofit = NetworkModule.provideRetrofit(client)
    private val api = NetworkModule.provideRijksmuseumApi(retrofit)

    @Test
    fun testCollectionEndpoint() = runBlocking {
        val response = api.getCollection()

        assertNotNull(response)
    }

    @Test
    fun testCollectionDetailEndpoint() = runBlocking {
        val response = api.getCollectionDetails(objectNumber = "BK-NM-1010")

        assertNotNull(response)
    }
}