package com.portal.musicmaster;

import com.portal.shared.SessionManagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ChangeListSizeActivity extends Activity{
	private EditText changesize;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changelistzize_layout);
		changesize= (EditText) findViewById(R.id.changingNumber);
	}
	public void changeListSize(View view){
		SessionManagement.createSessionManagement(this);
		SessionManagement.defineSize(changesize.getText().toString());
		Intent intent = new Intent(this,MusicMaster.class);
    	startActivity(intent);
	}
}
