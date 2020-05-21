package com.ymougenel.bgaranking.utils

import com.ymougenel.bgaranking.models.Player
import com.ymougenel.bgaranking.models.Ranking
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WebService {


    var logger = LoggerFactory.getLogger(WebService::class.java)
    var rankingMapper = RankingMapper()
    private val rankingService: RankingsService
    private val playerService: PlayerService

    @Autowired
    constructor(rankingsService: RankingsService, playerService: PlayerService) {
        this.rankingService = rankingsService
        this.playerService = playerService
    }

    fun save(gameId: String) {
        var collectedCount: Long = 0
        var rankings: List<Ranking>
        var players: List<Player>
        do {
            var json = WebConnector.getRanks(gameId, collectedCount)
            rankings = rankingMapper.getRanks(json).map { it.toRanking() }
            rankingService.saveAll(rankings)
            players = rankingMapper.getRanks(json).map { it.toPlayer() }
            playerService.saveAll(players)
            collectedCount += rankings.size
            //TODO remoe 11 hardcoded value (for debug purpose)
        } while (collectedCount < 11 && rankings.size == 10)
    }
}