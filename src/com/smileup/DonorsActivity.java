package com.smileup;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.smileup.adapters.DonorListAdapter;

public class DonorsActivity extends SherlockActivity {
	public static final String DonorDonates = "DonorDonates";
	public static final String DonorName = "DonorsName";
	public static final String DonorPhotos = "DonorPhotos";
	private ImageView backButton;
	private ArrayList<HashMap<String, Object>> dataList;
	private int[] donates;
	private String[] donorNames;
	private int[] donorsPhotos;
	private ListView list;
	private ActionBar mActionBar;
	private View mActionBarView;
	private TextView summaryView;
	private TextView titleActionBar;
	private int[] views = { 2131034282, 2131034283, 2131034285 };

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);

		setContentView(R.layout.activity_donors);

		Intent intent = getIntent();
		this.donates = intent.getIntArrayExtra("DonorDonates");
		this.donorsPhotos = intent.getIntArrayExtra("DonorPhotos");
		this.donorNames = intent.getStringArrayExtra("DonorsName");

		this.mActionBar = getSupportActionBar();
		this.mActionBar.setDisplayUseLogoEnabled(false);
		this.mActionBar.setDisplayShowHomeEnabled(false);
		this.mActionBar.setDisplayShowCustomEnabled(true);
		this.mActionBar.setCustomView(R.layout.actionbar_detail_activity);
		this.mActionBar.setBackgroundDrawable(getResources().getDrawable(
				android.R.color.white));
		this.mActionBarView = this.mActionBar.getCustomView();

		initActionBarViews(this.mActionBarView);

		this.list = ((ListView) findViewById(R.id.listview_donors_activity));

		createDataList();
		initList();

		this.summaryView = ((TextView) findViewById(R.id.countnumber_donors_activity));
		setSummary(this.summaryView);
	}

	private void createDataList() {

		this.dataList = new ArrayList<HashMap<String, Object>>();

		if ((this.dataList != null) && (this.donates != null)
				&& (this.donorsPhotos != null) && (this.donorNames != null)) {

			for (int i = 0; i < this.donorNames.length; i++) {
				HashMap<String, Object> localHashMap = new HashMap<String, Object>();
				localHashMap.put("headerphotofordonors",
						Integer.valueOf(this.donorsPhotos[i]));
				localHashMap.put("namefordonors", this.donorNames[i]);
				localHashMap.put("moneyfordonors", "$" + this.donates[i]);
				this.dataList.add(localHashMap);

			}
		}
	}

	private void initActionBarViews(View view) {

		this.backButton = ((ImageView) view
				.findViewById(R.id.backbutton_actionbar_detail_activity));
		this.backButton.setOnClickListener(new MyActionBarBackButtonListener());
		this.titleActionBar = ((TextView) view
				.findViewById(R.id.title_actionbar_detail_activity));
		this.titleActionBar.setText("Share");
	}

	private void initList() {
		DonorListAdapter listAdapter = new DonorListAdapter(this,
				this.dataList, R.layout.item_listview_donors_activity,
				this.views);
		this.list.setAdapter(listAdapter);
	}

	private void setSummary(TextView paramTextView) {
		paramTextView.setText("10 Donated");
	}

	private class MyActionBarBackButtonListener implements View.OnClickListener {
		private MyActionBarBackButtonListener() {
		}

		public void onClick(View paramView) {
			DonorsActivity.this.finish();
		}
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.DonorsActivity JD-Core Version: 0.6.0
 */