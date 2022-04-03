package com.mango.healthymango.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="leaderscore")
public class LeaderScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaderscoreNum;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @Column
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

