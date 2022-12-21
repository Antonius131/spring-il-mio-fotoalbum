package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.repo.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PhotoService {

	@Autowired
	private PhotoRepo photoRepo;
	
	public void save(Photo photo) {
		
		photoRepo.save(photo);
	}
	
	public List<Photo> findAll() {
		
		return photoRepo.findAll();
	}
	
	public Optional<Photo> findById(int id) {
		
		return photoRepo.findById(id);
	}
	
	public void delete(Photo photo) {
		
		photoRepo.delete(photo);
	}
	
	public void deleteById(int id) {
		
		photoRepo.deleteById(id);
	}
}