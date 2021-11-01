package com.skillstorm.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.beans.Rental;
import com.skillstorm.data.RentalDAO;

@WebServlet(urlPatterns = "/rental")
public class RentalServlet extends HttpServlet{
	RentalDAO dao = new RentalDAO();
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Hello Rental Servlet");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// use the DAO to retrieve all rentals
		List<Rental> allRentals = dao.findAll();

		// convert our list of movies to a json string
		String mjson = new ObjectMapper().writeValueAsString(allRentals);

		resp.getWriter().print(mjson);
		resp.setContentType("application/json");

	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		InputStream requestBody = req.getInputStream();
		Rental rental = new ObjectMapper().readValue(requestBody, Rental.class);
		Rental updatedRental = dao.create(rental);


		// returning back the updated movie as a json string
		resp.getWriter().print(new ObjectMapper().writeValueAsString(updatedRental));
		resp.setContentType("application/json");
		resp.setStatus(201);

	}



}

