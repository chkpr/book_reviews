package com.krysha.bookreview.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.krysha.bookreview.records.Answer;
import com.krysha.bookreview.records.Question;

import reactor.core.publisher.Flux;

import static org.springframework.ai.rag.retrieval.search
.VectorStoreDocumentRetriever.FILTER_EXPRESSION;


@Service
public class SpringAiReviewService implements AIReviewService {
	
	private static final Logger log =
			LoggerFactory.getLogger(SpringAiReviewService.class);

	private final ChatClient chatClient;
	private final BookContentService bookContentService;

	public SpringAiReviewService(ChatClient.Builder chatClientBuilder, BookContentService bookContentService) {
		this.chatClient = chatClientBuilder.build();
		this.bookContentService = bookContentService;
	}

	@Value("classpath:/promptTemplates/systemPromptTemplate.st")
	Resource promptTemplate;

	@Override
	public Answer askQuestion(Question question) {
		
		String bookNameMatch = String.format("bookTitle == '%s", normalizeBookTitle(question.bookTitle()));
		
		return chatClient.prompt()
				.system(systemSpec -> systemSpec
						.text(promptTemplate)
						.param("bookTitle", question.bookTitle()))
				.user(question.question())
				.advisors(advisorSpec ->
						advisorSpec.param(FILTER_EXPRESSION, bookNameMatch))
				.call()
				.entity(Answer.class);
						
		
	}
	
	private void logUsage(Usage usage) {
		log.info("Token usage: prompt={}, generation={}, total={}",
		usage.getPromptTokens(),
		usage.getCompletionTokens(),
		usage.getTotalTokens());
		}
	

	@Override
	public Flux<String> askQuestionStreamAnswer(Question question) {
		var bookContent = bookContentService.getContentFor(question.bookTitle(), question.question());
		
		return chatClient.prompt().system(systemSpec->systemSpec.text(promptTemplate)
				.param("bookTitle",question.bookTitle()).param("content",bookContent)).user(question.question())
				.stream().content();
	}
	

	private String normalizeBookTitle(String bookTitle) {
		return bookTitle.toLowerCase().replace(" ", "_");			
	}

}
