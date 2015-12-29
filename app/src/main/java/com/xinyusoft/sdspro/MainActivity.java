package com.xinyusoft.sdspro;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.xinyusoft.sdspro.Application.SDSproContext;
import com.xinyusoft.sdspro.fragment.JinrijiaoyiFragment;
import com.xinyusoft.sdspro.fragment.HistoryStatisticsFragment;
import com.xinyusoft.sdspro.fragment.ShipanFragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity {

	RadioGroup radioGroupNavigation;
	ImageView headImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//getUserInfo();
		
		
		ShipanFragment fragment = new ShipanFragment();
		FragmentTransaction fragmentTransaction = getFragmentManager()
				.beginTransaction().replace(R.id.fragment_main, fragment);
		fragmentTransaction.commit();
		init();

	}

	private void getUserInfo() {
		headImage = (ImageView) findViewById(R.id.iv_head);
		String logResponse = getIntent().getStringExtra("logResponse");
		JSONObject json = null;
		String token = "";
		String openid = "";
		try {
			json = new JSONObject(logResponse);
			token = json.getString("access_token");
			openid = json.getString("openid");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String url = "https://api.weixin.qq.com/sns/userinfo?access_token="
				+ token + "&openid=" + openid + "&lang=zh_CN";
		JsonObjectRequest request = new JsonObjectRequest(url, null,
				new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						// TODO Auto-generated method stub
						Log.d("dd", "进去之后" + response.toString());

						String headimgurl = "";
						try {
							headimgurl = response.getString("headimgurl");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						ImageRequest imageRequest = new ImageRequest(
								headimgurl, new Listener<Bitmap>() {

									@Override
									public void onResponse(Bitmap response) {
										// TODO Auto-generated method stub
										
										
										headImage.setImageBitmap(toRoundBitmap(response));
									}
								}, 100, 100, Config.ARGB_8888,
								new ErrorListener() {

									@Override
									public void onErrorResponse(
											VolleyError error) {
										// TODO Auto-generated method stub

									}
								});
						SDSproContext.getRequestQueue().add(imageRequest);

					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub

					}
				});

		SDSproContext.getRequestQueue().add(request);
	}

	private void init() {
		radioGroupNavigation = (RadioGroup) findViewById(R.id.radioGroupNavigation);
		radioGroupNavigation
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						Fragment fragment = null;

						switch (checkedId) {
						case R.id.radioShipan:
							fragment = new ShipanFragment();

							break;
						case R.id.radioJinritongji:
							fragment = new JinrijiaoyiFragment();
							break;
						case R.id.radioLishishuju:
							fragment = new HistoryStatisticsFragment();
							break;
						case R.id.radioSetting:

							break;
						default:
							break;
						}

						FragmentTransaction fragmentTransaction = getFragmentManager()
								.beginTransaction().replace(R.id.fragment_main,
										fragment);
						fragmentTransaction.commit();
					}
				});

	}

	/**
	 * 转换图片成圆形
	 * 
	 * @param bitmap
	 *            传入Bitmap对象
	 * @return
	 */
	public Bitmap toRoundBitmap(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) {
			roundPx = width / 2;
			top = 0;
			bottom = width;
			left = 0;
			right = width;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2;
			float clip = (width - height) / 2;
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}
		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right,
				(int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top,
				(int) dst_right, (int) dst_bottom);
		final RectF rectF = new RectF(dst);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, src, dst, paint);
		return output;
	}

}
