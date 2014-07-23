package com.isi.imagefinder;

import java.io.File;

import org.json.JSONException;

import com.isi.image.Media;
import com.isi.image.metadata.ImageMetaData;

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
		
				
		//TODO do something useful instead of a Toast
		//Toast.makeText(applicationContext, "New Image : " + media.getFile().getName(), Toast.LENGTH_LONG).show();
		Log.d("New Image", media.getFile().getName());
		
		ImageMetaData imd = new ImageMetaData(media.getFile().getAbsolutePath(),applicationContext);
		try {
			String json = imd.MetaDataJSON(imd);
			
			Log.d("JSON",json);
			
			new GenerateRDF().execute(json); //call the Async Task class 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
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
