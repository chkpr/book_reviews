package com.krysha.bookreview.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.charset.Charset;

@Service
public class BookContentService {
	
	private static final Logger LOG =
			LoggerFactory.getLogger(BookContentService.class);
	
	public String getContentFor(String bookName) {
		try {
			var filename = String.format(
					"classpath:/bookContent/%s.txt",
					bookName.toLowerCase().replace(" ", "_"));
			
			return new DefaultResourceLoader()
					.getResource(filename)
					.getContentAsString(Charset.defaultCharset()); 
		} catch (IOException e) {
			LOG.info("No content found for book: " + bookName);
			return "";
		}
	}

}
