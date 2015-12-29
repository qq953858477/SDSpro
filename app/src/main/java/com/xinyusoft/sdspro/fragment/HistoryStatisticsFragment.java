package com.xinyusoft.sdspro.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;

import com.xinyusoft.sdspro.R;
import com.xinyusoft.sdspro.adapter.HistoryAdapter;
import com.xinyusoft.sdspro.bean.History;

public class HistoryStatisticsFragment extends Fragment {

	private View view;

	private List<History> lists;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		lists = getLists();
	}

	private List<History> getLists() {
		List<History> list = new ArrayList<History>();
		list.add(new History("股票名字", "303213", 33, 33, (-2.4f)));
		list.add(new History("股票名2", "111111", 33, 33, (12.4f)));
		list.add(new History("股票名3", "2222222", 33, 33, (0f)));
		list.add(new History("股票名字", "333333", 33, 33, (-2.4f)));
		list.add(new History("股票名2", "444444", 33, 33, (12.4f)));
		list.add(new History("股票名3", "3213213", 33, 33, (0f)));
		list.add(new History("股票名字", "3213213", 33, 33, (-2.4f)));
		list.add(new History("股票名2", "3213213", 33, 33, (12.4f)));
		list.add(new History("股票名3", "3213213", 33, 33, (0f)));
		list.add(new History("股票名字", "3213213", 33, 33, (-2.4f)));
		list.add(new History("股票名2", "3213213", 33, 33, (12.4f)));
		list.add(new History("股票名3", "000000", 33, 33, (0f)));
		return list;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_historystatistics, container, false);
		ListView listView = (ListView) view.findViewById(R.id.history_lv_info);

		HistoryAdapter adapter = new HistoryAdapter(getActivity(), R.layout.item_history_info, lists);

		listView.setAdapter(adapter);

		return view;
	}

}
