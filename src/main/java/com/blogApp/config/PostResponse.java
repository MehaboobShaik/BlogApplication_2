package com.blogApp.config;

import java.util.List;

import com.blogApp.modelDto.PostDto;

public class PostResponse {
	 
	   private List<PostDto> content;
	   private int pageNumber;
	   private int pageSize;
	   private long totalElements;
	   private int totalPages;
	   private boolean lastPages;
	public PostResponse() {
		super();
	}
	public PostResponse(List<PostDto> content, int pageNumber, int pageSize, long totalElements, int totalPages,
			boolean lastPages) {
		super();
		this.content = content;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.lastPages = lastPages;
	}
	public List<PostDto> getContent() {
		return content;
	}
	public void setContent(List<PostDto> content) {
		this.content = content;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public boolean isLastPages() {
		return lastPages;
	}
	public void setLastPages(boolean lastPages) {
		this.lastPages = lastPages;
	}
	   
	   
}
