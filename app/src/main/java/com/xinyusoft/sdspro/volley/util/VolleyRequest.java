package com.xinyusoft.sdspro.volley.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.xinyusoft.sdspro.Application.SDSproContext;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yueyingsk on 15/8/9.
 */
public class VolleyRequest {
	public static JsonObjectRequest jsonObjectRequest;
	public static Context context;
	public static String PREFERENCE_NAME = "shihengkeji";

	/**
	 * json GET请求
	 * 
	 * @param context
	 * @param url
	 * @param tag
	 * @param vif
	 */
	public static void RequestGet(Context context, String url, String tag,
			VolleyInterface vif) {

		SDSproContext.getRequestQueue().cancelAll(tag);
		jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
				null, vif.loadingListener(), vif.errorListener);
		jsonObjectRequest.setTag(tag);
		SDSproContext.getRequestQueue().add(jsonObjectRequest);
		// 不写也能执行
		// MyApplication.getHttpQueues().start();
	}

	/**
	 * 带有cookies的Json POST请求
	 * 
	 * @param context
	 * @param url
	 * @param tag
	 * @param jsonParams
	 * @param vif
	 */
	public static void RequestPostWithCookies(final Context context,
			String url, String tag, JSONObject jsonParams, VolleyInterface vif) {
		SDSproContext.getRequestQueue().cancelAll(tag);
		jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,
				jsonParams, vif.loadingListener(), vif.errorListener()) {

			private String rawCookies;

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("Accept", "application/json");
				headers.put("Content-Type", "application/json; charset=UTF-8");
				String sendcookies = getString(context, "Cookies", null);
				headers.put("Cookie", sendcookies);
				Log.d("VolleyRequest", "cookies2+++" + sendcookies);

				return headers;
			}

			@Override
			protected Response<JSONObject> parseNetworkResponse(
					NetworkResponse response) {
				try {

					Map<String, String> responseHeaders = response.headers;
					Log.e("VolleyRequest",
							"headers" + responseHeaders.toString());
					rawCookies = responseHeaders.get("Set-Cookie");
					putString(context, "Cookies", rawCookies);
					Log.d("VolleyRequest", "cookies1+++" + rawCookies);
					String dataString = new String(response.data, "UTF-8");
					JSONObject json;
					try {
						json = new JSONObject(dataString);
						return Response.success(json,
								HttpHeaderParser.parseCacheHeaders(response));
					} catch (JSONException e) {
						return Response.error(new ParseError(e));
					}

				} catch (UnsupportedEncodingException e) {
					return Response.error(new ParseError(e));
				}
			}
		};

		jsonObjectRequest.setTag(tag);
		SDSproContext.getRequestQueue().add(jsonObjectRequest);
		// 不写也能执行
		// MyApplication.getHttpQueues().start();
	}

	/**
	 * Json POST请求
	 * 
	 * @param context
	 * @param url
	 * @param tag
	 * @param jsonParams
	 * @param vif
	 */
	public static void RequestPost(Context context, String url, String tag,
			JSONObject jsonParams, VolleyInterface vif) {
		SDSproContext.getRequestQueue().cancelAll(tag);
		jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,
				jsonParams, vif.loadingListener(), vif.errorListener()) {

			private HashMap<String, String> headers;
			private String rawCookies;

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				headers = new HashMap<String, String>();
				headers.put("Accept", "application/json");
				headers.put("Content-Type", "application/json; charset=UTF-8");

				return headers;
			}

		};
		jsonObjectRequest.setTag(tag);
		SDSproContext.getRequestQueue().add(jsonObjectRequest);
		// 不写也能执行
		// MyApplication.getHttpQueues().start();
	}

	// 存储cookies
	public static boolean putString(Context context, String key, String value) {
		SharedPreferences settings = context.getSharedPreferences(
				PREFERENCE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(key, value);
		return editor.commit();
	}

	// 获取cookies
	public static String getString(Context context, String key,
			String defaultValue) {
		SharedPreferences settings = context.getSharedPreferences(
				PREFERENCE_NAME, Context.MODE_PRIVATE);
		return settings.getString(key, defaultValue);
	}

	//判断返回是否成功
	public static boolean getStatus(JSONObject json){
		
		try {
			if(json.getInt("status") == 1){
				return true;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	
	
}
