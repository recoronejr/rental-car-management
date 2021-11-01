package com.skillstorm.beans;

// POJO: Plain Old Java Object 

public class Rental {
	
	private int carId;
	private int vehicleId;
	private int mileage;
	private boolean isAvailable;
	private double pricePerDay;
	private int year;
	private String make;
	private String model;
	private String type;
	private String boolInText;
	
	
	public Rental() {
		super();
	}

	
	public Rental(int vehicleId, int mileage, boolean isAvailable, double pricePerDay) {
		super();
		this.vehicleId = vehicleId;
		this.mileage = mileage;
		this.isAvailable = isAvailable;
		this.pricePerDay = pricePerDay;
	}


	public Rental(int carId, int mileage, boolean isAvailable, double pricePerDay, int year, String make, String model) {
		super();
		this.carId = carId;
		this.mileage = mileage;
		this.isAvailable = isAvailable;
		this.pricePerDay = pricePerDay;
		this.year = year;
		this.make = make;
		this.model = model;
	}
	
	
	public void setCarId(int carId) {
		this.carId = carId;
	}


	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getCarId() {
		return carId;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public void convertToBoolAndSet(String str) {
		if (str == null) {
			this.setAvailable(false);
		} else {
			this.setAvailable(true);
		}
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public double getPricePerDay() {
		return pricePerDay;
	}
	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

	@Override
	public String toString() {
		return "Rental [carId=" + carId + ", mileage=" + mileage + ", isAvailable="
				+ isAvailable + ", pricePerDay=" + pricePerDay + ", year=" + year + ", make=" + make + ", model="
				+ model + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + carId;
		result = prime * result + (isAvailable ? 1231 : 1237);
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + mileage;
		long temp;
		temp = Double.doubleToLongBits(pricePerDay);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + year;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rental other = (Rental) obj;
		if (carId != other.carId)
			return false;
		if (isAvailable != other.isAvailable)
			return false;
		if (make == null) {
			if (other.make != null)
				return false;
		} else if (!make.equals(other.make))
			return false;
		if (mileage != other.mileage)
			return false;
		if (Double.doubleToLongBits(pricePerDay) != Double.doubleToLongBits(other.pricePerDay))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	
	
	

}
