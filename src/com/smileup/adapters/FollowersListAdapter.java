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

public class FollowersListAdapter extends BaseAdapter {
	public static final String CheckBoolean = "checkBooleanforfollowers";
	public static final String HeaderPhoto = "headerphotoforfollowers";
	public static final String Name = "nameforfollowers";
	private Context context;
	private ArrayList<HashMap<String, Object>> datalist;
	private LayoutInflater inflater;
	private int resource;
	private int[] views;

	public FollowersListAdapter(Context paramContext,
			ArrayList<HashMap<String, Object>> paramArrayList, int paramInt,
			int[] paramArrayOfInt) {
		this.context = paramContext;
		this.datalist = paramArrayList;
		this.resource = paramInt;
		this.views = paramArrayOfInt;

		this.inflater = ((LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
	}

	private void fillListWithData(ViewHolder viewholder,
			ArrayList<HashMap<String, Object>> datalist, int position) {

		viewholder.headerImage.setImageDrawable(this.context.getResources()
				.getDrawable(
						((Integer) datalist.get(position).get(
								"headerphotoforfollowers"))));

		viewholder.name.setText((String) datalist.get(position).get(
				"nameforfollowers"));

		viewholder.followButton
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton comButton,
							boolean checked) {
					}
				});

		viewholder.followButton.setChecked(((Boolean) datalist.get(position)
				.get("checkBooleanforfollowers")));
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
					.findViewById(R.id.photo_item_listview_followingandfolloweractivity));
			viewholder.name = ((TextView) convertView
					.findViewById(R.id.name_item_lisview_followingandfolloweractivity));
			viewholder.followButton = ((ToggleButton) convertView
					.findViewById(R.id.followicon_item_listview_followingandfolloweractivity));

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
 * com.smileup.appv1.adapter.FollowersListAdapter JD-Core Version: 0.6.0
 */