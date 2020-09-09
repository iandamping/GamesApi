package com.junemon.model.games

import com.google.gson.annotations.SerializedName


/**
 * Created by Ian Damping on 09,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class EsrbRating(@SerializedName("name")
                      val name: String = "",
                      @SerializedName("id")
                      val id: Int = 0,
                      @SerializedName("slug")
                      val slug: String = "")

data class MetacriticPlatformsItem(@SerializedName("metascore")
                                   val metascore: Int = 0,
                                   @SerializedName("url")
                                   val url: String = "")

data class Platform(@SerializedName("name")
                    val name: String = "",
                    @SerializedName("id")
                    val id: Int = 0,
                    @SerializedName("slug")
                    val slug: String = "")

data class PlatformsItem(@SerializedName("requirements")
                         val requirements: Requirements,
                         @SerializedName("released_at")
                         val releasedAt: String = "",
                         @SerializedName("platform")
                         val platform: Platform)

data class Requirements(@SerializedName("minimum")
                        val minimum: String = "",
                        @SerializedName("recommended")
                        val recommended: String = "")


data class ShortScreenshotsItem(
    @SerializedName("image")
    val image: String = "",
    @SerializedName("id")
    val id: Int = 0
)




data class Store(
    @SerializedName("games_count")
    val gamesCount: Int = 0,
    @SerializedName("domain")
    val domain: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("image_background")
    val imageBackground: String = "",
    @SerializedName("slug")
    val slug: String = ""
)


data class StoresItem(
    @SerializedName("url_ru")
    val urlRu: String = "",
    @SerializedName("url_en")
    val urlEn: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("store")
    val store: Store
)


data class AddedByStatus(
    @SerializedName("owned")
    val owned: Int = 0,
    @SerializedName("beaten")
    val beaten: Int = 0,
    @SerializedName("dropped")
    val dropped: Int = 0,
    @SerializedName("yet")
    val yet: Int = 0,
    @SerializedName("playing")
    val playing: Int = 0,
    @SerializedName("toplay")
    val toplay: Int = 0
)


data class Clip(
    @SerializedName("preview")
    val preview: String = "",
    @SerializedName("clip")
    val clip: String = ""
)


data class RatingsItem(
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("percent")
    val percent: Double = 0.0
)


data class GenresItem(
    @SerializedName("games_count")
    val gamesCount: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("image_background")
    val imageBackground: String = "",
    @SerializedName("slug")
    val slug: String = ""
)


data class RequirementsEn(
    @SerializedName("minimum")
    val minimum: String = "",
    @SerializedName("recommended")
    val recommended: String = ""
)


data class ParentPlatformsItem(
    @SerializedName("platform")
    val platform: Platform
)
