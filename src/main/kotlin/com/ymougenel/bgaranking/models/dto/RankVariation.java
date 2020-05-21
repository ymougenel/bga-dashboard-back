package com.ymougenel.bgaranking.models.dto;

import com.ymougenel.bgaranking.models.Ranking;

public class RankVariation {
    public long elo;
    public long rank;
    public long rankVariation;
    public long eloVariation;

    public RankVariation(Ranking before, Ranking after) {
        this.elo = 0;
        this.rank = after.getRank();
        this.eloVariation = 0;
        this.rankVariation = after.getRank() - before.getRank();
    }
}
