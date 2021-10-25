package com.skillstorm.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import com.skillstorm.beans.Rental;

public class RentalDAO {
	
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
	public List<Rental> findAll() {
			
			List<Rental> allRentals = new LinkedList<>();
			
			
			// 2. create the connection -AND- 5. closing the connection
			try(Connection conn = DriverManager.getConnection(url, username, password)){
				
				// 3. creating our statement
				String sql = "Select * from Cars";
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				// 4. execute the statement
				ResultSet rs = stmt.executeQuery();	// Query because retrieving from table
				
				while(rs.next()) {
					
					// retrieving our returned attributes
					int carID = rs.getInt("CarId");
					int mileage = rs.getInt("mileage");
					boolean isAvailable = rs.getBoolean("isAvailable");
					double pricePerDay = rs.getDouble("pricePerDay");
					int year = rs.getInt("year");
					String make = rs.getString("make");
					String model = rs.getString("model");
					
	
					
					// creating new movie object with those attributes
					Rental rental = new Rental(carID,mileage,isAvailable,pricePerDay,year,make,model);
					
					// adding new movie to set of all movies
					allRentals.add(rental);
				}
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		return allRentals;
	}
	public Rental findById(int id) {
		Rental rental;
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			String sql = "SELECT * FROM Cars WHERE CarId = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);	// tells database to return generated keys
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			// retrieving our returned attributes
			int carID = rs.getInt("CarId");
			int mileage = rs.getInt("mileage");
			boolean isAvailable = rs.getBoolean("isAvailable");
			double pricePerDay = rs.getDouble("pricePerDay");
			int year = rs.getInt("year");
			String make = rs.getString("make");
			String model = rs.getString("model");
			

			
			// creating new movie object with those attributes
			rental = new Rental(carID,mileage,isAvailable,pricePerDay,year,make,model);
			
		} catch (SQLException e) {
			rental = new Rental();
			e.printStackTrace();
		}
		return rental;
	}
	public Rental create(Rental rental) {
		
		// 2. create the connection -AND- 5. closing the connection
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			// 3. creating our statement
			
			// PreparedStatement is compiled by JAVA, then sent to MySQL
			// vs.
			// Statement: compiled at the database
			String sql = "Insert Into Cars(Mileage, isAvailable, pricePerDay,year,make,model) Values (?,?,?,?,?,?);";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);	
			stmt.setInt(1, rental.getMileage());
			stmt.setBoolean(2, rental.isAvailable());
			stmt.setDouble(3, rental.getPricePerDay());
			stmt.setInt(4, rental.getYear());
			stmt.setString(5, rental.getMake());
			stmt.setString(6,rental.getModel());
			
			// 4. execute the statement
			stmt.executeUpdate();	// Update because changing the table
			
			ResultSet keys = stmt.getGeneratedKeys();
			keys.next();
			int rentalID = keys.getInt(1);
			
			rental.setCarId(rentalID);
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		// return back the updated movie with an ID
		return rental;
	}
	public void update(Rental newRental) {
		// 2. create the connection -AND- 5. closing the connection
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			// 3. creating our statement
			String sql = "update cars set mileage = ? , isAvailable = ? , pricePerDay = ? where CarId = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);	// tells database to return generated keys
			stmt.setInt(1, newRental.getMileage());
			stmt.setBoolean(2, newRental.isAvailable());
			stmt.setDouble(3, newRental.getPricePerDay());
			stmt.setInt(4, newRental.getCarId());
			
			// 4. execute the statement
			stmt.executeUpdate();	// Update because changing the table
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void delete(Rental rental) {
		// 2. create the connection -AND- 5. closing the connection
		try(Connection conn = DriverManager.getConnection(url, username, password)){
							
		// 3. creating our statement
		String sql = "DELETE FROM Cars WHERE CarId = ?;";
		PreparedStatement stmt = conn.prepareStatement(sql);	// tells database to return generated keys
		stmt.setInt(1, rental.getCarId());
							
		// 4. execute the statement
		stmt.executeUpdate();	// Update because changing the table
							
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
