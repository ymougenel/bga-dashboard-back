package com.ymougenel.bgaranking.utils;

import com.ymougenel.bgaranking.models.Ranking;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RankingMapperTest {

    public String json = "{\"status\":1,\"data\":{\"ranks\":[{\"playerId\":\"85045241\",\"name\":\"Simone92\",\"country\":{\"flag\":109,\"name\":\"Italy\",\"cur\":\"EUR\",\"code\":\"IT\",\"flag_x\":160,\"flag_y\":99},\"arena\":\"501.1912\",\"rank_no\":\"1\",\"avatar\":\"6f153eee4f\",\"device\":\"desktop\",\"status\":\"offline\"},{\"playerId\":\"63527099\",\"name\":\"UrBrother\",\"country\":{\"flag\":214,\"name\":\"Thailand\",\"cur\":\"THB\",\"code\":\"TH\",\"flag_x\":336,\"flag_y\":44},\"arena\":\"501.1851\",\"rank_no\":\"2\",\"avatar\":\"x00000\",\"device\":\"desktop\",\"status\":\"offline\"},{\"playerId\":\"84987468\",\"name\":\"bidouche\",\"country\":{\"flag\":19,\"name\":\"Belgium\",\"cur\":\"EUR\",\"code\":\"BE\",\"flag_x\":16,\"flag_y\":99},\"arena\":\"501.1850\",\"rank_no\":\"3\",\"avatar\":\"9cc1b7f715\",\"device\":\"mobile\",\"status\":\"offline\"},{\"playerId\":\"85496538\",\"name\":\"Callipp0\",\"country\":{\"flag\":19,\"name\":\"Belgium\",\"cur\":\"EUR\",\"code\":\"BE\",\"flag_x\":16,\"flag_y\":99},\"arena\":\"501.1831\",\"rank_no\":\"4\",\"avatar\":\"1b3208fc89\",\"device\":\"desktop\",\"status\":\"offline\"},{\"playerId\":\"6454190\",\"name\":\"babyboy6231\",\"country\":{\"flag\":155,\"name\":\"Malaysia\",\"cur\":\"MYR\",\"code\":\"MY\",\"flag_x\":240,\"flag_y\":55},\"arena\":\"501.1823\",\"rank_no\":\"5\",\"avatar\":\"x00000\",\"device\":\"desktop\",\"status\":\"offline\"},{\"playerId\":\"84735935\",\"name\":\"lincoln9818\",\"country\":{\"flag\":95,\"name\":\"Hong Kong\",\"cur\":\"HKD\",\"code\":\"HK\",\"flag_x\":144,\"flag_y\":55},\"arena\":\"501.1818\",\"rank_no\":\"6\",\"avatar\":\"9fc22854d6\",\"device\":\"desktop\",\"status\":\"offline\"},{\"playerId\":\"87685997\",\"name\":\"Seeker of the Way\",\"country\":{\"flag\":19,\"name\":\"Belgium\",\"cur\":\"EUR\",\"code\":\"BE\",\"flag_x\":16,\"flag_y\":99},\"arena\":\"501.1808\",\"rank_no\":\"7\",\"avatar\":\"c673ce56b1\",\"device\":\"mobile\",\"status\":\"offline\"},{\"playerId\":\"85493807\",\"name\":\"Bin16\",\"country\":{\"flag\":237,\"name\":\"Viet Nam\",\"cur\":\"VND\",\"code\":\"VN\",\"flag_x\":368,\"flag_y\":77},\"arena\":\"501.1806\",\"rank_no\":\"8\",\"avatar\":\"68f8c2e70e\",\"device\":\"desktop\",\"status\":\"offline\"},{\"playerId\":\"47192420\",\"name\":\"critical\",\"country\":{\"flag\":76,\"name\":\"France\",\"cur\":\"EUR\",\"code\":\"FR\",\"flag_x\":112,\"flag_y\":66},\"arena\":\"501.1802\",\"rank_no\":\"9\",\"avatar\":\"xd255345ce\",\"device\":\"desktop\",\"status\":\"online\"},{\"playerId\":\"84953342\",\"name\":\"pandanna2\",\"country\":{\"flag\":229,\"name\":\"United States\",\"cur\":\"USD\",\"code\":\"US\",\"flag_x\":352,\"flag_y\":99},\"arena\":\"501.1794\",\"rank_no\":\"10\",\"avatar\":\"e4fbd4bea2\",\"device\":\"desktop\",\"status\":\"offline\"}],\"champion\":{\"playerId\":\"84457426\",\"name\":\"Piranas\",\"country\":{\"flag\":76,\"name\":\"France\",\"cur\":\"EUR\",\"code\":\"FR\",\"flag_x\":112,\"flag_y\":66},\"avatar\":\"x00000\",\"device\":\"desktop\",\"status\":\"offline\"}}}";

    @Test
    public void testJsonToSomething() {

        List<Ranking> ranks = RankingMapper.INSTANCE.getRanks(json);
        assertEquals(10, ranks.size());
//        assertEquals("Simone92", ranks.get(0).getName());
        assertEquals(63527099, ranks.get(1).getPlayerId());
        assertEquals(1, ranks.get(0).getRank());

    }
}
