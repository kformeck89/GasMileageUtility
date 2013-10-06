package com.revdev.gasmileageutility;

import Adapters.AccountEditorAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class ManageAccountsFragment extends SherlockFragment {

	// Fields -----------------------------------------------------------------
	ViewPager editorPager = null;
	
	// Methods ----------------------------------------------------------------
	@Override
	public View onCreateView(
			LayoutInflater inflater,
			ViewGroup container,
			Bundle savedInstanceState) {
		
		// Base implementation
		super.onCreateView(inflater, container, savedInstanceState);
		
		SherlockFragment[] fragmentArray = new SherlockFragment[3];
		fragmentArray[0] = new AddAccountFragment();
		fragmentArray[1] = new EditAccountFragment();
		fragmentArray[2] = new DeleteAccountFragment();
		
		// Inflate the view
		View rootView = inflater.inflate(
				R.layout.manage_accounts_fragment, container, false);
		
		// Create a new adapter and set the page up
		editorPager = (ViewPager)rootView.findViewById(
				R.id.account_editor_pager);
		editorPager.setAdapter(new AccountEditorAdapter(
				(SherlockFragmentActivity) getActivity(), fragmentArray));
		
		// Retain this instance
		setRetainInstance(true);
		
		return rootView;
	}
	
}
