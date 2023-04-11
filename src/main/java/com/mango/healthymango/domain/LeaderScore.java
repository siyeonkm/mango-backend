package com.mango.healthymango.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@NoArgsConstructor
@Document(collection = "LeaderScore")
public class LeaderScore {

    @Id
    private String id;

    private User user;

    private Integer dailyScore;

    @Builder
    public LeaderScore(User user, Integer dailyScore) {
        this.user = user;
        this.dailyScore = dailyScore;
    }

    public void updateScore(Integer dailyScore) {
        this.dailyScore = dailyScore;
    }
}

