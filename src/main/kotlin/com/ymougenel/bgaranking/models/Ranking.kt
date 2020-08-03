package com.ymougenel.bgaranking.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import java.time.LocalDateTime
import java.time.OffsetDateTime


@Entity
class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    var playerId: Long = 0
    var gameId: String = ""
    var elo: Long = 0
    var rank: Long = 0
    var executionTraceId: Long = 0
    var date = OffsetDateTime.now()
}
