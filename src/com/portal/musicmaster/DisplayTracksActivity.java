 package com.portal.musicmaster;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.portal.model.ArtistModel;
import com.portal.model.TrackModel;
import com.portal.shared.ViewHolder;
import com.squareup.picasso.Picasso;

public class DisplayTracksActivity extends Activity {
	private ArrayList<TrackModel> all_tracks= new ArrayList<TrackModel>();
	private ListView trackdetais;
	private TextView title,album,artist;
	private ImageView image;
	private Context contxt;
	private EditText editText;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.artist_tracks);
		ActionBar actionBar = getActionBar();		 
        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
		contxt=this;
		trackdetais=(ListView)findViewById(R.id.listView1);
//		title=(TextView)findViewById(R.id.track_title);
//		album=(TextView)findViewById(R.id.track_album);
//		artist=(TextView)findViewById(R.id.track_artist);
//		image=(ImageView)findViewById(R.id.trackimage);
//		
		Intent mIntent = getIntent();
		all_tracks=(ArrayList<TrackModel>) mIntent.getSerializableExtra("tracksArray");
		System.out.println(all_tracks.toString());
		ViewTrackAdaptor adapter = new ViewTrackAdaptor(this, all_tracks);
		editText= (EditText) findViewById(R.id.edittext);
		 editText.addTextChangedListener(new TextWatcher() {
        
	            @Override
	            public void onTextChanged(CharSequence s, int start, int before, int count) {
	            	   int textlength = s.length();
	                   ArrayList<TrackModel> tempArrayList = new ArrayList<TrackModel>();
	                   for(TrackModel c: all_tracks){
	                      if (textlength <= c.getTrack_title().length()) {
	                         if (c.getTrack_title().toLowerCase().contains(s.toString().toLowerCase())) {
	                            tempArrayList.add(c);
	                         }
	                      }
	                   }
	                   ViewTrackAdaptor adapter2 = new ViewTrackAdaptor(contxt, tempArrayList);
	                   trackdetais.setAdapter(adapter2);
	            }

	            @Override
	            public void beforeTextChanged(CharSequence s, int start, int count,
	                    int after) {
	                // TODO Auto-generated method stub

	            }

	            @Override
	            public void afterTextChanged(Editable s) {
	                // TODO Auto-generated method stub

	            }
	        });
		trackdetais.setAdapter(adapter);
		trackdetais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
            	Intent intent = new Intent(DisplayTracksActivity.this,DisplayArtist_TrackDetails.class);
	            intent.putExtra("track",all_tracks.get(position));
	            intent.putExtra("track_or_artists", "track");
	            startActivity(intent);
            }
        });
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
	

	
	  class ViewTrackAdaptor extends ArrayAdapter<TrackModel>{
        private Context context;
        private ArrayList<TrackModel> tracks;
		public ViewTrackAdaptor(Context context, ArrayList<TrackModel> traacks) {
			super(context, R.layout.single_listview_row,traacks);
			this.context=context;
			this.tracks=traacks;
		}
			// TODO Auto-generated constructor stub
	  public View getView(int position, View view, ViewGroup parent) {
		  View rowView=view;
		  ViewHolder rowHolder=null;
			if(rowView==null){
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);				
				rowView= inflater.inflate(R.layout.single_listview_row, parent, false);
				rowHolder = new ViewHolder(rowView); 
				rowView.setTag(rowHolder);
			}
			else{
				rowHolder = (ViewHolder) rowView.getTag();
			}
				
			rowHolder.textview.setText(tracks.get(position).getTrack_title());
				Picasso.with(context).load(tracks.get(position).getTrack_image_file()).resize(100,100).centerCrop().into(rowHolder.imageview);

				return rowView;
				}
		}
		 
	 }


