package com.krysha.bookreview.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

import com.krysha.bookreview.model.Review;

@Data
@AllArgsConstructor
public class ReviewResponse {
	private Long id;
	private String comment;
	private Integer rating;
	private LocalDateTime createdAt;
	private String username;
	private Long bookId;
	
	public static ReviewResponse from(Review review) {
		return new ReviewResponse(
				review.getId(),
				review.getComment(),
				review.getRating(),
				review.getCreatedAt(),
				review.getUser().getUsername(),
				review.getBook().getId()
	);
	}

}
