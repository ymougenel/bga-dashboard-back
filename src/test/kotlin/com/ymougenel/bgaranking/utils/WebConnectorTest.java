package com.ymougenel.bgaranking.utils;

import com.ymougenel.bgaranking.models.Games;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WebConnectorTest {


    @Test
    public void testRankingsPage() throws IOException, URISyntaxException {

        String jsonRanks = WebConnector.getRanks(Games.DICE_FORGE,0);
        assertEquals(10, RankingMapper.INSTANCE.getRanks(jsonRanks).size());

        jsonRanks = WebConnector.getRanks(Games.DICE_FORGE,50);
        assertEquals(10, RankingMapper.INSTANCE.getRanks(jsonRanks).size());
        assertEquals(51, RankingMapper.INSTANCE.getRanks(jsonRanks).get(0).getRank());

    }
}
