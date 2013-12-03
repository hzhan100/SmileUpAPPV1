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

public class GridViewAdapterForDetails2Activity extends BaseAdapter {
	public static final String HeaderPhoto = "headerphotoforgridviewofdetail2";
	public static final String Name = "nameforgridviewofdetail2";
	private Context context;
	private ArrayList<HashMap<String, Object>> datalist;
	private LayoutInflater inflater;
	private int resource;
	private OneThreadLoadingClass thread;
	private int[] views;

	public GridViewAdapterForDetails2Activity(Context context, int res,
			ArrayList<HashMap<String, Object>> datalist, int[] views) {
		this.context = context;
		this.datalist = datalist;
		this.resource = res;
		this.views = views;

		this.thread = new OneThreadLoadingClass(context,
				"GridViewForDetail2Activity", "photos");

		this.inflater = ((LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
	}

	private void fillListWithData(ViewHolder viewholder,
			ArrayList<HashMap<String, Object>> datalist, int position) {

		this.thread.displayImage(
				(String) datalist.get(position).get(
						"headerphotoforgridviewofdetail2"),
				viewholder.headerImage, viewholder.relativelayout);
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
					.findViewById(R.id.image_item_gridview_details2));
			viewholder.relativelayout = ((RelativeLayout) convertView
					.findViewById(R.id.relativelayout_item_gridview_details));
			convertView.setTag(viewholder);

		} else {
			viewholder = (ViewHolder) convertView.getTag();
		}

		fillListWithData(viewholder, this.datalist, position);
		return convertView;

	}

	static class ViewHolder {
		ImageView headerImage = null;
		TextView name = null;
		RelativeLayout relativelayout = null;
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.adapter.GridViewAdapterForDetails2Activity JD-Core Version:
 * 0.6.0
 */