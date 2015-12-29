package com.xinyusoft.sdspro.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.xinyusoft.sdspro.R;
import com.xinyusoft.sdspro.adapter.StockAdapter;
import com.xinyusoft.sdspro.bean.Stock;

public class StockFragment extends Fragment implements OnClickListener {

	private View view;
	private List<Stock> listInfo;
	private SwipeMenuListView mListView;
	private StockAdapter adapter;
	private Context context;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		context = activity;
		listInfo = getDataList();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragement_stockinfo, container, false);
		mListView = (SwipeMenuListView) view.findViewById(R.id.fragment_stock_list);
		TextView zxg_delete_tv = (TextView) view.findViewById(R.id.zxg_delete_tv);
		zxg_delete_tv.setOnClickListener(this);
		getZXGActionInfo();
		createSwipemenulistview();

		return view;

	}

	/**
	 * 初始化listview
	 */
	private void createSwipemenulistview() {
		// step 1. create a MenuCreator
		SwipeMenuCreator creator = new SwipeMenuCreator() {

			@Override
			public void create(SwipeMenu menu) {
				// create "delete" item
				SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity().getApplicationContext());
				// set item background
				deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
				// set item width
				deleteItem.setWidth(dp2px(90));
				// set a icon
				deleteItem.setIcon(R.drawable.ic_delete);
				// add to menu
				menu.addMenuItem(deleteItem);
			}
		};
		mListView.setMenuCreator(creator);

		// step 2. listener item click event
		mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
				switch (index) {
				case 0:
					// delete
					listInfo.remove(position);
					adapter.notifyDataSetChanged();
					break;
				}
				return true;
			}
		});

		// set MenuStateChangeListener
		mListView.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
			@Override
			public void onMenuOpen(int position) {
			}

			@Override
			public void onMenuClose(int position) {
			}
		});

		// set SwipeListener
		mListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

			@Override
			public void onSwipeStart(int position) {
				// swipe start
			}

			@Override
			public void onSwipeEnd(int position) {
				// swipe end
			}
		});

		// test item long click
		mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getActivity().getApplicationContext(), position + " long click", Toast.LENGTH_SHORT).show();
				return false;
			}
		});
	}

	/**
	 * 获取自选股的数据
	 */
	private void getZXGActionInfo() {
		RequestQueue queue = Volley.newRequestQueue(getActivity());
		String url = "http://120.26.117.113:8080/uuFinancialPlanner/ESBServlet?userid=77&ordeCol=&ordeBy=&command=hq.getzxghqaction";
		StringRequest s = new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {

				Log.i("zzy", "responde:" + response);

				try {
					JSONObject jsonObject = new JSONObject(response);
					String data = jsonObject.getString("data");

					JSONArray array = new JSONArray(data);

					for (int i = 0; i < array.length(); i++) {
						JSONObject stock = array.getJSONObject(i);
						Log.i("zzy", "name:" + stock.getString("name"));
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}

				adapter = new StockAdapter(getActivity(), R.layout.item_stock22, listInfo);
				mListView.setAdapter(adapter);
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {

			}
		});

		queue.add(s);
	}

	private List<Stock> getDataList() {
		List<Stock> list = new ArrayList<Stock>();
		list.add(new Stock("哈哈股票", "000600", 111.2f, 222.1f, 22.1f, 55.3f));
		list.add(new Stock("哈哈444", "000600", 111.2f, 222.1f, 22.1f, 55.3f));
		list.add(new Stock("哈哈555", "000600", 111.2f, 222.1f, 22.1f, 55.3f));
		list.add(new Stock("哈哈666", "000600", 111.2f, 222.1f, 22.1f, 55.3f));
		return list;

	}

	private int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.zxg_delete_tv:
			adapter.setIsShowDelete(true);
			adapter.notifyDataSetChanged();
			break;

		default:
			break;
		}
	}

}
