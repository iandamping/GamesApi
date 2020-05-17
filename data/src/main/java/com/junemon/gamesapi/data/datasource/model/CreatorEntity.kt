package com.junemon.gamesapi.data.datasource.model


import com.google.gson.annotations.SerializedName
import com.junemon.gamesapi.domain2.model.CreatorData
import com.junemon.gamesapi.domain2.model.CreatorItemData

data class CreatorsEntity(@SerializedName("next")
                          val next: String = "",
                          @SerializedName("count")
                          val count: Int?,
                          @SerializedName("results")
                          val results: List<CreatorItem>?)


data class CreatorItem(@SerializedName("image")
                       val image: String?,
                       @SerializedName("games_count")
                       val gamesCount: Int?,
                       @SerializedName("name")
                       val name: String?,
                       @SerializedName("id")
                       val id: Int?,
                       @SerializedName("image_background")
                       val imageBackground: String?,
                       @SerializedName("slug")
                       val slug: String?)

fun CreatorItem.mapSingleItemToDomain(): CreatorItemData = CreatorItemData(image, gamesCount, name, id, imageBackground, slug)
fun List<CreatorItem>.mapToDomain():List<CreatorItemData> = map { it?.mapSingleItemToDomain() }


fun CreatorsEntity.mapSingleItemToDomain(): CreatorData = CreatorData(next,count,results?.mapToDomain())
fun List<CreatorsEntity>.mapRemoteItemToDomain():List<CreatorData> = map { it.mapSingleItemToDomain() }





