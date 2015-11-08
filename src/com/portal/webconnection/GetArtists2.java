package com.portal.webconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.util.Log;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.portal.shared.AppController;
import com.portal.shared.SessionManagement;

public class GetArtists2 {
	
	private String getArtistURL;
	private URL url;
	HttpURLConnection connection;
	public static  String response;
	public String getArtists(int size, String status,String sortingPart){
		String tag_json_obj = "json_obj_req";
		if(status.equals("sort")){
			this.getArtistURL = "http://freemusicarchive.org/api/get/artists.json?api_key=60BLHNQCAOUFPIBZ&limit="+Integer.toString(size)+sortingPart;
		}
		else{
			this.getArtistURL = "http://freemusicarchive.org/api/get/artists.json?api_key=60BLHNQCAOUFPIBZ&limit="+Integer.toString(size);

		}
		System.out.println(this.getArtistURL+"+++++++++++++++++++++++++++=");
	   
		         
		        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
		        		getArtistURL, null,
		                new Response.Listener<JSONObject>() {
		 
		                    @Override
		                    public void onResponse(JSONObject response) {
		                        Log.d("redd", response.toString());
		                        GetArtists2.response=response.toString();
		                        
		                    }
		                }, new Response.ErrorListener() {
		 
		                    @Override
		                    public void onErrorResponse(VolleyError error) {
		                        VolleyLog.d("exeee", "Error: " + error.getMessage());
		                        // hide the progress dialog
		                        
		                    }
		                });
		 
		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
		
		return response;
		
	}

}
