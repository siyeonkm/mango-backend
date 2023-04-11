package com.mango.healthymango.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaderScoreRepository extends MongoRepository<LeaderScore, String> {
    LeaderScore findLeaderScoreByUser(User user);
}


