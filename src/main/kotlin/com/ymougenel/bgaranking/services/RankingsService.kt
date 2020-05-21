package com.ymougenel.bgaranking.utils

import com.ymougenel.bgaranking.models.Ranking
import com.ymougenel.bgaranking.persistence.RankingsRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RankingsService {


    var logger = LoggerFactory.getLogger(RankingsService::class.java)
    private val rankingsRepository: RankingsRepository

    @Autowired
    constructor(rankingsRepository: RankingsRepository) {
        this.rankingsRepository = rankingsRepository
    }

    fun saveAll(rankings: List<Ranking>): List<Ranking> = rankingsRepository.saveAll(rankings)
}
