package com.madmed.categorymanagement.service;

import java.util.List;

import com.madmed.categorymanagement.domain.Category;

public interface CategoryService {
	public List<Category> searchCategories(String name);
	public List<Category> getChildren(long id);
    public List<Category> getRootCategories();
    public List<Category> getCategoryPath(long id);
    public boolean addCategory(String name, long parent);
    public boolean removeCategory(long id);
	public boolean editCategory(long id, String name);
}
