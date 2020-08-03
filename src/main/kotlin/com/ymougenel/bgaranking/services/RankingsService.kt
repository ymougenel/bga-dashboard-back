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
        //TODO jpa query (from, to, game, playerid)
        return rankingsRepository.findByPlayerId(playerId)
                .filter { it.gameId == gameId }
    }

    fun findRanksForGameBetween(gameId: String, playerId: Long, count: Long, endDate: OffsetDateTime, startDate: OffsetDateTime): List<RankingTableDTO> {
        val allGameRankings = this.rankingsRepository
                .findAllByDateLessThanEqualAndDateGreaterThanEqual(endDate, startDate)
                .filter { it.gameId == gameId }

        val slicedRankings = sliceRankingsOccurances(allGameRankings)
                .map { ranking -> RankingTableDTO.fromRanking(ranking, this.playersRepository.findById(ranking.playerId).get()) }
        return slicePlayerRankings(slicedRankings, playerId, count)
        // TODO: filter in sql request
    }


    private fun sliceRankingsOccurances(allrankings: List<Ranking>): List<Ranking> {
        if (allrankings.isEmpty()) {
            return allrankings
        }
        val total = allrankings.size
        // Between tow dates, many ranking snapshot could have been taken, take  most recent TODO oldest
        val occurrencesNb = allrankings
                .filter { ranking -> ranking.playerId == allrankings.get(0).playerId }
                .count()
        return allrankings.subList(0, total / occurrencesNb)
    }

    private fun slicePlayerRankings(rankings: List<RankingTableDTO>, playerId: Long, count: Long): List<RankingTableDTO> {
        if (rankings.isEmpty()) {
            return rankings;
        }
        val total = rankings.size;
        val playerRank = rankings.filter { r -> r.player!!.id == playerId }
                .first().rank;

        return rankings.filter{ ranking -> Math.abs(ranking.rank!! - playerRank!!) <= count / 2}
    }
}
