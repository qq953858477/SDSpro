package com.xinyusoft.sdspro.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xinyusoft.sdspro.R;

/**
 * Created by zzy on 2015/12/31.
 */
public class ZXGMoreAdapter extends BaseAdapter {

    private Context mContext;

    private boolean isChangeView = false;

    public ZXGMoreAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("zzy", "getview!!:" + isChangeView);

        View view = null;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_zxgmore_gridview, null);
        } else {
            view = convertView;
        }
        return view;

    }



}
