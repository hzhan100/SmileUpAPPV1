package com.smileup.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smileup.R;
import com.smileup.adapters.viewdownloaders.OneThreadLoadingClass;

public class DiscoverGridViewAdapter extends BaseAdapter {

	private Activity activity;

	private ArrayList<HashMap<String, Object>> datalist;

	private String loadingThreadName;

	private OneThreadLoadingClass oneThreadLoading;

	private LayoutInflater inflater;

	private String[] testname = { "test1", "test2" };
	private int[] testphotos = { R.drawable.smileupicon, R.drawable.smileupicon };

	public DiscoverGridViewAdapter(Context context,
			ArrayList<HashMap<String, Object>> datalist,
			String loadingThreadName) {

		this.activity = (Activity) context;
		this.datalist = datalist;
		this.loadingThreadName = loadingThreadName;

		oneThreadLoading = new OneThreadLoadingClass(activity,
				this.loadingThreadName, "drawable");

		inflater = (LayoutInflater) this.activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		// return datalist.size();
		return testname.length;
	}

	@Override
	public Object getItem(int arg0) {
		// return datalist.get(arg0);
		return arg0;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewholder = new ViewHolder();
		if (convertView == null) {

			convertView = inflater.inflate(
					R.layout.item_gridview_discover_fragment, null);

			viewholder.categoryPhoto = (ImageView) convertView
					.findViewById(R.id.image_item_gridview_discover_fragment);
			viewholder.categoryName = (TextView) convertView
					.findViewById(R.id.title_item_gridview_discover_fragment);

			convertView.setTag(viewholder);

		} else {
			viewholder = (ViewHolder) convertView.getTag();
		}

		fillDataIntoGridViewItem(viewholder, position);

		return convertView;
	}

	private void fillDataIntoGridViewItem(ViewHolder viewholder, int position) {
		viewholder.categoryName.setText(testname[position]);
		viewholder.categoryPhoto.setImageDrawable(activity.getResources()
				.getDrawable(testphotos[position]));
	}

	private class ViewHolder {

		ImageView categoryPhoto = null;
		TextView categoryName = null;

	}

}
