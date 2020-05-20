package com.ymougenel.bgaranking.utils

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.util.JSONPObject
import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.readValue
import com.ymougenel.bgaranking.models.Ranking

object RankingMapper {

    private val mapper = ObjectMapper()
    fun getRanks(jsonInput: String): List<Ranking> {
        var ranksNode :JsonNode= mapper.readTree(jsonInput).get("data").get("ranks")
        return mapper.convertValue<List<Ranking>>(ranksNode)
    }
}
