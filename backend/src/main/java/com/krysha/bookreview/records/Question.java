package com.krysha.bookreview.records;

import jakarta.validation.constraints.NotBlank;

public record Question(
		@NotBlank(message = "Book title is required") String bookTitle,
		@NotBlank(message = "Question is required") String question)
{

}
