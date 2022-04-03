package com.mango.healthymango.web.dto;

import com.mango.healthymango.domain.LeaderTotal;
import lombok.Getter;

@Getter
public class MyLeaderTotalResponseDto {
    private Long leaderNum;
    private Integer rank;
    private String user;
    private Integer score;

    public MyLeaderTotalResponseDto(LeaderTotal entity, Integer rank) {
        this.leaderNum = entity.getLeadertotalNum();
        this.rank = rank;
        this.user = entity.getUser().getUsername();
        this.score = entity.getTotalScore();
    }
}
