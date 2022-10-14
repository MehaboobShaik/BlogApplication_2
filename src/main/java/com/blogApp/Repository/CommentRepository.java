package com.blogApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApp.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
