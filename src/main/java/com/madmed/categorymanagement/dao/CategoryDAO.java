package com.madmed.categorymanagement.dao;

import java.util.List;

import com.madmed.categorymanagement.domain.Category;

public interface CategoryDAO {
    public List<Category> getChildren(long id);
    public List<Category> getRootCategories();
    public List<Category> searchCategories(String name);
    public List<Category> getCategoryPath(long id);
    public void addCategory(String name, long parent);
    public void addRootCategory(String name);
    public void removeCategory(long id);
	public void editCategory(long id, String name);
	public Category getCategory(long id);
	public long getRootCategoriesNumber();
}
