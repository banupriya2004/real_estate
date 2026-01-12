package com.realestate.marketplace.repository;

import com.realestate.marketplace.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import com.realestate.marketplace.entity.Agent;

import java.util.List;
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByAgent(Agent agent);
}
