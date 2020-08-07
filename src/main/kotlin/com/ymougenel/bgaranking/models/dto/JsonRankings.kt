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
    var countryName: String = ""
    var arena: String = ""
    var avatar: String = ""
    @JsonProperty("rank_no")
    var rank: Long = 0
    @JsonProperty("countryCode")
    var countryCode: String = ""
    @JsonProperty("countryFlagX")
    var countryFlagX: Int = 0
    @JsonProperty("countryFlag_y")
    var countryFlagY: Int = 0

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
        player.country = countryCode
        player.countryFlagX = countryFlagX
        player.countryFlagY = countryFlagY
        return player
    }

    @JsonProperty("country")
    private fun unpackNested(country: Map<String, Any>) {
        this.countryCode = country["code"] as String
        this.countryFlagX = country["flag_x"] as Int
        this.countryFlagY = country["flag_y"] as Int
    }
}
