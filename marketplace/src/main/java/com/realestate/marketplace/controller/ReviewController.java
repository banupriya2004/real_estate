package com.realestate.marketplace.controller;

import com.realestate.marketplace.dto.AgentResponse;
import com.realestate.marketplace.entity.Agent;
import com.realestate.marketplace.entity.Review;
import com.realestate.marketplace.repository.AgentRepository;
import com.realestate.marketplace.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final AgentRepository agentRepository;

    public ReviewController(ReviewRepository reviewRepository,
                            AgentRepository agentRepository) {
        this.reviewRepository = reviewRepository;
        this.agentRepository = agentRepository;
    }


    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // 1️⃣ ADD REVIEW
    @PostMapping("")
    public Review addReview(@RequestBody AgentResponse request) {

        Agent agent = agentRepository.findById(request.getAgentId())
                .orElseThrow(() -> new RuntimeException("Agent not found"));

        Review review = new Review();
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setAgent(agent);

        Review savedReview = reviewRepository.save(review);

        // update agent rating
        updateAgentRating(agent);

        return savedReview;
    }


    // ⭐ RATING CALCULATION
    private void updateAgentRating(Agent agent) {

        List<Review> reviews = reviewRepository.findByAgent(agent);

        double avg = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);

        agent.setRating(avg);
        agentRepository.save(agent);
    }
    // 🗑 DELETE review by id
    @DeleteMapping("/{reviewId}")
    public String deleteReview(@PathVariable Long reviewId) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        reviewRepository.delete(review);

        return "Review deleted successfully";
    }

    @GetMapping("/agent/{agentId}/average")
    public Double getAverageRating(@PathVariable Long agentId) {
        Double avg = reviewRepository.getAverageRating(agentId);
        return avg != null ? avg : 0.0;
    }

}
