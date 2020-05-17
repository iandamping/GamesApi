package com.junemon.gamesapi.model

import com.junemon.gamesapi.domain2.model.CreatorData
import com.junemon.gamesapi.domain2.model.CreatorItemData

/**
 * Created by Ian Damping on 11,December,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class CreatorPresentation(
    val next: String = "",
    val count: Int ?,
    val results: List<CreatorItemPresentation>?
)

data class CreatorItemPresentation(
    val image: String ?,
    val gamesCount: Int ?,
    val name: String ?,
    val id: Int?,
    val imageBackground: String?,
    val slug: String?
)

fun CreatorItemData.mapToPresentation():CreatorItemPresentation = CreatorItemPresentation(image, gamesCount, name, id, imageBackground, slug)
fun List<CreatorItemData>.mapItemToPresentation():List<CreatorItemPresentation> = map { it?.mapToPresentation() }

fun CreatorData.mapToPresentation():CreatorPresentation = CreatorPresentation(next,count,results?.mapItemToPresentation())
fun List<CreatorData>.mapListToPresentation():List<CreatorPresentation> = map { it.mapToPresentation() }
