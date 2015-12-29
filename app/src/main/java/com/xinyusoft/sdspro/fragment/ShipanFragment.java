package com.xinyusoft.sdspro.fragment;

import com.xinyusoft.sdspro.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ShipanFragment extends Fragment {
	
	private View view;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		StockFragment stockFragment = new StockFragment();
		InterestFragment interestFragment = new InterestFragment();
		BuyOrSaleFragment buy = new BuyOrSaleFragment();
		getFragmentManager().beginTransaction().replace(R.id.ll_main_layout_stockinfo, stockFragment).commit();
		getFragmentManager().beginTransaction().replace(R.id.fragment_interest_linearlayout, interestFragment).commit();
		getFragmentManager().beginTransaction().replace(R.id.fragment_buy_sale_linearlayout, buy).commit();
		view = inflater.inflate(R.layout.fragment_main, container, false);
		return view;
	}
}
