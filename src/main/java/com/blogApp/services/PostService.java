package com.blogApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogApp.config.PostResponse;
import com.blogApp.customexceptions.ResourceNotFoundException;
import com.blogApp.modelDto.PostDto;
import com.blogApp.modelDto.UserDto;
import com.blogApp.models.Posts;
@Service
public interface PostService {

	public PostDto createPost(PostDto postDto,Integer UserId,Integer categoryId);
	public PostDto UpdatePost(PostDto postDto,Integer postId);
	public void DeletePost(Integer postId,Integer userId,Integer categoryId);
	public PostDto getPost(Integer postId);
	public PostResponse getAllPosts(int pageNo, int pageSize);
	
	public List<PostDto> getPostByUser(Integer userId,int pageNo, int pageSize);
	public List<PostDto> getPostByCategory(Integer categotyId,int pageNo, int pageSize);
	public List<PostDto> getPostByTitle(String title,int pageNo, int pageSize);
	public List<PostDto> getPostByContent(String content,int pageNo, int pageSize);
}
