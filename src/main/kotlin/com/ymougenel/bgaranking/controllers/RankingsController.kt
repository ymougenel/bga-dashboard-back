package com.ymougenel.bgaranking.controllers

import com.ymougenel.bgaranking.models.Ranking
import com.ymougenel.bgaranking.utils.RankingsService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

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

//    @GetMapping("/{playerId}")
//    fun getRank(@PathVariable("playerId") playerId: Long): List<Ranking> {
//        return rankingsService.findRanksForGame("1127", playerId)
//    }

}