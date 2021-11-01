package com.skillstorm.beans;

import java.util.Objects;

public class Vehicle {
	private int vehicleId;
	private int vehicleTypeId;
	private int year;
	private String make;
	private String model;
	
	
	
	
	public Vehicle() {
		super();
	}
	

	
	public Vehicle(int vehicleId, int vehicleTypeId, int year, String make, String model) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleTypeId = vehicleTypeId;
		this.year = year;
		this.make = make;
		this.model = model;
	}



	public int getVehicleTypeId() {
		return vehicleTypeId;
	}
	public void setVehicleTypeId(int vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}



	
	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", vehicleTypeId=" + vehicleTypeId + ", year=" + year + ", make="
				+ make + ", model=" + model + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(make, model, vehicleId, vehicleTypeId, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		return Objects.equals(make, other.make) && Objects.equals(model, other.model) && vehicleId == other.vehicleId
				&& vehicleTypeId == other.vehicleTypeId && year == other.year;
	}
	
	
	

}
