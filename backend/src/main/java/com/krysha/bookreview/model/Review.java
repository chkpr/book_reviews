package com.krysha.bookreview.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="reviews")
@EntityListeners(AuditingEntityListener.class)
public class Review {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length=2000)
	private String comment;
	
	@Column(nullable=false)
	private Integer rating;
	
	@CreatedDate
	@Column(updatable=false)
	private LocalDateTime createdAt;
	
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;

	@ManyToOne
	@JoinColumn(name="book_id", nullable=false)
	private Book book;
}
