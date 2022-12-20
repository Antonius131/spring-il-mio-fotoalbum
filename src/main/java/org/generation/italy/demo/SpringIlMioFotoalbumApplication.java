package org.generation.italy.demo;

import java.util.ArrayList;
import java.util.List;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.service.CategoryService;
import org.generation.italy.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner{

	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService cateService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
				
		
		// creating photos
		Photo p1 = new Photo(
				"The Batman", 
				"Io sono Vendetta", 
				"https://static.posters.cz/image/350/poster/the-batman-2022-i122125.jpg", 
				true
				);
		
		Photo p2 = new Photo(
				"Love Actually", 
				"Very romantic. Very comedy.", 
				"https://static.posters.cz/image/750/poster/the-hateful-eight-teaser-i28466.jpg", 
				true
				);
		
		Photo p3 = new Photo(
				"The hateful eight", 
				"The 8th film", 
				"https://i.pinimg.com/originals/28/58/8f/28588f44d4cb45cf125706da30367c64.jpg", 
				true
				);
		
		Photo p4 = new Photo(
				"The Truman Show", 
				"The story of a lifetime", 
				"https://i.pinimg.com/originals/1f/62/15/1f6215facd358b080e029ceec919166a.jpg", 
				true
				);
		
		photoService.save(p1);
		photoService.save(p2);
		photoService.save(p3);
		photoService.save(p4);
		
		List<Photo> comedyList = new ArrayList<>();
		comedyList.add(p4);
		comedyList.add(p2);
		
		List<Photo> thrillerList = new ArrayList<>();
		thrillerList.add(p1);
		thrillerList.add(p3);
		
				
		
		// creating categories
		Category c1 = new Category("Drammatico", comedyList);
		Category c2 = new Category("Thriller", thrillerList);
		
		cateService.save(c1);
		cateService.save(c2);
	}
}