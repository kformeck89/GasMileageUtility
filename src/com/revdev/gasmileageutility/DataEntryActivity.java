/*
 * DataEntryActivity.java
 * 
 * v1.0.0
 * 
 * This class provides the code behind the data entry activity.  The main 
 * purpose is to insert new rows into the "Accounts.db" sQLite database.
 *
 * 09/08/2013
 * 
 */

package com.revdev.gasmileageutility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.revdev.gasmileageutility.Data.MileageRecord;
import com.revdev.gasmileageutility.Data.MileageRecordsDataSource;

public class DataEntryActivity extends SherlockActivity {

	// Fields -----------------------------------------------------------------
	private Button btnSubmitData;
	private EditText txtCurrentMileage;
	private EditText txtGallons;
	private MileageRecordsDataSource dataSource;
	private ArrayAdapter<CharSequence> adapter;
	
	// Constructors -----------------------------------------------------------
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// Base implementation		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data_entry);
		
		// Get the UI components
		btnSubmitData = (Button)findViewById(R.id.btnEnterMileageInfo);
		txtCurrentMileage = (EditText)findViewById(R.id.txtCurrentMileage);
		txtGallons = (EditText)findViewById(R.id.txtGallonsFilled);
		
		// Wire up the click event on the submit button
		btnSubmitData.setOnClickListener(submitButtonClickListener);
		
		// Create the dataSource object to interact with the database
		dataSource = new MileageRecordsDataSource(this);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		
		// Inflate the menu
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.data_entry_menu, menu);
		
		// Implement the superclass' implementation
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		// Determine which action item was pressed
		switch(item.getItemId()){
		
			// Settings
			case R.id.settings:{
				return true;
			}
			
			// Help
			case R.id.help:{
				return true;
			}
			
			// About
			case R.id.about:{
				Intent aboutIntent = new Intent(this, AboutActivity.class);
				startActivity(aboutIntent);
				return true;
			}
		}
		
		// By default return the superclass' implementation
		return super.onOptionsItemSelected(item);
	}
	
	// Event Listeners --------------------------------------------------------
	private OnClickListener submitButtonClickListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			
			// Open the database
			dataSource.open();
			
			// Create a new mileage record of the data and get date for now
			MileageRecord record = new MileageRecord();
			SimpleDateFormat dateFormat = 
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
			Date now = new Date();
			String dateString = dateFormat.format(now);

			try{
				
				// Set the record details
				// TODO Get account data here
				record.setAccount("");
				record.setDate(dateString);
				record.setCurrentMileage(
						Integer.parseInt(txtCurrentMileage.toString()));	
				record.setGallonsFilled(
						Integer.parseInt(txtGallons.toString()));
			}catch(Exception exception){
				Toast.makeText(
						DataEntryActivity.this,
						exception.getMessage(),
						Toast.LENGTH_SHORT).show();
			}
				
			// Let the user know that the data was submitted correctly
			Toast.makeText(
					DataEntryActivity.this, 
					"Mileage data submitted!", 
					Toast.LENGTH_SHORT).show();		
		}
		
	};
	
	// Methods ----------------------------------------------------------------

}
