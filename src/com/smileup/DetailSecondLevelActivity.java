package com.smileup;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.smileup.adapters.GridViewAdapterForDetails2Activity;

public class DetailSecondLevelActivity extends SherlockActivity {
	public static final String PhotosNames = "photos_names";
	public static final String Title = "title";
	private ImageView backButton;
	private GridView gridview;
	private Handler handler;
	private ActionBar mActionBar;
	private View mActionBarView;
	private String[] photos = null;
	private ArrayList<HashMap<String, Object>> photosList;
	private HandlerThread thread;
	private String title = null;
	private TextView titleActionBar;

	@Override
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);

		Intent localIntent = getIntent();
		this.photos = localIntent.getStringArrayExtra("photos_names");
		this.title = localIntent.getStringExtra("title");

		setContentView(R.layout.activity_details2);

		this.mActionBar = getSupportActionBar();
		this.mActionBar.setDisplayUseLogoEnabled(false);
		this.mActionBar.setDisplayShowHomeEnabled(false);
		this.mActionBar.setDisplayShowCustomEnabled(true);
		this.mActionBar.setCustomView(R.layout.actionbar_detail2_activity);
		this.mActionBar.setBackgroundDrawable(getResources().getDrawable(
				android.R.color.white));
		this.mActionBarView = this.mActionBar.getCustomView();
		initActionBarButton(this.mActionBarView);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (thread != null)
			this.thread.quit();
	}

	@Override
	protected void onResume() {
		super.onResume();

		this.thread = new HandlerThread("OnGoing Fragment");
		this.thread.start();

		this.handler = new Handler(this.thread.getLooper()) {

			public void handleMessage(Message msg) {

				if (!DetailSecondLevelActivity.this.isFinishing())
					DetailSecondLevelActivity.this
							.runOnUiThread(new DetailSecondLevelActivity.updateDisplay());
			}
		};

		this.photosList = new ArrayList<HashMap<String, Object>>();
		createDiscoverList();

		this.gridview = ((GridView) findViewById(R.id.gridview_detail2_activity));
		this.gridview.setOnItemClickListener(new MyGridItemClickListener());
		this.handler.sendMessage(new Message());
	}

	private void createDiscoverList() {
		if ((this.photosList != null) && (this.photos != null)) {

			for (int i = 0; i < this.photos.length; i++) {
				HashMap<String, Object> hashmap = new HashMap<String, Object>();
				hashmap.put("headerphotoforgridviewofdetail2", this.photos[i]);
				this.photosList.add(hashmap);
			}
		}
	}

	private void initActionBarButton(View paramView) {
		this.backButton = ((ImageView) paramView.findViewById(R.id.backbutton_actionbar_detail2_activity));
		this.backButton.setOnClickListener(new MyActionBarBackButtonListener());
		this.titleActionBar = ((TextView) paramView.findViewById(R.id.title_actionbar_detail2_activity));
		if (this.title != null)
			this.titleActionBar.setText(this.title);
	}

	private class MyActionBarBackButtonListener implements View.OnClickListener {

		public void onClick(View paramView) {
			DetailSecondLevelActivity.this.finish();
		}
	}

	class MyGridItemClickListener implements AdapterView.OnItemClickListener {

		public void onItemClick(AdapterView<?> paramAdapterView,
				View paramView, int paramInt, long paramLong) {
		}

	}

	class updateDisplay implements Runnable {

		public void run() {

			GridViewAdapterForDetails2Activity listAdapter = new GridViewAdapterForDetails2Activity(
					DetailSecondLevelActivity.this,
					R.layout.item_gridview_details2_activity,
					DetailSecondLevelActivity.this.photosList, null);
			DetailSecondLevelActivity.this.gridview.setAdapter(listAdapter);
//			DetailSecondLevelActivity.this.gridview
//					.setOnItemClickListener(new DetailSecondLevelActivity.MyGridItemClickListener());
			DetailSecondLevelActivity.this.gridview.invalidate();
		}
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.DetailSecondLevelActivity JD-Core Version: 0.6.0
 */