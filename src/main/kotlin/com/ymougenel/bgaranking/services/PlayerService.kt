package com.ymougenel.bgaranking.utils

import com.ymougenel.bgaranking.models.Player
import com.ymougenel.bgaranking.persistence.PlayersRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlayerService {


    var logger = LoggerFactory.getLogger(PlayerService::class.java)
    private val playersRepository: PlayersRepository

    @Autowired
    constructor(playersRepository: PlayersRepository) {
        this.playersRepository = playersRepository
    }

    fun saveAll(players: List<Player>) = playersRepository.saveAll(players)

    fun findByPlayerName(playerName: String) = playersRepository.findByName(playerName)
}