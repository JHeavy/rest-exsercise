package com.exercise.persistence.dto;

import java.util.List;

public class ListResponseDTO<E> {
	
	public static final <F extends Object> ListResponseDTO<F> of(List<F> data, int page, int maxPages, long totalResults) {
		ListResponseDTO<F> result = new ListResponseDTO<F>();
		
		PageResponseDTO dto = new PageResponseDTO();
		dto.page = page;
		dto.maxPages = maxPages;
		
		result.data = data;
		result.page = dto;
		result.totalResults = totalResults;
		
		return result;
	}
	
	List<E> data;
	
	PageResponseDTO page;
	
	long totalResults;

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

	public PageResponseDTO getPage() {
		return page;
	}

	public void setPage(PageResponseDTO page) {
		this.page = page;
	}

	public long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(long totalResults) {
		this.totalResults = totalResults;
	}
}
