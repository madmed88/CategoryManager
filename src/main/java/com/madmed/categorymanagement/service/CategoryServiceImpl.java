package com.madmed.categorymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.madmed.categorymanagement.dao.CategoryDAO;
import com.madmed.categorymanagement.domain.Category;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
    private CategoryDAO contactDAO;
	
	@Transactional
	public List<Category> searchCategories(String name) {
		return contactDAO.searchCategories(name);			
	}

	@Transactional
	public List<Category> getChildren(long id) {
		return contactDAO.getChildren(id);
	}
	
	@Transactional
	public List<Category> getRootCategories() {
		return contactDAO.getRootCategories();
	}

	@Transactional
	public List<Category> getCategoryPath(long id) {
		return contactDAO.getCategoryPath(id);
	}

	@Transactional
	public boolean addCategory(String name, long parent) {
		
		if (parent == -1){
		    long rootCategories = contactDAO.getRootCategoriesNumber();	
			if (rootCategories>1000) {
				return false;
			} else {
				contactDAO.addRootCategory(name);
				return true;
			}
			
		} else {
			Category parentCategory = contactDAO.getCategory(parent);
			int children = contactDAO.getChildren(parent).size();
			int parents = contactDAO.getCategoryPath(parentCategory.getId()).size();
			if (parents>9 || children>9) {
				return false;
			} else {
				contactDAO.addCategory(name, parent);
				return true;
			}
		}
	}

	@Transactional
	public boolean removeCategory(long id) {
		if (contactDAO.getChildren(id).size()==0) {
			contactDAO.removeCategory(id);
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	public boolean editCategory(long id, String name) {
		contactDAO.editCategory(id, name);
		return true;
	}
}
