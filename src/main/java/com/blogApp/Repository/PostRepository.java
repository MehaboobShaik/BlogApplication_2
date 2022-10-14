package com.blogApp.Repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApp.models.Category;
import com.blogApp.models.Posts;
import com.blogApp.models.User;

public interface PostRepository extends JpaRepository<Posts, Integer>{

 
	Page<Posts> findByUser(User user,Pageable pageable);
	Page<Posts> findByCategory(Category category,Pageable pageable);
	Page<Posts> findByTitleContaining(String title,Pageable pageable);
	Page<Posts> findByContentContaining(String title,Pageable pageable);

}
