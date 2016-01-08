package com.xinyusoft.sdspro.fragment;

import com.xinyusoft.sdspro.R;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BuyOrSaleFragment extends Fragment implements OnClickListener{
	private View view;
	private LinearLayout buy1_ll;
	private LinearLayout buy2_ll;
	private LinearLayout buy3_ll;
	private LinearLayout buy4_ll;
	private LinearLayout buy5_ll;
	private LinearLayout sale1_ll;
	private LinearLayout sale2_ll;
	private LinearLayout sale3_ll;
	private LinearLayout sale4_ll;
	private LinearLayout sale5_ll;
	private TextView sale1_numb;
	private TextView sale2_numb;
	private TextView sale3_numb;
	private TextView sale4_numb;
	private TextView sale5_numb;
	private TextView buy1_numb;
	private TextView buy2_numb;
	private TextView buy3_numb;
	private TextView buy4_numb;
	private TextView buy5_numb;
	private TextView buy1;
	private TextView buy2;
	private TextView buy3;
	private TextView buy4;
	private TextView buy5;
	private TextView sale1;
	private TextView sale2;
	private TextView sale3;
	private TextView sale4;
	private TextView sale5;
	private TextView stock_name;
	private TextView stock_code;
	private TextView stock_money;
	private TextView stock_percentage;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		view = inflater.inflate(R.layout.fragment_buy_sale,container,false);
		init();

		return view;
	}
	private void init(){
		buy1_ll = (LinearLayout) view.findViewById(R.id.buy_sale_buy1_ll);
		buy2_ll = (LinearLayout) view.findViewById(R.id.buy_sale_buy2_ll);
		buy3_ll = (LinearLayout) view.findViewById(R.id.buy_sale_buy3_ll);
		buy4_ll = (LinearLayout) view.findViewById(R.id.buy_sale_buy4_ll);
		buy5_ll = (LinearLayout) view.findViewById(R.id.buy_sale_buy5_ll);
		sale1_ll = (LinearLayout) view.findViewById(R.id.buy_sale_sale1_ll);
		sale2_ll = (LinearLayout) view.findViewById(R.id.buy_sale_sale2_ll);
		sale3_ll = (LinearLayout) view.findViewById(R.id.buy_sale_sale3_ll);
		sale4_ll = (LinearLayout) view.findViewById(R.id.buy_sale_sale4_ll);
		sale5_ll = (LinearLayout) view.findViewById(R.id.buy_sale_sale5_ll);
		sale1_numb = (TextView) view.findViewById(R.id.buy_sale_sale1numb);
		sale2_numb = (TextView) view.findViewById(R.id.buy_sale_sale2numb);
		sale3_numb = (TextView) view.findViewById(R.id.buy_sale_sale3numb);
		sale4_numb = (TextView) view.findViewById(R.id.buy_sale_sale4numb);
		sale5_numb = (TextView) view.findViewById(R.id.buy_sale_sale5numb);
		buy1_numb = (TextView) view.findViewById(R.id.buy_sale_stock_buy1numb);
		buy2_numb = (TextView) view.findViewById(R.id.buy_sale_stock_buy2numb);
		buy3_numb = (TextView) view.findViewById(R.id.buy_sale_stock_buy3numb);
		buy4_numb = (TextView) view.findViewById(R.id.buy_sale_stock_buy4numb);
		buy5_numb = (TextView) view.findViewById(R.id.buy_sale_stock_buy5numb);
		buy1 = (TextView) view.findViewById(R.id.buy_sale_stock_buy1);
		buy2 = (TextView) view.findViewById(R.id.buy_sale_stock_buy2);
		buy3 = (TextView) view.findViewById(R.id.buy_sale_stock_buy3);
		buy4 = (TextView) view.findViewById(R.id.buy_sale_stock_buy4);
		buy5 = (TextView) view.findViewById(R.id.buy_sale_stock_buy5);
		sale1 = (TextView) view.findViewById(R.id.buy_sale_sale1);
		sale2 = (TextView) view.findViewById(R.id.buy_sale_sale2);
		sale3 = (TextView) view.findViewById(R.id.buy_sale_sale3);
		sale4 = (TextView) view.findViewById(R.id.buy_sale_sale4);
		sale5 = (TextView) view.findViewById(R.id.buy_sale_sale5);
		stock_name = (TextView) view.findViewById(R.id.buy_sale_stock_name);
		stock_code = (TextView) view.findViewById(R.id.buy_sale_stock_code);
		stock_money = (TextView) view.findViewById(R.id.buy_sale_stock_money);
		stock_percentage = (TextView) view.findViewById(R.id.buy_sale_stock_percentage);


		buy1_ll.setOnClickListener(this);
		buy2_ll.setOnClickListener(this);
		buy3_ll.setOnClickListener(this);
		buy4_ll.setOnClickListener(this);
		buy5_ll.setOnClickListener(this);
		sale1_ll.setOnClickListener(this);
		sale2_ll.setOnClickListener(this);
		sale3_ll.setOnClickListener(this);
		sale4_ll.setOnClickListener(this);
		sale5_ll.setOnClickListener(this);




	}

	public void changeStock(String stockName,String stockCode){
		stock_name.setText(stockName);
		stock_code.setText(stockCode);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.buy_sale_buy1_ll:
				Toast.makeText(getActivity(),"买1",Toast.LENGTH_SHORT).show();
				break;
			case R.id.buy_sale_buy2_ll:
				Toast.makeText(getActivity(),"买2",Toast.LENGTH_SHORT).show();
				break;
			case R.id.buy_sale_buy3_ll:
				Toast.makeText(getActivity(),"买3",Toast.LENGTH_SHORT).show();
				break;
			case R.id.buy_sale_buy4_ll:
				Toast.makeText(getActivity(),"买4",Toast.LENGTH_SHORT).show();
				break;
			case R.id.buy_sale_buy5_ll:
				Toast.makeText(getActivity(),"买5",Toast.LENGTH_SHORT).show();
				break;
			case R.id.buy_sale_sale1_ll:
				Toast.makeText(getActivity(),"卖1",Toast.LENGTH_SHORT).show();
				break;
			case R.id.buy_sale_sale2_ll:
				Toast.makeText(getActivity(),"卖2",Toast.LENGTH_SHORT).show();
				break;
			case R.id.buy_sale_sale3_ll:
				Toast.makeText(getActivity(),"卖3",Toast.LENGTH_SHORT).show();
				break;
			case R.id.buy_sale_sale4_ll:
				Toast.makeText(getActivity(),"卖4",Toast.LENGTH_SHORT).show();
				break;
			case R.id.buy_sale_sale5_ll:
				Toast.makeText(getActivity(),"卖5",Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}


	}
}
