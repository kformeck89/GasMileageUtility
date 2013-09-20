/*
 * AccountDataSource.java
 * 
 * v1.0.0
 * 
 * This class provides an interface to the Account.db SQLite database which 
 * allows quick and easy access to opens, closes, reads, updates, inserts, 
 * deletes and most basic SQL functions.
 *
 * 09/08/2013
 * 
 */

package com.revdev.gasmileageutility.Data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.widget.Toast;

public class MileageRecordsDataSource extends DataSourceBase {
	
	// Constructor ------------------------------------------------------------
	public MileageRecordsDataSource(Context context){
		super(context);
		dbHelper = new MileageRecordsSQLiteHelper(context);
	}
	
	// Methods ----------------------------------------------------------------
	protected void initializeAllColumns(){
		
		// Initialize the array of column names
		allColumns = new String[]{
				MileageRecordsSQLiteHelper.COLUMN_ID,
				MileageRecordsSQLiteHelper.COLUMN_ACCOUNT,
				MileageRecordsSQLiteHelper.COLUMN_DATE,
				MileageRecordsSQLiteHelper.COLUMN_CURRENT_MILEAGE,
				MileageRecordsSQLiteHelper.COLUMN_GALLONS_FILLED
		};
	}
	
	private MileageRecord getRecordFromCursor(Cursor cursor){
		
		/*
		 * 0 => id
		 * 1 => account
		 * 2 => date
		 * 3 => current_mileage
		 * 4 => gallons_filled
		 * 
		 */
		MileageRecord record = new MileageRecord(
				cursor.getString(1),
				cursor.getString(2),
				cursor.getInt(3),
				cursor.getInt(4));
		return record;
	}
	public void insertMileageRecord(MileageRecord record){
		
		// Put record into a KVP
		ContentValues values = new ContentValues();
		values.put(MileageRecordsSQLiteHelper.COLUMN_ACCOUNT, record.getAccount());
		values.put(MileageRecordsSQLiteHelper.COLUMN_DATE, record.getDate());
		values.put(MileageRecordsSQLiteHelper.COLUMN_CURRENT_MILEAGE, record.getCurrentMileage());
		
		try{
			
			// Insert the new row and receive the id it was inserted into
			database.insert(
					MileageRecordsSQLiteHelper.TABLE_NAME, 
					null, 
					values);	
		}catch (SQLException exception){
			Toast.makeText(
					context, 
					exception.getMessage(), 
					Toast.LENGTH_SHORT).show();
		}
	}
	public List<MileageRecord> getAllRecordsForAccount(String account)
			throws SQLException{
			
			// Create the mileage record list
			List<MileageRecord> mileageRecordList = new ArrayList<MileageRecord>();
			
			try{
				
				// Select all entries from the database for the given account name
				Cursor cursor = database.query(
						MileageRecordsSQLiteHelper.TABLE_NAME,
						allColumns,
						MileageRecordsSQLiteHelper.COLUMN_ACCOUNT + " = " + account,
						null, null, null, null);
				
				// Move the cursor to the first result
				cursor.moveToFirst();
				
				// Iterate through the results until we reach the end
				while (!cursor.isAfterLast()){
					
					// Get the record and add it to the list
					MileageRecord record = getRecordFromCursor(cursor);
					mileageRecordList.add(record);
					
					// Move to the next entry in the database
					cursor.moveToNext();
				}	
			}catch (SQLException exception){
				Toast.makeText(
						context, 
						exception.getMessage(), 
						Toast.LENGTH_SHORT).show();
			}
			
			return mileageRecordList;
		}

}
