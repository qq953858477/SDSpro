package com.xinyusoft.sdspro.adapter;

import java.util.List;

import com.xinyusoft.sdspro.R;
import com.xinyusoft.sdspro.bean.History;
import com.xinyusoft.sdspro.bean.Stock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HistoryAdapter extends ArrayAdapter<History> {

	private int resourceId;
	private List<History> lists;

	public HistoryAdapter(Context context, int resource, List<History> objects) {
		super(context, resource, objects);
		resourceId = resource;
		lists = objects;
	}

	@Override
	public int getCount() {
		return lists.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;
		if (view == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			holder = new ViewHolder();
			holder.item_stock_name = (TextView) view.findViewById(R.id.item_history_info_stockName);
			holder.item_stock_code = (TextView) view.findViewById(R.id.item_history_info_stockCode);
			holder.item_stock_id = (TextView) view.findViewById(R.id.item_history_info_id);
			holder.item_stock_color = (TextView) view.findViewById(R.id.item_history_info_color);
			holder.item_stock_jiaoyiliang = (TextView) view.findViewById(R.id.item_history_info_jiaoyiliang);
			holder.item_stock_yikui = (TextView) view.findViewById(R.id.item_history_info_yikui);
			holder.item_stock_allCount = (TextView) view.findViewById(R.id.item_history_info_allCount);
			//holder.item_history_info_yili_bg = (LinearLayout) view.findViewById(R.id.item_history_info_yili_bg);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		History history = getItem(position);
		if (history != null) {
			float yikui = history.getYikui();
			if(yikui>0) {
				holder.item_stock_color.setBackgroundResource(R.color.red_shouyi);
				holder.item_stock_yikui.setBackgroundResource(R.color.red_shouyi);
			} else if(yikui<0){
				holder.item_stock_color.setBackgroundResource(R.color.green_kuishun);
				holder.item_stock_yikui.setBackgroundResource(R.color.green_kuishun);
			} else {
				holder.item_stock_color.setBackgroundResource(R.color.history_bukuibuying);
				holder.item_stock_yikui.setBackgroundResource(R.color.history_bukuibuying);
			}
			holder.item_stock_name.setText(history.getStockName());
			holder.item_stock_code.setText(history.getStockCode());
			holder.item_stock_id.setText((position + 1) + "");
			
			holder.item_stock_jiaoyiliang.setText(history.getJiaoyiliang()+"");
			holder.item_stock_yikui.setText(history.getYikui()+"");
			holder.item_stock_allCount.setText(history.getAllCount()+"");
		}
		return view;

	}

	private static class ViewHolder {
		TextView item_stock_name, item_stock_code, item_stock_id, item_stock_color, item_stock_jiaoyiliang, item_stock_yikui, item_stock_allCount;
	}
	
	
	
}
