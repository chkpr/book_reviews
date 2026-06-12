package com.krysha.bookreview.dto;

import lombok.Data;

import com.krysha.bookreview.model.Category;

import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class CategoryResponse {
	private Long id;
	private String name;
	private String description;
	
	public static CategoryResponse from(Category category) {
		return new CategoryResponse(
				category.getId(),
				category.getName(),
				category.getDescription()
				);
	}
}
