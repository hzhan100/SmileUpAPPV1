package com.smileup;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

public class ShareActivity extends SherlockActivity {
	private ImageView backButton;
	private ActionBar mActionBar;
	private View mActionBarView;
	private TextView titleActionBar;

	private void initActionBarViews(View paramView) {
		this.backButton = ((ImageView) paramView
				.findViewById(R.id.backbutton_actionbar_detail_activity));
		this.backButton.setOnClickListener(new MyActionBarBackButtonListener());
		this.titleActionBar = ((TextView) paramView
				.findViewById(R.id.title_actionbar_detail_activity));
		this.titleActionBar.setText("Share");
	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_share);
		this.mActionBar = getSupportActionBar();
		this.mActionBar.setDisplayUseLogoEnabled(false);
		this.mActionBar.setDisplayShowHomeEnabled(false);
		this.mActionBar.setDisplayShowCustomEnabled(true);
		this.mActionBar.setCustomView(R.layout.actionbar_detail_activity);
		this.mActionBar.setBackgroundDrawable(getResources().getDrawable(
				android.R.color.white));
		this.mActionBarView = this.mActionBar.getCustomView();
		initActionBarViews(this.mActionBarView);
	}

	private class MyActionBarBackButtonListener implements View.OnClickListener {
		private MyActionBarBackButtonListener() {
		}

		public void onClick(View paramView) {
			ShareActivity.this.finish();
		}
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.ShareActivity JD-Core Version: 0.6.0
 */