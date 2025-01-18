package com.albertheijn.rijksmuseumassignment.data.network

import com.albertheijn.rijksmuseumassignment.data.model.response.ArtDetailResponse
import com.albertheijn.rijksmuseumassignment.data.model.response.CollectionResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RijksmuseumApi {
    @GET("{culture}/collection/{objectNumber}")
    suspend fun getArtDetail(
        @Path("culture") culture: String = "en",
        @Path("objectNumber") objectNumber: String,
        @Query("key") apiKey: String
    ): ArtDetailResponse

    @GET("{culture}/collection")
    suspend fun getCollection(
        @Path("culture") culture: String = "en",
        @Query("q") query: String? = null,
        @Query("p") page: Int = 0,
        @Query("ps") pageSize: Int = 20,
        @Query("imgonly") imgOnly: Boolean = true
    ): CollectionResponse
}