package com.krysha.bookreview.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.krysha.bookreview.dto.ReviewResponse;
import com.krysha.bookreview.model.Review;
import com.krysha.bookreview.model.User;
import com.krysha.bookreview.service.ReviewService;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public Iterable<ReviewResponse> getReviews() {
        return reviewService.getReviews().stream()
        		.map(ReviewResponse::from)
        		.toList();
    }

    @GetMapping("/reviews/{requestedId}")
    private ResponseEntity<ReviewResponse> findById(@PathVariable Long requestedId) {
        Optional<Review> review = reviewService.getReview(requestedId);
        if (review.isPresent()) {
            return ResponseEntity.ok(ReviewResponse.from(review.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/reviews")
    private ResponseEntity<ReviewResponse> createReview(@RequestBody Review newReviewRequest, @AuthenticationPrincipal User currentUser, UriComponentsBuilder ucb) {
        
    	newReviewRequest.setUser(currentUser);
    	
    	Review savedReview = reviewService.saveReview(newReviewRequest);
        URI locationOfNewReview = ucb
                .path("reviews/{id}")
                .buildAndExpand(savedReview.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewReview).body(ReviewResponse.from(savedReview));
    }

    @DeleteMapping("/reviews/{id}")
    private ResponseEntity<Void> deleteReview(@PathVariable Long id, @AuthenticationPrincipal User currentUser) {
        reviewService.deleteReview(id, currentUser);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/reviews/{id}")
    private ResponseEntity<ReviewResponse> putReview(@PathVariable Long id, @RequestBody Review reviewUpdate, @AuthenticationPrincipal User currentUser) {
        Review updated = reviewService.updateReview(id, reviewUpdate, currentUser);
        return ResponseEntity.ok(ReviewResponse.from(updated));
    }
}