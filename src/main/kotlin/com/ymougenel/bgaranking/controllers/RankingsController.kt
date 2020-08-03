package com.ymougenel.bgaranking.controllers

import com.ymougenel.bgaranking.models.Ranking
import com.ymougenel.bgaranking.models.dto.RankingTableDTO
import com.ymougenel.bgaranking.utils.RankingsService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.Instant


//TODO: define origin policy
@CrossOrigin
@RestController
@RequestMapping("/ranking")
class RankingsController {

    private var rankingsService: RankingsService
    var logger = LoggerFactory.getLogger(RankingsController::class.java)

    @Autowired
    constructor(rankingsService: RankingsService) {
        this.rankingsService = rankingsService
    }


    @GetMapping("/{gameId}/{playerId}")
    fun getRank(@PathVariable("gameId") gameId: String, @PathVariable("playerId") playerId: Long): List<Ranking> {
        return rankingsService.findRanksForGame(gameId, playerId)
    }

    @GetMapping("/{gameId}/{playerId}/{count}/{startDate}/{endDate}")
    fun getRank(@PathVariable("gameId") gameId: String,
                @PathVariable("playerId") playerId: Long,
                @PathVariable("count") count: Long,
                @PathVariable("startDate") startDate: Long,
                @PathVariable("endDate") endDate: Long): List<RankingTableDTO> {

        val odt = OffsetDateTime.now(ZoneId.systemDefault())
        val zoneOffset = odt.offset
        return rankingsService.findRanksForGameBetween(gameId, playerId, count, Instant.ofEpochSecond(endDate).atOffset(zoneOffset),  Instant.ofEpochSecond(startDate).atOffset(zoneOffset))
    }


}