package com.krysha.bookreview.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.krysha.bookreview.model.Review;
import com.krysha.bookreview.repository.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	public Optional <Review> getReview(final Long id) {
		return reviewRepository.findById(id);
	}

	public Iterable<Review> getReviews() {
		return reviewRepository.findAll();
	}
	
	public void deleteReview(final Long id) {
		reviewRepository.deleteById(id);
	}
	
	public Review saveReview(Review review) {
		Review savedReview = reviewRepository.save(review);
		return savedReview;
	}
	
	public Review updateReview(final Long id, Review review) {
	    if (reviewRepository.existsById(id)) {
	        review.setId(id);
	        return reviewRepository.save(review);
	    }
	    throw new RuntimeException("Review non trouvée");
	}
	
	public Optional<Review> patchReview(final Long id, Review review) {
	    return reviewRepository.findById(id).map(existing -> {
	        if (review.getComment() != null) existing.setComment(review.getComment());
	        if (review.getRating() != null) existing.setRating(review.getRating());
	        return reviewRepository.save(existing);
	    });
	}
	
	public boolean existsById(final Long id) {
	    return reviewRepository.existsById(id);
	}
	
	
}
