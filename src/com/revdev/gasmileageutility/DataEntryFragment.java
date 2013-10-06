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

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.revdev.gasmileageutility.Data.MileageRecord;

public class DataEntryFragment extends SherlockFragment {

	// Fields -----------------------------------------------------------------
	private Button btnSubmitData;
	private EditText txtCurrentMileage;
	private EditText txtGallons;
	
	// Constructors -----------------------------------------------------------
	@Override
	public View onCreateView(
			LayoutInflater inflater,
			ViewGroup container,
			Bundle savedInstanceState) {
		
		// Base implementation		
		super.onCreate(savedInstanceState);
		View rootView = inflater.inflate(
				R.layout.data_entry_fragment, container, false);
		
		// Get the UI components
		btnSubmitData = (Button)rootView.findViewById
				(R.id.btnEnterMileageInfo);
		txtCurrentMileage = (EditText)rootView.findViewById
				(R.id.txtCurrentMileage);
		txtGallons = (EditText)rootView.findViewById
				(R.id.txtGallonsFilled);
		
		// Wire up the click event on the submit button
		btnSubmitData.setOnClickListener(submitButtonClickListener);
		
		// Create the dataSource object to interact with the database
		
		return rootView;
	}
	
	// Event Listeners --------------------------------------------------------
	private OnClickListener submitButtonClickListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			
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
						getActivity(),
						exception.getMessage(),
						Toast.LENGTH_SHORT).show();
			}
				
			// Let the user know that the data was submitted correctly
			Toast.makeText(
					getActivity(), 
					"Mileage data submitted!", 
					Toast.LENGTH_SHORT).show();		
		}
		
	};
	
	// Methods ----------------------------------------------------------------

}
