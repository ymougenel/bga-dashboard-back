package com.ymougenel.bgaranking.utils;

import com.ymougenel.bgaranking.models.Player;
import com.ymougenel.bgaranking.models.Ranking;
import com.ymougenel.bgaranking.models.dto.JsonRankings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RankingMapperTest {

    public String json = "{\"status\":1,\"data\":{\"ranks\":[{\"id\":\"85045241\",\"name\":\"Simone92\",\"country\":{\"flag\":109,\"name\":\"Italy\",\"cur\":\"EUR\",\"code\":\"IT\",\"flag_x\":160,\"flag_y\":99},\"arena\":\"501.1912\",\"rank_no\":\"1\",\"avatar\":\"6f153eee4f\",\"device\":\"desktop\",\"status\":\"offline\"},{\"id\":\"63527099\",\"name\":\"UrBrother\",\"country\":{\"flag\":214,\"name\":\"Thailand\",\"cur\":\"THB\",\"code\":\"TH\",\"flag_x\":336,\"flag_y\":44},\"arena\":\"501.1851\",\"rank_no\":\"2\",\"avatar\":\"x00000\",\"device\":\"desktop\",\"status\":\"offline\"},{\"id\":\"84987468\",\"name\":\"bidouche\",\"country\":{\"flag\":19,\"name\":\"Belgium\",\"cur\":\"EUR\",\"code\":\"BE\",\"flag_x\":16,\"flag_y\":99},\"arena\":\"501.1850\",\"rank_no\":\"3\",\"avatar\":\"9cc1b7f715\",\"device\":\"mobile\",\"status\":\"offline\"},{\"id\":\"85496538\",\"name\":\"Callipp0\",\"country\":{\"flag\":19,\"name\":\"Belgium\",\"cur\":\"EUR\",\"code\":\"BE\",\"flag_x\":16,\"flag_y\":99},\"arena\":\"501.1831\",\"rank_no\":\"4\",\"avatar\":\"1b3208fc89\",\"device\":\"desktop\",\"status\":\"offline\"},{\"id\":\"6454190\",\"name\":\"babyboy6231\",\"country\":{\"flag\":155,\"name\":\"Malaysia\",\"cur\":\"MYR\",\"code\":\"MY\",\"flag_x\":240,\"flag_y\":55},\"arena\":\"501.1823\",\"rank_no\":\"5\",\"avatar\":\"x00000\",\"device\":\"desktop\",\"status\":\"offline\"},{\"id\":\"84735935\",\"name\":\"lincoln9818\",\"country\":{\"flag\":95,\"name\":\"Hong Kong\",\"cur\":\"HKD\",\"code\":\"HK\",\"flag_x\":144,\"flag_y\":55},\"arena\":\"501.1818\",\"rank_no\":\"6\",\"avatar\":\"9fc22854d6\",\"device\":\"desktop\",\"status\":\"offline\"},{\"id\":\"87685997\",\"name\":\"Seeker of the Way\",\"country\":{\"flag\":19,\"name\":\"Belgium\",\"cur\":\"EUR\",\"code\":\"BE\",\"flag_x\":16,\"flag_y\":99},\"arena\":\"501.1808\",\"rank_no\":\"7\",\"avatar\":\"c673ce56b1\",\"device\":\"mobile\",\"status\":\"offline\"},{\"id\":\"85493807\",\"name\":\"Bin16\",\"country\":{\"flag\":237,\"name\":\"Viet Nam\",\"cur\":\"VND\",\"code\":\"VN\",\"flag_x\":368,\"flag_y\":77},\"arena\":\"501.1806\",\"rank_no\":\"8\",\"avatar\":\"68f8c2e70e\",\"device\":\"desktop\",\"status\":\"offline\"},{\"id\":\"47192420\",\"name\":\"critical\",\"country\":{\"flag\":76,\"name\":\"France\",\"cur\":\"EUR\",\"code\":\"FR\",\"flag_x\":112,\"flag_y\":66},\"arena\":\"501.1802\",\"rank_no\":\"9\",\"avatar\":\"xd255345ce\",\"device\":\"desktop\",\"status\":\"online\"},{\"id\":\"84953342\",\"name\":\"pandanna2\",\"country\":{\"flag\":229,\"name\":\"United States\",\"cur\":\"USD\",\"code\":\"US\",\"flag_x\":352,\"flag_y\":99},\"arena\":\"501.1794\",\"rank_no\":\"10\",\"avatar\":\"e4fbd4bea2\",\"device\":\"desktop\",\"status\":\"offline\"}],\"champion\":{\"id\":\"84457426\",\"name\":\"Piranas\",\"country\":{\"flag\":76,\"name\":\"France\",\"cur\":\"EUR\",\"code\":\"FR\",\"flag_x\":112,\"flag_y\":66},\"avatar\":\"x00000\",\"device\":\"desktop\",\"status\":\"offline\"}}}";
    public RankingMapper rankingMapper = new RankingMapper();

    @Test
    public void testJsonToRankings() {

        List<Ranking> rankings = rankingMapper.getRanks(json)
                .stream()
                .map(s -> s.toRanking("123"))
                .collect(Collectors.toList());

        assertEquals(10, rankings.size());
        assertEquals(63527099, rankings.get(1).getPlayerId());
        assertEquals(1912,rankings.get(0).getElo());
        assertEquals("123", rankings.get(0).getGameId());
        assertEquals(1, rankings.get(0).getRank());

    }


    @Test
    public void testJsonToPlayers() {

        List<Player> players = rankingMapper.getRanks(json)
                .stream()
                .map(JsonRankings::toPlayer)
                .collect(Collectors.toList());

        assertEquals(10, players.size());
        assertEquals("Simone92", players.get(0).getName());
        //TODO fix json country mapping
//        assertEquals("Thailand",players.get(1).getCountry());
        assertEquals("1b3208fc89", players.get(2).getAvatar());

    }
}
