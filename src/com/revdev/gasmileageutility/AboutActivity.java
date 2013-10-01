package com.revdev.gasmileageutility;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class AboutActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_activity);
		
		// Get the version text view
		TextView versionTextView = (TextView)findViewById(R.id.version);
		String versionName = "";
		
		// Get the running application version
		try{
			PackageInfo pInfo = getPackageManager().getPackageInfo(
					getPackageName(),
					0);
			versionName = pInfo.versionName;
		}catch(Exception ex){
			Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
		}
		
		// Allow up navigation on the action bar
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		// Set the TextView to display the running version
		versionTextView.setText(versionTextView.getText() + versionName);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu){		
		return super.onCreateOptionsMenu(menu);		
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
			case android.R.id.home:{
				NavUtils.navigateUpFromSameTask(this);
				return true;
			}
		}
		return super.onOptionsItemSelected(item);
	}

}
