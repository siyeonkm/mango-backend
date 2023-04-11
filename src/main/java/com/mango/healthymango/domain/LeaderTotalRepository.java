package com.mango.healthymango.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaderTotalRepository extends MongoRepository<LeaderTotal, String> {
    LeaderTotal findLeaderTotalByUser(User user);
}
