package com.blogApp.modelDto;

import javax.persistence.ManyToOne;

import com.blogApp.models.Category;
import com.blogApp.models.User;

public class PostDto {

	private int id;
	private String title;
    private String content;
    private String image;
    

//    private User user;
//    private Category category;
    
	public PostDto() {
		super();
	}
//	public PostDto(int id, String title, String content, String image, User user, Category category) {
//		super();
//		this.id = id;
//		this.title = title;
//		this.content = content;
//		this.image = image;
//		this.user = user;
//		this.category = category;
//	}
	public PostDto(int id, String title, String content, String image) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.image = image;
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
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
//	public Category getCategory() {
//		return category;
//	}
//	public void setCategory(Category category) {
//		this.category = category;
//	}
	@Override
	public String toString() {
		return "PostDto [id=" + id + ", title=" + title + ", content=" + content + ", image=" + image + "]";
	}
	
}
