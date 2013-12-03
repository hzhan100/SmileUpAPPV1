package com.smileup.adapters;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smileup.R;
import com.smileup.adapters.viewdownloaders.OneThreadLoadingClass;

public class DiscoverListAdapter extends BaseAdapter {
	public static final String HeaderPhoto = "headerphotofordiscover";
	public static final String Name = "namefordiscover";
	private Context context;
	private ArrayList<HashMap<String, Object>> datalist;
	private LayoutInflater inflater;
	private int resource;
	private OneThreadLoadingClass imageLoader;
	private int[] views;

	public DiscoverListAdapter(Context context, int res,
			ArrayList<HashMap<String, Object>> datalist, int[] views) {

		this.context = context;
		this.datalist = datalist;
		this.resource = res;
		this.views = views;

		this.inflater = ((LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE));

		this.imageLoader = new OneThreadLoadingClass(context,
				"DiscoverLoading", "drawable");
	}

	@Override
	public int getCount() {
		return this.datalist.size();
	}

	@Override
	public Object getItem(int position) {
		return this.datalist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parentView) {

		ViewHolder viewholder = new ViewHolder();

		if (convertView == null) {

			convertView = this.inflater.inflate(this.resource, null);

			viewholder.headerImage = ((ImageView) convertView
					.findViewById(R.id.image_item_gridview_discover_fragment));
			viewholder.name = ((TextView) convertView
					.findViewById(R.id.title_item_gridview_discover_fragment));
			viewholder.relativelayout = ((RelativeLayout) convertView
					.findViewById(R.id.relativelayout_item_gridview_discover_fragment));

			convertView.setTag(viewholder);
		} else {
			viewholder = (ViewHolder) convertView.getTag();

		}

		fillListWithData(viewholder, this.datalist, position);

		return convertView;
	}

	class ViewHolder {
		ImageView headerImage = null;
		TextView name = null;
		RelativeLayout relativelayout = null;
	}

	private void fillListWithData(ViewHolder viewholder,
			ArrayList<HashMap<String, Object>> datalist, int position) {

		this.imageLoader.displayImage((String) (datalist.get(position))
				.get(DiscoverListAdapter.HeaderPhoto), viewholder.headerImage,
				viewholder.relativelayout);
		viewholder.name.setText((String) (datalist.get(position))
				.get(DiscoverListAdapter.Name));
		
		Log.v("fillListWithDataInDiscoverList", (String) datalist.get(position)
				.get(DiscoverListAdapter.Name));
	}

	public void stopThread() {
		if (imageLoader != null) {
			imageLoader.stopSelf();
		}
	}

	public static Drawable getAssetImage(Context context, String name)
			throws IOException {
		Bitmap localBitmap = BitmapFactory
				.decodeStream(new BufferedInputStream(context.getResources()
						.getAssets().open("drawable/" + name + ".png")));
		return new BitmapDrawable(context.getResources(), localBitmap);
	}

}
