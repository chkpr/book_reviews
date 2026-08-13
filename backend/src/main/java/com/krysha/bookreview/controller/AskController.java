package com.krysha.bookreview.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.krysha.bookreview.records.Answer;
import com.krysha.bookreview.records.Question;
import com.krysha.bookreview.service.AIReviewService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;

@RestController
public class AskController {
	
	private final AIReviewService aiReviewService;
	
	public AskController(AIReviewService aiReviewService) {
		this.aiReviewService = aiReviewService;
	}
	
	@PostMapping(path="/ask", produces="application/json")
	public Answer ask(@RequestBody @Valid Question question) {
		return aiReviewService.askQuestion(question);
	}
	
	@PostMapping(path="/ask/stream", produces="application/json")
	public Flux<String> askStream(@RequestBody Question question) {
		return aiReviewService.askQuestionStreamAnswer(question);
	}

}
