package com.smileup.adapters.viewdownloaders;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import com.smileup.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class OneThreadLoadingClass {

	private static int IMAGE_MAX_HEIGHT = 480;
	private static int IMAGE_MAX_WIDTH = 480;

	private String assetPath = null;

	private Activity activity;

	private Map<ImageView, String> imageViews = Collections
			.synchronizedMap(new WeakHashMap<ImageView, String>());

	private HandlerThread thread;
	private ImageLoadHandler mHandler;

	private MemoryCache memoryCache = new MemoryCache();

	public OneThreadLoadingClass(Context context, String threadname,
			String assetPath) {
		this.activity = ((Activity) context);
		this.thread = new HandlerThread(threadname);
		this.thread.start();
		this.mHandler = new ImageLoadHandler(this.thread.getLooper());
		this.assetPath = assetPath;
	}

	public void displayImage(String filename, ImageView image,
			RelativeLayout relativelayout) {

		InsertProgressbar progressbar = new InsertProgressbar(relativelayout,
				this.activity);

		this.imageViews.put(image, filename);

		PhotoHolder photoholder = new PhotoHolder(filename, image, progressbar);

		Bitmap bitmapInMemory = this.memoryCache.get(photoholder.filename);

		if (bitmapInMemory != null) {
			// progressbar.stopBar();
			Log.v("OneThreadImageLoading", photoholder.filename);
			image.setImageBitmap(bitmapInMemory);
		} else {
			progressbar.insertBar();
			sendDownloadMessage(photoholder);
			image.setImageDrawable(activity.getResources().getDrawable(
					R.drawable.smileupicon));
		}
	}

	// Important!!!用时候注意
	public void stopSelf() {

		if (thread != null && thread.isAlive()) {
			thread.quit();
			thread = null;
		}
	}

	boolean imageViewReused(PhotoHolder photoholder) {

		String str = (String) this.imageViews.get(photoholder.image);

		return (str == null) || (!str.equals(photoholder.filename));
	}

	private Bitmap getAssetImage(Context context, String name)
			throws IOException {

		Bitmap bitmap = null;

		if (assetPath != null) {
			BitmapFactory.Options options = new BitmapFactory.Options();
			BufferedInputStream inputStream = new BufferedInputStream(context
					.getResources().getAssets()
					.open(assetPath + "/" + name + ".png"));

			// ===========================================================//
			// 可以使用getImageScale函数来获取这个SampleSize
			options.inSampleSize = 2;
			// ===========================================================//
			bitmap = BitmapFactory.decodeStream(inputStream, null, options);
		}
		return bitmap;
	}

	private static int getImageScale(BufferedInputStream inputstream) {

		BitmapFactory.Options options = new BitmapFactory.Options();

		options.inJustDecodeBounds = true;

		BitmapFactory.decodeStream(inputstream, null, options);

		int i = 1;
		while (true) {

			if ((options.outWidth / i < IMAGE_MAX_WIDTH)
					&& (options.outHeight / i < IMAGE_MAX_HEIGHT)) {

				return i;
			}
			i *= 2;
		}
	}

	private void sendDownloadMessage(PhotoHolder photoholder) {
		Message msg = this.mHandler.obtainMessage();
		msg.obj = photoholder;
		this.mHandler.sendMessage(msg);
	}

	class BitmapDisplayer implements Runnable {
		Bitmap bitmap;
		InsertProgressbar mProgreebar;
		PhotoHolder photoholder;

		public BitmapDisplayer(Bitmap bitmap, PhotoHolder photoholder) {
			this.bitmap = bitmap;
			this.photoholder = photoholder;
		}

		public void run() {

			if (bitmap != null && photoholder != null) {
				if (OneThreadLoadingClass.this
						.imageViewReused(this.photoholder)) {
					this.photoholder.mProgressbar.stopBar();
					return;
				}

				this.photoholder.mProgressbar.stopBar();
				this.photoholder.image.setImageBitmap(this.bitmap);

			}

		}
	}

	class ImageLoadHandler extends Handler {
		PhotoHolder photoHolder;
		String name;
		ImageView imageview;

		public ImageLoadHandler(Looper looper) {
			super(looper);
		}

		public void handleMessage(Message msg) {

			if (!OneThreadLoadingClass.this.activity.isFinishing()) {

				photoHolder = (PhotoHolder) msg.obj;

				// get image name of Hard codes
				name = photoHolder.filename;
				imageview = photoHolder.image;

				try {
					Bitmap bitmap = OneThreadLoadingClass.this.getAssetImage(
							OneThreadLoadingClass.this.activity, name);

					if (bitmap != null) {
						OneThreadLoadingClass.this.memoryCache
								.put(name, bitmap);

						BitmapDisplayer bitmapDisplay = new BitmapDisplayer(
								bitmap, photoHolder);

						((Activity) imageview.getContext())
								.runOnUiThread(bitmapDisplay);
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class PhotoHolder {
		String filename = null;
		ImageView image = null;
		InsertProgressbar mProgressbar = null;

		public PhotoHolder(String filename, ImageView image,
				InsertProgressbar progressbar) {
			this.mProgressbar = progressbar;
			this.filename = filename;
			this.image = image;
		}
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.adapter.OneThreadLoadingClass JD-Core Version: 0.6.0
 */