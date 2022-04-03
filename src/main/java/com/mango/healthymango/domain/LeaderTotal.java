package com.mango.healthymango.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="leadertotal")
public class LeaderTotal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leadertotalNum;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @Column
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
