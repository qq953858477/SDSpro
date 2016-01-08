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
import android.widget.EditText;

public class IncreaseStockDialog extends DialogFragment{
    private View view;
    private Button add;
    private Button cancel;
    private DialogAddStockListener dialogListener;
    private String stockName;
    AutoCompleteTextView tv_auto;

    public void setDialogListener(DialogAddStockListener dialogListener){
        this.dialogListener = dialogListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        view = inflater.inflate(R.layout.increase_stock,container);
        tv_auto = (AutoCompleteTextView) view.findViewById(R.id.increase_tv_auto);
        final EditText et_name = (EditText) view.findViewById(R.id.buy_tv_name);
        tv_auto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AutoCompleteStock a = (AutoCompleteStock) parent.getItemAtPosition(position);
                stockName = a.getName();
                et_name.setText(stockName);
            }
        });
        AutoCompleteStockAdapter adapter = new AutoCompleteStockAdapter(getActivity(),null);
        tv_auto.setAdapter(adapter);
        add = (Button) view.findViewById(R.id.btn_add);
        cancel = (Button) view.findViewById(R.id.btn_cancel);
        add.setOnClickListener(add_listener);
        return view;
    }

    OnClickListener add_listener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Stock s = new Stock(stockName, tv_auto.getText().toString(), 111.2f, 222.1f, 22.1f, 55.3f);
            dialogListener.add(s);
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
