package com.ymougenel.bgaranking.models.dto

import com.ymougenel.bgaranking.models.Player
import com.ymougenel.bgaranking.models.Ranking

import java.time.OffsetDateTime

class RankingTableDTO {
    var id: Long? = null
    var player: Player? = null
    var gameId: String = ""
    var rankVariation: Long = 0
    var elo: Long? = null
    var rank: Long? = null
    var offsetDateTime: OffsetDateTime? = null

    companion object {

        fun fromRanking(ranking: Ranking, beforeRanking: Ranking, player: Player): RankingTableDTO {
            val dto = RankingTableDTO()
            dto.id = ranking.id
            dto.player = player
            dto.gameId = ranking.gameId
            dto.elo = ranking.elo
            dto.rankVariation = beforeRanking.rank -ranking.rank
            dto.rank = ranking.rank
            return dto
        }
    }
}
