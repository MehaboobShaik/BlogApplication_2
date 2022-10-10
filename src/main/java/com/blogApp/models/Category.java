package com.blogApp.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	public Category() {
		super();
	}
	public Category(int id, String title) {
		super();
		this.id = id;
		this.title = title;
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
	@Override
	public String toString() {
		return "Category [id=" + id + ", title=" + title + "]";
	}
	
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	Set<Posts> list =new HashSet<Posts>();
	public Set<Posts> getList() {
		return list;
	}
	public void setList(Set<Posts> list) {
		this.list = list;
	}
	public Category(int id, String title, Set<Posts> list) {
		super();
		this.id = id;
		this.title = title;
		this.list = list;
	}
	
	
	
}
