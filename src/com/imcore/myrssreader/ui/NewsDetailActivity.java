package com.imcore.myrssreader.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.imcore.myrssreader.R;

public class NewsDetailActivity extends Activity {
	private WebView mWebView;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_detail);
		mWebView = (WebView) findViewById(R.id.wvNews);
		WebSettings webSettings = mWebView.getSettings();
		// ����֧������
		webSettings.setSupportZoom(true);
		// ����Ӧ��Ļ
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		webSettings.setLoadWithOverviewMode(true);
		// ȡ����������Ӧ
		// mWebView.setWebViewClient(new WebViewClient() {
		// @Override
		// public boolean shouldOverrideUrlLoading(WebView view, String url) {
		// return true;
		// }
		// });
		// ȡ��������Ϣ
		Intent intent = getIntent();
		String title = intent.getStringExtra("title");
		String link = intent.getStringExtra("url");
		getActionBar().setTitle(title);
		// ����ָ����URL��
		mWebView.loadUrl(link);
	}

}
