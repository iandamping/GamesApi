package com.junemon.gamesapi.core.domain.model


import com.google.gson.annotations.SerializedName


data class GameSearch(
    @SerializedName("added")
    val added: Int = 0,
    @SerializedName("rating")
    val rating: Double = 0.0,
    @SerializedName("playtime")
    val playtime: Int = 0,
    @SerializedName("short_screenshots")
    val shortScreenshots: List<ShortScreenshotsItem>?,
    @SerializedName("platforms")
    val platforms: List<PlatformsItem>?,
    @SerializedName("score")
    val score: String = "",
    @SerializedName("rating_top")
    val ratingTop: Int = 0,
    @SerializedName("reviews_text_count")
    val reviewsTextCount: Int = 0,
    @SerializedName("saturated_color")
    val saturatedColor: String = "",
    @SerializedName("added_by_status")
    val addedByStatus: AddedByStatus,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("parent_platforms")
    val parentPlatforms: List<ParentPlatformsItem>?,
    @SerializedName("ratings_count")
    val ratingsCount: Int = 0,
    @SerializedName("slug")
    val slug: String = "",
    @SerializedName("released")
    val released: String = "",
    @SerializedName("stores")
    val stores: List<StoresItem>?,
    @SerializedName("suggestions_count")
    val suggestionsCount: Int = 0,
    @SerializedName("background_image")
    val backgroundImage: String = "",
    @SerializedName("tba")
    val tba: Boolean = false,
    @SerializedName("dominant_color")
    val dominantColor: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("community_rating")
    val communityRating: Int = 0,
    @SerializedName("reviews_count")
    val reviewsCount: Int = 0
)


