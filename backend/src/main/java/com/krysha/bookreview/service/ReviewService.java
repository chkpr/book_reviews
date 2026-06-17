package com.krysha.bookreview.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krysha.bookreview.exception.AccessDeniedException;
import com.krysha.bookreview.model.Review;
import com.krysha.bookreview.model.User;
import com.krysha.bookreview.repository.ReviewRepository;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	public Optional <Review> getReview(final Long id) {
		return reviewRepository.findById(id);
	}

	public List<Review> getReviews() {
		return reviewRepository.findAll();
	}
	
	public void deleteReview(final Long id, User currentUser) {
		Review existingReview = reviewRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review non trouvée"));
		
		if (!existingReview.getUser().getId().equals(currentUser.getId())) {
			throw new AccessDeniedException("Vous ne pouvez supprimer que vos propres reviews");
		}
		
		reviewRepository.deleteById(id);
	}
	
	public Review saveReview(Review review) {
		Review savedReview = reviewRepository.save(review);
		return savedReview;
	}
	
	public Review updateReview(final Long id, Review reviewUpdate, User currentUser) {
		
	    Review existingReview = reviewRepository.findById(id)
	    		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review non trouvée"));
	    
	    if (!existingReview.getUser().getId().equals(currentUser.getId())) {
	    	throw new AccessDeniedException("Vous ne pouvez modifier que vos propres reviews.");
	    }
	    
	    reviewUpdate.setId(id);
	    reviewUpdate.setUser(existingReview.getUser());
	    return reviewRepository.save(reviewUpdate);
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
	
	public List<Review> getReviewsByBookId(Long bookId) {
		return reviewRepository.findByBookId(bookId);
	}
}
