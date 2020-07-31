package com.ymougenel.bgaranking.utils

import com.ymougenel.bgaranking.models.Game
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

import java.io.IOException
import java.net.URISyntaxException
import java.util.Arrays

import org.junit.Assert.*


@RunWith(SpringRunner::class)
@SpringBootTest
class WebConnectorTest {

    private val rankingMapper = RankingMapper()
    @Test
    @Throws(IOException::class, URISyntaxException::class)
    fun testRankingsPage() {
        val DICE_FORGE = Game()
        DICE_FORGE.id = 1127L
        DICE_FORGE.name = "Dice Forge"
        var jsonRanks = WebConnector.getRanks(DICE_FORGE.id.toString() + "", 0)
        assertEquals(10, rankingMapper.getRanks(jsonRanks).size.toLong())

        jsonRanks = WebConnector.getRanks(DICE_FORGE.id.toString() + "", 50)
        assertEquals(10, rankingMapper.getRanks(jsonRanks).size.toLong())
        assertEquals(51, rankingMapper.getRanks(jsonRanks)[0].rank)

    }
}
