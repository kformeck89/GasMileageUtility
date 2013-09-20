package com.revdev.gasmileageutility.Data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public abstract class DataSourceBase {

	// Fields -----------------------------------------------------------------
	protected Context context;
	protected SQLiteDatabase database;
	protected SQLiteHelperBase dbHelper;
	protected String[] allColumns;
	
	// Constructor ------------------------------------------------------------
	public DataSourceBase(Context context){
		this.context = context;
		initializeAllColumns();
	}
	
	// Methods ----------------------------------------------------------------
	protected abstract void initializeAllColumns();
	public void open() throws SQLException{
		try{
			database = dbHelper.getWritableDatabase();	
		}catch(SQLException exception){
			Toast.makeText(
					context, 
					exception.getMessage(), 
					Toast.LENGTH_SHORT).show();
		}
	}
	public void close(){
		dbHelper.close();
	}
	
}
