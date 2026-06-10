package com.krysha.bookreview.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.krysha.bookreview.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	List<Book> findByTitleContainingIgnoreCase(String title);
}

