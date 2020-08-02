package com.ymougenel.bgaranking.utils

import com.ymougenel.bgaranking.models.Ranking
import com.ymougenel.bgaranking.persistence.RankingsRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class RankingsService {


    var logger = LoggerFactory.getLogger(RankingsService::class.java)
    private val rankingsRepository: RankingsRepository

    @Autowired
    constructor(rankingsRepository: RankingsRepository) {
        this.rankingsRepository = rankingsRepository
    }

    fun saveAll(rankings: List<Ranking>): List<Ranking> = rankingsRepository.saveAll(rankings)
    fun findRanksForGame(gameId: String, playerId: Long): List<Ranking> {
        //TODO jpa query (from, to, game, playerid)
        var ranks = rankingsRepository.findByPlayerId(playerId).filter { it.gameId == gameId}
        return ranks
    }

    fun findRanksForGameBetween(gameId: String, endDate: OffsetDateTime, startDate: OffsetDateTime) =
            this.rankingsRepository
                    .findAllByDateLessThanEqualAndDateGreaterThanEqual(endDate, startDate)
                    .filter { it.gameId == gameId }
                    // TODO: filter in sql request
}
