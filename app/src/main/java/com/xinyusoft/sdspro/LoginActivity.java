package com.xinyusoft.sdspro;

import java.util.Hashtable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.xinyusoft.sdspro.Application.SDSproContext;

public class LoginActivity extends Activity {

	private ImageView qr_image;
	private final static int QR_WIDTH = 200, QR_HEIGHT = 200;
	protected static final int POLLING = 20;
	private int imageW, imageH;
	private String url = "http://wxtest.xinyusoft.com/weixin/getRandomServlet";
	private String sessionId;
	RequestQueue queue;
	StringRequest str;
	String sid;

	Handler handle = new Handler() {

	};
	Runnable r = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			queue.add(str);

		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		qr_image = (ImageView) findViewById(R.id.imageView1);
		queue = SDSproContext.getRequestQueue();

		StringRequest stringRequest = new StringRequest(url,
				new Listener<String>() {

					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub
						String message = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx4fbfd12de50a98c7&redirect_uri=http://wxtest.xinyusoft.com/weixin/testServlet?sid="
								+ response
								+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
						createImage(message);
						String pollingurl = "http://wxtest.xinyusoft.com/weixin/testGetUserInfoServlet?sid="
								+ response;

						str = new StringRequest(pollingurl,
								new Listener<String>() {

									@Override
									public void onResponse(String response) {
										// TODO Auto-generated method stub
										if (response != null
												&& !"null".equals(response)) {
											Log.d("dd", "登陆成功" + response);

											Intent intent = new Intent(
													LoginActivity.this,
													MainActivity.class);
											intent.putExtra("logResponse",
													response);
											startActivity(intent);
											finish();

										} else {
											Log.d("dd", "response" + response);
											handle.postDelayed(r, 1000);
										}
									}

								}, null);

						queue.add(str);

					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub

					}
				});
//		queue.add(stringRequest);
		Intent intent = new Intent(LoginActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	// ����QRͼ
	private void createImage(String text) {
		try {
			QRCodeWriter writer = new QRCodeWriter();

			if (text == null || "".equals(text) || text.length() < 1) {
				return;
			}

			BitMatrix martix = writer.encode(text, BarcodeFormat.QR_CODE,
					QR_WIDTH, QR_HEIGHT);
			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix bitMatrix = new QRCodeWriter().encode(text,
					BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
			int[] pixels = new int[QR_WIDTH * QR_HEIGHT];

			Bitmap[] bitmaps = new Bitmap[2];
			bitmaps[1] = BitmapFactory.decodeResource(getResources(),
					R.drawable.logo);
			imageW = bitmaps[1].getWidth();
			imageH = bitmaps[1].getHeight();
			int startW = QR_WIDTH / 2 - imageW / 2;
			int starH = QR_HEIGHT / 2 - imageH / 2;
			for (int y = 0; y < QR_HEIGHT; y++) {
				for (int x = 0; x < QR_WIDTH; x++) {
					if ((x <= startW || x >= starH + imageW)
							|| (y <= starH || y >= +imageH)) {
						if (bitMatrix.get(x, y)) {
							pixels[y * QR_WIDTH + x] = 0xff000000;
						}
					} else {
					}
				}
			}

			Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT,
					Bitmap.Config.ARGB_8888);

			bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);

			bitmaps[0] = bitmap;

			qr_image.setImageBitmap(combineBitmaps(bitmaps, startW, starH));
			// qr_image.setImageBitmap(bitmap);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Bitmap combineBitmaps(Bitmap[] bitmaps, int w, int h) {

		Bitmap newBitmap = Bitmap.createBitmap(bitmaps[0].getWidth(),
				bitmaps[0].getHeight(), Config.ARGB_8888);
		Canvas cv = new Canvas(newBitmap);
		for (int i = 0; i < bitmaps.length; i++) {
			if (i == 0) {
				cv.drawBitmap(bitmaps[0], 0, 0, null);
			} else {
				cv.drawBitmap(bitmaps[i], w, h, null);
			}
			cv.save(Canvas.ALL_SAVE_FLAG);
			cv.restore();
		}
		return newBitmap;
	}


}
