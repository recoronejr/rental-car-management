package com.skillstorm.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.beans.Vehicle;
import com.skillstorm.data.VehicleDAO;

@WebServlet(urlPatterns = "/vehicles")
public class VehicleServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// use the DAO to retrieve all vehicles
		VehicleDAO dao = new VehicleDAO();
		List<Vehicle> allVehicles = dao.findAll();

		// convert our list of movies to a json string
		String mjson = new ObjectMapper().writeValueAsString(allVehicles);

		resp.getWriter().print(mjson);
		resp.setContentType("application/json");
	}
}
