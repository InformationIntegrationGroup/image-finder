package com.isi.imagefinder;

import java.io.File;
import java.util.ArrayList;

import org.json.JSONException;

import com.isi.image.io.ImageReader;
import com.isi.image.metadata.ImageMetaData;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class Main extends ActionBarActivity {

	//private PhotosObserver instUploadObserver= new PhotosObserver(getApplicationContext());
	//private String saved;
	//TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		//tv = (TextView) findViewById(R.id.tv);
		
		//this.getApplicationContext().getContentResolver()
		//.registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, 
			//	false, instUploadObserver);
		
		Intent intent = new Intent(this.getApplicationContext(), ImageFinderService.class);
		startService(intent);
		
		//Toast.makeText(getApplicationContext(), "SErvice started", Toast.LENGTH_LONG).show();
		Log.d("SERVICE", "starting service");
	}

	//@Override
	//public void onResume() {
		//	super.onResume();
			//if (saved != null) {
				//tv.setText(saved);
		//	}
	//}

	//@Override
	//public void onDestroy() {
		//super.onDestroy();
		//this.getApplicationContext().getContentResolver()
		//.unregisterContentObserver(instUploadObserver);
		//Log.d("INSTANT", "unregistered content observer");
	//}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
public String GetPhoneNumberFromSim() {
		
		TelephonyManager tMgr = (TelephonyManager) getApplicationContext().getSystemService(TELEPHONY_SERVICE);
		
		if(tMgr.getLine1Number() != null){
			return tMgr.getLine1Number();
		}
		else{
			return "";
		}
	}
	
	public String GetDeviceId() { //returns IMEI (GSM)
		
		TelephonyManager tMgr = (TelephonyManager) getApplicationContext().getSystemService(TELEPHONY_SERVICE);
		return tMgr.getDeviceId();
		
	}
	
	public String GetMetaDataJSON() throws JSONException {
		
		String root = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera/";
		ArrayList<String> images = new ImageReader().ReadImageFiles(new File(root));
		ImageMetaData imd = new ImageMetaData(images.get(354),getApplicationContext());
		//Toast.makeText(getApplicationContext(), imd.getLatitude().toString(), Toast.LENGTH_LONG).show();
		return imd.MetaDataJSON(imd);
		
	}

}
