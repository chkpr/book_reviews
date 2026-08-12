package com.krysha.bookreview.controller;

import java.util.List;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrizesController {
	@Value("classpath:/promptTemplates/french-prizes-prompt.st")
	Resource frenchPrizesPromptTemplate;
	private final ChatClient chatClient;

	public PrizesController(ChatClient.Builder chatClientBuilder) {
		this.chatClient = chatClientBuilder.build();
	}

	@GetMapping(path = "/bookPrizes", produces = "application/json")
	public List<String> bookPrizes(@RequestParam("year") String year) {
		return chatClient.prompt().user(userSpec -> userSpec.text(frenchPrizesPromptTemplate).param("year", year)).call()
				.entity(new ParameterizedTypeReference<List<String>>() {
				});
	}
}
