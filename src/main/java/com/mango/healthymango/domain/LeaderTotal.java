package com.mango.healthymango.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@NoArgsConstructor
@Document(collection = "LeaderTotal")
public class LeaderTotal {
    @Id
    private String id;

    private User user;

    private Integer totalScore;

    @Builder
    public LeaderTotal(User user, Integer totalScore) {
        this.user = user;
        this.totalScore = totalScore;
    }

    public void updateScore(Integer totalScore) {
        this.totalScore = totalScore;
    }
}
