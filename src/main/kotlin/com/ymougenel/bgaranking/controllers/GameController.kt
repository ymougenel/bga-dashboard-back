package com.ymougenel.bgaranking.controllers

import com.ymougenel.bgaranking.models.Game
import com.ymougenel.bgaranking.utils.GameService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*
import java.util.*

//TODO: define origin policy
@CrossOrigin
@RestController
@RequestMapping("/game")
class GameController {

    private var gameService: GameService
    var logger = LoggerFactory.getLogger(GameController::class.java)

    @Autowired
    constructor(gameService: GameService) {
        this.gameService = gameService
    }

    @GetMapping("/{id}")
    fun getGame(@PathVariable("id") gameId: Long): Game {
        return gameService.findById(gameId).get()
    }

    @GetMapping("/all")
    fun getAll(): MutableList<Game> {
        var allGames = gameService.findAll()
        if (allGames.isEmpty()) {
            // TODO: fix data injection
            allGames = this.gameService.saveAll(Arrays.asList(Game("Dice Forge", 1127,"https://x.boardgamearena.net/data/themereleases/current/games/diceforge/200704-2331/img/game_box180.png"),
                    Game("7 Wonders", 1131,"https://x.boardgamearena.net/data/themereleases/current/games/sevenwonders/200213-1215/img/game_box180.png")))
        }
        return allGames
    }


    //TODO: FEAT add number participant / game
    // TODO ...requires regular game update


    @GetMapping("/search/{queryName}")
    fun searchMatchingPlayerName(@PathVariable("queryName") queryName: String): Page<Game> {
        val pageRequest : PageRequest = PageRequest.of(0, 10, Sort.Direction.fromString("ASC"), "name")
        return gameService.findPlayerByNameContaining(queryName, pageRequest)
    }


}