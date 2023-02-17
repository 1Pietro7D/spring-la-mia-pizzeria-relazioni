package org.feb14.pizzeria.model;

import java.math.BigDecimal;

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
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="pizze")
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@NotEmpty
	@Column(length = 50 , unique = false)
	private String name;
	
	// @Nonnull // Non gestisce la validazione per il db
	@Column(length = 300 , unique = false) // nullable = false , non gestisce la validazione lato client
	// TODO : lenght = 300, ritorna un varchar(300) è possibile come cosa?
	@NotNull // gestisce lato client e server
	@NotEmpty
	private String description;
	
	
	@Column(nullable=false)
	private String imgPath;
	
	
	@NotNull
	@DecimalMin(value = "0.01", message = "Il prezzo deve essere maggiore di zero")
	private BigDecimal price;
	
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
