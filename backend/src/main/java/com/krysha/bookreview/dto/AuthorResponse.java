package com.krysha.bookreview.dto;

import com.krysha.bookreview.model.Author;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorResponse {
	private Long id;
	private String name;
	private String firstName;
	private String biography;
	
	public static AuthorResponse from(Author author) {
		return new AuthorResponse(
				author.getId(),
				author.getName(),
				author.getFirstname(),
				author.getBiography()
				);
				
	}

}
