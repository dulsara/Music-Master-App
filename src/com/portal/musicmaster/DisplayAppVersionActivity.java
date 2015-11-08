package com.portal.musicmaster;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class DisplayAppVersionActivity extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.version_layout);
		ActionBar actionBar = getActionBar();		 
        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
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
}
