package com.isi.imagefinder;

import java.io.File;

import com.isi.image.Media;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;

public class PhotosObserver extends ContentObserver{
	
	Context applicationContext;
	
	public PhotosObserver(Context context) 
	{
		super(null);
		this.applicationContext = context;
	}
	
	@Override
	public void onChange(boolean selfChange)
	{
		super.onChange(selfChange);
		
		Media media =  readFromMediaStore(applicationContext,
		        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		        //saved = "I detected " + media.file.getName();
		        Log.d("INSTANT", "detected picture");
		
	}
	
	public Media readFromMediaStore(Context context, Uri uri) {
	    Cursor cursor = context.getContentResolver().query(uri, null, null,
	        null, "date_added DESC");
	    Media media = null;
	    if (cursor.moveToNext()) {
	        int dataColumn = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
	        String filePath = cursor.getString(dataColumn);
	        int mimeTypeColumn = cursor
	            .getColumnIndexOrThrow(MediaColumns.MIME_TYPE);
	        String mimeType = cursor.getString(mimeTypeColumn);
	        media = new Media(new File(filePath), mimeType);
	    }
	    cursor.close();
	    return media;
	}

}
