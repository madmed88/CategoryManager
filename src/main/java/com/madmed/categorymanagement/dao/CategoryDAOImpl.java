package com.madmed.categorymanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.madmed.categorymanagement.domain.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO{
	
	@Autowired
    private SessionFactory sessionFactory;

	public List<Category> getChildren(long parent) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Category where parent.id = :parent ");
		query.setParameter("parent", parent);
		return query.list();
	}

	public List<Category> getRootCategories() {
		Query query = sessionFactory.getCurrentSession().createQuery("from Category where parent.id is null ");
		return query.list();
	}

	public List<Category> searchCategories(String name) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Category where name like :name ");
		query.setParameter("name", "%" + name + "%");
		query.setMaxResults(1000);
		return query.list();
	}

	public List<Category> getCategoryPath(long id) {
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Category WHERE category_id = :id");               
	    query.setParameter("id", id);                                                          
	    Category category = (Category) query.uniqueResult();
		List<Category> path = new ArrayList<Category>();
		path.add(category);
		while (category.getParent() != null) {
			id = category.getParent().getId();
		    query.setParameter("id", id);                                                          
			category = (Category) query.uniqueResult();
			path.add(category);
		} ;
		return path;
	}

	public void addCategory(String name, long parent) {
		Category parentCategory = (Category) sessionFactory.getCurrentSession().load(Category.class, parent);
		sessionFactory.getCurrentSession().save(new Category(name, parentCategory));
	}
	
	public void addRootCategory(String name) {
		sessionFactory.getCurrentSession().save(new Category(name));
	}

	public void removeCategory(long id) {
		Category category = (Category) sessionFactory.getCurrentSession().load(Category.class, id);
		sessionFactory.getCurrentSession().delete(category);
	}

	public void editCategory(long id, String name){
		Category category = (Category) sessionFactory.getCurrentSession().get(Category.class, id);
		category.setName(name);
		sessionFactory.getCurrentSession().save(category);	
	}

	public long getRootCategoriesNumber() {
	    return (Long) sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM Category where parent is null").uniqueResult();
	}

	public Category getCategory(long id) {
		return (Category) sessionFactory.getCurrentSession().load(Category.class, id);
	}

}
