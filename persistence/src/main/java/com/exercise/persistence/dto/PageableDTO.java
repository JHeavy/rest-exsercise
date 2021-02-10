package com.exercise.persistence.dto;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.ObjectUtils;

import com.exercise.persistence.enumeration.OrderDTO;

public class PageableDTO {
	
	public static final PageableDTO of(int page, int size, OrderDTO order, String sortBy) {
		PageableDTO res = new PageableDTO();
		res.page = page;
		res.size = size;
		res.order = ObjectUtils.isEmpty(order) ? OrderDTO.DESC : order;
		res.sortBy = ObjectUtils.isEmpty(sortBy) ? "1" : sortBy;
		
		return res;
	}

	int page;

	int size;
	
	OrderDTO order;
	
	String sortBy;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public OrderDTO getOrder() {
		return order;
	}

	public void setOrder(OrderDTO order) {
		this.order = order;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	
	public final Sort asSort() {
		return OrderDTO.ASC.equals(order) ? Sort.by(Order.asc(sortBy)) : Sort.by(Order.desc(sortBy));
	}
}
