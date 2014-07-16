package com.imcore.myrssreader.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.imcore.myrssreader.R;
import com.imcore.myrssreader.util.MyTabListener;

public class MainActivity extends ActionBarActivity {
	private ActionBar mActionBar;
	private String[] rssList = {
			"http://news.baidu.com/n?cmd=4&class=civilnews&tn=rss",// ��������0
			"http://news.baidu.com/n?cmd=4&class=internews&tn=rss", // ��������1
			"http://news.baidu.com/n?cmd=1&class=edunews&tn=rss",// ��������2
			"http://news.baidu.com/n?cmd=1&class=socianews&tn=rss",// ��ό��3
			"http://news.baidu.com/n?cmd=1&class=internet&tn=rss",// ����������4
			"http://news.baidu.com/n?cmd=1&class=enternews&tn=rss",// ���ֽ���5
			"http://news.baidu.com/n?cmd=1&class=film&tn=rss",// ��Ӱ����6
			"http://news.baidu.com/n?cmd=1&class=healthnews&tn=rss",// Ů�˽���7
			"http://news.baidu.com/n?cmd=4&class=sportnews&tn=rss",// ��������8
			"http://news.baidu.com/n?cmd=1&class=gsdt&tn=rss"// ��˾��̬����9
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActionBar = getSupportActionBar();
		mActionBar.setTitle("������ҳ");
		// ���õ���ģʽΪTabѡ���ǩ����ģʽ
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// ����ActinBar���Tabѡ���ǩ
		mActionBar.addTab(mActionBar
				.newTab()
				.setText("���ڽ���")
				.setTabListener(
						new MyTabListener<FrgmtCommon>(this, FrgmtCommon.class,
								rssList[0])));

		mActionBar.addTab(mActionBar
				.newTab()
				.setText("���ʽ���")
				.setTabListener(
						new MyTabListener<FrgmtCommon>(this, FrgmtCommon.class,
								rssList[1])));

		mActionBar.addTab(mActionBar
				.newTab()
				.setText("��������")
				.setTabListener(
						new MyTabListener<FrgmtCommon>(this, FrgmtCommon.class,
								rssList[2])));

		mActionBar.addTab(mActionBar
				.newTab()
				.setText("��ό��")
				.setTabListener(
						new MyTabListener<FrgmtCommon>(this, FrgmtCommon.class,
								rssList[3])));

		mActionBar.addTab(mActionBar
				.newTab()
				.setText("����������")
				.setTabListener(
						new MyTabListener<FrgmtCommon>(this, FrgmtCommon.class,
								rssList[4])));

		mActionBar.addTab(mActionBar
				.newTab()
				.setText("���ֽ���")
				.setTabListener(
						new MyTabListener<FrgmtCommon>(this, FrgmtCommon.class,
								rssList[5])));

		mActionBar.addTab(mActionBar
				.newTab()
				.setText("��Ӱ����")
				.setTabListener(
						new MyTabListener<FrgmtCommon>(this, FrgmtCommon.class,
								rssList[6])));

		mActionBar.addTab(mActionBar
				.newTab()
				.setText("Ů�˽���")
				.setTabListener(
						new MyTabListener<FrgmtCommon>(this, FrgmtCommon.class,
								rssList[7])));

		mActionBar.addTab(mActionBar
				.newTab()
				.setText("��������")
				.setTabListener(
						new MyTabListener<FrgmtCommon>(this, FrgmtCommon.class,
								rssList[8])));
		mActionBar.addTab(mActionBar
				.newTab()
				.setText("��˾��̬����")
				.setTabListener(
						new MyTabListener<FrgmtCommon>(this, FrgmtCommon.class,
								rssList[9])));

	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_more:
			Intent intent = new Intent(MainActivity.this,
					SubscriptionActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
