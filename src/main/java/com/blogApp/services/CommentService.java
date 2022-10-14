package com.blogApp.services;

import java.util.List;

import com.blogApp.modelDto.CommentDto;
import com.blogApp.modelDto.PostDto;

public interface CommentService {

	public CommentDto createComment(CommentDto commentDto,Integer UserId,Integer postId);
	public CommentDto UpdateComment(CommentDto commentDto,Integer commentId);
	public void DeletePost(Integer userId,Integer postId,Integer commentId);
	
}
