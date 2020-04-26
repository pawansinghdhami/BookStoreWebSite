package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.bookstore.entity.Category;

public class CategoryDAOTest extends BaseDAOTest{
	
	
	private static CategoryDAO categoryDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		categoryDAO = new CategoryDAO(entityManager);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

	@Test
	public void testCreateCategory() {
		Category newCategory = new Category("Health");
		Category category = categoryDAO.create(newCategory);
		assertTrue(category!=null && category.getCategoryId()>0);
	}
	
	@Test
	public void testUpdateCategory() {
		Category category = new Category("Android");
		category.setCategoryId(3);
		Category categoryUpdated = categoryDAO.update(category);
		assertEquals(category.getName(), categoryUpdated.getName());
		
	}
	
	@Test
	public void testGetCategory() {
		Integer catId = 9;
		Category cat = categoryDAO.get(catId);
		assertNotNull(cat);
	}


	@Test
	public void testDeleteCategory() {
		Integer id = 4;
		categoryDAO.delete(id);
		Category category = categoryDAO.get(id);
		
		assertNull(category);
	}

	@Test
	public void testListAll() {
		List<Category> listCategory = categoryDAO.listAll();
		listCategory.forEach(c -> System.out.println(c.getName() + ","));
		assertTrue(listCategory.size()>0);
	}

	@Test
	public void testCount() {
		long totalCategories = categoryDAO.count();
		assertEquals(4, totalCategories);
	}
	
	@Test
	public void testFindByName() {
	String name = "core javaasd";
	Category category = categoryDAO.findByName(name);
	
	assertNull(category);
	}

}




