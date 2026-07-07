package com.krysha.bookreview.service;

import com.krysha.bookreview.records.Answer;
import com.krysha.bookreview.records.Question;

public interface AIReviewService {
	Answer askQuestion(Question question);
}
