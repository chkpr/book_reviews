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
	
	private static final String questionPromptTemplate = """
			You are a helpful assistant, answering questions about books. 
			If you don't know anything about the book or don't know the answer, 
			say "I don't know".
			
			The book is {book}.
			
			The question is: {question}.
			""";
	
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
