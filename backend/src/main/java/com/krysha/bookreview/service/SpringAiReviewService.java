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
	
	public SpringAiReviewService(ChatClient.Builder chatClientBuilder) {
		this.chatClient = chatClientBuilder.build();
	}
	
	@Value("classpath:/promptTemplates/questionPromptTemplate.st")
	Resource questionPromptTemplate;
	
	@Override
	public Answer askQuestion(Question question) {		
		var answerText = chatClient.prompt()
				.user(userSpec -> userSpec
						.text(questionPromptTemplate)
						.param("bookTitle", question.bookTitle())
						.param("question", question.question()))
				.call()
				.content();
		
		return new Answer(question.bookTitle(), answerText);
	}

}
