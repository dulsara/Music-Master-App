package com.portal.musicmaster;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.portal.model.ArtistModel;
import com.portal.model.TrackModel;
import com.portal.shared.SessionManagement;
import com.portal.webconnection.GetArtists;
import com.portal.webconnection.GetTracks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MusicMaster extends Activity {
	
	private Context context=this;
	private ArrayList<ArtistModel> artists=new ArrayList<ArtistModel>();
	private ArrayList<TrackModel> tracks=new ArrayList<TrackModel>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);
 
        return super.onCreateOptionsMenu(menu);
    }
	public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
        case R.id.action_search:
            // search action
            return true;
        case R.id.action_exit:
        	//finish();
        	System.exit(0);
            
            return true;
        case R.id.sorting:
        	Intent intent1 = new Intent(this,SortingActivity.class);
        	startActivity(intent1);
            return true;
        case R.id.action_list_number:
        	Intent intent2 = new Intent(this,ChangeListSizeActivity.class);
        	startActivity(intent2);
            return true;
        case R.id.action_check_updates:
        	Intent intent3 = new Intent(this,DisplayAppVersionActivity.class);
        	startActivity(intent3);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	
	public void viewArtists(View view){
		
		new GetallArtists().execute(new GetArtists());
		
		
	}
	
	public void viewtracks(View view){
		new GetAllTracks().execute(new GetTracks());
		
	}
	
	private  void checkInternetConenction(){
		ConnectivityManager check = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (check != null){
		         NetworkInfo[] info = check.getAllNetworkInfo();
		         if (info != null) {
		            for (int i = 0; i <info.length; i++) 
		            	if (info[i].getState() == NetworkInfo.State.CONNECTED){
		            		Toast.makeText(context, "Internet is connected",Toast.LENGTH_SHORT).show();
		            		}
		            }
		         }
			
		      else{
		         Toast.makeText(context, "not conencted to internet",Toast.LENGTH_SHORT).show();
		         }
		}
	
	private class GetallArtists extends AsyncTask<GetArtists, Void, String>{
		ProgressDialog progress = new ProgressDialog(context);
		
		protected void onPreExecute(){
		      checkInternetConenction();
		      progress.setMessage("loading");
		      progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		      progress.setIndeterminate(true);
		      progress.setProgress(0);
		      progress.setMax(100);
		   }

		@Override
		protected String doInBackground(GetArtists... params) {
			// TODO Auto-generated method stub
			SessionManagement.createSessionManagement(context);
			int size;
			if(SessionManagement.update==0){
				size=20;
			}
			else{
				size=SessionManagement.getlistSize();
			}
			try {
				return params[0].getArtists(size,"notApplicable","notApplicable");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Error";
			}
		}
		
		protected void onPostExecute(String result){
			
				displayArtists(result);
				
			
		}

	    protected void onProgressUpdate(Integer... progress) {
	    	this.progress.show();
	    }
		
	}
	
	private void displayArtists(String result)  {
		if(result==null){
			Toast.makeText(context, "Null passing",Toast.LENGTH_SHORT).show();
		}
		else{
		JSONObject jsonObject,jsonTrack;
		try {
			jsonObject = new JSONObject(result);
			
			JSONArray allTracks = jsonObject.getJSONArray("dataset");
			
			for(int i=0;i<allTracks.length();i++){
				jsonTrack=allTracks.getJSONObject(i);
				
				ArtistModel artist = new ArtistModel();
				artist.setArtist_id(jsonTrack.getString("artist_id"));
				artist.setArtist_name(jsonTrack.getString("artist_name"));
				artist.setArtist_favorites(jsonTrack.getString("artist_favorites"));
				artist.setArtist_image_file(jsonTrack.getString("artist_image_file"));
				artists.add(artist);
			}
			
            Intent intent = new Intent(MusicMaster.this,DisplayArtistActivity.class);
            intent.putExtra("artistsArray", artists);
            startActivity(intent);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
        
		
	}
	
	private String getCorrectURL(String url){
		System.out.println(url);

		String correctURL="";
		String[] urlparts= url.split("/");
		for(String parts:urlparts){
			correctURL=correctURL+parts;
		}
		return correctURL;
	}
	private class GetAllTracks extends AsyncTask<GetTracks, Void, String>{

		protected void onPreExecute(){
		      checkInternetConenction();
		}
		
		@Override
		protected String doInBackground(GetTracks... params) {
			SessionManagement.createSessionManagement(context);
			int size;
			if(SessionManagement.pref.contains("listViewSize")){
				size=20;
			}
			else{
				size=SessionManagement.getlistSize();
			}
			// TODO Auto-generated method stub
			try {
				return params[0].getTracks(size,"notApplicable","notApplicable");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Error";
			}
		}
		
		protected void onPostExecute(String result){
		      displayTracks(result);
		}

		private void displayTracks(String result) {
			if(result==null){
				Toast.makeText(context, "Null passing",Toast.LENGTH_SHORT).show();
			}
			else{
			JSONObject jsonObject,jsonTrack;
			try {
				jsonObject = new JSONObject(result);
				
				JSONArray allTracks = jsonObject.getJSONArray("dataset");
				System.out.println("Display json file ");
				for(int i=0;i<allTracks.length();i++){
					jsonTrack=allTracks.getJSONObject(i);
					
					TrackModel track = new TrackModel();
					track.setTrack_id(jsonTrack.getString("track_id"));
					track.setTrack_title(jsonTrack.getString("track_title"));
					track.setArtist_id(jsonTrack.getString("artist_id"));
					track.setArtist_name(jsonTrack.getString("artist_name"));
					track.setTrack_image_file(jsonTrack.getString("track_image_file"));
					track.setAlbum_id(jsonTrack.getString("album_id"));
					track.setAlbum_title(jsonTrack.getString("album_title"));
					tracks.add(track);
				}
				
	            Intent intent = new Intent(MusicMaster.this,DisplayTracksActivity.class);
	            intent.putExtra("tracksArray", tracks);
	            startActivity(intent);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		}
		
	}
	
	
	
	
	 

}
