package com.revdev.gasmileageutility.Data;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Build;

public abstract class SQLiteHelperBase extends SQLiteOpenHelper {
	
	// Fields -----------------------------------------------------------------
	public static String TABLE_NAME = "";
	protected static String DATABASE_NAME = "";
	protected static String DATABASE_CREATE = "";
	protected static int DATABASE_VERSION = 1;
	
	// Contructor -------------------------------------------------------------
	protected SQLiteHelperBase(Context context){
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
	@TargetApi(11)
	protected <T> void executeAsyncTask(AsyncTask<T, ?, ?> task, T... params) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
		} else {
			task.execute(params);
		}
	}
	protected abstract void initializeDatabase();
	
	
}
