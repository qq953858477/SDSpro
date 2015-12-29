package com.xinyusoft.sdspro.volley.util;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yueyingsk on 15/8/17.
 */
public abstract class VolleyInterface
{
    public Context context;
    public static Response.Listener<JSONObject> listener;
    public static Response.ErrorListener errorListener;

    public abstract void onMySuccess(JSONObject result);
    public abstract  void onMyError(VolleyError error);

    public VolleyInterface (Context context, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener)
    {
        this.context = context;
        this.listener = listener;
        this.errorListener = errorListener;
    }

    public Response.Listener<JSONObject> loadingListener()
    {
        listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                onMySuccess(response);
            }
        };
        return listener;
    }

    public Response.ErrorListener errorListener()
    {
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onMyError(error);
            }
        };
        return errorListener;
    }
}
