package com.ymougenel.bgaranking.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ymougenel.bgaranking.models.Player;
import com.ymougenel.bgaranking.models.Ranking;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonRankings {
    @JsonProperty("id")
    public long playerId;
    @JsonProperty("name")
    public String playerName;
    @JsonProperty("countryName")
    public String countryName;
    public String arena;
    public String avatar;
    @JsonProperty("rank_no")
    public long rank;


    public JsonRankings(){}

    public Ranking toRanking() {
        Ranking ranking = new Ranking();
        ranking.setPlayerId(playerId);
        ranking.setRank(rank);
        ranking.setElo(Long.valueOf(arena.split("\\.")[1])); // arena is formed as this: 501.1851
        return ranking;
    }

    public Player toPlayer() {
        Player player = new Player();
        player.setId(playerId);
        player.setName(playerName);
        player.setAvatar(avatar);
//        player.setCountry(countryName);
        return player;
    }
}
