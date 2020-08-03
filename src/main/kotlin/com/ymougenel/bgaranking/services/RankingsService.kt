package com.ymougenel.bgaranking.utils

import com.ymougenel.bgaranking.models.Ranking
import com.ymougenel.bgaranking.models.dto.RankingTableDTO
import com.ymougenel.bgaranking.persistence.PlayersRepository
import com.ymougenel.bgaranking.persistence.RankingsRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class RankingsService {


    var logger = LoggerFactory.getLogger(RankingsService::class.java)
    private val rankingsRepository: RankingsRepository
    private val playersRepository: PlayersRepository

    @Autowired
    constructor(rankingsRepository: RankingsRepository, playersRepository: PlayersRepository) {
        this.rankingsRepository = rankingsRepository
        this.playersRepository = playersRepository
    }

    fun saveAll(rankings: List<Ranking>): List<Ranking> = rankingsRepository.saveAll(rankings)
    fun findRanksForGame(gameId: String, playerId: Long): List<Ranking> {
        //TODO jpa query (from, to, gameId, playerid)
        return rankingsRepository.findByPlayerId(playerId)
                .filter { it.gameId == gameId }
    }

    fun findRanksForGameBetween(gameId: String, playerId: Long, count: Long, endDate: OffsetDateTime, startDate: OffsetDateTime): List<RankingTableDTO> {
        val allGameRankings = this.rankingsRepository
                .findAllByDateLessThanEqualAndDateGreaterThanEqual(endDate, startDate)
                .filter { it.gameId == gameId }

        val slicedRankings = slicePlayerRankings(allGameRankings, playerId, count)
//                .map { ranking -> RankingTableDTO.fromRanking(ranking, this.playersRepository.findById(ranking.playerId).get()) }
        return slicedRankings
        // TODO: filter in sql request
    }

    private fun slicePlayerRankings(rankings: List<Ranking>, playerId: Long, count: Long): List<RankingTableDTO> {
        if (rankings.isEmpty()) {
            return ArrayList()
        }
        val playerRankings = rankings.filter { r -> r.playerId == playerId }
        if (playerRankings.isEmpty()) {
            return ArrayList()
        }

        val latesetExecutionId = playerRankings.get(playerRankings.size - 1).executionTraceId
        val playerRank = playerRankings.last().rank
        // Filter:
        // 1/ Take latest date (executionTraceId)
        // 2/ Select ranking around rank
        val filterRankings = rankings
                .filter { ranking -> ranking.executionTraceId == latesetExecutionId }
                .filter { ranking -> Math.abs(ranking.rank - playerRank) <= count / 2 }
        return filterRankings.map { ranking ->
            RankingTableDTO.fromRanking(ranking,
                                                                            rankings.first { it.playerId == ranking.playerId },
                    this.playersRepository.findById(ranking.playerId).get())
        }
    }
}
