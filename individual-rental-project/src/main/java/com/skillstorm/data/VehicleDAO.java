package com.skillstorm.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.skillstorm.beans.Vehicle;

public class VehicleDAO {
	
	// database credentials
	private static final String url = "jdbc:mysql://localhost:3306/rental_cars-api";
	private static final String username = "root";
	private static final String password = "Pa$$w0rd";
			
	// 1. load class into memory
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// CRUD operations
	public List<Vehicle> findAll() {
				
				List<Vehicle> allVehicles = new LinkedList<>();
				
				
				// 2. create the connection -AND- 5. closing the connection
				try(Connection conn = DriverManager.getConnection(url, username, password)){
					
					// 3. creating our statement
					String sql = "Select * from Vehicle;";
					PreparedStatement stmt = conn.prepareStatement(sql);
					
					// 4. execute the statement
					ResultSet rs = stmt.executeQuery();	// Query because retrieving from table
					
					while(rs.next()) {
						
						// retrieving our returned attributes
						int vehicleId = rs.getInt("VehicleId");
						int vehicleTypeId = rs.getInt("VehicleTypeId");
						int year = rs.getInt("year");
						String make = rs.getString("make");
						String model = rs.getString("model");
		
						
						// creating new movie object with those attributes
						Vehicle v = new Vehicle(vehicleId,vehicleTypeId, year, make, model);
						
						// adding new movie to set of all movies
						allVehicles.add(v);
						
					}
					
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
			// return back the set of all movies
			return allVehicles;
		}

}
