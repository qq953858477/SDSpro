package com.xinyusoft.sdspro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xinyusoft.sdspro.adapter.ZXGMoreAdapter;
import com.xinyusoft.sdspro.bean.Stock;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 自选股的编辑界面
 */
public class ZXGMoreActivity extends Activity implements View.OnClickListener {

    private GridView gridView;  //默认显示的gridView
    private boolean isChange = true;  //是否需要显示行情
    private List<Stock> listInfo;     //数据的集合
    private ZXGMoreAdapter adapter;
    private ProgressBar progressBar;
    private RelativeLayout zxg_more_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxgmore);
        listInfo = new ArrayList<>();
        getZXGActionInfo();
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        initGridView();

        progressBar = (ProgressBar) findViewById(R.id.zxg_more_pb);
        zxg_more_main = (RelativeLayout) findViewById(R.id.zxg_more_main);
        TextView back = (TextView) findViewById(R.id.zxg_more_back);
        back.setOnClickListener(this);
        TextView tv_title = (TextView) findViewById(R.id.zxg_more_title_tv);
        tv_title.setOnClickListener(this);

    }

    /**
     * 初始化gridview
     */
    private void initGridView() {
        gridView = (GridView) findViewById(R.id.moreactivity_more_gv);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= listInfo.size()) {
                    Toast.makeText(ZXGMoreActivity.this, "点击了添加", 0).show();

                }
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //弹出提示框
                showAlertDialog(position);
                return false;
            }
        });
    }

    /**
     * 弹出提示框
     *
     * @param position
     */
    private void showAlertDialog(final int position) {
        //通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
        final AlertDialog.Builder builder = new AlertDialog.Builder(ZXGMoreActivity.this);
        //设置Title的图标
        builder.setIcon(R.drawable.ic_launcher);
        //设置Title的内容
        builder.setTitle("弹出警告框");
        //设置Content来显示一个信息
        builder.setMessage("确定删除吗？");
        //设置一个PositiveButton
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ZXGMoreActivity.this, "positive: " + which, Toast.LENGTH_SHORT).show();
                listInfo.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        //设置一个NegativeButton
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ZXGMoreActivity.this, "negative: " + which, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //显示出该对话框
        AlertDialog dialog = builder.show();


    }


    /**
     * 获取自选股的数据
     */
    private void getZXGActionInfo() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://120.26.117.113:8080/uuFinancialPlanner/ESBServlet?userid=77&ordeCol=&ordeBy=&command=hq.getzxghqaction";
        StringRequest s = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String data = jsonObject.getString("data");
                    JSONArray array = new JSONArray(data);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject stock = array.getJSONObject(i);
                        listInfo.add(new Stock(stock.getString("name"), "000600", 111.2f, 222.1f, 22.1f, 55.3f));
                    }
                    adapter = new ZXGMoreAdapter(ZXGMoreActivity.this, R.layout.item_stock_more, listInfo);
                    gridView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                    zxg_more_main.setVisibility(View.VISIBLE);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(s);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zxg_more_back:
                finish();
                break;
            case  R.id.zxg_more_title_tv:
                adapter.setIsShowHq(isChange);
                adapter.notifyDataSetChanged();
                isChange = !isChange;
                break;
        }
    }
}
