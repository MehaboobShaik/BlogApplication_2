package com.blogApp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String comment;
	
	@ManyToOne
	private User user;
	@ManyToOne
	private Posts posts;
	
	public Comment(int id, String comment, User user, Posts posts) {
		super();
		this.id = id;
		this.comment = comment;
		this.user = user;
		this.posts = posts;
	}

	public Comment() {
		super();
	}
	
	public Comment(int id, String comment) {
		super();
		this.id = id;
		this.comment = comment;
	}

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Posts getPosts() {
		return posts;
	}

	public void setPosts(Posts posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + ", user=" + user + ", posts=" + posts + "]";
	}

}
