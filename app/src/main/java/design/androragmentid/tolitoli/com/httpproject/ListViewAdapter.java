package design.androragmentid.tolitoli.com.httpproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
 
public class ListViewAdapter extends BaseAdapter {
 
	// Declare Variables
	Context context;
	LayoutInflater inflater;

	ImageLoader imageLoader;

	List<Population> populations;
 
	public ListViewAdapter(Context context,List<Population> populations) {
		this.context = context;
		this.populations = populations;
	}
 
	@Override
	public int getCount() {
		return populations.size();
	}
 
	@Override
	public Object getItem(int position) {
		return null;
	}
 
	@Override
	public long getItemId(int position) {
		return 0;
	}
 
	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables
		TextView rank;
		TextView country;
		TextView population;
		ImageView flag;
 
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View itemView = inflater.inflate(R.layout.listview_item, parent, false);
		// Get the position
	Population	populationVal = populations.get(position);

		// Locate the TextViews in listview_item.xml
		rank = (TextView) itemView.findViewById(R.id.rank);
		country = (TextView) itemView.findViewById(R.id.country);
		population = (TextView) itemView.findViewById(R.id.population);
 
		// Locate the ImageView in listview_item.xml
		flag = (ImageView) itemView.findViewById(R.id.flag);

		// Capture position and set results to the TextViews
		rank.setText(populationVal.getRank());
		country.setText(populationVal.getCountry());
		population.setText(populationVal.getPopulation());
		// Capture position and set results to the ImageView
		// Passes flag images URL into ImageLoader.class
		Bitmap bitmap = ImageLoader.downloadImageAsBitmap(populationVal.getFlag());

		flag.setImageBitmap(bitmap);
		// Capture ListView item click
		itemView.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				// Get the position

 
			}
		});
		return itemView;
	}
}