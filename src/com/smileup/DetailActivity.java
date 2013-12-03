package com.smileup;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class DetailActivity extends SherlockFragmentActivity {

	public static final String CharityName = "charity_name";
	public static final String CharityPhotoName = "charitryphoto_name";
	public static final String Title = "tile";

	private ImageView backButton;
	private ActionBar mActionBar;
	private View mActionBarView;
	private TabHost mTabHost;
	private RelativeLayout listviewIndicator;
	private RelativeLayout gridviewIndicator;
	private String title = null;
	private TextView titleActionBar;

	public ActionBar getMyActionBar() {

		return this.mActionBar;
	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_details);

		this.title = getIntent().getStringExtra("tile");

		this.mActionBar = getSupportActionBar();
		this.mActionBar.setDisplayUseLogoEnabled(false);
		this.mActionBar.setDisplayShowHomeEnabled(false);
		this.mActionBar.setDisplayShowCustomEnabled(true);
		this.mActionBar.setCustomView(R.layout.actionbar_detail_activity);
		this.mActionBar.setBackgroundDrawable(getResources().getDrawable(
				android.R.color.white));
		this.mActionBarView = this.mActionBar.getCustomView();
		initTabHost(this);
		initActionBarViews();
	}

	private void initActionBarViews() {

		this.backButton = ((ImageView) this.mActionBarView
				.findViewById(R.id.backbutton_actionbar_detail_activity));
		this.backButton.setOnClickListener(new MyActionBarBackButtonListener());

		this.titleActionBar = ((TextView) this.mActionBarView
				.findViewById(R.id.title_actionbar_detail_activity));

		if (this.title != null)
			this.titleActionBar.setText(this.title);
	}

	private void initTabHost(Activity activity) {

		this.mTabHost = ((TabHost) activity
				.findViewById(R.id.tabhost_detail_activity));
		// ========================================================//
		// Important!!!先设置tabwidget的布局
		findTabView(this.mTabHost);
		// ========================================================//
		// 然后再设置tabhost
		this.mTabHost.setup();
		this.mTabHost.addTab(this.mTabHost.newTabSpec("listview")
				.setIndicator(this.listviewIndicator)
				.setContent(R.id.fragment1_detail_activity));
		this.mTabHost.addTab(this.mTabHost.newTabSpec("gridview")
				.setIndicator(this.gridviewIndicator)
				.setContent(R.id.fragment2_detail_activity));
		this.mTabHost.setOnTabChangedListener(new MyTabhostListener());
		this.mTabHost.setCurrentTab(0);
		// ========================================================//
		// 设置tab的样式，根据选中的tab并作出变化
		updateTab(this.mTabHost);
	}

	private void findTabView(TabHost tabhost) {

		TabWidget localTabWidget = (TabWidget) ((LinearLayout) tabhost
				.getChildAt(0)).getChildAt(0);

		this.listviewIndicator = ((RelativeLayout) LayoutInflater.from(this)
				.inflate(R.layout.tab_indicator_detail_activity,
						localTabWidget, false));

		TextView listViewText = (TextView) this.listviewIndicator.getChildAt(1);

		((ImageView) this.listviewIndicator.getChildAt(0))
				.setImageDrawable(getResources().getDrawable(
						R.drawable.white_list));

		listViewText.setText("List View");

		this.gridviewIndicator = ((RelativeLayout) LayoutInflater.from(this)
				.inflate(R.layout.tab_indicator_detail_activity,
						localTabWidget, false));

		TextView gridViewText = (TextView) this.gridviewIndicator.getChildAt(1);

		((ImageView) this.gridviewIndicator.getChildAt(0))
				.setImageDrawable(getResources().getDrawable(
						R.drawable.white_grid));

		gridViewText.setText("Grid View");
	}

	private void updateTab(TabHost tabhost) {
		TabWidget tabwidget = tabhost.getTabWidget();
		for (int i = 0; i < tabwidget.getChildCount(); i++) {
			View localView = tabwidget.getChildAt(i);

			if (tabhost.getCurrentTab() == i) {
				localView.setBackgroundResource(R.color.SmileupGreen);
			} else {
				localView.setBackgroundResource(R.color.SmileupGreen2);
			}
		}

	}

	private class MyActionBarBackButtonListener implements View.OnClickListener {

		public void onClick(View paramView) {
			DetailActivity.this.finish();
		}
	}

	private class MyTabhostListener implements TabHost.OnTabChangeListener {
		private MyTabhostListener() {
		}

		public void onTabChanged(String paramString) {
			DetailActivity.this.mTabHost.setCurrentTabByTag(paramString);
			DetailActivity.this.updateTab(DetailActivity.this.mTabHost);
		}
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.DetailActivity JD-Core Version: 0.6.0
 */