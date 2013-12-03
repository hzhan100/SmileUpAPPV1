package com.smileup.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smileup.R;
import com.smileup.adapters.viewdownloaders.OneThreadLoadingClass;

public class GridViewAdapterForDetailActivity extends BaseAdapter {
	public static final String HeaderPhoto = "headerphotoforgridviewofdetail";
	public static final String Name = "nameforgridviewofdetail";
	private Context context;
	private ArrayList<HashMap<String, Object>> datalist;
	private LayoutInflater inflater;
	private int resource;
	private OneThreadLoadingClass imageLoader;
	private int[] views;

	public GridViewAdapterForDetailActivity(Context paramContext, int paramInt,
			ArrayList<HashMap<String, Object>> paramArrayList,
			int[] paramArrayOfInt) {
		this.context = paramContext;
		this.datalist = paramArrayList;
		this.resource = paramInt;
		this.views = paramArrayOfInt;

		this.imageLoader = new OneThreadLoadingClass(paramContext,
				"GridViewForDetailActivity", "logo");

		this.inflater = ((LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
	}

	public int getCount() {
		return this.datalist.size();
	}

	public Object getItem(int paramInt) {
		return this.datalist.get(paramInt);
	}

	public long getItemId(int paramInt) {
		return paramInt;
	}

	public View getView(int position, View convertView, ViewGroup parentView) {

		ViewHolder viewholder = new ViewHolder();

		if (convertView == null) {

			convertView = this.inflater.inflate(this.resource, null);

			viewholder.headerImage = ((ImageView) convertView
					.findViewById(R.id.image_item_gridview_details));
			viewholder.name = ((TextView) convertView
					.findViewById(R.id.title_item_gridview_details));
			viewholder.relativelayout = ((RelativeLayout) convertView
					.findViewById(R.id.relativelayout_item_gridview_details));

			convertView.setTag(viewholder);
		} else {
			viewholder = (ViewHolder) convertView.getTag();
		}

		fillListWithData(viewholder, this.datalist, position);
		return convertView;

	}

	private void fillListWithData(ViewHolder viewholder,
			ArrayList<HashMap<String, Object>> datalist, int position) {

		this.imageLoader.displayImage(
				(String) ((HashMap<String, Object>) datalist.get(position))
						.get("headerphotoforgridviewofdetail"),
				viewholder.headerImage, viewholder.relativelayout);

		viewholder.name.setText((String) ((HashMap<String, Object>) datalist
				.get(position)).get("nameforgridviewofdetail"));
	}

	static class ViewHolder {
		ImageView headerImage = null;
		TextView name = null;
		RelativeLayout relativelayout = null;
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.adapter.GridViewAdapterForDetailActivity JD-Core Version:
 * 0.6.0
 */