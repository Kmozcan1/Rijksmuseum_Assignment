package com.albertheijn.rijksmuseumassignment.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MakerDto(
    @SerialName("name")
    val name: String? = null,

    @SerialName("unFixedName")
    val unFixedName: String? = null,

    @SerialName("placeOfBirth")
    val placeOfBirth: String? = null,

    @SerialName("dateOfBirth")
    val dateOfBirth: String? = null,

    @SerialName("dateOfBirthPrecision")
    val dateOfBirthPrecision: String? = null,

    @SerialName("dateOfDeath")
    val dateOfDeath: String? = null,

    @SerialName("dateOfDeathPrecision")
    val dateOfDeathPrecision: String? = null,

    @SerialName("placeOfDeath")
    val placeOfDeath: String? = null,

    @SerialName("occupation")
    val occupation: List<String>? = null,

    @SerialName("roles")
    val roles: List<String>? = null,

    @SerialName("nationality")
    val nationality: String? = null,

    @SerialName("biography")
    val biography: String? = null,

    @SerialName("productionPlaces")
    val productionPlaces: List<String>? = null,

    @SerialName("qualification")
    val qualification: String? = null,

    @SerialName("labelDesc")
    val labelDesc: String? = null
)
