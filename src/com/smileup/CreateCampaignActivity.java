package com.smileup;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

public class CreateCampaignActivity extends SherlockActivity {
	public static final int GETIMAGECODE = 0x01;
	private static int IMAGE_MAX_HEIGHT = 480;
	private static int IMAGE_MAX_WIDTH = 480;

	private TextView actionbarTitle;
	private ImageView addPhoto;
	private ImageView backarrowImage;
	private ActionBar mActionBar;
	private View mActionBarView;
	private PopupWindow mWindow;

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_createcampaign);
		this.mActionBar = getSupportActionBar();
		this.mActionBar.setDisplayUseLogoEnabled(false);
		this.mActionBar.setDisplayShowHomeEnabled(false);
		this.mActionBar.setDisplayShowCustomEnabled(true);
		this.mActionBar.setCustomView(R.layout.actionbar_detail_activity);
		this.mActionBar.setBackgroundDrawable(getResources().getDrawable(
				android.R.color.white));
		this.mActionBarView = this.mActionBar.getCustomView();
		initActionBarViews(this.mActionBarView);
		initViewCompounents();
	}

	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		if ((requestCode == GETIMAGECODE) && (resultCode == -1)) {
			// File localFile = getFilefromUri(paramIntent.getData());

			String imagepath = getRealPathFromURI(this, intent.getData());
			File imagefile = new File(imagepath);

			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = getImageScale(imagefile.getAbsolutePath());

			Bitmap bitmap = BitmapFactory.decodeFile(
					imagefile.getAbsolutePath(), options);

			if (bitmap != null)
				this.addPhoto.setImageBitmap(bitmap);
		}
		super.onActivityResult(requestCode, resultCode, intent);
	}

	// @SuppressWarnings("deprecation")
	// private File getFilefromUri(Uri paramUri) {
	// Cursor localCursor = managedQuery(paramUri, new String[] { "_data" },
	// null, null, null);
	// int i = localCursor.getColumnIndexOrThrow("_data");
	// localCursor.moveToFirst();
	// return new File(localCursor.getString(i));
	// }

	// ==============================================================//
	// 最常见的提取方法

	// private File getFileFromUri(Uri url) {
	//
	// return new File(url.getPath());
	//
	// }

	// =============================================================//
	// 提取特定內容
	public String getRealPathFromURI(Context context, Uri contentUri) {
		Cursor cursor = null;
		try {
			String[] domain = { MediaStore.Images.Media.DATA };
			cursor = context.getContentResolver().query(contentUri, domain,
					null, null, null);
			int column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			return cursor.getString(column_index);
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	}

	// =============================================================//

	private static int getImageScale(String path) {
		BitmapFactory.Options localOptions = new BitmapFactory.Options();
		localOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, localOptions);
		int i = 1;
		while (true) {
			if ((localOptions.outWidth / i < IMAGE_MAX_WIDTH)
					&& (localOptions.outHeight / i < IMAGE_MAX_HEIGHT))
				return i;
			i *= 2;
		}
	}

	// private float getRawSize(int paramInt, float paramFloat) {
	// return TypedValue.applyDimension(paramInt, paramFloat, getResources()
	// .getDisplayMetrics());
	// }

	private void initActionBarViews(View view) {

		this.actionbarTitle = ((TextView) view
				.findViewById(R.id.title_actionbar_detail_activity));
		this.actionbarTitle.setText("Create a Campaign");

		this.backarrowImage = ((ImageView) view
				.findViewById(R.id.backbutton_actionbar_detail_activity));
		this.backarrowImage.setOnClickListener(new View.OnClickListener() {

			public void onClick(View paramView) {
				CreateCampaignActivity.this.finish();
			}

		});
	}

	private void initViewCompounents() {
		this.addPhoto = ((ImageView) findViewById(R.id.addphoto_createcampaign_activity));
		this.addPhoto.setOnClickListener(new AddPhotoListener());
	}

	private void popupinvitewindow(View parentView) {
		this.mWindow = null;
		if (this.mWindow == null) {
			View popupView = ((LayoutInflater) getSystemService("layout_inflater"))
					.inflate(R.layout.addphotos_popupwindow, null);

			RelativeLayout takePhoto = (RelativeLayout) popupView
					.findViewById(R.id.takephoto_popupwindow);
			RelativeLayout gallery = (RelativeLayout) popupView
					.findViewById(R.id.selectfromgallery_popupwindow);

			takePhoto.setOnClickListener(new View.OnClickListener() {
				public void onClick(View paramView) {
					if (CreateCampaignActivity.this.mWindow != null)
						CreateCampaignActivity.this.mWindow.dismiss();
				}
			});
			gallery.setOnClickListener(new View.OnClickListener() {
				public void onClick(View paramView) {
					Intent localIntent = new Intent(
							"android.intent.action.GET_CONTENT");
					localIntent.addCategory("android.intent.category.OPENABLE");
					localIntent.setType("image/*");
					CreateCampaignActivity.this.startActivityForResult(
							localIntent, GETIMAGECODE);
					if (CreateCampaignActivity.this.mWindow != null)
						CreateCampaignActivity.this.mWindow.dismiss();
				}
			});

			this.mWindow = new PopupWindow(popupView);
			this.mWindow.setWidth(LayoutParams.WRAP_CONTENT);
			this.mWindow.setHeight(LayoutParams.WRAP_CONTENT);
		}
		this.mWindow.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.stroke_background_backframe));
		this.mWindow.setOutsideTouchable(true);
		this.mWindow.setFocusable(true);
		this.mWindow.setTouchable(true);
		this.mWindow.update();
		this.mWindow.showAtLocation(parentView, Gravity.CENTER_HORIZONTAL, 0,
				-100);
	}

	class AddPhotoListener implements View.OnClickListener {

		public void onClick(View paramView) {
			View parentView = CreateCampaignActivity.this.getWindow()
					.getDecorView().findViewById(android.R.id.content)
					.findViewById(R.id.suqarelayout1_createcampaign_activity);
			CreateCampaignActivity.this.popupinvitewindow(parentView);
		}
	}

	class LoadingAddedPhotoHandler extends Handler {

		public void handleMessage(Message paramMessage) {
		}
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.CreateCampaignActivity JD-Core Version: 0.6.0
 */