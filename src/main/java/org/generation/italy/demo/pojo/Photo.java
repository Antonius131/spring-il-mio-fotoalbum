package org.generation.italy.demo.pojo;

import java.util.List;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;



@Entity
@Table
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Deve esserci un titolo")
	@Column
	private String title;
	
	@NotEmpty(message = "Inserire una descrizione")
	@Column
	@Lob
	private String description;
	
	@NotEmpty(message = "Inserire l'URL dell'immagine")
	@URL(message = "Inserisci un URL valido")
	@Column
	private String url;
	
	@NotNull(message = "Scegli se renderla visibile o no")
	@Column
	private boolean visible;

	@ManyToMany
	private List<Category> categories;
	
	public Photo(){}
	
	public Photo(String title, String description, String url, boolean visible) {
		
		setTitle(title);
		setDescription(description);
		setUrl(url);
		setVisible(visible);
	}
	
	public Photo(String title, String description, String url, boolean visible, Category category) {
		
		setTitle(title);
		setDescription(description);
		setUrl(url);
		setVisible(visible);
		addCategory(category);
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	

	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	

	public void addCategory(Category category) {
		boolean finded = false;
		
		for (Category c : getCategories()) {
			
			if (c.getId() == category.getId()) finded = true;
			
			if(!finded)
			getCategories().add(category);
		}
	}

	@Override
	public String toString() {
		
		return "(" + getId() + ")"
				+ " - "
				+ getTitle()
				+ "\n "
				+ getDescription()
				+ "\n "
				+ isVisible()
				+ "\n "
				+ getUrl();
	}
}
