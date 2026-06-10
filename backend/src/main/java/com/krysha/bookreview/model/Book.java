package com.krysha.bookreview.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="books")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(unique = true)
	private String isbn;
	
	@Column(length=2000)
	private String summary;
	
	private LocalDate publishedDate;
	
	@ManyToOne
	@JoinColumn(name="author_id", nullable=false)
	private Author author;
	
	@ManyToMany
	@JoinTable(
			name="book_categories",
			joinColumns = @JoinColumn(name = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id")
		)
		private List<Category> categories;
	
	@OneToMany(mappedBy = "book")
	private List<Review> reviews;
	

}
