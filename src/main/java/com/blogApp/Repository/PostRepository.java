package com.blogApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApp.models.Posts;

public interface PostRepository extends JpaRepository<Posts, Integer>{

}
