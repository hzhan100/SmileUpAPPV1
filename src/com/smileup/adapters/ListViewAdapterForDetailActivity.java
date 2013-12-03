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

public class ListViewAdapterForDetailActivity extends BaseAdapter {
	public static final String HeaderPhoto = "headerphotoforlistviewofdetail";
	public static final String Name = "nameforlistviewofdetail";
	private Context context;
	private ArrayList<HashMap<String, Object>> datalist;
	private LayoutInflater inflater;
	private int resource;
	private OneThreadLoadingClass imageLoader;
	private int[] views;

	public ListViewAdapterForDetailActivity(Context context, int res,
			ArrayList<HashMap<String, Object>> datalist, int[] paramArrayOfInt) {
		this.context = context;
		this.datalist = datalist;
		this.resource = res;
		this.views = paramArrayOfInt;
		this.imageLoader = new OneThreadLoadingClass(context,
				"ListViewForDetailActivity", "logo");

		this.inflater = ((LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
	}

	public int getCount() {
		return this.datalist.size();
	}

	public Object getItem(int position) {
		return this.datalist.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parentView) {
		ViewHolder viewholder = new ViewHolder();

		if (convertView == null) {

			convertView = this.inflater.inflate(this.resource, null);

			viewholder.headerImage = ((ImageView) convertView
					.findViewById(R.id.image_item_listview_details));
			viewholder.name = ((TextView) convertView
					.findViewById(R.id.title_item_listview_details));
			viewholder.relativelayout = ((RelativeLayout) convertView
					.findViewById(R.id.relativelayout_item_listview_details));

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
						.get("headerphotoforlistviewofdetail"),
				viewholder.headerImage, viewholder.relativelayout);

		viewholder.name.setText((String) ((HashMap<String, Object>) datalist
				.get(position)).get("nameforlistviewofdetail"));
	}

	static class ViewHolder {
		ImageView headerImage = null;
		TextView name = null;
		RelativeLayout relativelayout = null;
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.adapter.ListViewAdapterForDetailActivity JD-Core Version:
 * 0.6.0
 */