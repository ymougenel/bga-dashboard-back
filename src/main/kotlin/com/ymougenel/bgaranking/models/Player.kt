package com.ymougenel.bgaranking.models

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Player {
    var name: String = ""
    @Id
    var id: Long = 0
    var country: String = ""
    var avatar: String = ""

}