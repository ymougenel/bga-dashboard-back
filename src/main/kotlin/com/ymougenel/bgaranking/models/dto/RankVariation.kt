package com.ymougenel.bgaranking.models.dto

import com.ymougenel.bgaranking.models.Ranking

class RankVariation(before: Ranking, after: Ranking) {
    var elo: Long = 0
    var rank: Long = 0
    var rankVariation: Long = 0
    var eloVariation: Long = 0

    init {
        this.elo = 0
        this.rank = after.rank
        this.eloVariation = 0
        this.rankVariation = after.rank - before.rank
    }
}
