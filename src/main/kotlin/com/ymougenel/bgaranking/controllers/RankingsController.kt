package com.ymougenel.bgaranking.controllers

import com.ymougenel.bgaranking.utils.RankingsService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ranking")
class RankingsController {

    private var rankingsService: RankingsService
    var logger = LoggerFactory.getLogger(RankingsController::class.java)

    @Autowired
    constructor(rankingsService: RankingsService) {
        this.rankingsService = rankingsService
    }


}