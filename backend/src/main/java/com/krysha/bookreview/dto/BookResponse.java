package com.krysha.bookreview.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.List;

import com.krysha.bookreview.model.Author;
import com.krysha.bookreview.model.Book;

@Data
@AllArgsConstructor
public class BookResponse {
	private Long id;
	private String title;
	private String isbn;
	private String summary;
	private Integer publishedYear;
	private String coverUrl;
	private AuthorResponse author;
	private List<CategoryResponse> categories;
	
	public static BookResponse from(Book book) {
		return new BookResponse(
				book.getId(),
				book.getTitle(),
				book.getIsbn(),
				book.getSummary(),
				book.getPublishedYear(),
				book.getCoverUrl(),
				AuthorResponse.from(book.getAuthor()),
				book.getCategories().stream()
					.map(CategoryResponse::from)
					.toList()
				);
				
	}
}
