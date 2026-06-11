package com.krysha.bookreview.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.krysha.bookreview.model.Review;
import com.krysha.bookreview.service.ReviewService;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public Iterable<Review> getReviews() {
        return reviewService.getReviews();
    }

    @GetMapping("/reviews/{requestedId}")
    private ResponseEntity<Review> findById(@PathVariable Long requestedId) {
        Optional<Review> review = reviewService.getReview(requestedId);
        if (review.isPresent()) {
            return ResponseEntity.ok(review.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/reviews")
    private ResponseEntity<Void> createReview(@RequestBody Review newReviewRequest, UriComponentsBuilder ucb) {
        Review savedReview = reviewService.saveReview(newReviewRequest);
        URI locationOfNewReview = ucb
                .path("reviews/{id}")
                .buildAndExpand(savedReview.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewReview).build();
    }

    @DeleteMapping("/reviews/{id}")
    private ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        if (!reviewService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/reviews/{id}")
    private ResponseEntity<Void> putReview(@PathVariable Long id, @RequestBody Review reviewUpdate) {
        if (!reviewService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reviewService.updateReview(id, reviewUpdate);
        return ResponseEntity.noContent().build();
    }
}