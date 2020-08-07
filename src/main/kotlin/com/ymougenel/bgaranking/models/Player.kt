package com.ymougenel.bgaranking.models

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Player {
    var name: String = ""
    @Id
    var id: Long = 0
    var avatar: String = ""
    var country: String = ""
    var countryFlagX = 0
    var countryFlagY = 0

}