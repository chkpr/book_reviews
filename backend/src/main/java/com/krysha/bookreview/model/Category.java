package com.krysha.bookreview.model;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="categories")
public class Category {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, unique=true)
	private String name;
	
	@Column(length=500)
	private String description;
	
	@ManyToMany(mappedBy = "categories")
	private List<Book> books;
}
