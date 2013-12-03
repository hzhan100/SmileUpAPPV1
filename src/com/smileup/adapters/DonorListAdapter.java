package com.smileup.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smileup.R;
import com.smileup.customviews.RoundedImageView;

public class DonorListAdapter extends BaseAdapter {
	public static final String HeaderPhoto = "headerphotofordonors";
	public static final String Money = "moneyfordonors";
	public static final String Name = "namefordonors";
	private Context context;
	private ArrayList<HashMap<String, Object>> datalist;
	private LayoutInflater inflater;
	private int resource;
	private int[] views;

	public DonorListAdapter(Context context,
			ArrayList<HashMap<String, Object>> datalist, int res, int[] views) {

		this.context = context;
		this.datalist = datalist;
		this.resource = res;
		this.views = views;

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

	public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
		ViewHolder viewholder = new ViewHolder();
		if (paramView == null) {
			paramView = this.inflater.inflate(this.resource, null);
			viewholder.headerImage = ((RoundedImageView) paramView
					.findViewById(R.id.photo_item_listview_donorsactivity));
			viewholder.name = ((TextView) paramView.findViewById(R.id.name_item_lisview_donorsactivity));
			viewholder.money = ((TextView) paramView.findViewById(R.id.money_item_listview_donorsactivity));
			paramView.setTag(viewholder);
		} else {
			viewholder = (ViewHolder) paramView.getTag();
		}

		fillListWithData(viewholder, this.datalist, paramInt);
		return paramView;

	}

	private void fillListWithData(ViewHolder viewholder,
			ArrayList<HashMap<String, Object>> datalist, int position) {

		viewholder.headerImage.setImageDrawable(this.context.getResources()
				.getDrawable(
						((Integer) datalist.get(position).get(
								"headerphotofordonors"))));
		viewholder.name.setText((String) datalist.get(position).get(
				"namefordonors"));
		viewholder.money.setText((String) datalist.get(position).get(
				"moneyfordonors"));
	}
	static class ViewHolder {
		RoundedImageView headerImage = null;
		TextView money = null;
		TextView name = null;
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.adapter.DonorListAdapter JD-Core Version: 0.6.0
 */