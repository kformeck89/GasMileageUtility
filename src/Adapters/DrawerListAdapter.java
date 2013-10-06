package Adapters;

import com.revdev.gasmileageutility.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerListAdapter extends BaseAdapter {

	// Fields -----------------------------------------------------------------
	private String[] drawerTitles;
	private int[] icons;
	private LayoutInflater inflater;
	
	// Constructor ------------------------------------------------------------
	public DrawerListAdapter(
			Context context,
			String[] drawerTitles,
			int[] icons){
		this.drawerTitles = drawerTitles;
		this.icons = icons;
		inflater = (LayoutInflater)context.getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
	}
	
	// Accessors --------------------------------------------------------------
	@Override
	public int getCount(){
		return drawerTitles.length;
	}
	@Override
	public Object getItem(int position){
		return drawerTitles[position];
	}
	@Override
	public long getItemId(int position){
		return position;
	}
	
	// Methods ----------------------------------------------------------------
	public View getView(int position, View convertView, ViewGroup parent){

		ViewHolder viewHolder;
		
		// If this is being instantiated for the first time
		if (convertView == null){
			
			// Inflate the layout
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(
					R.layout.drawer_list_item, parent, false);
			
			// Get the views from the drawer list template
			viewHolder.txtTitle = (TextView)convertView.findViewById(
					R.id.drawer_item_title);
			viewHolder.imgIcon = (ImageView)convertView.findViewById(
					R.id.drawer_item_icon);
			
			// Set the tag so we know this has been instantiated now
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		
		// Set the text resources and the icon image
		viewHolder.txtTitle.setText(drawerTitles[position]);
		viewHolder.imgIcon.setImageResource(icons[position]);
		
		return convertView;
		
	}
	
	// Classes ----------------------------------------------------------------
	private static class ViewHolder {
		TextView txtTitle;
		ImageView imgIcon;
	}
	
}
