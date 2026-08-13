package com.krysha.bookreview.service;

import com.krysha.bookreview.records.Answer;
import com.krysha.bookreview.records.Question;

import reactor.core.publisher.Flux;

public interface AIReviewService {
	Answer askQuestion(Question question);
	
	Flux<String> askQuestionStreamAnswer(Question question);
}
