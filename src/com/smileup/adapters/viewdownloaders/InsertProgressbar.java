package com.smileup.adapters.viewdownloaders;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class InsertProgressbar {
	private Activity activity;
	private ProgressBar bar;
	private RelativeLayout relativeLayout;

	public InsertProgressbar(RelativeLayout paramRelativeLayout, Context context) {
		this.relativeLayout = paramRelativeLayout;
		this.bar = new ProgressBar(context);
		RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		localLayoutParams.getRules()[RelativeLayout.CENTER_IN_PARENT] = RelativeLayout.TRUE;
		this.bar.setLayoutParams(localLayoutParams);
	}

	public void insertBar() {
		this.bar.setProgress(0);
		if (!this.bar.isShown()) {
			this.bar.setVisibility(View.VISIBLE);
			this.relativeLayout.addView(this.bar);
		}
	}

	public void stopBar() {
		this.bar.setVisibility(View.GONE);
		this.relativeLayout.removeView(this.bar);
	}
}
