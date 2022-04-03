package com.mango.healthymango.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaderScoreRepository extends JpaRepository<LeaderScore, Long> {
    LeaderScore findLeaderScoreByUser(User user);
}


