package com.blogApp.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Posts {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
    private String content;
    private String image;
    
    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;
    
    public Posts() {
		super();
	}


	public Posts(int id, String title, String content, String image) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.image = image;
	}


	public Posts(int id, String title, String content, String image, User user, Category category) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.image = image;
		this.user = user;
		this.category = category;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@OneToMany(mappedBy = "posts",cascade = CascadeType.ALL)
	Set<Comment> commentSet =new HashSet<Comment>();

	public Posts(int id, String title, String content, String image, User user, Category category,
			Set<Comment> commentSet) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.image = image;
		this.user = user;
		this.category = category;
		this.commentSet = commentSet;
	}


	public Set<Comment> getCommentSet() {
		return commentSet;
	}


	public void setCommentSet(Set<Comment> commentSet) {
		this.commentSet = commentSet;
	}




    


	

	
}
