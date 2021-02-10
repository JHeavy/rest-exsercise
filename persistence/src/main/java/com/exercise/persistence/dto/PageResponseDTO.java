package com.exercise.persistence.dto;

public class PageResponseDTO {
	
	int page;
	
	int maxPages;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getMaxPages() {
		return maxPages;
	}

	public void setMaxPages(int maxPages) {
		this.maxPages = maxPages;
	}
}
