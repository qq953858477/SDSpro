package com.xinyusoft.sdspro;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.xinyusoft.sdspro.Application.SDSproContext;
import com.xinyusoft.sdspro.bean.AutoCompleteStock;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zzy on 2015/12/31.
 * 自动提示的适配器
 */
public class TestAdapter extends ArrayAdapter<AutoCompleteStock> implements Filterable {
    private Context ctx;
    private List<AutoCompleteStock> cityList = new ArrayList<AutoCompleteStock>();

    public TestAdapter(Context ctx, List<AutoCompleteStock> cityList) {
        super(ctx, R.layout.item_autocomplete_adapter, cityList);
        this.cityList = cityList;
        this.ctx = ctx;
    }

    @Override
    public AutoCompleteStock getItem(int position) {
        if (cityList != null)
            return cityList.get(position);
        return null;
    }

    @Override
    public int getCount() {
        if (cityList != null)
            return cityList.size();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View result = convertView;
        ViewHolder holder;
        if (result == null) {
            LayoutInflater inf = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            result = inf.inflate(R.layout.item_autocomplete_adapter, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) result.findViewById(R.id.tv_autocomplete_name);
            holder.symbol = (TextView) result.findViewById(R.id.tv_autocomplete_symbol);
            result.setTag(holder);
        } else {
            result = convertView;
            holder = (ViewHolder) result.getTag();
        }
        holder.name.setText(cityList.get(position).getName());
        holder.symbol.setText(cityList.get(position).getSymbol());
        return result;
    }


    private class ViewHolder {
        TextView name,symbol;
    }


    @Override
    public long getItemId(int position) {
        if (cityList != null)
            return cityList.get(position).hashCode();
        return 0;
    }

    @Override
    public Filter getFilter() {
        Filter cityFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint == null || constraint.length() < 2)
                    return results;
                List<AutoCompleteStock> cityResultList = changeAutoComplete(constraint.toString());


                results.values = cityResultList;
                results.count = cityResultList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                cityList = (List) results.values;
                notifyDataSetChanged();
            }
        };

        return cityFilter;
    }


    /**
     * 通过字符获取数据
     *
     * @param s
     */
    private List<AutoCompleteStock> changeAutoComplete(CharSequence s) {
        final List<AutoCompleteStock> dataList = new ArrayList<>();
        //异步转成同步的方法
        final CountDownLatch latch = new CountDownLatch(1);

        final RequestQueue queue = SDSproContext.getRequestQueue();
        String url = "http://120.26.117.113:8080/uuFinancialPlanner/ESBServlet?key=" + s + "&command=hq.SearchStockByKeyWords";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String data = jsonObject.getString("data");

                    JSONArray array = new JSONArray(data);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject stock = array.getJSONObject(i);
                        Log.i("zzy", "name:" + stock.getString("name") + "----symbol:" + stock.getString("symbol"));
                        dataList.add(new AutoCompleteStock(stock.getString("name"), stock.getString("symbol")));
                        latch.countDown();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    latch.countDown();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("zzy", "VolleyError:" + volleyError);
            }
        });
        queue.add(request);
        try {
            latch.await();
        } catch (InterruptedException e) {
        }

        return dataList;
    }
}
