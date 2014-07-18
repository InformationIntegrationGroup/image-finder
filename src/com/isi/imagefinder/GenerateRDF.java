package com.isi.imagefinder;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;

public class GenerateRDF extends AsyncTask<String, Void, Void> {
	
	
	HttpURLConnection urlCon;
	URL url;
	public final static String webServiceURL = "http://localhost:8080/rest/hello/world";
	
	@Override
	protected Void doInBackground(String... params) 
	{
		try 
		{
			url = new URL(webServiceURL);
			urlCon = (HttpURLConnection) url.openConnection();
			
			urlCon.setDoOutput(true); //its a POST request
			
			urlCon.setRequestMethod("POST"); //is not needed, but still...
			
			urlCon.setFixedLengthStreamingMode(params[0].getBytes().length);
			
			urlCon.setRequestProperty("Content-Type", "application/json");
			
			PrintWriter out = new PrintWriter(urlCon.getOutputStream());
			
			out.print(params[0]);
			
			out.close();
			
			//InputStream is = new BufferedInputStream(urlCon.getInputStream());
			//readStream(is);
			
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
