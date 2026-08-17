package com.krysha.vector_store_loader;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import org.slf4j.Logger;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
public class VectorStoreLoaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(VectorStoreLoaderApplication.class, args);
	}

	@Bean
	Function<Flux<byte[]>, Flux<Document>> documentReader() {
		return resourceFlux -> resourceFlux
				.map(fileBytes -> new TikaDocumentReader(new ByteArrayResource(fileBytes)).get().getFirst())
				.subscribeOn(Schedulers.boundedElastic());
	}

	@Bean
	Function<Flux<Document>, Flux<List<Document>>> splitter() {
		var splitter = new TokenTextSplitter();
		return documentFlux -> documentFlux.map(incoming -> splitter.apply(List.of(incoming)))
				.subscribeOn(Schedulers.boundedElastic());
	}
	
	private static final Logger LOGGER =
			LoggerFactory.getLogger(VectorStoreLoaderApplication.class);
			@Value("classpath:/promptTemplates/nameOfTheBook.st")
			Resource nameOfTheBookTemplateResource;
			@Bean
			Function<Flux<List<Document>>, Flux<List<Document>>>
			titleDeterminer(ChatClient.Builder chatClientBuilder) {
				
				var chatClient = chatClientBuilder.build();
				return documentListFlux -> documentListFlux
				.map(documents -> {
				if (!documents.isEmpty()) {
				var firstDocument = documents.getFirst();
				
				var bookTitle = chatClient.prompt()
						.user(userSpec -> userSpec
						.text(nameOfTheBookTemplateResource)
						.param("document", firstDocument.getText()))
						.call()
						.entity(BookTitle.class);
				
				if (Objects.requireNonNull(bookTitle).title().equals("UNKNOWN")) {
					LOGGER.warn("Unable to determine the name of a book; " +
					"not adding to vector store.");
					documents = Collections.emptyList();
					return documents;
					}
				
				LOGGER.info("Determined book title to be {}", bookTitle.title());
				documents = documents.stream().peek(document -> {
				document.getMetadata()
				.put("bookTitle", bookTitle.getNormalizedTitle());
				
				}).toList();
				}
				
				return documents;
				});
			}

}
