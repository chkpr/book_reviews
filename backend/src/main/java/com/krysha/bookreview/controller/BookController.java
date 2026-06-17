package com.krysha.bookreview.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.krysha.bookreview.dto.BookResponse;
import com.krysha.bookreview.dto.ReviewResponse;
import com.krysha.bookreview.model.Book;
import com.krysha.bookreview.service.BookService;
import com.krysha.bookreview.service.ReviewService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/books")
	public Iterable<BookResponse> getBooks() {
		return bookService.getBooks().stream()
				.map(BookResponse::from)
				.toList();
	}
	
	@GetMapping("/books/{requestedId}")
	private ResponseEntity<BookResponse> findById(@PathVariable Long requestedId) {
		Optional <Book> book = bookService.getBook(requestedId);
		if(book.isPresent()) {
			return ResponseEntity.ok(BookResponse.from(book.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/books")
	private ResponseEntity<BookResponse> createBook(@RequestBody Book newBookRequest, UriComponentsBuilder ucb) {
		Book savedBook = bookService.saveBook(newBookRequest);
			URI locationOfNewBook = ucb
					.path("books/{id}")
					.buildAndExpand(savedBook.getId())
					.toUri();
			return ResponseEntity.created(locationOfNewBook).body(BookResponse.from(savedBook));
	}
	
	@DeleteMapping("/books/{id}")
	private ResponseEntity<Void>deleteBook(@PathVariable Long id){
		if(!bookService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
	bookService.deleteBook(id);
	return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/books/{id}")
	private ResponseEntity<BookResponse> putBook(@PathVariable Long id, @RequestBody Book bookUpdate) {
	if(!bookService.existsById(id)) {
		return ResponseEntity.notFound().build();
		}
    Book updated = bookService.updateBook(id, bookUpdate);
    return ResponseEntity.ok(BookResponse.from(updated));
	}
	
	@GetMapping("/books/{id}/reviews")
		public List<ReviewResponse>getReviewsByBook(@PathVariable Long id) {
			return reviewService.getReviewsByBookId(id).stream()
				.map(ReviewResponse::from)
				.toList();
	}
}
