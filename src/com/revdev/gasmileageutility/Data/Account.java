package com.revdev.gasmileageutility.Data;

public class Account {
	
	// Fields -----------------------------------------------------------------
	private String accountName;
	private int year;
	private String make;
	private String model;
	private int startingMileage;
	private int currentMileage;
	
	// Constructor ------------------------------------------------------------
	public Account(){}
	public Account(
		String accountName,
		int startingMileage){
		this.accountName = accountName;
		this.startingMileage = startingMileage;
	}
	public Account(
			String accountName,
			int year,
			String make,
			String model,
			int startingMileage,
			int currentMileage){
		this.accountName = accountName;
		this.year = year;
		this.make = make;
		this.model = model;
		this.startingMileage = startingMileage;
		this.currentMileage = currentMileage;
	}
	
	// Accessors --------------------------------------------------------------
	public String getAccountName(){
		return accountName;
	}
	public void setAccountName(String accountName){
		this.accountName = accountName;
	}
	public int getYear(){
		return year;
	}
	public void setYear(int year){
		this.year = year;
	}
	public String getMake(){
		return make;
	}
	public void setMake(String make){
		this.make = make;
	}
	public String getModel(){
		return model;
	}
	public void setModel(String model){
		this.model = model;
	}
	public int getStartingMileage(){
		return startingMileage;
	}
	public void setStartingMileage(int startingMileage){
		this.startingMileage = startingMileage;
	}
	public int getCurrentMileage(){
		return currentMileage;
	}
	public void setCurrentMileage(int currentMileage){
		this.currentMileage = currentMileage;
	}
	
}
