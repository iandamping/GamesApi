package com.junemon.gamesapi.core.data.repository

import com.junemon.gamesapi.core.data.datasource.cache.entity.GameEntity
import com.junemon.gamesapi.core.data.datasource.cache.entity.GameFavoriteEntity
import com.junemon.gamesapi.core.data.datasource.remote.response.GameDetailResponse
import com.junemon.gamesapi.core.data.datasource.remote.response.GameGenreResponse
import com.junemon.gamesapi.core.data.datasource.remote.response.GameResponse
import com.junemon.gamesapi.core.data.datasource.remote.response.GameSearchResponse
import com.junemon.gamesapi.core.data.datasource.remote.response.GamesItemResponse
import com.junemon.gamesapi.core.data.datasource.remote.response.GenresItemResponse
import com.junemon.gamesapi.core.data.source.cache.FakeGameCacheDataSource
import com.junemon.gamesapi.core.data.source.remote.FakeGameRemoteDataSource
import org.junit.Before

/**
 * Created by Ian Damping on 01,December,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */

private const val EMPTY_DATA = "No Data Available"

class GameRepositoryTest {
    private val genreItem1 = GenresItemResponse("satu")
    private val genreItem2 = GenresItemResponse("dua")
    private val genreItem3 = GenresItemResponse("tiga")
    private val listOfGenre = listOf(genreItem1, genreItem2, genreItem3)
    private val gameItemRespons1 = GamesItemResponse("satu", 1)
    private val gameItemRespons2 = GamesItemResponse("dua", 2)
    private val gameItemRespons3 = GamesItemResponse("tiga", 3)
    private val listOfGameItem = listOf(gameItemRespons1, gameItemRespons2, gameItemRespons3)

    private val game1 =
        GameResponse(name = "a", id = 1, genres = listOfGenre, backgroundImage = "a")
    private val game2 =
        GameResponse(name = "b", id = 1, genres = listOfGenre, backgroundImage = "b")
    private val game3 =
        GameResponse(name = "c", id = 1, genres = listOfGenre, backgroundImage = "c")
    private val listOfGame = listOf(game1, game2, game3)
    private val gameGenre1 = GameGenreResponse(name = "a", listOfGameItem)
    private val gameGenre2 = GameGenreResponse(name = "b", listOfGameItem)
    private val gameGenre3 = GameGenreResponse(name = "c", listOfGameItem)
    private val listOfGameGenre = listOf(gameGenre1, gameGenre2, gameGenre3)
    private val gameDetail1 = GameDetailResponse(
        backgroundImage = "a",
        description = "a",
        listOfGenre,
        rating = 1.1,
        name = "a"
    )
    private val gameDetail2 = GameDetailResponse(
        backgroundImage = "b",
        description = "b",
        listOfGenre,
        rating = 1.1,
        name = "b"
    )
    private val gameDetail3 = GameDetailResponse(
        backgroundImage = "c",
        description = "c",
        listOfGenre,
        rating = 1.1,
        name = "c"
    )
    private val listOfGameDetail = listOf(gameDetail1, gameDetail2, gameDetail3)
    private val gameSearch1 = GameSearchResponse(id = 1, name = "a", backgroundImage = "a")
    private val gameSearch2 = GameSearchResponse(id = 2, name = "b", backgroundImage = "b")
    private val gameSearch3 = GameSearchResponse(id = 3, name = "c", backgroundImage = "c")
    private val listOfGameSearch = listOf(gameSearch1, gameSearch2, gameSearch3)
    private lateinit var remoteSource: FakeGameRemoteDataSource

    private val gameResponse1 = GameResponse(name = "a", id = 1, listOfGenre, backgroundImage = "a")
    private val gameResponse2 = GameResponse(name = "b", id = 1, listOfGenre, backgroundImage = "b")
    private val gameResponse3 = GameResponse(name = "c", id = 1, listOfGenre, backgroundImage = "c")
    private val listOfGameResponse = listOf(gameResponse1, gameResponse2, gameResponse3)
    private val gameEntity1 =
        GameEntity(gameId = 1, gameName = "a", gameGenre = "a", gameImage = "a")
    private val gameEntity2 =
        GameEntity(gameId = 2, gameName = "b", gameGenre = "b", gameImage = "b")
    private val gameEntity3 =
        GameEntity(gameId = 3, gameName = "c", gameGenre = "c", gameImage = "c")
    private val listOfGameEntity = listOf(gameEntity1, gameEntity2, gameEntity3)
    private val gameFavEntity1 = GameFavoriteEntity(
        gameFavoriteId = 1,
        gameName = "a",
        gameDescription = "a",
        gameGenre = "a",
        gameImage = "a",
        gameRating = "1.1"
    )
    private val gameFavEntity2 = GameFavoriteEntity(
        gameFavoriteId = 1,
        gameName = "b",
        gameDescription = "b",
        gameGenre = "b",
        gameImage = "b",
        gameRating = "2.2"
    )
    private val gameFavEntity3 = GameFavoriteEntity(
        gameFavoriteId = 1,
        gameName = "c",
        gameDescription = "c",
        gameGenre = "c",
        gameImage = "c",
        gameRating = "3.3"
    )
    private val listOfGameFavEntity = listOf(gameFavEntity1, gameFavEntity2, gameFavEntity3)

    private lateinit var localSource: FakeGameCacheDataSource

    // Class under test
    private lateinit var gameRepository: GameRepositoryImpl

    @Before
    fun createRepository() {
        remoteSource = FakeGameRemoteDataSource(
            fakeGame = listOfGame.toMutableList(),
            fakeGameGenre = listOfGameGenre.toMutableList(),
            fakeGameDetail = listOfGameDetail.toMutableList(),
            fakeGameSearch = listOfGameSearch.toMutableList()
        )
        localSource = FakeGameCacheDataSource(
            fakeSaveGame = listOfGameResponse.toMutableList(),
            fakeGame = listOfGameEntity.toMutableList(),
            fakeGameFavorite = listOfGameFavEntity.toMutableList()
        )

    }
}