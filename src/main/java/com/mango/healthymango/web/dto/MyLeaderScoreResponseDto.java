package com.mango.healthymango.web.dto;

import com.mango.healthymango.domain.LeaderScore;
import lombok.Getter;

@Getter
public class MyLeaderScoreResponseDto {
    private String id;
    private Integer rank;
    private String user;
    private Integer score;

    public MyLeaderScoreResponseDto(LeaderScore entity, Integer rank) {
        this.id = entity.getId();
        this.rank = rank;
        this.user = entity.getUser().getUsername();
        this.score = entity.getDailyScore();
    }
}
