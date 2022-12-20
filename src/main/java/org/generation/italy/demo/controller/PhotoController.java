package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/photos")
public class PhotoController {

	@Autowired
	private PhotoService photoService;
	
	@GetMapping
	public String index(Model model) {
		
		List<Photo> photos = photoService.findAll();
		model.addAttribute("photos", photos);
		
		return "photo-index";
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
}