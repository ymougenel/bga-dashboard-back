package com.ymougenel.bgaranking.models.dto;

import com.ymougenel.bgaranking.models.Player;
import com.ymougenel.bgaranking.models.Ranking;

import java.time.OffsetDateTime;

public class RankingTableDTO {
    public Long id;
    public Player player;
    public String gameId;
    public Long elo;
    public Long rank;
    public OffsetDateTime offsetDateTime;

    public static RankingTableDTO fromRanking(Ranking ranking, Player player) {
        RankingTableDTO dto = new RankingTableDTO();
        dto.id = ranking.getId();
        dto.player = player;
        dto.gameId = ranking.getGameId();
        dto.elo = ranking.getElo();
        dto.rank = ranking.getRank();
        return dto;
    }
}
