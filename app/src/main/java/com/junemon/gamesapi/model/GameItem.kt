package com.junemon.gamesapi.model

import com.junemon.gamesapi.domain2.model.ClipModel
import com.junemon.gamesapi.domain2.model.GameModel

/**
 * Created by Ian Damping on 31,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class ClipItem(
    val preview: String = "",
    val clip: String = ""
)

data class GameItem(
    val id: Int = 0,
    val slug: String = "",
    val released: String = "",
    val backgroundImage: String = "",
    val name: String = "",
    val clip: ClipItem
)

private fun ClipModel.mapToPresentation() = ClipItem(preview, clip)
fun List<GameModel>.mapToPresentation(): List<GameItem> = map { GameItem(it.id, it.slug, it.released, it.backgroundImage, it.name, it.clip.mapToPresentation()) }
