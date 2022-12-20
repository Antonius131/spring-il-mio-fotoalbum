package org.generation.italy.demo.controller;

import java.util.List;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService cateService;
	
	@GetMapping
	public String index(Model model) {
		
		List<Category> categories = cateService.findAll();
		categories.sort((id1, id2) -> id1.getId() - id2.getId());
		
		model.addAttribute("categories", categories);
		
		return "category-index";
	}
	
}
