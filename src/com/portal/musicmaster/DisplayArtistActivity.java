package com.portal.musicmaster;

import java.util.ArrayList;

import com.portal.model.ArtistModel;
import com.portal.shared.ViewHolder;
import com.squareup.picasso.Picasso;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

public class DisplayArtistActivity extends Activity{
	private ArrayList<ArtistModel> all_artists;
	private ListView artistdetais;
	private TextView name,rate;
	private ImageView image;
	private Context contxt;
	private EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.artist_tracks);
		ActionBar actionBar = getActionBar();		 
        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
		contxt=this;
		artistdetais=(ListView)findViewById(R.id.listView1);
		Intent mIntent = getIntent();
		all_artists=(ArrayList<ArtistModel>) mIntent.getSerializableExtra("artistsArray");
		editText= (EditText) findViewById(R.id.edittext);
		ViewAdaptor adapter = new ViewAdaptor(this, all_artists);
		artistdetais.setAdapter(adapter);
	       editText.addTextChangedListener(new TextWatcher() {

	            @Override
	            public void onTextChanged(CharSequence s, int start, int before, int count) {
	            	   int textlength = s.length();
	                   ArrayList<ArtistModel> tempArrayList = new ArrayList<ArtistModel>();
	                   for(ArtistModel c: all_artists){
	                      if (textlength <= c.getArtist_name().length()) {
	                         if (c.getArtist_name().toLowerCase().contains(s.toString().toLowerCase())) {
	                            tempArrayList.add(c);
	                         }
	                      }
	                   }
	                   ViewAdaptor adapter2 = new ViewAdaptor(contxt, tempArrayList);
	                   artistdetais.setAdapter(adapter2);
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
		artistdetais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
            	Intent intent = new Intent(DisplayArtistActivity.this,DisplayArtist_TrackDetails.class);
	            intent.putExtra("artist",all_artists.get(position));
	            intent.putExtra("track_or_artists", "artist");
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
	
	 private class ViewAdaptor extends ArrayAdapter<ArtistModel>{
        private Context context;
        private ArrayList<ArtistModel> artists;
		public ViewAdaptor(Context context, ArrayList<ArtistModel> artistis) {
			super(context, R.layout.single_listview_row, artistis);
			this.context=context;
			this.artists=artistis;
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
			rowHolder.textview.setText(artists.get(position).getArtist_name());
				Picasso.with(context).load(artists.get(position).getArtist_image_file()).resize(100, 100).centerCrop().into(rowHolder.imageview);
				
				return rowView;
				}
		}
		 
	 }


