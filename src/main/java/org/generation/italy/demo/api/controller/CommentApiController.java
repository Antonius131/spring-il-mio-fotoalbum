package org.generation.italy.demo.api.controller;

import java.util.List;

import org.generation.italy.demo.pojo.Comment;
import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.service.CommentService;
import org.generation.italy.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/1")
@CrossOrigin("*")
public class CommentApiController {
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CommentService comService;
	
	@GetMapping("/ph/comments/{id}")
	public List<Comment> getCategoriesById(@PathVariable("id") int id) {
		
		List<Comment> commentsById = photoService.findById(id).get().getComments();
		
		return commentsById;
	}
	
	@PostMapping("/send/{id}")
	public Comment addComment(
			@PathVariable("id") int id,
			@Valid @RequestBody Comment comment) {
		
		Photo photo = photoService.findById(id).get();
		
		Comment newComment = new Comment(comment.getText(), photo);
		
		comService.save(newComment);
		
		return newComment;
	}
}
