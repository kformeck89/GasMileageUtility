package com.revdev.gasmileageutility.Data;

import android.content.Context;

public class AccountsDataSource extends DataSourceBase {

	// Constructor ------------------------------------------------------------
	public AccountsDataSource(Context context){
		super(context);
		dbHelper = new AccountsSQLiteHelper(context);
	}
	
	// Methods ----------------------------------------------------------------
	protected void initializeAllColumns(){
		
		allColumns = new String[]{
			AccountsSQLiteHelper.COLUMN_ID,
			AccountsSQLiteHelper.COLUMN_ACCOUNT_NAME,
			AccountsSQLiteHelper.COLUMN_YEAR,
			AccountsSQLiteHelper.COLUMN_MAKE,
			AccountsSQLiteHelper.COLUMN_MODEL,
			AccountsSQLiteHelper.COLUMN_STARTING_MILEAGE,
			AccountsSQLiteHelper.COLUMN_CURRENT_MILEAGE
		};
	}

}
