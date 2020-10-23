package com.junemon.gamesapi.core.data.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Ian Damping on 29,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */


data class GameData(
    @SerializedName("added")
    val added: Int = 0,
    @SerializedName("rating")
    val rating: Double = 0.0,
    @SerializedName("metacritic")
    val metacritic: Int = 0,
    @SerializedName("playtime")
    val playtime: Int = 0,
    @SerializedName("short_screenshots")
    val shortScreenshots: List<ShortScreenshotsItem>?,
    @SerializedName("platforms")
    val platforms: List<PlatformsItem>?,
    @SerializedName("rating_top")
    val ratingTop: Int = 0,
    @SerializedName("reviews_text_count")
    val reviewsTextCount: Int = 0,
    @SerializedName("ratings")
    val ratings: List<RatingsItem>?,
    @SerializedName("genres")
    val genres: List<GenresItem>?,
    @SerializedName("saturated_color")
    val saturatedColor: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("added_by_status")
    val addedByStatus: AddedByStatus,
    @SerializedName("parent_platforms")
    val parentPlatforms: List<ParentPlatformsItem>?,
    @SerializedName("ratings_count")
    val ratingsCount: Int = 0,
    @SerializedName("slug")
    val slug: String = "",
    @SerializedName("released")
    val released: String = "",
    @SerializedName("suggestions_count")
    val suggestionsCount: Int = 0,
    @SerializedName("stores")
    val stores: List<StoresItem>?,
    @SerializedName("background_image")
    val backgroundImage: String = "",
    @SerializedName("tba")
    val tba: Boolean = false,
    @SerializedName("dominant_color")
    val dominantColor: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("clip")
    val clip: Clip,
    @SerializedName("reviews_count")
    val reviewsCount: Int = 0
)


