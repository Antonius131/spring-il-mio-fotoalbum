package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Comment;
import org.generation.italy.demo.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	@Autowired
	private CommentRepo comRepo;
	
	public void save(Comment comment) {
		
		comRepo.save(comment);
	}
	
	public List<Comment> findAll() {
		
		return comRepo.findAll();
	}
	
	public Optional<Comment> findById(int id) {
		
		return comRepo.findById(id);
	}
	
	public void deleteById(int id) {
		
		comRepo.deleteById(id);
	}
}
