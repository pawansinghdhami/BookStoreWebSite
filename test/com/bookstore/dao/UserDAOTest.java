package com.bookstore.dao;

import static org.junit.Assert.*;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.bookstore.entity.Users;

public class UserDAOTest extends BaseDAOTest{

	
	private static UserDAO userDAO;

	@BeforeClass
	public static void setUpClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		userDAO = new UserDAO(entityManager);
	}

	@Test
	public void testCreateUser() {
		Users user1 = new Users();
		user1.setEmail("neelkanth@varni.maharaj");
		user1.setFullName("Jai Shri Neelkanth ji");
		user1.setPassword("Neelkanth#123456");

		user1 = userDAO.create(user1);

		assertTrue(user1.getUserId() > 0);
	}

	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user1 = new Users();

		user1 = userDAO.create(user1);

	}
	
	@Test
	public void testUpdateUsers() {
		Users user = new Users();
		user.setUserId(1);
		user.setEmail("dsfdsf@ddfdsf.yahoo");
		user.setPassword("234@sdf%");
		user.setFullName("Pawan Singh Dhami");
		user = userDAO.update(user);
		String expected = "234@sdf%";
		String actual = user.getPassword();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetUsersFound() {
		Integer userId = 7;
		Users user = userDAO.get(userId);
		if(user!=null)
		System.out.println(user.getEmail());
		assertNotNull(user);
	}
	
	@Test
	public void testGetUsersNotFound() {
		Integer userId = 99;
		Users user = userDAO.get(userId);
		
		assertNull(user);
	}
	
	@Test
	public void testDeleteUsers() {
		Integer userId = 5;
		userDAO.delete(userId);
		Users user = userDAO.get(userId);
		assertNull(user);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNonExistUsers() {
		Integer userId = 58;
		userDAO.delete(userId);
	}
	
	@Test
	public void testListAll() {
	List<Users> listUsers = userDAO.listAll();
	assertTrue(listUsers.size()>0);
	}
	
	@Test 
	public void testCount() {
		long totalUsers = userDAO.count();
		assertEquals(6, totalUsers);
	}
	
	@Test
	public void testFindByEmail() {
		String email = "jaishrirams@saket.dham";
		Users user = userDAO.findByEmail(email);
		assertNotNull(user);
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

}
