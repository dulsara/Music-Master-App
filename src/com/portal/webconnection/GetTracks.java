package com.portal.webconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetTracks {
	private String getTracksURL;
	private URL url;
	HttpURLConnection connection;
	public String getTracks(int size, String status,String sortingPart) throws IOException{
		if(status.equals("sort")){
			this.getTracksURL = "http://freemusicarchive.org/api/get/tracks.json?api_key=60BLHNQCAOUFPIBZ&limit="+Integer.toString(size)+sortingPart;
		}
		else{
			this.getTracksURL = "http://freemusicarchive.org/api/get/tracks.json?api_key=60BLHNQCAOUFPIBZ&limit="+Integer.toString(size);

		}
		
		System.out.println(this.getTracksURL);
		try {
			url= new URL(getTracksURL);
			connection =  (HttpURLConnection) url.openConnection();
			connection.setReadTimeout(10000);
	         connection.setConnectTimeout(15000);
	         connection.setRequestMethod("GET");
	         connection.setDoInput(true);
	         connection.connect();
	         InputStream is = connection.getInputStream();
	         BufferedReader reader = new BufferedReader(new InputStreamReader
	         (is, "UTF-8") );
	         String data = null;
	         String artistJsonData = "";
	         while ((data = reader.readLine()) != null){
	        	 artistJsonData += data;
		         }
		         return artistJsonData;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			return "Error Loading";
		}
		
	}
}
