package com.smileup.preferences;

import com.smileup.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class PhotoPreference extends Preference {
	private ImageView background;
	private Context context;
	private Drawable mBackgroundDrawable;
	private Drawable mPhotoDrawable;
	private ImageView photo;

	public PhotoPreference(Context context, AttributeSet paramAttributeSet) {

		super(context, paramAttributeSet);

		this.context = context;
		this.mBackgroundDrawable = context.getResources().getDrawable(
				R.drawable.smileupicon);
		this.mPhotoDrawable = context.getResources().getDrawable(
				R.drawable.smileupicon);

		setLayoutResource(R.layout.preference_baselayout_photo);
	}

	protected void onBindView(View paramView) {

		super.onBindView(paramView);

		this.background = ((ImageView) paramView
				.findViewById(R.id.background_image_baselayout_preference));
		this.background.setClickable(true);
		this.background.setImageDrawable(this.mBackgroundDrawable);

		this.photo = ((ImageView) paramView
				.findViewById(R.id.photo_image_baselayout_pereference));
		this.photo.setImageDrawable(this.mPhotoDrawable);
		this.photo.setClickable(true);
	}

	public void setBackgroundDrawable(Drawable background) {
		this.mBackgroundDrawable = background;
	}

	public void setPhotoDrawable(Drawable photo) {
		this.mPhotoDrawable = photo;
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.preference.PhotoPreference JD-Core Version: 0.6.0
 */