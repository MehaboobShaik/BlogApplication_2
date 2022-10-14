package com.blogApp.modelDto;

import javax.persistence.ManyToOne;

import com.blogApp.models.Posts;
import com.blogApp.models.User;

public class CommentDto {

	private int id;
	private String comment;
//	private Posts posts;
//	private User user;
	
	public CommentDto() {
		super();
	}

	public CommentDto(int id, String comment) {
		super();
		this.id = id;
		this.comment = comment;
	}

//	public CommentDto(int id, String comment, Posts posts, User user) {
//		super();
//		this.id = id;
//		this.comment = comment;
//		this.posts = posts;
//		this.user = user;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "CommentDto [id=" + id + ", comment=" + comment + "]";
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	public Posts getPosts() {
//		return posts;
//	}
//
//	public void setPosts(Posts posts) {
//		this.posts = posts;
//	}
	
}
