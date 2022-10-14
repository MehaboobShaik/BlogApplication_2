package com.blogApp.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApp.Repository.CommentRepository;
import com.blogApp.Repository.PostRepository;
import com.blogApp.Repository.UserRepository;
import com.blogApp.customexceptions.ResourceNotFoundException;
import com.blogApp.modelDto.CommentDto;
import com.blogApp.models.Comment;
import com.blogApp.models.Posts;
import com.blogApp.models.User;

@Service
public class CommentServiceImpl  implements com.blogApp.services.CommentService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId) {
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","id", userId));
		Posts post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("userId", "id", postId));
        comment.setUser(user);
        comment.setPosts(post);
		Comment InsertedComment = this.commentRepository.save(comment);
		CommentDto insertedCommentDto = this.modelMapper.map(InsertedComment, CommentDto.class);
		return insertedCommentDto;
	}

	@Override
	public CommentDto UpdateComment(CommentDto commentDto, Integer commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "id", commentId));
		comment.setComment(commentDto.getComment());
		Comment updatedComment = this.commentRepository.save(comment);
        CommentDto updatedCommentDto = this.modelMapper.map(updatedComment, CommentDto.class);
        return updatedCommentDto;
	}

	@Override
	public void DeletePost(Integer userId, Integer postId, Integer commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "id", commentId));
 	    User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","id", userId));
		user.getCommentSet().remove(comment);
 	    Posts post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("userId", "id", postId));
        post.getCommentSet().remove(comment);
        commentRepository.delete(comment);
	}

}
