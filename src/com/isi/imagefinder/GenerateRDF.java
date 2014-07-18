package com.isi.imagefinder;

import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;

public class GenerateRDF extends AsyncTask<String, Void, Void> {
	
	
	HttpURLConnection urlCon;
	URL url;
	public final static String webServiceURL = "http://localhost:8080/rdf/metadata/images";
	
	@Override
	protected Void doInBackground(String... params) 
	{
		try 
		{
			urlCon = (HttpURLConnection) url.openConnection();
			
			urlCon.setDoOutput(true); //its a POST request
			
			urlCon.setRequestMethod("POST"); //is not needed, but still...
			
			urlCon.setRequestProperty("Content-Type", "text/plain");
			
			
			//byte[] data = strJSON.getBytes("UTF-8");
			urlCon.setFixedLengthStreamingMode(params[0].length());
			
			OutputStream os = urlCon.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			
			writer.write(params[0]);
			writer.flush();
			writer.close();
			os.close();
			
			urlCon.connect();
			
		} 
		catch (MalformedURLException e) 
		{
					e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			urlCon.disconnect();
		}
		return null;
	}

}
