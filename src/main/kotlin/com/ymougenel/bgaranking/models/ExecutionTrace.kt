package com.ymougenel.bgaranking.models

import java.time.OffsetDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class ExecutionTrace {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    var gameId : Long = 0
    var date = OffsetDateTime.now()

    constructor(){}
    constructor(gameId: Long){
        this.gameId = gameId
    }
}