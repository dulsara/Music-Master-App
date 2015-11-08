package com.portal.shared;

import com.portal.musicmaster.R;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
	
	public ImageView imageview;
	public TextView textview;
	
	public ViewHolder(View v){
		imageview = (ImageView) v.findViewById(R.id.rowimage);
		textview = (TextView) v.findViewById(R.id.title);
	}
}
 