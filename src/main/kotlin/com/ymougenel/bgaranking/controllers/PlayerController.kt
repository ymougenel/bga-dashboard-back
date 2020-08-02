package com.ymougenel.bgaranking.controllers

import com.ymougenel.bgaranking.models.Player
import com.ymougenel.bgaranking.utils.PlayerService
import com.ymougenel.bgaranking.utils.RankingsService
import com.ymougenel.bgaranking.utils.WebService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
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
        logger.info("bga-REQUEST /player/$playerName")
        return playerService.findByPlayerName(playerName)
    }

    @GetMapping("/search/{queryName}")
    fun searchMatchingPlayerName(@PathVariable("queryName") queryName: String): Page<Player> {
        logger.info("bga-REQUEST /player/search/$queryName")
        val pageRequest: PageRequest = PageRequest.of(0, 10, Sort.Direction.fromString("ASC"), "name")
        return playerService.findPlayerByNameContaining(queryName, pageRequest)
    }

    // TODO move to batch
    @GetMapping("/save/{id}")
    fun saveGameRankings(@PathVariable("id") gameId: String) {
        this.webService.save(gameId)
    }


}