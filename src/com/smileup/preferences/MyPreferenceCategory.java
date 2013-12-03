package com.smileup.preferences;

import android.content.Context;
import android.graphics.Color;
import android.preference.PreferenceCategory;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class MyPreferenceCategory extends PreferenceCategory {

	public MyPreferenceCategory(Context context,
			AttributeSet attrs) {
		
		super(context, attrs);
	}

	protected void onBindView(View paramView) {
		super.onBindView(paramView);
		//Color.parseColor这个方法一定要记住
		paramView.setBackgroundColor(Color.parseColor("#7F7F7F"));
		
		if ((paramView instanceof TextView)) {
			TextView localTextView = (TextView) paramView;
			localTextView.setTextSize(16.0F);
			localTextView.setTextColor(Color.WHITE);
		}
	}
}

