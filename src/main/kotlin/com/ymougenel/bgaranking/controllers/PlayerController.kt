package com.ymougenel.bgaranking.controllers

import com.ymougenel.bgaranking.models.Player
import com.ymougenel.bgaranking.utils.PlayerService
import com.ymougenel.bgaranking.utils.RankingsService
import com.ymougenel.bgaranking.utils.WebService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

//TODO: define origin policy
@CrossOrigin
@RestController
@RequestMapping("/player")
class PlayerController {

    private var rankingsService: RankingsService
    private var webService: WebService
    private var playerService: PlayerService
    var logger = LoggerFactory.getLogger(PlayerController::class.java)

    @Autowired
    constructor(rankingsService: RankingsService, playerService: PlayerService, webService: WebService) {
        this.rankingsService = rankingsService
        this.playerService = playerService
        this.webService = webService
    }

    @GetMapping("/{playerName}")
    fun getRank(@PathVariable("playerName") playerName: String): Player {
        return playerService.findByPlayerName(playerName)
    }

    @GetMapping("/save/{id}")
    fun saveGameRankings(@PathVariable("id") gameId: String) {
        this.webService.save(gameId)
    }


}