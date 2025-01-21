package com.albertheijn.rijksmuseumassignment.data.network

import com.albertheijn.rijksmuseumassignment.data.model.response.CollectionDetailsResponse
import com.albertheijn.rijksmuseumassignment.data.model.response.CollectionResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RijksmuseumApi {
    @GET("{culture}/collection/{objectNumber}")
    suspend fun getCollectionDetails(
        @Path("culture") culture: String = "en",
        @Path("objectNumber") objectNumber: String
    ): CollectionDetailsResponse

    @GET("{culture}/collection")
    suspend fun getCollection(
        @Path("culture") culture: String = "en",
        @Query("p") page: Int = 0,
        @Query("ps") pageSize: Int = 20,
        @Query("s") sort: String = "artist"
    ): CollectionResponse
}