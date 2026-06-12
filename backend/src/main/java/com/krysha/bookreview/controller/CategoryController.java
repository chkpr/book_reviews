package com.krysha.bookreview.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.krysha.bookreview.dto.CategoryResponse;
import com.krysha.bookreview.model.Category;
import com.krysha.bookreview.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/categories")
	public Iterable<CategoryResponse> getCategories() {
		return categoryService.getCategories().stream()
				.map(CategoryResponse::from)
				.toList();
	}
	
	@GetMapping("/categories/{requestedId}")
	private ResponseEntity<CategoryResponse> findById(@PathVariable Long requestedId) {
		Optional<Category> category = categoryService.getCategory(requestedId);
		if(category.isPresent()) {
			return ResponseEntity.ok(CategoryResponse.from(category.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/categories")
	private ResponseEntity<CategoryResponse> createCategory(@RequestBody Category newCategoryRequest, UriComponentsBuilder ucb) {
		Category savedCategory = categoryService.saveCategory(newCategoryRequest);
			URI locationOfNewCategory = ucb
					.path("categories/{id}")
					.buildAndExpand(savedCategory.getId())
					.toUri();
			return ResponseEntity.created(locationOfNewCategory).body(CategoryResponse.from(savedCategory));
	}
	
	@DeleteMapping("/categories/{id}")
	private ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		if(!categoryService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		categoryService.deleteCategory(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/categories/{id}")
	private ResponseEntity<CategoryResponse> putCategory(@PathVariable Long id, @RequestBody Category categoryUpdate) {
	if(!categoryService.existsById(id)) {
		return ResponseEntity.notFound().build();
		}
    Category updated = categoryService.updateCategory(id, categoryUpdate);
    return ResponseEntity.ok(CategoryResponse.from(updated));
	}
}
