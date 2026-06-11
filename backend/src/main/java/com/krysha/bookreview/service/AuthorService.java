package com.krysha.bookreview.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.krysha.bookreview.model.Author;
import com.krysha.bookreview.repository.AuthorRepository;

import lombok.Data;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	public Optional <Author> getAuthor(final Long id) {
		return authorRepository.findById(id);
	}
	
	public Iterable<Author> getAuthors() {
		return authorRepository.findAll();
	}
	
	public void deleteAuthor(final Long id) {
		authorRepository.deleteById(id);
	}
	
	public Author saveAuthor(Author author) {
		Author savedAuthor = authorRepository.save(author);
		return savedAuthor;
	}
	
	public Author updateAuthor(final Long id, Author author) {
	    if (authorRepository.existsById(id)) {
	        author.setId(id);
	        return authorRepository.save(author);
	    }
	    throw new RuntimeException("Auteur non trouvé");
	}
	
	public Optional<Author> patchAuthor(final Long id, Author author) {
	    return authorRepository.findById(id).map(existing -> {
	        if (author.getName() != null) existing.setName(author.getName());
	        if (author.getFirstname() != null) existing.setFirstname(author.getFirstname());
	        if (author.getBiography() != null) existing.setBiography(author.getBiography());
	        return authorRepository.save(existing);
	    });
	}
	
	public boolean existsById(final Long id) {
	    return authorRepository.existsById(id);
	}
	
}
