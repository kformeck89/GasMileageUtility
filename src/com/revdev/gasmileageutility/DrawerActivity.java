package com.revdev.gasmileageutility;

import Adapters.DrawerListAdapter;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class DrawerActivity extends SherlockFragmentActivity {
	
	// Fields -----------------------------------------------------------------
	private Fragment dataEntryFragment;
	private ListView drawerList;
	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle drawerToggle;
	private String[] titles;
		
	// Methods ----------------------------------------------------------------
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		// Base implementation
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_activity);
		
		// Create the fragments needed
		dataEntryFragment = new DataEntryFragment();		
		
		// Get the activities views
		drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		drawerList = (ListView)findViewById(R.id.drawer_list);
		
		// Set the title of drawer and shadow of the drawer
		drawerLayout.setDrawerShadow(
				R.drawable.drawer_shadow, GravityCompat.START);
		
		// Get the icon list and set the drawer adapter and onClickListener
		titles = getResources().getStringArray(R.array.drawer_list_titles);
		int[] iconList = new int[]{
				R.drawable.new_record,
				R.drawable.find_gas_station,
				R.drawable.view_statistics
		};
		drawerList.setAdapter(
				new DrawerListAdapter(
						DrawerActivity.this,
						getResources().getStringArray(R.array.drawer_list_titles),
						iconList));
		drawerList.setOnItemClickListener(new DrawerItemClickListener());
		
		// Enable action bar up navigation and home button
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		// Action bar toggle
		drawerToggle = new ActionBarDrawerToggle(
				this,
				drawerLayout,
				R.drawable.ic_navigation_drawer,
				R.string.drawer_open,
				R.string.drawer_closed){
			public void onDrawerClosed(View view){
				super.onDrawerClosed(view);
			}
			public void onDrawerOpened(View view){
				getSupportActionBar().setTitle(getTitle());
				super.onDrawerOpened(view);
			}
		};
			
		// Set the drawer listener
		drawerLayout.setDrawerListener(drawerToggle);
		
		// If this is the first time opening this activity
		if (savedInstanceState == null){
			selectItem(0);
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		
		// Inflate the menu
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.data_entry_menu, menu);
		
		// Chain this menu up to the super class
		return super.onCreateOptionsMenu(menu);		
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch (item.getItemId()){
		
			// Action bar icon
			case android.R.id.home:{
				if (drawerLayout.isDrawerOpen(drawerList)){
					drawerLayout.closeDrawer(drawerList);
				} else {
					drawerLayout.openDrawer(drawerList);
				}
				return true;
			}
			
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
				Intent aboutIntent = new Intent(
						this, AboutActivity.class);
				startActivity(aboutIntent);
				return true;
			}
		}
		
		// By default, chain up to the super implementation
		return super.onOptionsItemSelected(item);
		
	}
	private void selectItem(int position){
		
		// Create a fragment transaction and begin the transaction
		FragmentTransaction fragTran = getSupportFragmentManager()
									   .beginTransaction();
		
		// Determine the selected item
		switch (position){
			case 0:{
				fragTran.replace(
						R.id.content_frame, dataEntryFragment);
				break;
			}
		}
		
		// Commit the transaction and set the item as checked
		fragTran.commit();
		drawerList.setItemChecked(position, true);
		
		// Set the title and close the drawer
		setTitle(titles[position]);
		drawerLayout.closeDrawer(drawerList);
		
	}
	@Override
	protected void onPostCreate(Bundle savedInstanceState){
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig){
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}
	@Override
	public void setTitle(CharSequence title){
		getSupportActionBar().setTitle(title);
	}
	
	// Classes ----------------------------------------------------------------
	private class DrawerItemClickListener 
	implements ListView.OnItemClickListener{

		@Override
		public void onItemClick(
				AdapterView<?> parent, View view, int position, long id) {
			selectItem(position);			
		}
		
	}

}
