package com.ymougenel.bgaranking.persistence

import com.ymougenel.bgaranking.models.Ranking
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RankingsRepository : JpaRepository<Ranking, Long> {
    fun findByPlayerId(playerId: Long): List<Ranking>
}