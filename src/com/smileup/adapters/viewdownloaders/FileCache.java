package com.smileup.adapters.viewdownloaders;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import java.io.File;

public class FileCache {
	private File fileCacheDir;

	public FileCache(Context context) {
		if (Environment.getExternalStorageDirectory().equals(
				Environment.MEDIA_MOUNTED)) {
			fileCacheDir = new File(Environment.getExternalStorageDirectory(),
					"HackerDojo");
		} else {
			fileCacheDir = context.getCacheDir();
			if (!fileCacheDir.exists()) {
				fileCacheDir.mkdirs();
			}
		}
		Log.w("FilePahFilePath", fileCacheDir.getAbsolutePath());
	}

	public void clear() {
		File[] files = fileCacheDir.listFiles();
		if (files == null) {
			return;
		}
		for (File f : files) {
			f.delete();
		}
	}

	public File getFileByHashCode(String url) {
		String str = String.valueOf(url.hashCode());
		return new File(this.fileCacheDir.getAbsolutePath() + "/" + str);
	}

	public File getFileByName(String name) {
		return new File(this.fileCacheDir.getAbsolutePath() + "/"
				+ name.trim().toLowerCase());

	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.picloading.FileCache JD-Core Version: 0.6.0
 */