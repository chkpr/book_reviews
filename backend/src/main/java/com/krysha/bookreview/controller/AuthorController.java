package com.krysha.bookreview.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.krysha.bookreview.model.Author;
import com.krysha.bookreview.service.AuthorService;

@RestController
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	@GetMapping("/authors")
	public Iterable<Author> getAuthors() {
		return authorService.getAuthors();
	}
	
	@GetMapping("/authors/{requestedId}")
	private ResponseEntity<Author> findById(@PathVariable Long requestedId) {
		Optional <Author> author = authorService.getAuthor(requestedId);
		if(author.isPresent()) {
			return ResponseEntity.ok(author.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/authors")
	private ResponseEntity<Void> createAuthor(@RequestBody Author newAuthorRequest, UriComponentsBuilder ucb) {
			Author savedAuthor = authorService.saveAuthor(newAuthorRequest);
				URI locationOfNewAuthor = ucb
						.path("authors/{id}")
						.buildAndExpand(savedAuthor.getId())
						.toUri();
		return ResponseEntity.created(locationOfNewAuthor).build();
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<Void>deleteAuthor(@PathVariable Long id) {
		if(!authorService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
	
		authorService.deleteAuthor(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/authors/{id}")
	private ResponseEntity<Void> putAuthor(@PathVariable Long id, @RequestBody Author authorUpdate) {
	if(!authorService.existsById(id)) {
		return ResponseEntity.notFound().build();
		}
    authorService.updateAuthor(id, authorUpdate);
    return ResponseEntity.noContent().build();
	}
}
