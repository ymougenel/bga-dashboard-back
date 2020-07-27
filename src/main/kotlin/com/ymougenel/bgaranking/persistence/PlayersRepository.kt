package com.ymougenel.bgaranking.persistence

import com.ymougenel.bgaranking.models.Player
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlayersRepository : JpaRepository<Player, Long> {
    fun findByName(playerName: String): Player
    fun findPlayerByNameContainingIgnoreCase(queryName: String, pageable: Pageable) : Page<Player>
}