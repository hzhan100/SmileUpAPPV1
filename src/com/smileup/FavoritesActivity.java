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
import com.smileup.adapters.FavoriteListAdapter;

public class FavoritesActivity extends SherlockActivity {
	private ImageView backButton;
	private ArrayList<HashMap<String, Object>> dataList;
	private ToggleButton favoriteAllButton;
	private ListView list;
	private ActionBar mActionBar;
	private View mActionBarView;
	private TextView summayText;
	private TextView titleActionBar;
	private int[] views = { 2131034286, 2131034287, 2131034288 };

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_favorite);
		this.mActionBar = getSupportActionBar();
		this.mActionBar.setDisplayUseLogoEnabled(false);
		this.mActionBar.setDisplayShowHomeEnabled(false);
		this.mActionBar.setDisplayShowCustomEnabled(true);
		this.mActionBar.setCustomView(R.layout.actionbar_detail_activity);
		this.mActionBar.setBackgroundDrawable(getResources().getDrawable(
				android.R.color.white));
		this.mActionBarView = this.mActionBar.getCustomView();

		initActionBarViews(this.mActionBarView);

		this.list = ((ListView) findViewById(R.id.listview_favoriteactivity));

		createData();
		initList();

		this.summayText = ((TextView) findViewById(R.id.name_favoriteactivity));
		this.summayText.setText("10 Favorites");
		this.favoriteAllButton = ((ToggleButton) findViewById(R.id.favoritebutton_favoriteactivity));
		this.favoriteAllButton
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					public void onCheckedChanged(CompoundButton comButton,
							boolean checked) {
					}
				});
	}

	private void createData() {
		this.dataList = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < 10; i++) {

			HashMap<String, Object> hashmap = new HashMap<String, Object>();
			hashmap.put("headerphotoforfavorites",
					Integer.valueOf(R.drawable.smileupicon));
			hashmap.put("nameforfavorites", "NO." + String.valueOf(i));
			hashmap.put("checkBooleanforfavorites", Boolean.valueOf(true));
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
		FavoriteListAdapter localFavoriteListAdapter = new FavoriteListAdapter(
				this, this.dataList, R.layout.item_listview_favorite_activity,
				this.views);
		this.list.setAdapter(localFavoriteListAdapter);
	}

	private class MyActionBarBackButtonListener implements View.OnClickListener {
		private MyActionBarBackButtonListener() {
		}

		public void onClick(View paramView) {
			FavoritesActivity.this.finish();
		}
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.FavoritesActivity JD-Core Version: 0.6.0
 */