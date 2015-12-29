package com.xinyusoft.sdspro.fragment;

import com.xinyusoft.sdspro.R;
import com.xinyusoft.sdspro.adapter.TaoLiAdapter;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class JinrijiaoyiFragment extends Fragment{
	View view;
	ListView list;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		view = inflater.inflate(R.layout.fragment_jinrijiaoyi,container,false);
		list= (ListView) view.findViewById(R.id.jinrijiaoyi_list);
		TaoLiAdapter adapter = new TaoLiAdapter(getActivity());
		list.setAdapter(adapter);
		return view;
	}
}
