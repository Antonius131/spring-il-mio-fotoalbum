package org.generation.italy.demo.api.controller;

import java.util.List;

import org.generation.italy.demo.pojo.Comment;
import org.generation.italy.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1")
@CrossOrigin("*")
public class CommentApiController {
	
	@Autowired
	private PhotoService photoService;
	
	@GetMapping("/ph/comments/{id}")
	public List<Comment> getCategoriesById(@PathVariable("id") int id) {
		
		List<Comment> commentsById = photoService.findById(id).get().getComments();
		
		return commentsById;
	}
}
