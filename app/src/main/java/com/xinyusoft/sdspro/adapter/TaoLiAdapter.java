package com.xinyusoft.sdspro.adapter;

import com.xinyusoft.sdspro.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TaoLiAdapter extends BaseAdapter{
	
	private Context mContext;
	
	public TaoLiAdapter(Context context) {
		mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view;
		if(convertView == null) {
			view = LayoutInflater.from(mContext).inflate(R.layout.item_interest, null);
		} else {
			view = convertView;
		}
		return view;
	}

}
