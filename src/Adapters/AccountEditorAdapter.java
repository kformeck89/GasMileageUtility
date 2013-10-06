package Adapters;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.revdev.gasmileageutility.R;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

public class AccountEditorAdapter extends FragmentStatePagerAdapter {
	
	// Fields -----------------------------------------------------------------
	Fragment[] fragmentArray = null;
	String[] pagerTitleArray = null;

	// Constructor ------------------------------------------------------------
	public AccountEditorAdapter(
			SherlockFragmentActivity context, Fragment[] fragmentArray) {
		super(context.getSupportFragmentManager());
		this.fragmentArray = fragmentArray;
		pagerTitleArray = context.getResources()
								 .getStringArray(
										 R.array.account_editor_view_titles);
	}

	// Methods ----------------------------------------------------------------
	@Override
	public Fragment getItem(int position) {
		return fragmentArray[position];
	}
	@Override
	public int getCount() {
		return 3;
	}
	@Override
	public String getPageTitle(int position) {
		return pagerTitleArray[position];
	}
	

}
