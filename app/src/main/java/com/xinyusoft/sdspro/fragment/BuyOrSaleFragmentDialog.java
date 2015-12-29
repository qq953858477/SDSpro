package com.xinyusoft.sdspro.fragment;

import com.xinyusoft.sdspro.R;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

public class BuyOrSaleFragmentDialog extends DialogFragment{
	View view;
	Button add;
	Button cancel;
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
		cancel.setOnClickListener(cancel_listener);
		return view;
	}
	
	OnClickListener add_listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
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
