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
	private Context context;
	private String[] drawerTitles;
	private String[] drawerSubtitles;
	private int[] icons;
	private LayoutInflater inflater;
	
	// Constructor ------------------------------------------------------------
	public DrawerListAdapter(
			Context context,
			String[] drawerTitles,
			String[] drawerSubtitles,
			int[] icons){
		this.context = context;
		this.drawerTitles = drawerTitles;
		this.drawerSubtitles = drawerSubtitles;
		this.icons = icons;
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
	
		// Inflate the layout
		inflater = (LayoutInflater)context.getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.drawer_list_item, parent);
				
		// Get the views from the drawer list template
		TextView txtTitle = (TextView)itemView.findViewById
				(R.id.drawer_item_title);
		TextView txtSubtitle = (TextView)itemView.findViewById
				(R.id.drawer_item_subtitle);
		ImageView imgIcon = (ImageView)itemView.findViewById
				(R.id.drawer_item_icon);
		
		// Set the text resources and the icon image
		txtTitle.setText(drawerTitles[position]);
		txtSubtitle.setText(drawerSubtitles[position]);
		imgIcon.setImageResource(icons[position]);
		
		return itemView;
		
	}
	
}
