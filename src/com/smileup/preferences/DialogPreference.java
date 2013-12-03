package com.smileup.preferences;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.smileup.R;

public class DialogPreference extends Preference {
	private String content = "";
	private TextView contentView;
	private Context context;
	private String hint = "";
	private ImageView iconImage;
	private AlertDialog.Builder mBuilder;
	private Dialog mDialog;
	private String mDialogTitle = "";
	private Drawable mDrawable;
	private SharedPreferences mSharePrefs;
	// PrefsKey很重要，如果想要存储信息则必须设置这个key
	private String sharePrefsKey;
	private String title = "";
	private TextView titleView;

	public DialogPreference(Context context, AttributeSet attrs) {

		super(context, attrs);
		this.context = context;
		this.mDrawable = context.getResources().getDrawable(
				R.drawable.grey_pencil);
		setLayoutResource(R.layout.preference_baselayout_dialog);
	}

	protected void onBindView(View paramView) {

		super.onBindView(paramView);

		this.titleView = ((TextView) paramView
				.findViewById(R.id.title_dialog_baselayout_preference));
		this.titleView.setText(this.title);

		this.iconImage = ((ImageView) paramView
				.findViewById(R.id.icon_dialog_baselayout_preference));
		this.iconImage.setImageDrawable(this.mDrawable);

		this.contentView = ((TextView) paramView
				.findViewById(R.id.content_dialog_baselayout_preference));

		iniContent();

		this.contentView.setHint(this.hint);
		this.contentView.setText(this.content);
	}

	protected void onClick() {
		if ((this.mDialog != null) && (this.mDialog.isShowing()))
			return;
		showDialog();
	}

	private void iniContent() {

		if ((this.sharePrefsKey != null) && (!"".equals(this.sharePrefsKey))) {
			this.mSharePrefs = PreferenceManager
					.getDefaultSharedPreferences(this.context);
			this.content = this.mSharePrefs.getString(this.sharePrefsKey, "");
			this.contentView.setText(this.content);
		}
	}

	private void showDialog() {
		Context context = getContext();
		this.mBuilder = new AlertDialog.Builder(context);

		final View inflatedView = LayoutInflater.from(context).inflate(
				R.layout.preference_dialoglayout_edittext, null);

		((TextView) inflatedView
				.findViewById(R.id.title_edit_dialog_preferencelayout))
				.setText(this.mDialogTitle);

		this.mBuilder.setView(inflatedView);
		this.mBuilder.setPositiveButton("Ok",
				new DialogInterface.OnClickListener() {
					EditText edittext;

					public void onClick(DialogInterface dialogInterface,
							int which) {

						this.edittext = ((EditText) inflatedView
								.findViewById(R.id.edittext_edit_dialog_preferencelayot));

						if ((DialogPreference.this.sharePrefsKey != null)
								&& (!DialogPreference.this.sharePrefsKey
										.equals(""))) {

							PreferenceManager
									.getDefaultSharedPreferences(
											DialogPreference.this.context)
									.edit()
									.putString(
											DialogPreference.this.sharePrefsKey,
											this.edittext.getText().toString())
									.commit();

							DialogPreference.this.content = this.edittext
									.getText().toString();

							if (content != null)
								DialogPreference.this.contentView
										.setText(DialogPreference.this.content);
						}
					}
				});

		this.mBuilder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface paramDialogInterface,
							int paramInt) {
					}
				});

		this.mDialog = this.mBuilder.create();
		this.mDialog.show();
	}

	public void setDialogTitle(String dialogTitle) {
		this.mDialogTitle = dialogTitle;
	}

	public void setHintForContent(String hint) {
		this.hint = hint;
	}

	public void setIconDrawable(Drawable iconDrawable) {
		this.mDrawable = iconDrawable;
	}

	// 使用这个类时一定要设置key
	public void setSharePrefsKey(String sharePrefsKey) {
		this.sharePrefsKey = sharePrefsKey;
	}

	public void setTitleText(String paramString) {
		this.title = paramString;
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.preference.DialogPreference JD-Core Version: 0.6.0
 */