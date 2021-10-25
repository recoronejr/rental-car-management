package com.skillstorm.servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.beans.Rental;
import com.skillstorm.data.RentalDAO;


@WebServlet("/delete")
public class DeleteServlet extends HttpServlet{
	RentalDAO dao = new RentalDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Rental rental = new Rental();
		String id = req.getParameter("id");
		int idInt = Integer.parseInt(id);
		
		rental.setCarId(idInt);
		

		
		dao.delete(rental);
		
		resp.sendRedirect("index.jsp");
		resp.setStatus(201);
	}

}
