<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>

<%@page import="com.skillstorm.beans.Rental"%>
<%@page import="com.skillstorm.data.RentalDAO"%>

<%@page import="com.skillstorm.beans.Vehicle"%>
<%@page import="com.skillstorm.data.VehicleDAO"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ page import="java.util.*, java.io.*" %>
<%
	RentalDAO rentalData = new RentalDAO();
	List<Rental> allRentals = rentalData.findAll();
	request.setAttribute("RENTALS_LIST",allRentals);
%>

<%

	VehicleDAO vehicleData = new VehicleDAO();
	List<Vehicle> allVehicles = vehicleData.findAll();
	request.setAttribute("VEHICLES_LIST",allVehicles);
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Rental Car Application</title>
  </head>
  <body>
    <h1 style="text-align: center;">Rental Car API</h1>

  <!-- Modal -->
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Add New Rental</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <form method="post" action="rental">
            <div class="modal-body"> 
                <label class="form-label">Select Vehicle</label>
                <br>
                <c:forEach var="tempVehicle" items="${VEHICLES_LIST}">
                <input type="radio" name="car" value="${tempVehicle.year} ${tempVehicle.make} ${tempVehicle.model}">
                <label for="exampleFormControlInput1" class="form-label">${tempVehicle.year} ${tempVehicle.make} ${tempVehicle.model}</label>
                	
                	<br>
                </c:forEach>
                <br>
                <label class="form-label">Enter Price Per Day</label>
                
                <br>
                <div class="input-group mb-3">
                    <span class="input-group-text">$</span>
                    <input id="pricePerDay" type="text" class="form-control" name="pricePerDay" aria-label="Dollar amount (with dot and two decimal places)">
                </div>
                <div class="mb-3">
                    <label for="exampleFormControlInput1" class="form-label">Mileage</label>
                    <input type="number" class="form-control" id="exampleFormControlInput1" placeholder="10,000" name="mileage">
                </div>
                <div class="form-check form-switch">
                    <input class="form-check-input" type="checkbox" role="switch" name="isAvailable" id="flexSwitchCheckChecked" checked>
                    <label class="form-check-label" for="flexSwitchCheckChecked">Available</label>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <input type="submit" class="btn btn-primary" value="Save changes"/>
            </div>    
        </form>
      </div>
    </div>
  </div>

    <div style="width: 75%;margin: auto; border: 5px solid #b8b8b8; padding: 10px;">
        <div>
                <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" style="float: right;">
                Add Rental
            </button>
        </div>
        <table class="table table-striped table-hover">
                <thead>
                    <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Mileage</th>
                    <th scope="col">Price Per Day</th>
                    <th scope="col">Year</th>
                    <th scope="col">Model</th>
                    <th scope="col">Make</th>
                    <th scope="col">Available</th>
                    </tr>
                </thead>
                <tbody id = 'rentalTable'>
        			<c:forEach var="tempRental" items="${RENTALS_LIST}">
        				<tr>
	        				<td>${tempRental.carId}</td>
	        				<td>${tempRental.mileage}</td>
	        				<td>$${tempRental.pricePerDay}</td>
	        				<td>${tempRental.year}</td>
	        				<td>${tempRental.model}</td>
	        				<td>${tempRental.make}</td>
	        				<td>${tempRental.isAvailable()}</td>
	        				<td><a class="btn btn-link" href="http://localhost:8080/individual-rental-project/edit.jsp?id=${tempRental.carId}">Edit</a>|<a class="btn btn-link" href= "http://localhost:8080/individual-rental-project/delete?id=${tempRental.carId}">Delete</a></td>
        				</tr>
        			</c:forEach>
                </tbody>
            </table>
    </div>

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

  </body>
</html>