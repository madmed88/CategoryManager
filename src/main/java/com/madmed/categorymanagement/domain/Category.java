package com.madmed.categorymanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
 
@Entity
@Table(name="category")
public class Category {
	
	@Id
    @Column(name="category_id")
    @GeneratedValue
    private long id;
     
    @Column(name="name", unique = true)
    private String name;
 
    @ManyToOne
    @JoinColumn(name="parent")
    private Category parent;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}
	
	public Category(String name, Category parent) {
		this.name = name;
		this.parent = parent;
	}
	
	public Category(String name) {
		this.name = name;
	}
	
	public Category() {
		
	}
}