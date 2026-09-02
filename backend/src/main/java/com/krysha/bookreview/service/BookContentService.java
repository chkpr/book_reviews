package com.krysha.bookreview.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookContentService {
	
	private final VectorStore vectorStore;
	
	public BookContentService(VectorStore vectorStore) {
		this.vectorStore = vectorStore;
	}
	
	
	public String getContentFor(String bookName, String question) {
		var searchRequest = SearchRequest
				.builder()
				.query(question)
				.filterExpression(
						new FilterExpressionBuilder()
						.eq("bookTitle", normalizeBookTitle(bookName)).build())
				.build();
		
		System.err.println("Search request: " + searchRequest);
		
		var similarDocs =
				vectorStore.similaritySearch(searchRequest);
		
		if(similarDocs.isEmpty()) {
			return "The content for " + bookName + " is not available.";
		}
		
		return similarDocs.stream()
				.map(Document::getText)
				.collect(Collectors.joining(System.lineSeparator()));
	}
	
	private String normalizeBookTitle(String bookTitle) {
		return bookTitle.toLowerCase().replace(" ", "_");			
	}

}
