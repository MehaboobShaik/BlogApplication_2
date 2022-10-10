package com.blogApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogApp.customexceptions.ResourceNotFoundException;
import com.blogApp.modelDto.PostDto;
import com.blogApp.modelDto.UserDto;
@Service
public interface PostService {

	public PostDto createPost(PostDto postDto,Integer UserId,Integer categoryId);
	public PostDto UpdatePost(PostDto postDto,Integer postId);
	public void DeletePost(Integer postId,Integer userId,Integer categoryId);
	public PostDto getPost(Integer postId);
	public List<PostDto> getAllPosts();
}
