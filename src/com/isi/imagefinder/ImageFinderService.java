package com.isi.imagefinder;

import android.app.IntentService;
import android.content.Intent;
import android.provider.MediaStore;

public class ImageFinderService extends IntentService {
	
	PhotosObserver instUploadObserver;
	
	
	public ImageFinderService() 
	{
		super("ImageFinderService");
	}
	

	@Override
	protected void onHandleIntent(Intent intent) 
	{
		instUploadObserver = new PhotosObserver(getApplicationContext());
		this.getApplication().
		getContentResolver().
		registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, 
				false, instUploadObserver);
	}
	
	@Override
	public void onDestroy() {
		this.getApplicationContext().
		getContentResolver().unregisterContentObserver(instUploadObserver);
		super.onDestroy();
	}
	
	

}
