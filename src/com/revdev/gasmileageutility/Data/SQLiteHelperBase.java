package com.revdev.gasmileageutility.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class SQLiteHelperBase extends SQLiteOpenHelper {
	
	// Fields -----------------------------------------------------------------
	public static String TABLE_NAME = "";
	
	protected static String DATABASE_NAME = "";
	protected static String DATABASE_CREATE = "";
	protected static int DATABASE_VERSION = 1;
	
	// Contructor -------------------------------------------------------------
	public SQLiteHelperBase(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		initializeDatabase();
	}
	
	// Methods ----------------------------------------------------------------
	// Lifecycle Callbacks
	@Override
	public void onCreate(SQLiteDatabase database){
		
		// On creation of the database,
		// run the create statement
		database.execSQL(DATABASE_CREATE);
	}
	@Override
	public void onUpgrade(
			SQLiteDatabase database,
			int oldVersion,
			int newVersion){
		
		// Drop the table if it exists
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		
		// Then call onCreate to re-create the database
		onCreate(database);
	}
	
	// Abstract Methods
	protected abstract void initializeDatabase();
	
}
