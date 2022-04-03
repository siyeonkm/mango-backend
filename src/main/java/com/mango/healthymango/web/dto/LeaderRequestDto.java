package com.mango.healthymango.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LeaderRequestDto {
    private String username;
    private Integer score;

    public LeaderRequestDto(String username, Integer score) {
        this.username = username;
        this.score = score;
    }
}
