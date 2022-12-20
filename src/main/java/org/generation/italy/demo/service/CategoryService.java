package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo cateRepo;
	
	public void save(Category category) {
		
		cateRepo.save(category);
	}
	
	public List<Category> findAll() {
		
		return cateRepo.findAll();
	}
	
	public Optional<Category> findById(int id) {
		
		return cateRepo.findById(id);
	}
	
	public void delete(Category category) {
		
		cateRepo.delete(category);
	}
}
