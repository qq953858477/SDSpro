package com.xinyusoft.sdspro.fragment;

import com.xinyusoft.sdspro.R;
import com.xinyusoft.sdspro.adapter.TaoLiAdapter;
import com.xinyusoft.sdspro.bean.TaoLi;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class InterestFragment extends Fragment{
	private View view;
	private ListView listView;
	private List<TaoLi> listDate;
	private TaoLiAdapter adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_interest,container,false);
		listView= (ListView) view.findViewById(R.id.interest_list);
		initList();
		adapter = new TaoLiAdapter(getActivity(),listDate);
		listView.setAdapter(adapter);
		return view;
	}

	private void initList(){
		listDate = new ArrayList<TaoLi>();
		listDate.add(new TaoLi("岳阳长兴"));
		listDate.add(new TaoLi("岳阳长兴"));
		listDate.add(new TaoLi("岳阳长兴"));
		listDate.add(new TaoLi("岳阳长兴"));
		listDate.add(new TaoLi("哈哈444"));
		listDate.add(new TaoLi("哈哈444"));
		listDate.add(new TaoLi("哈哈444"));
		listDate.add(new TaoLi("哈哈555"));

	}

	public void sortList(String name){
		if (name == null) {
			return;
		}
		List<TaoLi> tempList = new ArrayList<TaoLi>();

		for (int i = listDate.size() - 1; i >= 0; i--) {
			if (listDate.get(i).getStockName().equals(name)) {
				tempList.add(listDate.get(i));
				listDate.remove(i);
			}
		}

		for (int i = 0; i < tempList.size(); i++) {
			listDate.add(i,tempList.get(i));
		}
		tempList.clear();
		tempList = null;
		adapter.notifyDataSetChanged();
	}

}
