package com.mango.healthymango.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaderTotalRepository extends JpaRepository<LeaderTotal, Long> {
    LeaderTotal findLeaderTotalByUser(User user);
}
