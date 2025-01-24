package com.albertheijn.rijksmuseumassignment.data.enumeration

enum class SortOption(val value: String) {
    RELEVANCE("relevance"),
    OBJECT_TYPE("objecttype"),
    CHRONOLOGIC("chronologic"),
    ACHRONOLOGIC("achronologic"),
    ARTIST("artist"),
    ARTIST_DESC("artistdesc"),
    POSITION("position");

    override fun toString(): String = value
}