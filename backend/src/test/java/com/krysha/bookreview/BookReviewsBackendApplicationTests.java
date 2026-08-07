package com.krysha.bookreview;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.krysha.bookreview.records.Question;
import com.krysha.bookreview.service.SpringAiReviewService;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;
import java.io.IOException;
import java.nio.charset.Charset;

@EnableWireMock(@ConfigureWireMock(baseUrlProperties = "ollama.base.url"))
@SpringBootTest(properties = {"spring.ai.ollama.base-url=${ollama.base.url}", "spring.ai.ollama.chat.options.model=llama3"})
public class BookReviewsBackendApplicationTests {
	@Value("classpath:/test-ollama-response.json")
	Resource responseResource;
	@Autowired
	ChatClient.Builder chatClientBuilder;

	@BeforeEach
	public void setup() throws IOException {
		var cannedResponse = responseResource.getContentAsString(Charset.defaultCharset());
		var mapper = new ObjectMapper();
		var responseNode = mapper.readTree(cannedResponse);
		WireMock.stubFor(
				WireMock.post("/api/chat").willReturn(ResponseDefinitionBuilder.okForJson(responseNode)));
	}

	/*
	@Test
	public void testAskQuestion() {
		var boardGameService = new SpringAiReviewService(chatClientBuilder);
		var answer = boardGameService.askQuestion(new Question("What is the capital of France?"));
		Assertions.assertThat(answer).isNotNull();
		Assertions.assertThat(answer.answer()).isEqualTo("Paris");
	}
	*/
}
