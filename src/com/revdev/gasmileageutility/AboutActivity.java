package com.revdev.gasmileageutility;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;

public class AboutActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		
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
		
		// Set the TextView to display the running version
		versionTextView.setText(versionTextView.getText() + versionName);
	}

}
