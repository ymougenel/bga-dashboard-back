package com.ymougenel.bgaranking.utils;

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

    private RankingMapper rankingMapper = new RankingMapper();
    @Test
    public void testRankingsPage() throws IOException, URISyntaxException {
        String jsonRanks = WebConnector.getRanks("1127l",0);
        assertEquals(10, rankingMapper.getRanks(jsonRanks).size());

        jsonRanks = WebConnector.getRanks("1127l",50);
        assertEquals(10, rankingMapper.getRanks(jsonRanks).size());
        assertEquals(51, rankingMapper.getRanks(jsonRanks).get(0).getRank());

    }
}
