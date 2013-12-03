package com.smileup.fragments;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.smileup.FavoritesActivity;
import com.smileup.FollowersActivity;
import com.smileup.FollowingActivity;
import com.smileup.R;
import com.smileup.adapters.CampaignListAdapter;
import com.smileup.customviews.LinearSquareLayout;
import com.smileup.dummycode.HardCodes;

public class FragmentProfileForMain extends Fragment {
	private LinearSquareLayout campaignsButton;
	private LinearSquareLayout favoritesButton;
	private LinearSquareLayout followersButton;
	private LinearSquareLayout followingsButton;
	private ListView list;
	private CampaignListAdapter listAdapter;
	private ArrayList<HashMap<String, Object>> mycampaignsList;
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

	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
	}

	public View onCreateView(LayoutInflater paramLayoutInflater,
			ViewGroup paramViewGroup, Bundle paramBundle) {

		this.view = paramLayoutInflater.inflate(R.layout.fragment_profile_main,
				paramViewGroup, false);
		this.list = ((ListView) this.view
				.findViewById(R.id.listview_profile_fragment));
		this.followersButton = ((LinearSquareLayout) this.view
				.findViewById(R.id.followers_button_profilefragment));
		this.followingsButton = ((LinearSquareLayout) this.view
				.findViewById(R.id.followings_button_profilefragment));
		this.campaignsButton = ((LinearSquareLayout) this.view
				.findViewById(R.id.champaigns_button_profilefragment));
		this.favoritesButton = ((LinearSquareLayout) this.view
				.findViewById(R.id.favorites_button_profilefragment));

		setButtonsListeners();

		this.mycampaignsList = new ArrayList<HashMap<String, Object>>();

		createNewsList();

		this.listAdapter = new CampaignListAdapter(getActivity(),
				this.mycampaignsList, R.layout.item_listview_news_fragment,
				null, this.viewIds, "ProfileImageLoading");

		this.list.setAdapter(this.listAdapter);

		return this.view;
	}

	public void onDestroyView() {
		super.onDestroyView();
	}

	private void createNewsList() {
		if (this.mycampaignsList != null) {
			for (int i = 0; i < HardCodes.ProfileNames.length; i++) {

				HashMap<String, Object> localHashMap = new HashMap<String, Object>();

				localHashMap.put("headerImage",
						Integer.valueOf(HardCodes.ProfilePhotos[i]));
				localHashMap.put("name", HardCodes.ProfileNames[i]);

				Calendar localCalendar = Calendar.getInstance();
				localCalendar.set(Calendar.DAY_OF_MONTH, i + 1);
				Date localDate = new Date(localCalendar.getTimeInMillis());
				localHashMap.put("time", new SimpleDateFormat("yyyy-MM-dd")
						.format(localDate).toString());
				localHashMap.put("describe", "Hello");
				localHashMap.put("mainImage",
						HardCodes.ProfileMainImageNames[i]);
				localHashMap.put("commentsNumber", "12");
				localHashMap.put("sharesNumber", "12321");
				localHashMap.put("DonorsDonates", HardCodes.usersDonates);
				localHashMap.put("donorsPhotos", HardCodes.DonorsPhotos);
				localHashMap.put("donors", HardCodes.DonorsNames);
				localHashMap.put("details", HardCodes.ProfileDetails[i]);
				this.mycampaignsList.add(localHashMap);
			}
		}
	}

	private void setButtonsListeners() {

		this.followersButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {
				Intent intent = new Intent(FragmentProfileForMain.this
						.getActivity(), FollowersActivity.class);
				FragmentProfileForMain.this.getActivity().startActivity(intent);
			}
		});

		this.followingsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {
				Intent intent = new Intent(FragmentProfileForMain.this
						.getActivity(), FollowingActivity.class);
				FragmentProfileForMain.this.getActivity().startActivity(intent);
			}
		});

		this.campaignsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {
			}
		});

		this.favoritesButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {
				Intent intent = new Intent(FragmentProfileForMain.this
						.getActivity(), FavoritesActivity.class);
				FragmentProfileForMain.this.getActivity().startActivity(intent);
			}
		});
	}

	class FirstLoading implements Runnable {
		public void run() {
		}
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.fragment.FragmentProfileForMain JD-Core Version: 0.6.0
 */