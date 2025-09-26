package com.example.usedcar.model;


public class Car {
    private double mileage;
    private int year;
    private double engineSize;
    private double price; // actual price if available
	public double getMileage() {
		return mileage;
	}
	public void setMileage(double mileage) {
		this.mileage = mileage;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getEngineSize() {
		return engineSize;
	}
	public void setEngineSize(double engineSize) {
		this.engineSize = engineSize;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
    
}
