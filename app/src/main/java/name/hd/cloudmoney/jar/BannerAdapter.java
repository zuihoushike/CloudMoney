/*
 * AUTHOR：Yolanda
 * 
 * DESCRIPTION：create the File, and add the content.
 *
 * Copyright © ZhiMore. All Rights Reserved
 *
 */
package name.hd.cloudmoney.jar;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 *
 */
public class BannerAdapter<T extends View> extends PagerAdapter {

	private Context mContext;

	private List<T> resIds;

	public BannerAdapter(Context context) {
		this.mContext = context;
	}

	public void update(List<T> resIds) {
		if (this.resIds != null)
			this.resIds.clear();
		if (resIds != null)
			this.resIds = resIds;
	}

	@Override
	public int getCount() {
		return resIds == null ? 0 : Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View v = resIds.get(position % resIds.size());
		ViewGroup parent = (ViewGroup) v.getParent();
		if (parent != null) {
			parent.removeView(v);
		}
		container.addView(v);
		return v;
	}
}
