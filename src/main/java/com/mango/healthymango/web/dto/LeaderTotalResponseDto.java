package com.mango.healthymango.web.dto;

import com.mango.healthymango.domain.LeaderScore;
import com.mango.healthymango.domain.LeaderTotal;
import com.mango.healthymango.domain.User;
import lombok.Getter;

@Getter
public class LeaderTotalResponseDto {
    private String id;
    private String user;
    private Integer score;

    public LeaderTotalResponseDto(LeaderTotal entity) {
        this.id = entity.getId();
        this.user = entity.getUser().getUsername();
        this.score = entity.getTotalScore();
    }
}
