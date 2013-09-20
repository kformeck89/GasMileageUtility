package com.revdev.gasmileageutility.Data;

import android.content.Context;

public class MileageRecordsSQLiteHelper extends SQLiteHelperBase {
	
	// Fields -----------------------------------------------------------------
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_ACCOUNT = "account";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_CURRENT_MILEAGE = "current_mileage";
	public static final String COLUMN_GALLONS_FILLED = "gallons_filled";
	
	// Constructor ------------------------------------------------------------
	public MileageRecordsSQLiteHelper(Context context){
		super(context);
	}
	
	// Methods ----------------------------------------------------------------
	public void initializeDatabase(){
		
		// Initialize the base column names
		TABLE_NAME = "MileageRecords";
		DATABASE_NAME = "mileageRecords.db";
		DATABASE_VERSION = 1;
		
		// Initialize the create statement
		DATABASE_CREATE = 
				"create table " + TABLE_NAME + "(" + 
				COLUMN_ID + " integer primary key autoincrement, " +
				COLUMN_ACCOUNT + " text not null, " + 
				COLUMN_DATE + " text not null, " + 
				COLUMN_CURRENT_MILEAGE + " int not null, " + 
				COLUMN_GALLONS_FILLED + " int not null);";
	}

}
