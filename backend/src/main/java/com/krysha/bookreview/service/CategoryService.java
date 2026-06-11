package com.krysha.bookreview.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krysha.bookreview.model.Category;
import com.krysha.bookreview.repository.CategoryRepository;


@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Optional <Category> getCategory(final Long id){
		return categoryRepository.findById(id);
	}
	
	public Iterable<Category> getCategories() {
		return categoryRepository.findAll();
	}
	
	public void deleteCategory(final Long id) {
		categoryRepository.deleteById(id);
	}
	
	public Category saveCategory(Category category) {
		Category savedCategory = categoryRepository.save(category);
		return savedCategory;
	}
	
	public Category updateCategory(final Long id, Category category) {
		if(categoryRepository.existsById(id)) {
			category.setId(id);
			return categoryRepository.save(category);
		}
		throw new RuntimeException("Cette catégorie n'existe pas");
	}
	
	public Optional<Category> patchCategory(final Long id, Category category) {
		return categoryRepository.findById(id).map(existing ->  {
			if(category.getName() != null) existing.setName(category.getName());
			if(category.getDescription() !=null) existing.setDescription(category.getDescription());
			return categoryRepository.save(existing);
		});
	}
	
	public boolean existsById(final Long id) {
	    return categoryRepository.existsById(id);
	}
}
