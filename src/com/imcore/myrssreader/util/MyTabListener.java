package com.imcore.myrssreader.util;

import android.app.Activity;
import android.app.backup.BackupManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;

/**
 * Tab选项标签监听接口
 * 
 */
public class MyTabListener<T extends Fragment> implements TabListener {
	private Fragment mFragment;
	private final Activity mActivity;
	private final Class<T> mClass;
	private String url;

	public MyTabListener(Activity mActivity, Class<T> mClass, String url) {
		this.mActivity = mActivity;
		this.mClass = mClass;
		this.url = url;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		if (mFragment == null) {
			mFragment = Fragment.instantiate(mActivity, mClass.getName()); 
			Bundle bundle = new Bundle();
			bundle.putString("url", url);
			mFragment.setArguments(bundle);
			ft.add(android.R.id.content, mFragment, null);
		}
		ft.attach(mFragment);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		if (mFragment != null) {
			ft.detach(mFragment);
		}
	}

}
