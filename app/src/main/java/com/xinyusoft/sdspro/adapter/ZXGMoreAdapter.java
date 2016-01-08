package com.xinyusoft.sdspro.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinyusoft.sdspro.R;
import com.xinyusoft.sdspro.bean.Stock;

import java.util.List;

public class ZXGMoreAdapter extends BaseAdapter {

    private int resourceId;
    private List<Stock> stockLists;
    private int mSelect = -1;  //选中的项
    private boolean isShowHq = false;
    private Context context;

    public ZXGMoreAdapter(Context context, int textViewResourceId, List<Stock> objects) {
        this.context = context;
        resourceId = textViewResourceId;
        stockLists = objects;
    }

    @Override
    public int getCount() {

        return stockLists.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        if (position >= stockLists.size()-1) {
            return position;
        } else {
            return stockLists.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //代表是最后一个item，使用增加按钮
        if (position >= stockLists.size()) {
            View addView = LayoutInflater.from(context).inflate(R.layout.item_stockmore_add, null);
            return addView;
        } else if (position <= stockLists.size()) {
            Log.i("zzy","position:"+position + "----"+convertView);
            View view = convertView;
            ViewHolder holder;
            if (view == null || view instanceof LinearLayout) {  //如果碰到添加视图，就重新创建一个新的
                view = LayoutInflater.from(context).inflate(resourceId, null);
                holder = new ViewHolder();
                holder.tv_stockName = (TextView) view.findViewById(R.id.item_stock_name);
                holder.item_stock_code = (TextView) view.findViewById(R.id.item_stock_code);
                holder.stockmore_ll_hq = (LinearLayout) view.findViewById(R.id.stockmore_ll_hq);
                holder.stockmore_ll_stockinfo = (LinearLayout) view.findViewById(R.id.stockmore_ll_stockinfo);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
//        //长亮显示？ 有必要？
//        if (mSelect == position) {
//            view.setBackgroundResource(R.drawable.view_yuan_morelist_selected);
//        } else {
//            view.setBackgroundResource(R.drawable.view_yuan_morelist);
//        }
            Stock stock = stockLists.get(position);
            if (stock != null) {
                if (isShowHq) {
                    holder.stockmore_ll_hq.setVisibility(View.VISIBLE);
                    holder.stockmore_ll_stockinfo.setVisibility(View.GONE);
                } else {
                    holder.stockmore_ll_stockinfo.setVisibility(View.VISIBLE);
                    holder.stockmore_ll_hq.setVisibility(View.GONE);
                    //只有显示股票信息的时候，才设置
                    holder.tv_stockName.setText(stock.getStockName());
                    holder.item_stock_code.setText(stock.getStockCode());
                }
            }
            return view;

        }
        return null;
    }

    private static class ViewHolder {
        TextView tv_stockName, item_stock_code;
        LinearLayout stockmore_ll_hq, stockmore_ll_stockinfo;

    }

    public boolean isShowHq() {
        return isShowHq;
    }

    public void setIsShowHq(boolean isShow) {
        this.isShowHq = isShow;
    }


    public void changeSelected(int positon) { //刷新方法
        if (positon != mSelect) {
            mSelect = positon;
            notifyDataSetChanged();
        }
    }
}
