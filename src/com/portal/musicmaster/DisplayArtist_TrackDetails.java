package com.portal.musicmaster;

import com.portal.model.ArtistModel;
import com.portal.model.TrackModel;
import com.squareup.picasso.Picasso;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayArtist_TrackDetails extends Activity {
	
	private TextView name,rate,title,album,artist;
	private ImageView image;
	private Context contxt;

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent mIntent = getIntent();
		ActionBar actionBar = getActionBar();		 
        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
		contxt=this;
		Bundle bundle = getIntent().getExtras();
		String track_artist = bundle.getString("track_or_artists");
		
		if(track_artist.equals("artist")){
		setContentView(R.layout.artist_layout);
    	name=(TextView)findViewById(R.id.artistname);
		rate=(TextView)findViewById(R.id.artistfavorites);
		image=(ImageView)findViewById(R.id.artistimage);
		ArtistModel artist=(ArtistModel) mIntent.getSerializableExtra("artist");
    	name.setText("Name	:"+artist.getArtist_name());
    	rate.setText("Rate	:"+artist.getArtist_favorites()+"/10");
		Picasso.with(contxt).load(artist.getArtist_image_file()).resize(500, 400).centerCrop().into(image);
	    }
		else{
			
			setContentView(R.layout.track_layout);
        	title=(TextView)findViewById(R.id.track_title);
    		album=(TextView)findViewById(R.id.track_album);
    		artist=(TextView)findViewById(R.id.track_artist);
    		image=(ImageView)findViewById(R.id.trackimage);
    		TrackModel track=(TrackModel) mIntent.getSerializableExtra("track");
        	title.setText("Title	:"+track.getTrack_title());
        	album.setText("Album	:"+track.getAlbum_title());
        	artist.setText("Artist	:"+track.getArtist_name());
			Picasso.with(contxt).load(track.getTrack_image_file()).resize(500, 400).centerCrop().into(image);

		}
		
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
        	Intent intent = new Intent(Intent.ACTION_MAIN);
        	intent.addCategory(Intent.CATEGORY_HOME);
        	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        	startActivity(intent);
            
            return true;
        case R.id.sorting:
        	Intent intent1 = new Intent(this,SortingActivity.class);
        	startActivity(intent1);            return true;
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
}
