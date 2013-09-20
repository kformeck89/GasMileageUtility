/*
 * MileageRecord.java
 * 
 * v1.0.0
 * 
 * This class represents a single mileage record in the database including
 * fields for the date of record, the previous and the current mileage for
 * the given account.
 *
 * 09/08/2013
 * 
 */

package com.revdev.gasmileageutility.Data;

public class MileageRecord {

	// Fields -----------------------------------------------------------------
	private String account;
	private String date;
	private int currentMileage;
	private int gallonsFilled;
	
	// Constructor ------------------------------------------------------------
	public MileageRecord(){}
	public MileageRecord(
			String account, String date, 
			int currentMileage, int gallonsFilled){
		this.account = account;
		this.date = date;
		this.currentMileage = currentMileage;
		this.gallonsFilled = gallonsFilled;
	}	
	
	// Accessors --------------------------------------------------------------
	public String getAccount(){
		return account;
	}
	public void setAccount(String account){
		this.account = account;
	}
	public String getDate(){
		return date;
	}
	public void setDate(String date){
		this.date = date;
	}
	public int getCurrentMileage(){
		return currentMileage;
	}
	public void setCurrentMileage(int currentMileage){
		this.currentMileage = currentMileage;
	}
	public int getGallonsFilled(){
		return gallonsFilled;
	}
	public void setGallonsFilled(int gallonsFilled){
		this.gallonsFilled = gallonsFilled;
	}
	
}
