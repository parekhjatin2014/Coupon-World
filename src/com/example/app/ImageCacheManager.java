package com.example.app;

import android.content.Context;
import android.graphics.Bitmap;

import android.util.LruCache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class ImageCacheManager {
    /**
     * Image cache size
     */
    private static final int CACHE_SIZE = 1024 * 1024 * 5;
    private static Context sContext;
    private static ImageCacheManager sInstance;
    /**
     * Image request queue
     */
    private RequestQueue mRequestQueue;
    /**
     * Image loader
     */
    private ImageLoader mImageLoader;

    private ImageCacheManager(Context context) {
        sContext = context;
        mRequestQueue = getRequestQueue();
        mImageLoader = new ImageLoader(mRequestQueue, new LruBitmapCache(CACHE_SIZE));
    }

    public static synchronized ImageCacheManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ImageCacheManager(context);
        }
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(sContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    /**
     * In memory Lru Bitmap cache for images
     */
    private class LruBitmapCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {

        public LruBitmapCache(int maxSize) {
            super(maxSize);
        }

        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getRowBytes() * value.getHeight();
        }


        public Bitmap getBitmap(String url) {
            return get(url);
        }

        public void putBitmap(String url, Bitmap bitmap) {
            put(url, bitmap);
        }
    }
}
