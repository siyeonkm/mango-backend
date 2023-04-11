package com.mango.healthymango.web.dto;

import com.mango.healthymango.domain.LeaderScore;
import com.mango.healthymango.domain.User;
import lombok.Getter;

@Getter
public class LeaderScoreResponseDto {
    private String id;
    private String user;
    private Integer score;

    public LeaderScoreResponseDto(LeaderScore entity) {
        this.id = entity.getId();
        this.user = entity.getUser().getUsername();
        this.score = entity.getDailyScore();
    }
}
