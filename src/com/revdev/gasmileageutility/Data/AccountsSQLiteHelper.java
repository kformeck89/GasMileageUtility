package com.revdev.gasmileageutility.Data;

import android.content.Context;

public class AccountsSQLiteHelper extends SQLiteHelperBase {
	
	// Fields -----------------------------------------------------------------
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_ACCOUNT_NAME = "account_name";
	public static final String COLUMN_YEAR = "year";
	public static final String COLUMN_MAKE = "make";
	public static final String COLUMN_MODEL = "model";
	public static final String COLUMN_STARTING_MILEAGE = "starting_mileage";
	public static final String COLUMN_CURRENT_MILEAGE = "current_mileage"; 
	
	// Constructor ------------------------------------------------------------
	public AccountsSQLiteHelper(Context context){
		super(context);
	}
	
	// Methods ----------------------------------------------------------------
	protected void initializeDatabase(){
		
		// Initialize the base column names
		TABLE_NAME = "Accounts";
		DATABASE_NAME = "accounts.db";
		DATABASE_VERSION = 1;
		DATABASE_CREATE = 
				"create table " + TABLE_NAME + "(" +
		        COLUMN_ID + " integer primary key autoincrement, " +
				COLUMN_ACCOUNT_NAME + " text not null, " +
		        COLUMN_YEAR + " integer, " + 
				COLUMN_MAKE + " text, " +
		        COLUMN_MODEL + " text, " + 
				COLUMN_STARTING_MILEAGE + " integer not null, " +
				COLUMN_CURRENT_MILEAGE + " integer);";
	}

}
