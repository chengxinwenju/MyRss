package com.imcore.myrssreader.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.imcore.myrssreader.R;
import com.imcore.myrssreader.model.NewsBean;
import com.imcore.myrssreader.util.NewsListAdapter;
import com.imcore.myrssreader.util.XMLRequest;

public class FrgmtCommon extends Fragment implements OnItemClickListener {
	private List<NewsBean> newsList;
	private ListView lvData;
	private String urlString;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.frgmt_common, null);

		Bundle bundle = getArguments();
		urlString = bundle.getString("url");

		lvData = (ListView) v.findViewById(R.id.newsLv);
		lvData.setOnItemClickListener(this);
		getData();
		return v;
	}

	private void getData() {
		// 国内新闻
		String url = urlString;
		XMLRequest request = new XMLRequest(url,
				new Response.Listener<XmlPullParser>() {
					@Override
					public void onResponse(XmlPullParser parser) {
						newsList = parserData(parser);
						NewsListAdapter adapter = new NewsListAdapter(
								getActivity(), newsList);
						lvData.setAdapter(adapter);
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError e) {
					}
				});
		Volley.newRequestQueue(getActivity()).add(request);
	}

	protected ArrayList<NewsBean> parserData(XmlPullParser parser) {
		ArrayList<NewsBean> data = null;
		try {
			int eventType = parser.getEventType();
			boolean flag = false;
			NewsBean bean = null;
			String nodeName = null;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					data = new ArrayList<NewsBean>();
					break;
				case XmlPullParser.START_TAG:
					nodeName = parser.getName();
					if ("item".equals(nodeName)) {
						bean = new NewsBean();
						flag = true;
					} else if (flag && "title".equals(nodeName)) {
						if (bean != null) {
							bean.title = parser.nextText();
						}
					} else if (flag && "link".equals(nodeName)) {
						if (bean != null) {
							bean.link = parser.nextText();
						}
					} else if (flag && "description".equals(nodeName)) {
						if (bean != null) {
							bean.description = parser.nextText();
						}
					} else if (flag && "pubDate".equals(nodeName)) {
						if (bean != null) {
							bean.pubDate = parser.nextText();
						}
					} else if (flag && "source".equals(nodeName)) {
						if (bean != null) {
							bean.source = parser.nextText();
						}
					} else if (flag && "author".equals(nodeName)) {
						if (bean != null) {
							bean.author = parser.nextText();
						}
					}
					break;
				case XmlPullParser.END_TAG:
					nodeName = parser.getName();
					if ("item".equals(nodeName)) {
						data.add(bean);
						bean = null;
						flag = false;
					}
					break;
				default:
					break;
				}
				eventType = parser.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return data;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long id) {
		Intent intent = new Intent();
		intent.setClass(getActivity(), NewsDetailActivity.class);
		intent.putExtra("url", newsList.get(position).link);
		intent.putExtra("title", newsList.get(position).title);
		startActivity(intent);
	}

}
