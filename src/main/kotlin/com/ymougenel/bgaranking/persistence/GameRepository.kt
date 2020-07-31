package com.ymougenel.bgaranking.persistence

import com.ymougenel.bgaranking.models.Game
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GameRepository : JpaRepository<Game, Long> {
    fun findGameByNameContainingIgnoreCase(queryName: String, pageable: Pageable) : Page<Game>
}