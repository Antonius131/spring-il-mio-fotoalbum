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
@RequestMapping("/photos")
public class PhotoController {

	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService cateService;
	
	@GetMapping
	public String index(Model model) {
		
		List<Photo> photos = photoService.findAll();
		model.addAttribute("photos", photos);
		
		return "photo-index";
	}
	
	
	@GetMapping("/create")
	public String create(Model model) {
		
		Photo photo = new Photo();
		List<Category> categories = cateService.findAll();
		
		model.addAttribute("photo", photo);
		model.addAttribute("categories", categories);
		
		return "photo-create";
	}
	
	@PostMapping("/create")
	public String store(
			@Valid Photo photo, 
			BindingResult bindingResult, 
			RedirectAttributes redirectAttributes
		) {
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/photos/create";
		}
		
		photoService.save(photo);
		return "redirect:/photos";
	}
	
	@GetMapping("/show/{id}")
	public String show(
			@PathVariable("id") int id,
			Model model) {
		
		Optional<Photo> optPhoto = photoService.findById(id);
		
		Photo photo = optPhoto.get();
		List<Category> categories = photo.getCategories();
		
		model.addAttribute("photo", photo);
		model.addAttribute("categories", categories);
		
		return "photo-show";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(
			@PathVariable("id") int id,
			Model model
		) {
		
		Photo photo = photoService.findById(id).get();
		List<Category> categories = cateService.findAll();
		
		model.addAttribute("photo", photo);
		model.addAttribute("categories", categories);
		
		return "photo-edit";
	}
	
	@PostMapping("/edit")
	public String update(
			@Valid Photo photo,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes
		) {
		
		if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/photos/edit/" + photo.getId();
		}
		
		photoService.save(photo);
		
		return "redirect:/photos";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(
			@PathVariable("id") int id) {
		
		photoService.deleteById(id);
		
		return "redirect:/photos";
	}
}