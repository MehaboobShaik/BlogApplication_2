package com.blogApp.modelDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ManyToOne;


import com.blogApp.models.Category;
import com.blogApp.models.Comment;
import com.blogApp.models.User;

public class PostDto {

	private int id;
	private String title;
	private String content;
	private String image;

	private UserDto userDto;
	
	private CategoryDto categoryDto;
	
	private List<CommentDto> commentPostSet = new ArrayList<CommentDto>();

	
	public PostDto() {
		super();
	}


	public PostDto(int id, String title, String content, String image, UserDto userDto, CategoryDto categoryDto,
			List<CommentDto> commentPostSet) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.image = image;
		this.userDto = userDto;
		this.categoryDto = categoryDto;
		this.commentPostSet = commentPostSet;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public UserDto getUserDto() {
		return userDto;
	}


	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}


	public CategoryDto getCategoryDto() {
		return categoryDto;
	}


	public void setCategoryDto(CategoryDto categoryDto) {
		this.categoryDto = categoryDto;
	}


	public List<CommentDto> getCommentPostSet() {
		return commentPostSet;
	}


	public void setCommentPostSet(List<CommentDto> commentPostSet) {
		this.commentPostSet = commentPostSet;
	}


	@Override
	public String toString() {
		return "PostDto [id=" + id + ", title=" + title + ", content=" + content + ", image=" + image + ", userDto="
				+ userDto + ", categoryDto=" + categoryDto + ", commentPostSet=" + commentPostSet + "]";
	}




}
