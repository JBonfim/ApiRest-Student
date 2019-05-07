package br.com.jabes.apistudent.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import br.com.jabes.apistudent.util.CustomSortDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class PageableResponse<T> extends PageImpl<T> {
	
	private boolean last;
	private boolean first;
	private int totalPages;

	public PageableResponse(@JsonProperty("content") List<T> content
			, @JsonProperty("number") int page 
			, @JsonProperty("size") int size 
			, @JsonProperty("totalElements") long totalElements,
			@JsonProperty("sort") @JsonDeserialize(using=CustomSortDeserializer.class)
									Sort sort) {
		super(content,new PageRequest(page, size,sort), totalElements);
		// TODO Auto-generated constructor stub
	}
	
	public PageableResponse() {
		super(new ArrayList<>());
		// TODO Auto-generated constructor stub
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	

}
