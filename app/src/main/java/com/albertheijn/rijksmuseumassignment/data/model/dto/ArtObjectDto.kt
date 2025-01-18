package com.albertheijn.rijksmuseumassignment.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtObjectDto(
    @SerialName("acquisition")
    val acquisition: AcquisitionDto? = null,

    @SerialName("artistRole")
    val artistRole: String? = null,

    @SerialName("associations")
    val associations: List<String?>? = null,

    @SerialName("catRefRPK")
    val catRefRPK: List<String?>? = null,

    @SerialName("classification")
    val classification: ClassificationDto? = null,

    @SerialName("colors")
    val colors: List<ColorDto?>? = null,

    @SerialName("colorsWithNormalization")
    val colorsWithNormalization: List<NormalizedColorDto?>? = null,

    @SerialName("normalized32Colors")
    val normalized32Colors: List<ColorDto?>? = null,

    @SerialName("normalizedColors")
    val normalizedColors: List<ColorDto?>? = null,

    @SerialName("copyrightHolder")
    val copyrightHolder: String? = null,

    @SerialName("dating")
    val dating: DatingDto? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("dimensions")
    val dimensions: List<DimensionDto?>? = null,

    @SerialName("documentation")
    val documentation: List<String?>? = null,

    @SerialName("exhibitions")
    val exhibitions: List<String?>? = null,

    @SerialName("hasImage")
    val hasImage: Boolean? = null,

    @SerialName("historicalPersons")
    val historicalPersons: List<String?>? = null,

    @SerialName("id")
    val id: String? = null,

    @SerialName("inscriptions")
    val inscriptions: List<String?>? = null,

    @SerialName("label")
    val label: LabelDto? = null,

    @SerialName("labelText")
    val labelText: String? = null,

    @SerialName("language")
    val language: String? = null,

    @SerialName("links")
    val links: LinksDto? = null,

    @SerialName("location")
    val location: String? = null,

    @SerialName("longTitle")
    val longTitle: String? = null,

    @SerialName("makers")
    val makers: List<MakerDto?>? = null,

    @SerialName("materials")
    val materials: List<String?>? = null,

    @SerialName("materialsThesaurus")
    val materialsThesaurus: List<String?>? = null,

    @SerialName("objectCollection")
    val objectCollection: List<String?>? = null,

    @SerialName("objectNumber")
    val objectNumber: String? = null,

    @SerialName("objectTypes")
    val objectTypes: List<String?>? = null,

    @SerialName("physicalMedium")
    val physicalMedium: String? = null,

    @SerialName("physicalProperties")
    val physicalProperties: List<String?>? = null,

    @SerialName("plaqueDescriptionDutch")
    val plaqueDescriptionDutch: String? = null,

    @SerialName("plaqueDescriptionEnglish")
    val plaqueDescriptionEnglish: String? = null,

    @SerialName("principalMaker")
    val principalMaker: String? = null,

    @SerialName("principalMakers")
    val principalMakers: List<MakerDto?>? = null,

    @SerialName("principalOrFirstMaker")
    val principalOrFirstMaker: String? = null,

    @SerialName("priref")
    val priref: String? = null,

    @SerialName("productionPlaces")
    val productionPlaces: List<String?>? = null,

    @SerialName("productionPlacesThesaurus")
    val productionPlacesThesaurus: List<String?>? = null,

    @SerialName("scLabelLine")
    val scLabelLine: String? = null,

    @SerialName("showImage")
    val showImage: Boolean? = null,

    @SerialName("subTitle")
    val subTitle: String? = null,

    @SerialName("techniques")
    val techniques: List<String?>? = null,

    @SerialName("techniquesThesaurus")
    val techniquesThesaurus: List<String?>? = null,

    @SerialName("title")
    val title: String? = null,

    @SerialName("titles")
    val titles: List<String?>? = null,

    @SerialName("webImage")
    val webImage: ImageDto? = null
)
