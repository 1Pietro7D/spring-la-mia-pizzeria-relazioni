package org.feb14.pizzeria.model;

//// https://jakarta.ee/specifications/persistence/3.1/apidocs/index.html

import jakarta.annotation.Nonnull;
// https://docs.oracle.com/javaee/7/api/javax/persistence/Column.html
// https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/column
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="pizza")
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Nonnull
	@Column(length = 50 , nullable = false , unique = false)
	private String name;
	
	@Nonnull
	@Column(length = 300 , nullable = false , unique = false)
	private String description;
	
	@Nonnull
	@Column(nullable=false)
	private String imgPath;
	
	@Nonnull
	@Column(nullable=false)
	private double price;
	
	public String getName() {
		return name;
	}
	
	// Getter and Setting
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getId() {
		return id;
	}
	
}
