package com.albertheijn.rijksmuseumassignment.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtObjectListedDto(
    @SerialName("hasImage")
    val hasImage: Boolean?,

    @SerialName("headerImage")
    val headerImage: ImageDto?,

    @SerialName("id")
    val id: String?,

    @SerialName("links")
    val links: LinksDto?,

    @SerialName("longTitle")
    val longTitle: String?,

    @SerialName("objectNumber")
    val objectNumber: String?,

    @SerialName("permitDownload")
    val permitDownload: Boolean?,

    @SerialName("principalOrFirstMaker")
    val principalOrFirstMaker: String?,

    @SerialName("productionPlaces")
    val productionPlaces: List<String?>? = emptyList(),

    @SerialName("showImage")
    val showImage: Boolean?,

    @SerialName("title")
    val title: String?,

    @SerialName("webImage")
    val webImage: ImageDto?
)