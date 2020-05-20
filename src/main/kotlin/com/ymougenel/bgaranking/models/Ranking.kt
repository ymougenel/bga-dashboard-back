package com.ymougenel.bgaranking.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("data")
class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @JsonProperty("playerId")
    var playerId: Long = 0

    //TODO parse elo from arena field
    //    public String elo;

    @JsonProperty("rank_no")
    var rank: Long = 0
}
