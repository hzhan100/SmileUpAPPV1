package com.smileup.fragments;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.smileup.R;
import com.smileup.adapters.CampaignListAdapter;
import com.smileup.dummycode.HardCodes;

public class FragmentNewsForMain extends Fragment {
	private ListView listview;
	private ArrayList<HashMap<String, Object>> newsList;
	private View view;
	private int[] viewIds = { R.id.headprotrait_item_listview_newsfragment,
			R.id.name_item_listview_newsfragment,
			R.id.time_item_listview_newsfragment,
			R.id.describe_item_listview_newsfragment,
			R.id.image_item_listview_newsfragment,
			R.id.comments_item_listview_newsfragment,
			R.id.share_item_listview_newsfragment,
			R.id.donate_item_listview_newsfragment,
			R.id.details_item_listview_newsfragment,
			R.id.comment_button_item_listview_newsfragment,
			R.id.favorite_button_item_listview_newsfragment,
			R.id.share_button_item_listview_newsfragment,
			R.id.donate_button_item_listview_newsfragment,
			R.id.photos_layout_item_listview_newsfragment };

	@Override
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle paramBundle) {

		this.view = inflater.inflate(R.layout.fragment_news_main,
				parent, false);

		this.listview = ((ListView) this.view
				.findViewById(R.id.listview_news_fragment));
		this.newsList = new ArrayList<HashMap<String, Object>>();
		
		createNewsList();
		
		CampaignListAdapter listAdapter = new CampaignListAdapter(
				getActivity(), this.newsList,
				R.layout.item_listview_news_fragment, null, this.viewIds,
				"NewsImageLoading");
		this.listview.setAdapter(listAdapter);
		return this.view;
	}
	
	
	

	@SuppressLint("SimpleDateFormat")
	private void createNewsList() {
		if (this.newsList != null) {
			for (int i = 0; i < HardCodes.NewsUsers.length; i++) {

				HashMap<String, Object> hashmap = new HashMap<String, Object>();

				hashmap.put("headerImage",
						Integer.valueOf(HardCodes.NewsUsersPhotos[i]));
				hashmap.put("name", HardCodes.NewsUsers[i]);

				int d = i + 1;
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.DAY_OF_MONTH, d);

				Date date = new Date(calendar.getTimeInMillis());
				hashmap.put("time",
						new SimpleDateFormat("yyyy-MM-dd").format(date)
								.toString());

				hashmap.put("describe", "Hello");
				hashmap.put("mainImage", HardCodes.NewsPhotos[i]);
				hashmap.put("commentsNumber", "12");
				hashmap.put("sharesNumber", "12321");
				hashmap.put("donorsDonates", HardCodes.usersDonates);
				hashmap.put("donorsPhotos", HardCodes.DonorsPhotos);
				hashmap.put("donors", HardCodes.DonorsNames);
				hashmap.put("details", HardCodes.NewsIntros[i]);

				this.newsList.add(hashmap);
			}
		}
	}
}
