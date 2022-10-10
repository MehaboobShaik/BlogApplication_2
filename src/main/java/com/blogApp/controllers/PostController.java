package com.blogApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApp.customexceptions.ApiResponse;
import com.blogApp.modelDto.PostDto;
import com.blogApp.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;
	
	@PostMapping("/createpost/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto ,
			                                  @PathVariable Integer userId,
			                                  @PathVariable Integer categoryId){
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/updatepost/{postId}")
	public ResponseEntity<PostDto> UpdatePost(@RequestBody PostDto postDto , @PathVariable Integer postId){
		 PostDto updatePost = this.postService.UpdatePost(postDto,postId);
		 return new ResponseEntity<PostDto>(updatePost,HttpStatus.CREATED);
		
	}
	@DeleteMapping("/deletepost/{postId}/user/{userId}/category/{categoryId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId,
                                                  @PathVariable Integer userId,
                                                  @PathVariable Integer categoryId){
		System.out.println(postId+" "+userId+" "+categoryId);
		this.postService.DeletePost(postId,userId,categoryId);
		ApiResponse apiResponse = new ApiResponse("post deleted sucessfully",false);
		 return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.FOUND);
		
	}
	@GetMapping("/getpost/{postId}")
	public ResponseEntity<PostDto> getpost(@PathVariable Integer postId){
		PostDto post = this.postService.getPost(postId);
		 return new ResponseEntity<PostDto>(post,HttpStatus.FOUND);
		
	}
	@GetMapping("/getAllposts")
	public ResponseEntity<List<PostDto>> getAllposts(){
		List<PostDto> allPosts = this.postService.getAllPosts();
		 return new ResponseEntity<List<PostDto>>(allPosts,HttpStatus.FOUND);
		
	}
}
