package com.realestate.marketplace.repository;

import com.realestate.marketplace.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import com.realestate.marketplace.entity.Agent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByAgent(Agent agent);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.agent.id = :agentId")
    Double getAverageRating(@Param("agentId") Long agentId);
}
