package com.ymougenel.bgaranking.utils

import com.ymougenel.bgaranking.models.ExecutionTrace
import com.ymougenel.bgaranking.models.Player
import com.ymougenel.bgaranking.models.Ranking
import com.ymougenel.bgaranking.persistence.ExecutionTracerRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WebService {


    var logger = LoggerFactory.getLogger(WebService::class.java)
    var rankingMapper = RankingMapper()
    private val rankingService: RankingsService
    private val playerService: PlayerService
    private val exeexecutionTracerRepository: ExecutionTracerRepository

    @Autowired
    constructor(rankingsService: RankingsService, playerService: PlayerService, exeexecutionTracerRepository: ExecutionTracerRepository) {
        this.rankingService = rankingsService
        this.playerService = playerService
        this.exeexecutionTracerRepository = exeexecutionTracerRepository
    }

    fun save(gameId: String) {
        var executionTrace = this.exeexecutionTracerRepository.save(ExecutionTrace(gameId.toLong()))
        var collectedCount: Long = 0
        var rankings: List<Ranking>
        var players: List<Player>
        do {
            var json = WebConnector.getRanks(gameId, collectedCount)
            rankings = rankingMapper.getRanks(json)
                    .map { it.toRanking(gameId) }

            rankings.forEach{ r -> r.executionTraceId = executionTrace.id}
            rankingService.saveAll(rankings)
            players = rankingMapper.getRanks(json).map { it.toPlayer() }
            playerService.saveAll(players)
            collectedCount += rankings.size
            //TODO remoe 11 hardcoded value (for debug purpose)
        } while (collectedCount < 300 && rankings.size == 10)
    }
}