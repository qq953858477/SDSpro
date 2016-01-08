package com.xinyusoft.sdspro.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinyusoft.sdspro.R;
import com.xinyusoft.sdspro.bean.Stock;

import java.util.List;

public class StockAdapter extends ArrayAdapter<Stock> {

	private int resourceId;
	private List<Stock> stockLists;
	private int mSelect = -1;  //选中的项
	private boolean isShowDelete;

	public StockAdapter(Context context, int textViewResourceId, List<Stock> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
		stockLists = objects;
	}

	@Override
	public int getCount() {
		return stockLists.size();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		Stock stock = getItem(position);
		View view = convertView;
		ViewHolder holder;
		if (view == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			holder = new ViewHolder();

			holder.tv_stockName = (TextView) view.findViewById(R.id.item_stock_name);

			holder.item_stock_code = (TextView) view.findViewById(R.id.item_stock_code);
			holder.zxg_alldelete_ll = (LinearLayout) view.findViewById(R.id.zxg_alldelete_ll);
			holder.zxg_alldelete_ll.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Log.i("zzy", "postion:" + position);
					stockLists.remove(position);  //现在只是移除
					isShowDelete = false;
					StockAdapter.this.notifyDataSetChanged();
				}
			});

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		if (mSelect == position) {
			view.setBackgroundResource(R.drawable.view_yuan_morelist_selected);
		} else {
			view.setBackgroundResource(R.drawable.view_yuan_morelist);
		}

		if (stock != null) {
			if (isShowDelete) {
				
				holder.zxg_alldelete_ll.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.zxg_translate_in));
				holder.zxg_alldelete_ll.setVisibility(View.VISIBLE);
			} else {
				holder.zxg_alldelete_ll.setVisibility(View.INVISIBLE);
			}
			holder.tv_stockName.setText(stock.getStockName());
			holder.item_stock_code.setText(stock.getStockCode());
		}
		return view;
	}

	private static class ViewHolder {
		TextView tv_stockName, item_stock_code;
		LinearLayout zxg_alldelete_ll;
	}

	public boolean isShowDelete() {
		return isShowDelete;
	}

	public void setIsShowDelete(boolean isShow) {
		isShowDelete = isShow;
	}


	public void changeSelected(int positon){ //刷新方法
		if(positon != mSelect){
			mSelect = positon;
			notifyDataSetChanged();
		}
	}
}
