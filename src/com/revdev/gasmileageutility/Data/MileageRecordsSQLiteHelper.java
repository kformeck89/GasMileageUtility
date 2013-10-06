package com.revdev.gasmileageutility.Data;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;

public class MileageRecordsSQLiteHelper extends SQLiteHelperBase {
	
	// Fields -----------------------------------------------------------------
	private static MileageRecordsSQLiteHelper singleton = null;
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_ACCOUNT = "account";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_CURRENT_MILEAGE = "current_mileage";
	public static final String COLUMN_GALLONS_FILLED = "gallons_filled";
	
	// Constructor ------------------------------------------------------------
	private MileageRecordsSQLiteHelper(Context context) {
		super(context);
	}
	
	// Methods ----------------------------------------------------------------
	synchronized static MileageRecordsSQLiteHelper getInstance(Context context) {
		if (singleton == null) {
			singleton = new MileageRecordsSQLiteHelper(context);
		}
		return singleton;
	}
	protected void initializeDatabase(){
		
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
	public void InsertRecord(MileageRecord record) {
		this.executeAsyncTask(new InsertRecordTask(), record);
	}
	
	// Classes ----------------------------------------------------------------
	private class InsertRecordTask 
	extends AsyncTask<MileageRecord, Void, Integer> {
		@Override
		protected Integer doInBackground(MileageRecord... record) {
			
			// Put the record into a content values object
			ContentValues values = new ContentValues();
			values.put(COLUMN_ACCOUNT, record[0].getAccount());
			values.put(COLUMN_DATE, record[0].getDate());
			values.put(COLUMN_CURRENT_MILEAGE, record[0].getCurrentMileage());
			values.put(COLUMN_GALLONS_FILLED, record[0].getGallonsFilled());
			
			// Insert the row into the database
			getWritableDatabase().insert(TABLE_NAME, null, values);
			
			return null;
		}
	}

}
