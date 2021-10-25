package com.skillstorm.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.beans.Rental;
import com.skillstorm.data.RentalDAO;


@WebServlet("/edit")
public class EditServlet extends HttpServlet{
	RentalDAO dao = new RentalDAO();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Enumeration<String> params = req.getParameterNames();	

		Rental rental = new Rental();
		int carId = Integer.parseInt(req.getParameter("id"));
		
		String mileage = req.getParameter("mileage");
		int milageInt = Integer.parseInt(mileage);
		
		String isAvailable = req.getParameter("isAvailable");
		rental.convertToBoolAndSet(isAvailable);
		
		String pricePerDay = req.getParameter("pricePerDay");
		double price = Double.parseDouble(pricePerDay);
		
		
		rental.setCarId(carId);
		rental.setMileage(milageInt);
		rental.setPricePerDay(price);

		dao.update(rental);
						
		// set the status code to 201: CREATED
		resp.setStatus(201);
						
		// setting content type to JSON
		resp.sendRedirect("index.jsp");
		resp.setContentType("application/json");
	}

}

