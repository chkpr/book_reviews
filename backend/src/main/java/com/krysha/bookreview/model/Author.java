package com.krysha.bookreview.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String firstname;
	
	@Column(length=2000)
	private String biography;
	
	@OneToMany(mappedBy="author")
	private List<Book> books;
	
}
