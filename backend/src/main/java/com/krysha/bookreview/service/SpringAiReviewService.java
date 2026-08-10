package com.krysha.bookreview.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.krysha.bookreview.records.Answer;
import com.krysha.bookreview.records.Question;

@Service
public class SpringAiReviewService implements AIReviewService{
	
	private final ChatClient chatClient;
	private final BookContentService bookContentService;
	
	public SpringAiReviewService(ChatClient.Builder chatClientBuilder, BookContentService bookContentService) {
		this.chatClient = chatClientBuilder.build();
		this.bookContentService = bookContentService;
	}
	
	@Value("classpath:/promptTemplates/questionPromptTemplate.st")
	Resource questionPromptTemplate;
	
	@Override
	public Answer askQuestion(Question question) {	
		var bookContent = bookContentService.getContentFor(question.bookTitle());
		
		var answerText = chatClient.prompt()
				.user(userSpec -> userSpec
						.text(questionPromptTemplate)
						.param("bookTitle", question.bookTitle())
						.param("question", question.question())
						.param("content", bookContent))
				.call()
				.content();
		
		return new Answer(question.bookTitle(), answerText);
	}

}
