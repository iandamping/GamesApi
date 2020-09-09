package com.junemon.model.games

import com.google.gson.annotations.SerializedName

data class GameDetail(
    @SerializedName("added")
    val added: Int = 0,
    @SerializedName("name_original")
    val nameOriginal: String = "",
    @SerializedName("metacritic")
    val metacritic: Int = 0,
    @SerializedName("rating")
    val rating: Int = 0,
    @SerializedName("description")
    val description: String = "",
    @SerializedName("game_series_count")
    val gameSeriesCount: Int = 0,
    @SerializedName("playtime")
    val playtime: Int = 0,
    @SerializedName("metacritic_url")
    val metacriticUrl: String = "",
    @SerializedName("alternative_names")
    val alternativeNames: List<String>?,
    @SerializedName("parents_count")
    val parentsCount: Int = 0,
    @SerializedName("platforms")
    val platforms: List<PlatformsItem>?,
    @SerializedName("metacritic_platforms")
    val metacriticPlatforms: List<MetacriticPlatformsItem>?,
    @SerializedName("creators_count")
    val creatorsCount: Int = 0,
    @SerializedName("rating_top")
    val ratingTop: Int = 0,
    @SerializedName("reviews_text_count")
    val reviewsTextCount: String = "",
    @SerializedName("achievements_count")
    val achievementsCount: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("added_by_status")
    val addedByStatus: AddedByStatus,
    @SerializedName("reddit_url")
    val redditUrl: String = "",
    @SerializedName("reddit_name")
    val redditName: String = "",
    @SerializedName("ratings_count")
    val ratingsCount: Int = 0,
    @SerializedName("reddit_count")
    val redditCount: Int = 0,
    @SerializedName("slug")
    val slug: String = "",
    @SerializedName("released")
    val released: String = "",
    @SerializedName("parent_achievements_count")
    val parentAchievementsCount: String = "",
    @SerializedName("website")
    val website: String = "",
    @SerializedName("suggestions_count")
    val suggestionsCount: Int = 0,
    @SerializedName("youtube_count")
    val youtubeCount: String = "",
    @SerializedName("additions_count")
    val additionsCount: Int = 0,
    @SerializedName("movies_count")
    val moviesCount: Int = 0,
    @SerializedName("twitch_count")
    val twitchCount: String = "",
    @SerializedName("background_image_additional")
    val backgroundImageAdditional: String = "",
    @SerializedName("background_image")
    val backgroundImage: String = "",
    @SerializedName("tba")
    val tba: Boolean = false,
    @SerializedName("esrb_rating")
    val esrbRating: EsrbRating,
    @SerializedName("screenshots_count")
    val screenshotsCount: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("reddit_description")
    val redditDescription: String = "",
    @SerializedName("reddit_logo")
    val redditLogo: String = "",
    @SerializedName("updated")
    val updated: String = ""
)




