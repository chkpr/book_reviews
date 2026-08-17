package com.krysha.vector_store_loader;

public record BookTitle(String title) {
	
	public String getNormalizedTitle() {
		return title.toLowerCase().replace(" ", "_");
		}

}
