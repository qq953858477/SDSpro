package com.xinyusoft.sdspro.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;


public class SDSproContext extends Application{

	public static RequestQueue queue;
	public static Application APP_CONTEXT;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		
		super.onCreate();
		APP_CONTEXT = this;
						
	}
	
	
	public static RequestQueue getRequestQueue(){
		if(queue == null){
			queue =Volley.newRequestQueue(APP_CONTEXT);	
		}
		return queue;
	}
}
