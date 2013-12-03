package com.smileup.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smileup.CommentsActivity;
import com.smileup.DonorsActivity;
import com.smileup.R;
import com.smileup.ShareActivity;
import com.smileup.adapters.viewdownloaders.OneThreadLoadingClass;
import com.smileup.customviews.RoundedImageView;

public class CampaignListAdapter extends BaseAdapter {
	public static final String CommentsNumber = "commentsNumber";
	public static final String Describe = "describe";
	public static final String Detials = "details";
	public static final String DonatesNumber = "donatesNumber";
	public static final String Donors = "donors";
	public static final String DonorsDonates = "DonorsDonates";
	public static final String DonorsPhotos = "DonorsPhotos";
	public static final String HearderImage = "headerImage";
	public static final String MainImage = "mainImage";
	public static final String Name = "name";
	public static final String SharesNumber = "sharesNumber";
	public static final String Time = "time";
	private Activity activity;
	private ArrayList<HashMap<String, Object>> dataList;
	private LayoutInflater inflater;
	private String[] keys;
	private OneThreadLoadingClass thread;
	private int viewResource;
	private int[] viewsIds;

	public CampaignListAdapter(Activity activity,
			ArrayList<HashMap<String, Object>> datalist, int res,
			String[] keys, int[] viewsIds, String threadname) {

		this.activity = activity;
		this.dataList = datalist;
		this.keys = keys;
		this.viewsIds = viewsIds;
		this.viewResource = res;

		this.thread = new OneThreadLoadingClass(activity, threadname,
				"newsphotos");
		this.inflater = ((LayoutInflater) this.activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
	}

	@Override
	public int getCount() {
		return this.dataList.size();
	}

	@Override
	public Object getItem(int paramInt) {
		return this.dataList.get(paramInt);
	}

	@Override
	public long getItemId(int paramInt) {
		return paramInt;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parentView) {

		ViewHolder viewholder = new ViewHolder();

		if (convertView == null) {

			convertView = this.inflater.inflate(this.viewResource, null);

			viewholder.headerImage = ((RoundedImageView) convertView
					.findViewById(this.viewsIds[0]));
			viewholder.name = ((TextView) convertView
					.findViewById(this.viewsIds[1]));
			viewholder.time = ((TextView) convertView
					.findViewById(this.viewsIds[2]));
			viewholder.describe = ((TextView) convertView
					.findViewById(this.viewsIds[3]));
			viewholder.mainImage = ((ImageView) convertView
					.findViewById(this.viewsIds[4]));
			viewholder.comments = ((TextView) convertView
					.findViewById(this.viewsIds[5]));
			viewholder.shares = ((TextView) convertView
					.findViewById(this.viewsIds[6]));
			viewholder.donates = ((TextView) convertView
					.findViewById(this.viewsIds[7]));
			viewholder.details = ((TextView) convertView
					.findViewById(this.viewsIds[8]));
			viewholder.commentsButton = ((LinearLayout) convertView
					.findViewById(this.viewsIds[9]));
			viewholder.favoriteButton = ((LinearLayout) convertView
					.findViewById(this.viewsIds[10]));
			viewholder.shareButton = ((LinearLayout) convertView
					.findViewById(this.viewsIds[11]));
			viewholder.donateButton = ((LinearLayout) convertView
					.findViewById(this.viewsIds[12]));
			viewholder.donorImagesContainer = ((RelativeLayout) convertView
					.findViewById(this.viewsIds[13]));
			viewholder.relativelayout = ((RelativeLayout) convertView
					.findViewById(R.id.squarelayout_item_listview_newsfragment));
			viewholder.views[0] = ((RoundedImageView) convertView
					.findViewById(R.id.donor1_item_listview_newsfragment));
			viewholder.views[1] = ((RoundedImageView) convertView
					.findViewById(R.id.donor2_item_listview_newsfragment));
			viewholder.views[2] = ((RoundedImageView) convertView
					.findViewById(R.id.donor3_item_listview_newsfragment));
			viewholder.views[3] = ((RoundedImageView) convertView
					.findViewById(R.id.donor4_item_listview_newsfragment));
			viewholder.views[4] = ((RoundedImageView) convertView
					.findViewById(R.id.donor5_item_listview_newsfragment));
			viewholder.donors = ((TextView) convertView
					.findViewById(R.id.donor6_item_listview_newsfragment));
			convertView.setTag(viewholder);
		} else {
			viewholder = (ViewHolder) convertView.getTag();
		}

		fillTheDummyDataToList(viewholder, position);
		return convertView;

	}

	private void fillTheDummyDataToList(ViewHolder viewholder, int position) {

		viewholder.headerImage
				.setImageDrawable(this.activity.getResources().getDrawable(
						((Integer) ((HashMap<String, Object>) this.dataList
								.get(position)).get("headerImage")).intValue()));
		//=========================================================================//
		//filename±ØíšÊÇuniqueµÄ
		this.thread
				.displayImage((String) ((HashMap<String, Object>) this.dataList
						.get(position)).get("mainImage"), viewholder.mainImage,
						viewholder.relativelayout);
		
		//=========================================================================//
		viewholder.name
				.setText((String) ((HashMap<String, Object>) this.dataList
						.get(position)).get("name"));
		viewholder.time
				.setText((String) ((HashMap<String, Object>) this.dataList
						.get(position)).get("time"));
		viewholder.describe
				.setText((String) ((HashMap<String, Object>) this.dataList
						.get(position)).get("describe"));
		viewholder.comments.setText(((HashMap<String, Object>) this.dataList
				.get(position)).get("commentsNumber") + " comments");
		viewholder.shares.setText(((HashMap<String, Object>) this.dataList
				.get(position)).get("sharesNumber") + " shares");
		viewholder.details
				.setText((String) ((HashMap<String, Object>) this.dataList
						.get(position)).get("details"));

		viewholder.commentsButton
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View paramView) {
						Intent intent = new Intent(
								CampaignListAdapter.this.activity,
								CommentsActivity.class);
						CampaignListAdapter.this.activity.startActivity(intent);
					}
				});

		final String name = (String) ((HashMap<String, Object>) this.dataList
				.get(position)).get("name");

		viewholder.favoriteButton
				.setOnClickListener(new View.OnClickListener() {

					public void onClick(View paramView) {

						Toast.makeText(CampaignListAdapter.this.activity,
								"Has favorited " + name, Toast.LENGTH_SHORT)
								.show();
					}
				});

		viewholder.shareButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {
				Intent intent = new Intent(CampaignListAdapter.this.activity,
						ShareActivity.class);
				CampaignListAdapter.this.activity.startActivity(intent);
			}
		});

		viewholder.donateButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {

			}

		});

		final int[] donorsMoney = (int[]) ((HashMap<String, Object>) this.dataList
				.get(position)).get("donorsDonates");
		final int[] donorsPhotos = (int[]) ((HashMap<String, Object>) this.dataList
				.get(position)).get("donorsPhotos");
		final String[] donorsName = (String[]) ((HashMap<String, Object>) this.dataList
				.get(position)).get("donors");

		viewholder.donorImagesContainer
				.setOnClickListener(new View.OnClickListener() {

					public void onClick(View view) {
						Intent intent = new Intent(
								CampaignListAdapter.this.activity,
								DonorsActivity.class);
						intent.putExtra("DonorsName", donorsName);
						intent.putExtra("DonorPhotos", donorsPhotos);
						intent.putExtra("DonorDonates", donorsMoney);
						CampaignListAdapter.this.activity.startActivity(intent);
					}
				});

		int k = 0;
		for (int i = 0; i < donorsName.length; i++) {
			if (k <= 4 && k >= 0) {
				viewholder.views[k].setImageDrawable(this.activity
						.getResources().getDrawable(donorsPhotos[k]));
				viewholder.views[k].setVisibility(View.VISIBLE);
			} else {
				if (k == 6) {
					viewholder.donors.setText("+"
							+ String.valueOf(donorsName.length - 5));
					viewholder.donors.setVisibility(View.VISIBLE);
				}
			}
			k++;
		}
	}

	static class ViewHolder {
		TextView comments = null;
		LinearLayout commentsButton = null;
		TextView describe = null;
		TextView details = null;
		LinearLayout donateButton = null;
		TextView donates = null;
		RelativeLayout donorImagesContainer = null;
		TextView donors = null;
		LinearLayout favoriteButton = null;
		RoundedImageView headerImage = null;
		ImageView mainImage = null;
		TextView name = null;
		RelativeLayout relativelayout = null;
		LinearLayout shareButton = null;
		TextView shares = null;
		TextView time = null;
		RoundedImageView[] views = new RoundedImageView[6];
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.adapter.CampaignListAdapter JD-Core Version: 0.6.0
 */