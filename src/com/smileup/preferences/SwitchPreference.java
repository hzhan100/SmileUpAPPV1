package com.smileup.preferences;

import com.smileup.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class SwitchPreference extends Preference {
	private SharedPreferences mSharePrefs;
	private Context mcontext;
	private String mtitle = "";
	private String sharePreKey = null;
	private boolean switchChecked = false;
	private Switch switcher;
	private String textOff = "";
	private String textOn = "";
	private TextView title;

	public SwitchPreference(Context context, AttributeSet attrs) {
		super(context, attrs);

		this.mcontext = context;
		setLayoutResource(R.layout.preference_baselayout_switch);
	}

	private void initSwitchChecked() {

		if ((this.sharePreKey != null) && (!"".equals(this.sharePreKey))) {

			this.mSharePrefs = PreferenceManager
					.getDefaultSharedPreferences(this.mcontext);

			this.switchChecked = this.mSharePrefs.getBoolean(this.sharePreKey,
					false);
			this.switcher.setChecked(this.switchChecked);
		}
	}

	protected void onBindView(View view) {

		super.onBindView(view);

		this.title = ((TextView) view
				.findViewById(R.id.title_switch_baselayout_preference));
		this.switcher = ((Switch) view
				.findViewById(R.id.switcher_switch_baselayout_preference));

		this.title.setText(this.mtitle);
		this.switcher.setTextOn(this.textOn);
		this.switcher.setTextOff(this.textOff);

		initSwitchChecked();

		this.switcher
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					public void onCheckedChanged(
							CompoundButton paramCompoundButton,
							boolean paramBoolean) {
						if ((SwitchPreference.this.sharePreKey != null)
								&& (!"".equals(SwitchPreference.this.sharePreKey))) {

							SwitchPreference.this.mSharePrefs = PreferenceManager
									.getDefaultSharedPreferences(SwitchPreference.this.mcontext);
							SharedPreferences.Editor editor = SwitchPreference.this.mSharePrefs
									.edit();
							editor.putBoolean(
									SwitchPreference.this.sharePreKey,
									paramBoolean);
							editor.commit();
						}
					}
				});
	}

	public void setChecked(boolean paramBoolean) {
		this.switchChecked = paramBoolean;
	}

	public void setSharePreferenceKey(String paramString) {
		this.sharePreKey = paramString;
	}

	public void setTextOff(String textoff) {
		this.textOff = textoff;
	}

	public void setTextOn(String texton) {
		this.textOn = texton;
	}

	public void setTitle(String title) {
		this.mtitle = title;
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.preference.SwitchPreference JD-Core Version: 0.6.0
 */