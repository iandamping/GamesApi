package com.junemon.gamesapi.data.datasource.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.ian.app.helper.util.fromJsonHelper
import com.junemon.gamesapi.domain2.model.CreatorData
import com.junemon.gamesapi.domain2.model.CreatorItemData
import com.junemon.gamesapi.domain2.model.GamesItemData
import com.junemon.gamesapi.domain2.model.PublisherData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Ian Damping on 10,December,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Entity(tableName = "creator_table")
data class CreatorDbEntity(
    @PrimaryKey
    @SerializedName("creator_next") val next: String,
    @SerializedName("creator_count") val count: Int?,
    @SerializedName("creator_results") val results: String?)


fun CreatorDbEntity.mapToDomain(): CreatorData = CreatorData(next,count,
    results?.let { sharedGson.gson.fromJsonHelper<List<CreatorItemData>>(it) })
fun List<CreatorDbEntity>.mapToDomain():List<CreatorData> = map { it.mapToDomain() }

fun Flow<List<CreatorDbEntity>>.mapToDomain() = map { it.mapToDomain() }

fun CreatorData.mapToDatabase(): CreatorDbEntity = CreatorDbEntity(next,count,sharedGson.gson.toJson(results))
fun List<CreatorData>.mapToDatabase(): List<CreatorDbEntity> = map { it.mapToDatabase() }
