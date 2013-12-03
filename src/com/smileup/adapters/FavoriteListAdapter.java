package com.smileup.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.smileup.R;
import com.smileup.customviews.RoundedImageView;

public class FavoriteListAdapter extends BaseAdapter {
	public static final String CheckBoolean = "checkBooleanforfavorites";
	public static final String HeaderPhoto = "headerphotoforfavorites";
	public static final String Name = "nameforfavorites";
	private Context context;
	private ArrayList<HashMap<String, Object>> datalist;
	private LayoutInflater inflater;
	private int resource;
	private int[] views;

	public FavoriteListAdapter(Context context,
			ArrayList<HashMap<String, Object>> datalist, int res, int[] views) {

		this.context = context;
		this.datalist = datalist;
		this.resource = res;
		this.views = views;

		this.inflater = ((LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
	}

	private void fillListWithData(ViewHolder viewholder,
			ArrayList<HashMap<String, Object>> datalist, int position) {

		viewholder.headerImage.setImageDrawable(this.context.getResources()
				.getDrawable(
						((Integer) datalist.get(position).get(
								"headerphotoforfavorites"))));

		viewholder.name.setText((String) datalist.get(position).get(
				"nameforfavorites"));

		viewholder.followButton
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					public void onCheckedChanged(CompoundButton comButton,
							boolean checked) {
					}
				});

		viewholder.followButton.setChecked(((Boolean) datalist.get(position)
				.get("checkBooleanforfavorites")));
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

			viewholder.headerImage = ((RoundedImageView) convertView
					.findViewById(R.id.photo_item_listview_favoriteactivity));

			viewholder.name = ((TextView) convertView
					.findViewById(R.id.name_item_lisview_favoriteactivity));

			viewholder.followButton = ((ToggleButton) convertView
					.findViewById(R.id.favoritebutton_item_listview_favoriteactivity));

			convertView.setTag(viewholder);
		} else {
			viewholder = (ViewHolder) convertView.getTag();
		}
		fillListWithData(viewholder, this.datalist, position);
		return convertView;
	}

	static class ViewHolder {
		ToggleButton followButton = null;
		RoundedImageView headerImage = null;
		TextView name = null;
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.adapter.FavoriteListAdapter JD-Core Version: 0.6.0
 */