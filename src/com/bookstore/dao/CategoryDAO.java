package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Category;

public class CategoryDAO extends JpaDAO<Category> implements GenericDAO<Category> {
	
	private static CategoryDAO instance;
	
	public static CategoryDAO getInstance() {
		if(instance==null) {
			instance = new CategoryDAO();
		}
		return instance;
	}

	public CategoryDAO(EntityManager entityManager) {
		super(entityManager);
		
	}
	
	public CategoryDAO() {}

	@Override
	public Category create(Category category) {
		
		return super.create(category);
	}
	
	@Override
	public Category update(Category entity) {
		return super.update(entity);
	}

	@Override
	public Category get(Object id) {
		
		return super.find(Category.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Category.class, id);
		
	}

	@Override
	public List<Category> listAll() {
		
		return super.findWithNamedQuery("Category.findAll");
	}

	@Override
	public long count() {
		
		return super.countWithNamedQuery("Category.countAll");
	}

	public Category findByName(String name) {
		List<Category> listCategory = super.findWithNamedQuery("Category.findByName", "name", name);
		if(listCategory!=null && listCategory.size()>0) {
			return listCategory.get(0);
		}
		return null;
	}

}
