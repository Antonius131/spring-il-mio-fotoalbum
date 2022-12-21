package org.generation.italy.demo;

import java.util.ArrayList;
import java.util.List;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.pojo.Role;
import org.generation.italy.demo.pojo.User;
import org.generation.italy.demo.service.CategoryService;
import org.generation.italy.demo.service.PhotoService;
import org.generation.italy.demo.service.RoleService;
import org.generation.italy.demo.service.UserService;
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
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
				
		
		// creating categories
		Category c1 = new Category("Drammatico");
		Category c2 = new Category("Thriller");
		Category c3 = new Category("Amore");
		Category c4 = new Category("Investigativo");
		Category c5 = new Category("Romantico");
		Category c6 = new Category("Suspence");
		
		cateService.save(c1);
		cateService.save(c2);
		cateService.save(c3);
		cateService.save(c4);
		cateService.save(c5);
		cateService.save(c6);
		
		
		List<Category> cateList1 = new ArrayList<>();
		cateList1.add(c1);
		cateList1.add(c3);
		cateList1.add(c5);
		cateList1.add(c6);
		
		List<Category> cateList2 = new ArrayList<>();
		cateList2.add(c2);
		cateList2.add(c4);
		cateList2.add(c6);
		
		
		
		// creating photos
		Photo p1 = new Photo(
				"The Batman", 
				"Io sono Vendetta", 
				"https://static.posters.cz/image/350/poster/the-batman-2022-i122125.jpg", 
				true,
				cateList2
				);
		
		Photo p2 = new Photo(
				"Love Actually", 
				"Very romantic. Very comedy.", 
				"https://static.posters.cz/image/750/poster/the-hateful-eight-teaser-i28466.jpg", 
				true,
				cateList1
				);
		
		Photo p3 = new Photo(
				"The hateful eight", 
				"The 8th film", 
				"https://i.pinimg.com/originals/28/58/8f/28588f44d4cb45cf125706da30367c64.jpg", 
				true,
				cateList2
				);
		
		Photo p4 = new Photo(
				"The Truman Show", 
				"The story of a lifetime", 
				"https://i.pinimg.com/originals/1f/62/15/1f6215facd358b080e029ceec919166a.jpg", 
				true,
				cateList1
				);
		
		photoService.save(p1);
		photoService.save(p2);
		photoService.save(p3);
		photoService.save(p4);
		
		
		// creating User and Admin
		
		Role admin = new Role("Admin");
		roleService.save(admin);
		
		
		User user = new User("admin", "admin", admin);
		userService.save(user);
	}
}



















