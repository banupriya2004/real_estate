package com.realestate.marketplace.service;

import com.realestate.marketplace.entity.Review;
import com.realestate.marketplace.entity.Agent;
import com.realestate.marketplace.repository.ReviewRepository;
import com.realestate.marketplace.repository.AgentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final AgentRepository agentRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         AgentRepository agentRepository) {
        this.reviewRepository = reviewRepository;
        this.agentRepository = agentRepository;
    }

    public Review addReview(Long agentId, Review review) {
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found"));

        review.setAgent(agent);
        Review saved = reviewRepository.save(review);

        // recalculate rating
        List<Review> reviews = reviewRepository.findByAgent(agent);
        double avgRating = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);

        agent.setRating(avgRating);
        agentRepository.save(agent);

        return saved;
    }
}
