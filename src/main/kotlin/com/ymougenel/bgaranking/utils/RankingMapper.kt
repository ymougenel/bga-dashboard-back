package com.ymougenel.bgaranking.utils

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import com.ymougenel.bgaranking.models.dto.JsonRankings

class RankingMapper {


    private val mapper = ObjectMapper()
    fun getRanks(jsonInput: String): List<JsonRankings> {
        var ranksNode :JsonNode= mapper.readTree(jsonInput).get("data").get("ranks")
        return mapper.convertValue<List<JsonRankings>>(ranksNode)
    }
}
