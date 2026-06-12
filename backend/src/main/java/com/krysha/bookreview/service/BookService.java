package com.krysha.bookreview.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krysha.bookreview.model.Book;
import com.krysha.bookreview.repository.BookRepository;


@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public Optional <Book> getBook(final Long id) {
		return bookRepository.findById(id);
	}
	
	public List<Book> getBooks(){
		return bookRepository.findAll();
	}
	
	public void deleteBook(final Long id) {
		bookRepository.deleteById(id);
	}
	
	public Book saveBook(Book book) {
		Book savedBook = bookRepository.save(book);
		return savedBook;
	}
	
	public Book updateBook(final Long id, Book book) {
		if(bookRepository.existsById(id)) {
			book.setId(id);
			return bookRepository.save(book);
		}
		throw new RuntimeException("Ce livre n'existe pas");
	}

	public Optional <Book> patchBook(final Long id, Book book) {
		return bookRepository.findById(id).map(existing -> {
			if(book.getTitle() != null) existing.setTitle(book.getTitle());
			if(book.getIsbn() !=null) existing.setIsbn(book.getIsbn());
			if(book.getSummary() !=null) existing.setSummary(book.getSummary());
			if(book.getPublishedYear() !=null) existing.setPublishedYear(book.getPublishedYear());
			if (book.getAuthor() != null) existing.setAuthor(book.getAuthor());
	        if (book.getCategories() != null && !book.getCategories().isEmpty()) 
	            existing.setCategories(book.getCategories());
	        return bookRepository.save(existing);
	    });
	}
	
	public boolean existsById(final Long id) {
	    return bookRepository.existsById(id);
	}
}
