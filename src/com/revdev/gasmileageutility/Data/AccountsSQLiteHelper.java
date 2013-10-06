package com.revdev.gasmileageutility.Data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

public class AccountsSQLiteHelper extends SQLiteHelperBase {
	
	// Fields -----------------------------------------------------------------
	private static AccountsSQLiteHelper singleton;
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_ACCOUNT_NAME = "account_name";
	public static final String COLUMN_YEAR = "year";
	public static final String COLUMN_MAKE = "make";
	public static final String COLUMN_MODEL = "model";
	public static final String COLUMN_STARTING_MILEAGE = "starting_mileage";
	public static final String COLUMN_CURRENT_MILEAGE = "current_mileage"; 
	
	// Constructor ------------------------------------------------------------
	private AccountsSQLiteHelper(Context context){
		super(context);
	}
	
	// Methods ----------------------------------------------------------------
	synchronized static AccountsSQLiteHelper getInstance(Context context) {
		if (singleton == null) {
			singleton = new AccountsSQLiteHelper(context);
		}
		return singleton;
	}
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
	public void createNewAccount(Account account) {
		this.executeAsyncTask(new CreateNewAccountTask(), account);
	}
	public void getAllAccounts(AccountListener listener) {
		this.executeAsyncTask(new GetAllAccountsTask(listener));
	}
	
	// Interfaces -------------------------------------------------------------
	public interface AccountListener {
		void setAllAccounts(Account[] allAccounts);
	}
	
	// Classes ----------------------------------------------------------------
	private class CreateNewAccountTask
	extends AsyncTask<Account, Void, Void> {
		@Override
		protected Void doInBackground(Account...account) {
			
			// Put the Account data into a ContentValues object
			ContentValues values = new ContentValues();
			values.put(COLUMN_ACCOUNT_NAME, account[0].getAccountName());
			values.put(COLUMN_YEAR, account[0].getYear());
			values.put(COLUMN_MAKE, account[0].getMake());
			values.put(COLUMN_MODEL, account[0].getModel());
			values.put(COLUMN_STARTING_MILEAGE, account[0].getStartingMileage());
			values.put(COLUMN_CURRENT_MILEAGE, account[0].getCurrentMileage());
			
			// Insert the new account into the database
			getWritableDatabase().insert(TABLE_NAME, null, values);
			
			return null;
		}
	}
	private class GetAllAccountsTask
	extends AsyncTask<Void, Void, Account[]> {
		AccountListener listener = null;
		
		private GetAllAccountsTask(AccountListener listener) {
			this.listener = listener;
		}
		
		@Override
		protected Account[] doInBackground(Void... params) {
			List<Account> accountList = new ArrayList<Account>();
			Cursor cursor = getReadableDatabase().rawQuery(
					"SELECT * FROM " + TABLE_NAME + ";", null);
			cursor.moveToFirst();
			if (cursor.isAfterLast()) {
				return null;
			} else {
				while (!cursor.isAfterLast()) {
					Account account = new Account();
					account.setAccountName(cursor.getString(0));
					account.setYear(cursor.getInt(1));
					account.setMake(cursor.getString(2));
					account.setModel(cursor.getString(3));
					account.setStartingMileage(cursor.getInt(4));
					account.setCurrentMileage(cursor.getInt(5));
					accountList.add(account);
					cursor.moveToNext();
				}
			}
			cursor.close();
										
			return (Account[]) accountList.toArray();
		}
		@Override
		public void onPostExecute(Account[] accounts) {
			listener.setAllAccounts(accounts);
		}
	}
	
}
