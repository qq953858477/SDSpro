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

public class BuyOrSaleFragment extends Fragment{
	View view;
	Button btn_buy;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		view = inflater.inflate(R.layout.fragment_buy_sale,container,false);
		btn_buy = (Button) view.findViewById(R.id.btn_buy);
		btn_buy.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				BuyOrSaleFragmentDialog dialog = new BuyOrSaleFragmentDialog();
				dialog.show(getFragmentManager(), null);
				
			}
		});
		return view;
	}

}
