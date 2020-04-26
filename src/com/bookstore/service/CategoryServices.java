package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

public class CategoryServices {

	private EntityManager entityManager;
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CategoryServices(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.entityManager = entityManager;
		categoryDAO = new CategoryDAO(entityManager);
	}

	public void listCategory() throws ServletException, IOException {
		listCategory(null);
	}

	public void listCategory(String message) throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		request.setAttribute("message", message);
		request.getRequestDispatcher("category_list.jsp").forward(request, response);
	}

	public void createCategory() throws ServletException, IOException {
		String name = request.getParameter("name");
		Category category = categoryDAO.findByName(name);
		String message = "Category created successfully.";

		if (category != null) {
			message = "Could not create Category. A Category with " + name + " is already exist!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} else {
			Category newCategory = new Category(name);
			categoryDAO.create(newCategory);
			listCategory(message);

		}

	}

	public void editCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.get(categoryId);
		String destPage = "category_form.jsp";
		if (category != null) {
			request.setAttribute("category", category);
		} else {
			destPage = "message.jsp";
			String message = "Could not find category. Category with categoryId " + categoryId + " has been removed!";
			request.setAttribute("message", message);

		}
		request.getRequestDispatcher(destPage).forward(request, response);
	}

	public void updateCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String categoryName = request.getParameter("name");
		Category categoryById = categoryDAO.get(categoryId);
		Category categoryByName = categoryDAO.findByName(categoryName.trim());
		String message = "Category has been updated successfully";
		if (categoryByName != null && categoryById.getCategoryId() != categoryByName.getCategoryId()) {
			message = "Could not update Category. Category with name " + categoryName + " already exist!";
           request.setAttribute("message", message);
           request.getRequestDispatcher("message.jsp").forward(request, response);
		}else {
			categoryById.setName(categoryName);
			categoryDAO.update(categoryById);
			listCategory(message);
		}

	}
	
	public void deleteCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.get(categoryId); 
		String message = "Category deleted successfully";
		if(categoryId==1) {
			message = "The default category cannot be deleted.";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
			return;
		}
		if(category!=null) {
			categoryDAO.delete(categoryId);
			listCategory(message);
		}else {
			message ="Could not delete this category. Category with id " +categoryId+" already removed!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
		}
		
	

}
