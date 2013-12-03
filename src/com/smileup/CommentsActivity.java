package com.smileup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.smileup.adapters.CommentsListAdapter;

public class CommentsActivity extends SherlockActivity {
	private ImageView backButton;
	private ArrayList<HashMap<String, Object>> dataList;
	private ListView list;
	private ActionBar mActionBar;
	private View mActionBarView;
	private TextView titleActionBar;
	private int[] views = { 2131034272, 2131034273, 2131034275, 2131034276,
			2131034277 };

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_comments);
		this.mActionBar = getSupportActionBar();
		this.mActionBar.setDisplayUseLogoEnabled(false);
		this.mActionBar.setDisplayShowHomeEnabled(false);
		this.mActionBar.setDisplayShowCustomEnabled(true);
		this.mActionBar.setCustomView(R.layout.actionbar_detail_activity);
		this.mActionBar.setBackgroundDrawable(getResources().getDrawable(
				android.R.color.white));
		this.mActionBarView = this.mActionBar.getCustomView();

		initActionBarViews(this.mActionBarView);

		this.list = ((ListView) findViewById(R.id.listview_comments_activity));
		createData();
		initList();
	}

	private void createData() {
		this.dataList = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 10; i++) {

			HashMap<String, Object> hashmap = new HashMap<String, Object>();
			hashmap.put("headerphotoforcomments",
					Integer.valueOf(R.drawable.smileupicon));
			hashmap.put("nameforcomments", "NO." + String.valueOf(i));
			hashmap.put("commentsforcoments", "This is comments");
			hashmap.put("timeforcomments", new Date(Calendar.getInstance()
					.getTimeInMillis()).toGMTString());
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
		CommentsListAdapter listAdapter = new CommentsListAdapter(this,
				this.dataList, R.layout.item_listview_comments_activity, null);
		this.list.setAdapter(listAdapter);
	}

	private class MyActionBarBackButtonListener implements View.OnClickListener {

		public void onClick(View paramView) {
			CommentsActivity.this.finish();
		}
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.CommentsActivity JD-Core Version: 0.6.0
 */