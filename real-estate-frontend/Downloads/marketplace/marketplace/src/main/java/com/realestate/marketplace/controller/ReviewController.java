package com.realestate.marketplace.controller;

import com.realestate.marketplace.entity.Review;
import com.realestate.marketplace.service.ReviewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // ADD REVIEW
    @PostMapping("/agent/{agentId}")
    public Review addReview(
            @PathVariable Long agentId,
            @RequestBody Review review) {

        return reviewService.addReview(agentId, review);
    }
}
