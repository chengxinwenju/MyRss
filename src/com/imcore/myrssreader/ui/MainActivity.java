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
			"http://news.baidu.com/n?cmd=4&class=civilnews&tn=rss",// 国内新闻0
			"http://news.baidu.com/n?cmd=4&class=internews&tn=rss", // 国际新闻1
			"http://news.baidu.com/n?cmd=1&class=edunews&tn=rss",// 教育焦点2
			"http://news.baidu.com/n?cmd=1&class=socianews&tn=rss",// 社会焦点3
			"http://news.baidu.com/n?cmd=1&class=internet&tn=rss",// 互联网焦点4
			"http://news.baidu.com/n?cmd=1&class=enternews&tn=rss",// 娱乐焦点5
			"http://news.baidu.com/n?cmd=1&class=film&tn=rss",// 电影焦点6
			"http://news.baidu.com/n?cmd=1&class=healthnews&tn=rss",// 女人焦点7
			"http://news.baidu.com/n?cmd=4&class=sportnews&tn=rss",// 体育焦点8
			"http://news.baidu.com/n?cmd=1&class=gsdt&tn=rss"// 公司动态焦点9
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActionBar = getSupportActionBar();
		mActionBar.setTitle("新闻首页");
		// 设置导航模式为Tab选项标签导航模式
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// 设置ActinBar添加Tab选项标签
		mActionBar.addTab(mActionBar
				.newTab()
				.setText("国内焦点")
				.setTabListener(
						new MyTabListener<FrgmtCommon>(this, FrgmtCommon.class,
								rssList[0])));

		mActionBar.addTab(mActionBar
				.newTab()
				.setText("国际焦点")
				.setTabListener(
						new MyTabListener<FrgmtCommon>(this, FrgmtCommon.class,
								rssList[1])));

		mActionBar.addTab(mActionBar
				.newTab()
				.setText("教育焦点")
				.setTabListener(
						new MyTabListener<FrgmtCommon>(this, FrgmtCommon.class,
								rssList[2])));

		mActionBar.addTab(mActionBar
				.newTab()
				.setText("社会焦点")
				.setTabListener(
						new MyTabListener<FrgmtCommon>(this, FrgmtCommon.class,
								rssList[3])));

		mActionBar.addTab(mActionBar
				.newTab()
				.setText("互联网焦点")
				.setTabListener(
						new MyTabListener<FrgmtCommon>(this, FrgmtCommon.class,
								rssList[4])));

		mActionBar.addTab(mActionBar
				.newTab()
				.setText("娱乐焦点")
				.setTabListener(
						new MyTabListener<FrgmtCommon>(this, FrgmtCommon.class,
								rssList[5])));

		mActionBar.addTab(mActionBar
				.newTab()
				.setText("电影焦点")
				.setTabListener(
						new MyTabListener<FrgmtCommon>(this, FrgmtCommon.class,
								rssList[6])));

		mActionBar.addTab(mActionBar
				.newTab()
				.setText("女人焦点")
				.setTabListener(
						new MyTabListener<FrgmtCommon>(this, FrgmtCommon.class,
								rssList[7])));

		mActionBar.addTab(mActionBar
				.newTab()
				.setText("体育焦点")
				.setTabListener(
						new MyTabListener<FrgmtCommon>(this, FrgmtCommon.class,
								rssList[8])));
		mActionBar.addTab(mActionBar
				.newTab()
				.setText("公司动态焦点")
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
