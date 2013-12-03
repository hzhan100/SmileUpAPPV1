package com.smileup.preferences;

import com.smileup.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ToggleButtonPreference extends Preference {
	private Context context;
	private ImageView logoView;
	private ToggleButton mButton;
	private Drawable mDrawable;
	private SharedPreferences mSharePrefs;
	private TextView mTextview;
	private String sharePreKey = null;
	private String title;
	private Boolean toggleButtonChecked = Boolean.valueOf(false);

	public ToggleButtonPreference(Context context, AttributeSet attrs) {

		super(context, attrs);
		this.context = context;
		this.mDrawable = context.getResources().getDrawable(
				R.drawable.grey_pencil);
		setLayoutResource(R.layout.preference_baselayout_togglebutton);
	}

	private void initToggleButtonChecked() {
		if ((this.sharePreKey != null) && (!"".equals(this.sharePreKey))) {

			this.mSharePrefs = PreferenceManager
					.getDefaultSharedPreferences(this.context);
			this.toggleButtonChecked = Boolean.valueOf(this.mSharePrefs
					.getBoolean(this.sharePreKey, false));
			this.mButton.setChecked(this.toggleButtonChecked.booleanValue());
		}
	}

	protected void onBindView(View view) {

		super.onBindView(view);

		this.logoView = ((ImageView) view
				.findViewById(R.id.logo_togglebutton_baselayout_preference));
		this.logoView.setImageDrawable(this.mDrawable);

		this.mTextview = ((TextView) view
				.findViewById(R.id.title_togglebutton_baselayout_preference));
		this.mTextview.setText(this.title);

		this.mButton = ((ToggleButton) view
				.findViewById(R.id.button_togglebutton_baselayout_preference));

		initToggleButtonChecked();

		this.mButton.setChecked(this.toggleButtonChecked.booleanValue());
		this.mButton
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					public void onCheckedChanged(
							CompoundButton paramCompoundButton,
							boolean paramBoolean) {

						if ((ToggleButtonPreference.this.sharePreKey != null)
								&& (!"".equals(ToggleButtonPreference.this.sharePreKey))) {

							ToggleButtonPreference.this.mSharePrefs = PreferenceManager
									.getDefaultSharedPreferences(ToggleButtonPreference.this.context);

							SharedPreferences.Editor editor = ToggleButtonPreference.this.mSharePrefs
									.edit();

							editor.putBoolean(
									ToggleButtonPreference.this.sharePreKey,
									paramBoolean);
							editor.commit();
						}
					}
				});
	}

	public void setIconDrawable(Drawable icondrawable) {
		this.mDrawable = icondrawable;
	}

	public void setSharePrefsKey(String key) {
		this.sharePreKey = key;
	}

	public void setTitleText(String title) {
		this.title = title;
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.preference.ToggleButtonPreference JD-Core Version: 0.6.0
 */