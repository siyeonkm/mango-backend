package com.mango.healthymango.web.dto;

import com.mango.healthymango.domain.LeaderScore;
import com.mango.healthymango.domain.User;
import lombok.Getter;

@Getter
public class LeaderScoreResponseDto {
    private Long leaderNum;
    private String user;
    private Integer score;

    public LeaderScoreResponseDto(LeaderScore entity) {
        this.leaderNum = entity.getLeaderscoreNum();
        this.user = entity.getUser().getUsername();
        this.score = entity.getDailyScore();
    }
}
