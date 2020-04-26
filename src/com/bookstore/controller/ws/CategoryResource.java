package com.bookstore.controller.ws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

@Path("/category")
public class CategoryResource {

	private CategoryDAO categoryDAO = CategoryDAO.getInstance();
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> listCategory(){
		return categoryDAO.listAll();
	}
}
