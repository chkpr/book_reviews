package com.krysha.bookreview.config;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.preretrieval.query.transformation.RewriteQueryTransformer;
import org.springframework.ai.rag.preretrieval.query.transformation.TranslationQueryTransformer;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {
	
	@Bean
	ChatClient chatClient(
			ChatClient.Builder chatClientBuilder, VectorStore vectorStore, ChatMemory chatMemory) {
		/*
		var advisor = RetrievalAugmentationAdvisor.builder()
				.documentRetriever(
						VectorStoreDocumentRetriever.builder()
							.vectorStore(vectorStore)
							.build())
				.queryTransformers(
						TranslationQueryTransformer.builder()
							.chatClientBuilder(chatClientBuilder)
							.targetLanguage("English")
							.build(),
						RewriteQueryTransformer.builder()
							.chatClientBuilder(chatClientBuilder)
							.build())
				
				.build();
				*/
		
		return chatClientBuilder
				.defaultAdvisors(
						MessageChatMemoryAdvisor.builder(chatMemory).build(),
						QuestionAnswerAdvisor.builder(vectorStore)
							.searchRequest(SearchRequest.builder().build()).build())
				.build();
	}
}

