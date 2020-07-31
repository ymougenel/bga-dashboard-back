package com.ymougenel.bgaranking.models

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Game(
    var name: String = "",
    @Id
    var id: Long = 0,
    var logo: String = ""
) {
    constructor() : this("", 0L, "")
}