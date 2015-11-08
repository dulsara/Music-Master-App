package com.portal.shared;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManagement {
	
	public static SharedPreferences pref;
	private static Editor editor;
	private static Context context;
	private static int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "MusicMasterSharedPref";
    private static final String LISTSIZE = "listViewSize";
    public static int update=0;
    
    public static void createSessionManagement(Context contxt){
        context = contxt;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        
    }
    public static void defineSize(String size){
        // Storing login value as TRUE
        editor.putInt(LISTSIZE,Integer.parseInt(size));
        editor.commit();
        update=1;
    } 
    
    public static int getlistSize(){
    	
    	return pref.getInt(LISTSIZE, 0);
    }
    
    public SharedPreferences getSharedPreferences(){
    	return pref;
    }
}
