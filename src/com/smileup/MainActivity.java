package com.smileup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.smileup.helpers.ImportSlidingMenu;

public class MainActivity extends SherlockFragmentActivity {
	private ImageView createCampaignButton;
	private ActionBar mActionBar;
	private View mActionBarView;
	private SlidingMenu mSlidingMenu;
	private TabHost mTabHost;
	private ImageView menuButton;
	private ImportSlidingMenu slidingMenuImporter;

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_main);

		this.mActionBar = getSupportActionBar();
		this.mActionBar.setDisplayUseLogoEnabled(false);
		this.mActionBar.setDisplayShowHomeEnabled(false);
		this.mActionBar.setDisplayShowCustomEnabled(true);
		this.mActionBar.setCustomView(R.layout.actionbar_main_activity);
		this.mActionBar.setBackgroundDrawable(getResources().getDrawable(   
				android.R.color.white));
		this.mActionBarView = this.mActionBar.getCustomView();

		initActionBarButtons(this.mActionBarView);
		initTabHost(this);

		this.slidingMenuImporter = new ImportSlidingMenu(this);
		this.mSlidingMenu = this.slidingMenuImporter.createLeftMenu();
		this.slidingMenuImporter
				.setListViewItemClickListener(new OnItemClickListener() {

					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// switch (paramInt) {
						// case 2:
						// default:
						// case 0:
						// case 1:
						// do {
						// do {
						// return;
						// Intent localIntent2 = new Intent();
						// localIntent2.setClass(MainActivity.this,
						// CreateCampaignActivity.class);
						// MainActivity.this
						// .startActivity(localIntent2);
						// return;
						// } while (MainActivity.this.mSlidingMenu == null);
						// MainActivity.this.mSlidingMenu.toggle();
						// } while (MainActivity.this.mTabHost == null);
						// MainActivity.this.mTabHost.setCurrentTab(1);
						// return;
						// case 3:
						// }
						// Intent localIntent1 = new Intent();
						// localIntent1.setClass(MainActivity.this,
						// FindFriendsActivity.class);
						// MainActivity.this.startActivity(localIntent1);
					}
				});

		this.slidingMenuImporter
				.setSettingsOnClickListener(new View.OnClickListener() {
					public void onClick(View paramView) {
						// Intent localIntent = new Intent(MainActivity.this,
						// SettingsActivity.class);
						// MainActivity.this.startActivity(localIntent);
					}
				});

	}

	protected void onResume() {
		super.onResume();
	}

	private void initActionBarButtons(View paramView) {
		this.menuButton = ((ImageView) paramView
				.findViewById(R.id.menubutton_actionabr_main));
		this.createCampaignButton = ((ImageView) paramView
				.findViewById(R.id.createcampaignbutton_actionbar_main));
		this.menuButton.setClickable(true);
		this.createCampaignButton.setClickable(true);
		this.menuButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {
				MainActivity.this.mSlidingMenu.toggle();
			}
		});
		this.createCampaignButton
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View paramView) {
						Intent localIntent = new Intent(MainActivity.this,
								CreateCampaignActivity.class);
						MainActivity.this.startActivity(localIntent);
					}
				});
	}

	private void initTabHost(Activity activity) {

		this.mTabHost = ((TabHost) activity.findViewById(R.id.tabhost));
		this.mTabHost.setup();
		this.mTabHost.addTab(this.mTabHost.newTabSpec("fragment_news")
				.setIndicator("News").setContent(R.id.fragment1_main_activity));
		this.mTabHost.addTab(this.mTabHost.newTabSpec("fragment_discover")
				.setIndicator("Discover")
				.setContent(R.id.fragment2_main_activity));
		this.mTabHost.addTab(this.mTabHost.newTabSpec("fragment_profile")
				.setIndicator("Profile")
				.setContent(R.id.fragment3_main_activity));
		this.mTabHost.setOnTabChangedListener(new MyTabhostListener());

		TabWidget widget = this.mTabHost.getTabWidget();
		for (int i = 0; i < widget.getChildCount(); i++) {
			TextView localTextView = (TextView) widget.getChildAt(i)
					.findViewById(android.R.id.title);
			localTextView.setTextSize(15.0F);
			localTextView.setTextColor(getResources().getColor(
					R.color.SmileupGreen));
		}

		// ================================================================//
		this.mTabHost.setCurrentTab(0);
		updateTab(this.mTabHost);
	}

	private void updateTab(TabHost paramTabHost) {

		TabWidget localTabWidget = this.mTabHost.getTabWidget();

		for (int i = 0; i < localTabWidget.getChildCount(); i++) {

			View localView = localTabWidget.getChildAt(i);
			TextView localTextView = (TextView) localView
					.findViewById(android.R.id.title);

			if (this.mTabHost.getCurrentTab() == i) {
				localView.setBackgroundResource(R.color.SmileupGreen);
				localTextView.setTextColor(getResources().getColor(
						android.R.color.white));
			} else {
				localView.setBackgroundResource(android.R.color.white);
				localTextView.setTextColor(getResources().getColor(
						R.color.SmileupGreen));
			}
		}

	}

	class MyTabhostListener implements TabHost.OnTabChangeListener {
		MyTabhostListener() {
		}

		public void onTabChanged(String tableId) {

			MainActivity.this.mTabHost.setCurrentTabByTag(tableId);
			MainActivity.this.updateTab(MainActivity.this.mTabHost);
		}
	}
}
