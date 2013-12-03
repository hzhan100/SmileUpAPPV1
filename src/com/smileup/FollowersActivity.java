package com.smileup;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.smileup.adapters.FollowersListAdapter;
import com.smileup.dummycode.HardCodes;

public class FollowersActivity extends SherlockActivity {
	private ImageView backButton;
	private ArrayList<HashMap<String, Object>> dataList;
	private ToggleButton followAllButton;
	private ListView list;
	private ActionBar mActionBar;
	private View mActionBarView;
	private TextView summayText;
	private TextView titleActionBar;
	private int[] views = { 2131034292, 2131034293 };

	protected void onCreate(Bundle paramBundle) {

		super.onCreate(paramBundle);
		setContentView(R.layout.activity_follower);
		this.mActionBar = getSupportActionBar();
		this.mActionBar.setDisplayUseLogoEnabled(false);
		this.mActionBar.setDisplayShowHomeEnabled(false);
		this.mActionBar.setDisplayShowCustomEnabled(true);
		this.mActionBar.setCustomView(R.layout.actionbar_detail_activity);
		this.mActionBar.setBackgroundDrawable(getResources().getDrawable(
				android.R.color.white));
		this.mActionBarView = this.mActionBar.getCustomView();
		initActionBarViews(this.mActionBarView);

		this.list = ((ListView) findViewById(R.id.listview_followeractivity));

		createData();
		initList();

		this.summayText = ((TextView) findViewById(R.id.summary_followeractivity));
		this.summayText.setText(String.valueOf(this.dataList.size()
				+ " Followers"));
		this.followAllButton = ((ToggleButton) findViewById(R.id.followbutton_followeractivity));

		this.followAllButton
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					public void onCheckedChanged(CompoundButton comButton,
							boolean checked) {
					}
				});
	}

	private void createData() {
		this.dataList = new ArrayList<HashMap<String, Object>>();
		for (int i = 10; i < 20; i++) {

			HashMap<String, Object> hashmap = new HashMap<String, Object>();
			hashmap.put("headerphotoforfollowers",
					Integer.valueOf(HardCodes.usersPhoto[i]));
			hashmap.put("nameforfollowers", HardCodes.usersName[i]);
			hashmap.put("checkBooleanforfollowers", Boolean.valueOf(false));

			this.dataList.add(hashmap);
		}
	}

	private void initActionBarViews(View paramView) {

		this.backButton = ((ImageView) paramView
				.findViewById(R.id.backbutton_actionbar_detail_activity));

		this.backButton.setOnClickListener(new MyActionBarBackButtonListener());

		this.titleActionBar = ((TextView) paramView
				.findViewById(R.id.title_actionbar_detail_activity));

		this.titleActionBar.setText("Share");
	}

	private void initList() {
		FollowersListAdapter localFollowersListAdapter = new FollowersListAdapter(
				this, this.dataList,
				R.layout.item_listview_followerandfollowing_activity,
				this.views);
		this.list.setAdapter(localFollowersListAdapter);
	}

	private class MyActionBarBackButtonListener implements View.OnClickListener {
		private MyActionBarBackButtonListener() {
		}

		public void onClick(View paramView) {
			FollowersActivity.this.finish();
		}
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.FollowersActivity JD-Core Version: 0.6.0
 */