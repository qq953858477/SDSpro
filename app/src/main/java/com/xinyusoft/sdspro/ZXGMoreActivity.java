package com.xinyusoft.sdspro;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.xinyusoft.sdspro.adapter.ZXGMoreAdapter;
import com.xinyusoft.sdspro.adapter.ZXGMoreChangeAdapter;

/**
 * 自选股的编辑界面
 */
public class ZXGMoreActivity extends Activity {

    private ZXGMoreAdapter adapter;
    private ZXGMoreChangeAdapter changeAdapter;
    private GridView gridView;  //默认显示的gridView
    private GridView gridViewChange; //当点击的时候，显示这个自选股的套利情况
    private boolean isChange;  //是否需要显示
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxgmore);
        gridView = (GridView) findViewById(R.id.moreactivity_more_gv);
        adapter = new ZXGMoreAdapter(this);
        gridView.setAdapter(adapter);
        gridViewChange = (GridView) findViewById(R.id.moreactivity_more_gv_change);
        changeAdapter = new ZXGMoreChangeAdapter(this);
        gridViewChange.setAdapter(changeAdapter);
        TextView tv_title = (TextView) findViewById(R.id.moreactivity_title_tv);
        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isChange) {
                    gridView.setVisibility(View.GONE);
                    gridViewChange.setVisibility(View.VISIBLE);
                } else {
                    gridView.setVisibility(View.VISIBLE);
                    gridViewChange.setVisibility(View.GONE);
                }
                isChange = !isChange;

            }
        });
    }
}
