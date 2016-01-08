package com.xinyusoft.sdspro.fragment;

import com.xinyusoft.sdspro.R;
import com.xinyusoft.sdspro.adapter.AutoCompleteStockAdapter;
import com.xinyusoft.sdspro.bean.AutoCompleteStock;
import com.xinyusoft.sdspro.bean.Stock;
import com.xinyusoft.sdspro.dialoginterface.DialogAddStockListener;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class BuyOrSaleFragmentDialog extends DialogFragment{
	private View view;
	private Button add;
	private Button cancel;
	private String stockName;



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		getDialog().setCancelable(false);
		getDialog().setCanceledOnTouchOutside(false);
		view = inflater.inflate(R.layout.buy,container);
		add = (Button) view.findViewById(R.id.btn_add);
		cancel = (Button) view.findViewById(R.id.btn_cancel);
		add.setOnClickListener(add_listener);
		return view;
	}
	
	OnClickListener add_listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			getDialog().dismiss();
		}
	};

	OnClickListener cancel_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			getDialog().dismiss();
		}
	};

}
