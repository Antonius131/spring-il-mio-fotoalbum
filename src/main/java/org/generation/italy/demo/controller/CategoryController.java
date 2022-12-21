package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.service.CategoryService;
import org.generation.italy.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService cateService;
	
	@Autowired
	private PhotoService photoService;
	
	@GetMapping
	public String index(Model model) {
		
		List<Category> categories = cateService.findAll();
		categories.sort((id1, id2) -> id1.getId() - id2.getId());
		
		model.addAttribute("categories", categories);
		
		return "category-index";
	}
	
	@GetMapping("/show/{id}")
	public String show(
			@PathVariable("id") int id,
			Model model) {
		
		Optional<Category> optCategory= cateService.findById(id);
		Category category = optCategory.get();
		
		List<Photo> photos = category.getPhotos();
		
		model.addAttribute("category", category);
		model.addAttribute("photos", photos);
		
		return "category-show";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		
		Category category = new Category();
		List<Photo> photos = photoService.findAll();
		
		model.addAttribute("category", category);
		model.addAttribute("photos", photos);
		
		return "category-create";
	}
	
	@PostMapping("/create")
	public String store(
			@Valid Category category, 
			BindingResult bindingResult, 
			RedirectAttributes redirectAttributes
		) {
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/categories/create";
		}
		
		cateService.save(category);
		return "redirect:/categories";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(
			@PathVariable("id") int id,
			Model model
		) {
		
		Category category= cateService.findById(id).get();
		List<Photo> photos = photoService.findAll();
		
		model.addAttribute("category", category);
		model.addAttribute("photos", photos);
		
		return "category-edit";
	}
	
	@PostMapping("/edit")
	public String update(
			@PathVariable("id") int id,
			@Valid Category category,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes
		) {
		
		if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/categories/edit/" + category.getId();
		}
		
		cateService.save(category);
		
		return "redirect:/categories";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(
			@PathVariable("id") int id) {
		
		cateService.deleteById(id);
		
		return "redirect:/categories";
	}
}


















