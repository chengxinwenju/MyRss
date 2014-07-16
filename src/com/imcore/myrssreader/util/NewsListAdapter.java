package com.imcore.myrssreader.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.imcore.myrssreader.R;
import com.imcore.myrssreader.model.NewsBean;

public class NewsListAdapter extends BaseAdapter {
	private List<NewsBean> data;
	private Context mContext;

	public NewsListAdapter(Context context, List<NewsBean> data) {
		this.mContext = context;
		this.data = data;
	}

	@Override
	public int getCount() {
		System.out.println("getCount:" + data.size());
		return data == null ? 0 : data.size();
	}

	@Override
	public Object getItem(int arg0) {
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder vh = null;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.view_item_news, null);
			vh = new ViewHolder();
			vh.titleTv = (TextView) convertView.findViewById(R.id.titleTv);
			vh.descriptionTv = (TextView) convertView
					.findViewById(R.id.descriptionTv);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		vh.titleTv.setText(Html.fromHtml(data.get(position).title));
		vh.descriptionTv.setText(Html.fromHtml(data.get(position).description,
				new MyImageGetter(), null));
		// vh.descriptionTv.setMovementMethod(LinkMovementMethod.getInstance());
		return convertView;
	}

	class MyImageGetter implements ImageGetter {
		private BitmapCache mBitmapCache;

		public MyImageGetter() {
			mBitmapCache = new BitmapCache();
		}

		@SuppressLint("NewApi")
		@Override
		public Drawable getDrawable(String source) {
			source = URLDecoder.decode(source);
			// System.out.println("before replace:" + source);
			source = source.replace("http://t1.baidu.com/it/u=", "");
			source = source.replace("&fm=30", "");
			System.out.println("source:" + source);
			Drawable drawable = null;

			// ImageLoader imageLoader = new
			// ImageLoader(Volley.newRequestQueue(mContext),new BitmapCache());
			// ImageContainer imgCon = imageLoader.get(source, new
			// MyImageListener(drawable));
			// drawable = new BitmapDrawable(imgCon.getBitmap());
			// drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable
			// .getIntrinsicHeight());
			// System.out.println("get:" + drawable);
			/*
			 * BitmapCache bitmapCache = new BitmapCache(); Bitmap bitmap =
			 * bitmapCache.getBitmap(source); try { if(bitmap== null){ bitmap =
			 * BitmapFactory.decodeStream(new URL(source).openStream());
			 * bitmapCache.putBitmap(source, bitmap); } drawable = new
			 * BitmapDrawable(bitmap); drawable.setBounds(0, 0,
			 * drawable.getIntrinsicWidth(), drawable .getIntrinsicHeight());
			 * System.out.println("drawable:" + drawable); } catch
			 * (MalformedURLException e) { e.printStackTrace(); } catch
			 * (IOException e) { e.printStackTrace(); }
			 */
			Bitmap bitmap = mBitmapCache.getBitmap(source);
			if (bitmap == null) {
				try {
					drawable = new AsyncTask<String, Void, Drawable>() {
						@Override
						protected Drawable doInBackground(String... arg0) {
							/*
							 * Drawable drawable = null; try { drawable =
							 * Drawable.createFromStream(new
							 * URL(arg0[0]).openStream(), "");
							 * drawable.setBounds(0, 0,
							 * drawable.getIntrinsicWidth(), drawable
							 * .getIntrinsicHeight()); } catch
							 * (MalformedURLException e) { e.printStackTrace();
							 * } catch (IOException e) { e.printStackTrace(); }
							 * //»ñÈ¡ÍøÂ·Í¼Æ¬ return drawable;
							 */
							Drawable drawable = null;
							String source = arg0[0];
							try {
								Bitmap bitmap = BitmapFactory
										.decodeStream(new URL(source)
												.openStream());
								mBitmapCache.putBitmap(source, bitmap);
								drawable = new BitmapDrawable(bitmap);
								drawable.setBounds(0, 0,
										drawable.getIntrinsicWidth(),
										drawable.getIntrinsicHeight());
								System.out.println("drawable:" + drawable);
							} catch (MalformedURLException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
							return drawable;
						}

						@Override
						protected void onPostExecute(Drawable result) {
							super.onPostExecute(result);
						}
					}.execute(source).get();
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			} else {
				System.out.println("get cache");
				drawable = new BitmapDrawable(bitmap);
				drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
						drawable.getIntrinsicHeight());
			}
			return drawable;
		}

	}

	class ViewHolder {
		TextView titleTv, descriptionTv;
	}

	class MyImageListener implements ImageListener {
		private Drawable mDrawable;

		public MyImageListener(Drawable drawable) {
			this.mDrawable = drawable;
		}

		@Override
		public void onErrorResponse(VolleyError arg0) {

		}

		@Override
		public void onResponse(ImageContainer arg0, boolean arg1) {
			this.mDrawable = new BitmapDrawable(arg0.getBitmap());
			mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(),
					mDrawable.getIntrinsicHeight());
			System.out.println("onResponse:" + mDrawable);
		}

	}
}
