package com.revdev.gasmileageutility;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class DrawerActivity extends SherlockFragmentActivity {
	
	// Fields -----------------------------------------------------------------
	private DrawerLayout drawerLayout;
	private ListView drawerList;
		
	// Methods ----------------------------------------------------------------
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		// Base implementation
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_activity);
		
		// Get the activities views
		drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		drawerList = (ListView)findViewById(R.id.drawer_list);
		
		// Set the title of drawer
		
	}

}
