package com.madmed.categorymanagement.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
 
import com.madmed.categorymanagement.domain.Category;
import com.madmed.categorymanagement.service.CategoryService;
 
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
 
@Controller
public class CategoryController {
 
    @Autowired
    private CategoryService categoryService;
 
    @RequestMapping(value = "/search/{name}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Category> searchCategories(@PathVariable String name) {
    	return new ArrayList<Category>(categoryService.searchCategories(name));
    }
    
    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Category> getChildren(@PathVariable Long id) {
    	return new ArrayList<Category>(categoryService.getChildren(id));
    }
    
    @RequestMapping(value = "/category", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Category> getRootCategories() {
    	return new ArrayList<Category>(categoryService.getRootCategories());
    }
    
    @RequestMapping(value = "/path/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Category> getCategoryPath(@PathVariable Long id) {
    	return new ArrayList<Category>(categoryService.getCategoryPath(id));
    }
    

    @RequestMapping(value = "/category", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody ResultResponse addCategory(@RequestBody pseudoCategory pCategory) { 
        if (categoryService.addCategory(pCategory.getName(), pCategory.getParent())) {
        	return new ResultResponse("true");
		} else {
			return new ResultResponse("false");
		}
    }
    
    @RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody ResultResponse deleteCategory(@PathVariable("id") Long id) {
        if (categoryService.removeCategory(id)) {
        	return new ResultResponse("true");
		} else {
			return new ResultResponse("false");
		}
        
    }
    
    @RequestMapping(value = "/category", method = RequestMethod.PUT)
    public @ResponseBody ResultResponse editCategory(@RequestBody pseudoCategory category) {
        categoryService.editCategory(category.getId(), category.getName());
        return new ResultResponse("true");
    } 
    
    
    @ExceptionHandler({DataIntegrityViolationException.class, ConstraintViolationException.class})
    public @ResponseBody ResultResponse handleException(Exception ex, HttpServletRequest request) {
    	  return new ResultResponse(ex.getMessage());
    }
}

class ResultResponse {
	private String status;
	public ResultResponse(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}

class pseudoCategory {
	private long id;
	private String name;
	private long parent;
	
	public pseudoCategory(String name, long parent) {
		this.name = name;
		this.parent = parent;
	}
	
	public pseudoCategory(long id, String name, long parent) {
		this.id = id;
		this.name = name;
		this.parent = parent;
	}
	
	public pseudoCategory(){
    }
	
	public String getName() {
		return name;
	}
	public void setName(String status) {
		this.name = status;
	}
	public long getParent() {
		return parent;
	}
	public void setParent(long parent) {
		this.parent = parent;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
