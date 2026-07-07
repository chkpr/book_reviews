package com.krysha.bookreview.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.krysha.bookreview.records.Answer;
import com.krysha.bookreview.records.Question;

@Service
public class SpringAiReviewService implements AIReviewService{
	
	private final ChatClient chatClient;
	
	public SpringAiReviewService(ChatClient.Builder chatClientBuilder) {
		this.chatClient = chatClientBuilder.build();
	}
	
	@Override
	public Answer askQuestion(Question question) {
		var answerText = chatClient.prompt()
				.user(question.question())
				.call()
				.content();
		return new Answer(answerText);
	}

}
