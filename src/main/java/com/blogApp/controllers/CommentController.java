package com.blogApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApp.customexceptions.ApiResponse;
import com.blogApp.modelDto.CommentDto;
import com.blogApp.services.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("/createComment/user/{userId}/post/{postId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
			                                        @PathVariable Integer userId,
			                                        @PathVariable Integer postId ){
		
		CommentDto createComment = this.commentService.createComment(commentDto, userId, postId);
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
	}  
	
	@PutMapping("/updateComment/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto,
			                                        @PathVariable Integer commentId){
		
		CommentDto createComment = this.commentService.UpdateComment(commentDto,commentId);
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
	}  
	@DeleteMapping("/deleteComment/user/{userId}/post/{postId}/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment( @PathVariable Integer userId,
			                                         @PathVariable Integer postId,
			                                          @PathVariable Integer commentId){
		
		this.commentService.DeletePost(userId, postId, commentId);
		ApiResponse apiResponse = new ApiResponse("comment Deleted sucessfully",true);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.CREATED);
	}  
}
