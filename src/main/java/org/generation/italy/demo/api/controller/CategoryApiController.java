package org.generation.italy.demo.api.controller;

import java.util.List;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/1/categories")
@CrossOrigin("*")
public class CategoryApiController {

	@Autowired
	private CategoryService cateService;
	
	@GetMapping("/all")
	public List<Category> getCategories() {
		
		return cateService.findAll();
	}
}
