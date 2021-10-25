<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="com.skillstorm.beans.Rental"%>
<%@page import="com.skillstorm.data.RentalDAO"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ page import="java.util.*, java.io.*" %>
<%
	String carId = request.getParameter("id");
	RentalDAO doa = new RentalDAO();
	Rental rental = doa.findById(Integer.parseInt(carId));
	request.setAttribute("rental",rental);
%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Edit Rental</title>
		 <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	</head>
	<body>
	<h1 style="text-align: center;">Rental Car API</h1>
	
	<div style="width: 75%;margin: auto; border: 5px solid #b8b8b8; padding: 10px;">
			<form method="post" action="edit">
	            <div class="modal-body"> 
	                <input class="form-control" type="text" name="id" value="${rental.carId}" required>
	                <br>
	                <label class="form-label">Enter Price Per Day</label>
	                
	                <br>
	                <div class="input-group mb-3">
	                    <span class="input-group-text">$</span>
	                    <input id="pricePerDay" type="text" value="${rental.pricePerDay}" class="form-control" name="pricePerDay" aria-label="Dollar amount (with dot and two decimal places)">
	                </div>
	                <div class="mb-3">
	                    <label for="exampleFormControlInput1" class="form-label">Mileage</label>
	                    <input type="number" class="form-control" value="${rental.mileage}" id="exampleFormControlInput1" placeholder="10,000" name="mileage">
	                </div>
	                <div class="form-check form-switch">
	                    <input class="form-check-input" type="checkbox" role="switch" name="isAvailable" id="flexSwitchCheckChecked" checked>
	                    <label class="form-check-label" for="flexSwitchCheckChecked">Available</label>
	                </div>
	            </div>
	            <div class="modal-footer">
	                <a href="http://localhost:8080/individual-rental-project/index.jsp" class="btn btn-secondary" data-bs-dismiss="modal">Close</a>
	                <input type="submit" class="btn btn-primary" value="Save changes"/>
	            </div>    
	        </form>
		</div>
	</body>
</html>