package com.hjalmarsson.fashion.model

data class RecommendedModel (
    val title: String,
    val image: String? = null, // If this is null, just show the data as a horizontal list. Else just show a big image
    val data: List<RecommendedItemModel>
)
