package com.smileup.adapters.viewdownloaders;

import android.graphics.Bitmap;
import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MemoryCache {
	private Map<String, SoftReference<Bitmap>> cache = Collections
			.synchronizedMap(new HashMap<String, SoftReference<Bitmap>>());

	public Bitmap get(String name) {
		if (!this.cache.containsKey(name))
			return null;
		return ((SoftReference<Bitmap>) this.cache.get(name)).get();
	}

	public void put(String name, Bitmap bitmap) {
		this.cache.put(name, new SoftReference<Bitmap>(bitmap));
	}

	public void clear() {
		this.cache.clear();
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.picloading.MemoryCache JD-Core Version: 0.6.0
 */