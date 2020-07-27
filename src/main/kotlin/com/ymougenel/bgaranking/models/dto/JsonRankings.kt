package com.ymougenel.bgaranking.models.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.ymougenel.bgaranking.models.Player
import com.ymougenel.bgaranking.models.Ranking


@JsonIgnoreProperties(ignoreUnknown = true)
class JsonRankings {
    @JsonProperty("id")
    var playerId: Long = 0
    @JsonProperty("name")
    var playerName: String = ""
    @JsonProperty("countryName")
    var countryName: String? = null
    var arena: String = ""
    var avatar: String = ""
    @JsonProperty("rank_no")
    var rank: Long = 0

    fun toRanking(gameId: String): Ranking {
        val ranking = Ranking()
        ranking.gameId = gameId
        ranking.playerId = playerId
        ranking.rank = rank
        ranking.elo = java.lang.Long.valueOf(arena!!.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]) // arena is formed as this: 501.1851
        return ranking
    }

    fun toPlayer(): Player {
        val player = Player()
        player.id = playerId
        player.name = playerName
        player.avatar = avatar
        //        player.setCountry(countryName);
        return player
    }
}
