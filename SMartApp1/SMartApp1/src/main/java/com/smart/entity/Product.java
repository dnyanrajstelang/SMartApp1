
package com.smart.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = true)
    private Object quantity;
    @Column(nullable = false, unique = true)
    private Object price;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference
    private Category category;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

	public Object getQuantity() {
		// TODO Auto-generated method stub
		return quantity;
	}

	public void setQuantity(Object quantity) {
		this.quantity=quantity;
		
	}

	public Object getPrice() {
		// TODO Auto-generated method stub
		return price;
	}

	public void setPrice(Object price) {
		this.price=price;
		
	}
}

