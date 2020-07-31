package com.ymougenel.bgaranking.utils

import com.ymougenel.bgaranking.models.Game
import com.ymougenel.bgaranking.persistence.GameRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class GameService {


    var logger = LoggerFactory.getLogger(GameService::class.java)
    private val gameRepository: GameRepository

    @Autowired
    constructor(gameRepository: GameRepository) {
        this.gameRepository = gameRepository
    }

    fun save(game: List<Game>) = gameRepository.saveAll(game)
    fun saveAll(games: List<Game>) = gameRepository.saveAll(games)


    fun findPlayerByNameContaining(queryName: String, pageRequest: PageRequest) = gameRepository.findGameByNameContainingIgnoreCase(queryName, pageRequest)

    fun findById(id: Long): Optional<Game> {
        return gameRepository.findById(id)
    }

    fun findAll() = gameRepository.findAll()
}