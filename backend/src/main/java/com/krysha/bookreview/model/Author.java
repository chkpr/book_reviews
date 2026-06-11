package com.krysha.bookreview.model;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="authors")
public class Author {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable=false)
	private String firstname;
	
	@Column(length=2000)
	private String biography;
	
	@ManyToMany(mappedBy = "authors")
	private List<Book> books;
}
