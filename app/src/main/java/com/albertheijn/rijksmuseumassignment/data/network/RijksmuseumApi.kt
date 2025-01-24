package com.albertheijn.rijksmuseumassignment.data.network

import com.albertheijn.rijksmuseumassignment.data.DEFAULT_CULTURE
import com.albertheijn.rijksmuseumassignment.data.DEFAULT_PAGE_SIZE
import com.albertheijn.rijksmuseumassignment.data.DEFAULT_SORT_OPTION
import com.albertheijn.rijksmuseumassignment.data.enumeration.SortOption
import com.albertheijn.rijksmuseumassignment.data.model.response.CollectionDetailsResponse
import com.albertheijn.rijksmuseumassignment.data.model.response.CollectionResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RijksmuseumApi {
    /**
     * Fetches detailed information about a specific art object from the Rijksmuseum API.
     *
     * @param culture The culture or language for the collection. Default is [DEFAULT_CULTURE] ("en").
     *
     * @param objectNumber The unique identifier for the specific art object you want to fetch details about.
     *                     This identifier can be found in the `objectNumber` field of objects returned
     *                     by the `getCollection` endpoint.
     *
     * @return [CollectionDetailsResponse] containing detailed information about the specified art object, such as:

     * API Endpoint: [GET] /{culture}/collection/{objectNumber}
     *
     * Example usage:
     * ```
     * val detailsResponse = api.getCollectionDetails(objectNumber = "SK-C-5")
     * println(detailsResponse.artObject.title) // "The Night Watch"
     * ```
     *
     * @throws HttpException If the request fails due to client or server error.
     * @throws IOException If there is a network failure.
     */
    @GET("{culture}/collection/{objectNumber}")
    suspend fun getCollectionDetails(
        @Path("culture") culture: String = DEFAULT_CULTURE,
        @Path("objectNumber") objectNumber: String
    ): CollectionDetailsResponse

    /**
     * Fetches a collection of art objects from the Rijksmuseum API.
     *
     * @param culture The culture or language for the collection. Default is [DEFAULT_CULTURE] ("en").
     * @param format The format of the response. Acceptable values are "json" or "xml". Default is null.
     * @param page The page number for pagination. Default is 0 (the first page).
     * @param pageSize The number of items per page. Default is [DEFAULT_PAGE_SIZE].
     * @param query A search term to filter results. Default is null.
     * @param involvedMaker Filters the results to objects created by a specific maker. Default is null.
     * @param type Filters the results to objects of a specific type (e.g., "painting", "sculpture"). Default is null.
     * @param material Filters the results to objects made with a specific material (e.g., "wood", "metal"). Default is null.
     * @param technique Filters the results to objects made with a specific technique (e.g., "etching", "oil painting"). Default is null.
     * @param period Filters the results to objects from a specific historical period (e.g., 17 for the 17th century). Default is null.
     * @param colorHex Filters the results to objects that predominantly feature a specific color, defined by its HEX code (e.g., "#000000"). Default is null.
     * @param imgOnly Filters the results to include only objects that have an image. Default is null.
     * @param topPieces Filters the results to include only the museum's top pieces. Default is null.
     * @param sort Specifies the sorting order for the results. Acceptable values are provided in [SortOption]. Default is [SortOption.POSITION].
     *
     * @return A [CollectionResponse] containing the list of art objects and additional metadata.
     *
     * Example Usage:
     * ```
     * val response = api.getCollection(
     *     culture = "nl",
     *     query = "Rembrandt",
     *     page = 1,
     *     pageSize = 10,
     *     imgOnly = true,
     *     sort = SortOption.RELEVANCE
     * )
     * ```
     */
    @GET("{culture}/collection")
    suspend fun getCollection(
        @Path("culture") culture: String = DEFAULT_CULTURE,
        @Query("format") format: String? = null,
        @Query("p") page: Int = 0,
        @Query("ps") pageSize: Int = DEFAULT_PAGE_SIZE,
        @Query("q") query: String? = null,
        @Query("involvedMaker") involvedMaker: String? = null,
        @Query("type") type: String? = null,
        @Query("material") material: String? = null,
        @Query("technique") technique: String? = null,
        @Query("f.dating.period") period: Int? = null,
        @Query("f.normalized32Colors.hex") colorHex: String? = null,
        @Query("imgonly") imgOnly: Boolean? = null,
        @Query("toppieces") topPieces: Boolean? = null,
        @Query("s") sort: SortOption? = DEFAULT_SORT_OPTION
    ): CollectionResponse

}