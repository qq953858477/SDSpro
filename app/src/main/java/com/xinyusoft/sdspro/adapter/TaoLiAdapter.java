package com.xinyusoft.sdspro.adapter;

import com.xinyusoft.sdspro.R;
import com.xinyusoft.sdspro.bean.TaoLi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TaoLiAdapter extends BaseAdapter{
	
	private Context mContext;
	private List mList;
	
	public TaoLiAdapter(Context context,List<TaoLi> list) {
		mContext = context;
		mList = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		ViewHolder holder;
		if(convertView == null) {
			view = LayoutInflater.from(mContext).inflate(R.layout.item_interest, null);
			holder = new ViewHolder();
			holder.tv_stockName = (TextView) view.findViewById(R.id.interes_stock_name);
			//holder.item_stock_code = (TextView) view.findViewById(R.id.interes_stock_code);
			view.setTag(holder);
		}else{
			holder = (ViewHolder) view.getTag();
		}
		TaoLi taoli = (TaoLi) mList.get(position);
		holder.tv_stockName.setText(taoli.getStockName());
		//holder.item_stock_code.setText(taoli.getStockCode());
		return view;
	}

	private class ViewHolder {
		TextView tv_stockName, item_stock_code;
	}

}
