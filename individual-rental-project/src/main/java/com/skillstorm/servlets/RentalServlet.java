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
import com.skillstorm.beans.Vehicle;

import com.skillstorm.data.RentalDAO;
import com.skillstorm.data.VehicleDAO;

@WebServlet(urlPatterns = "/rental")
public class RentalServlet extends HttpServlet{
	RentalDAO dao = new RentalDAO();
	VehicleDAO vehDao = new VehicleDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// use the DAO to retrieve all rentals
		List<Rental> allRentals = dao.findAll();
		
		// Retrieve all vehicles
		List<Vehicle> allVehicles = vehDao.findAll();
				
		// convert our list of movies to a json string
		String mjson = new ObjectMapper().writeValueAsString(allRentals);
		
		String vjson = new ObjectMapper().writeValueAsString(allVehicles);
		
				
		// write the json string to our http response
		
		String concatData = "[" + mjson + "," + vjson + "]";
		
		resp.getWriter().print(concatData);
				
		// setting content type to JSON
		resp.setContentType("application/json");
		
	}
	
	private void parseCarName(String str, Rental rental) {
		String[] arr = str.split(" ");  
		for (int i = 0; i < arr.length; i++) {
		    if (i == 0) {
		    	int year = Integer.parseInt(arr[i]);
		    	rental.setYear(year);
		    }
		    else if(i == 1) {
		    	rental.setMake(arr[i]);
		    }
		    else if(i == 2) {
		    	rental.setModel(arr[i]);
		    }
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Rental rental = new Rental();
		String mileage = req.getParameter("mileage");
		int milageInt = Integer.parseInt(mileage);
		
		String isAvailable = req.getParameter("isAvailable");
		rental.convertToBoolAndSet(isAvailable);
		
		String pricePerDay = req.getParameter("pricePerDay");
		double price = Double.parseDouble(pricePerDay);
		
		String carName = req.getParameter("car");
		
		this.parseCarName(carName, rental);
		
		
		rental.setMileage(milageInt);
		rental.setPricePerDay(price);

		
		dao.create(rental);
		
		resp.sendRedirect("index.jsp");
		resp.setStatus(201);
		
	}
	


}
