package com.junemon.gamesapi.domain2.model

/**
 * Created by Ian Damping on 10,December,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class CreatorData(
    val next: String ="",
    val count: Int ?,
    val results: List<CreatorItemData>?
)

data class CreatorItemData(
    val image: String ?,
    val gamesCount: Int?,
    val name: String ?,
    val id: Int ?,
    val imageBackground: String ?,
    val slug: String ?
)
