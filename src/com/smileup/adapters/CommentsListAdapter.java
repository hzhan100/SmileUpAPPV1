package com.smileup.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.smileup.R;
import com.smileup.customviews.RoundedImageView;

public class CommentsListAdapter extends BaseAdapter {
	public static final String Comments = "commentsforcoments";
	public static final String HeaderPhoto = "headerphotoforcomments";
	public static final String Name = "nameforcomments";
	public static final String Time = "timeforcomments";
	private Context context;
	private ArrayList<HashMap<String, Object>> datalist;
	private LayoutInflater inflater;
	private int resource;
	private int[] views;

	public CommentsListAdapter(Context paramContext,
			ArrayList<HashMap<String, Object>> datalist, int res, int[] views) {
		this.context = paramContext;
		this.datalist = datalist;
		this.resource = res;
		this.views = views;
		this.inflater = ((LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
	}

	private void fillListWithData(ViewHolder viewholder,
			ArrayList<HashMap<String, Object>> datalist, int paramInt) {

		viewholder.headerImage.setImageDrawable(this.context.getResources()
				.getDrawable(
						((Integer) datalist.get(paramInt).get(
								"headerphotoforcomments")).intValue()));
		viewholder.name.setText((String) datalist.get(paramInt).get(
				"nameforcomments"));
		viewholder.time.setText((String) datalist.get(paramInt).get(
				"timeforcomments"));
		viewholder.comments.setText((String) datalist.get(paramInt).get(
				"commentsforcoments"));

		viewholder.backButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
			}
		});
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
					.findViewById(R.id.photo_item_listview_commentsactivity));
			viewholder.name = ((TextView) convertView
					.findViewById(R.id.name_item_listview_commentsactivity));
			viewholder.comments = ((TextView) convertView
					.findViewById(R.id.comments_item_listview_commentsactivity));
			viewholder.time = ((TextView) convertView
					.findViewById(R.id.time_item_listview_commentsactivity));
			viewholder.backButton = ((Button) convertView
					.findViewById(R.id.backbutton_item_listview_commentsactivity));
			convertView.setTag(viewholder);
		} else {
			viewholder = (ViewHolder) convertView.getTag();
		}
		fillListWithData(viewholder, this.datalist, position);
		return convertView;

	}

	static class ViewHolder {
		Button backButton = null;
		TextView comments = null;
		RoundedImageView headerImage = null;
		TextView name = null;
		TextView time = null;
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.adapter.CommentsListAdapter JD-Core Version: 0.6.0
 */