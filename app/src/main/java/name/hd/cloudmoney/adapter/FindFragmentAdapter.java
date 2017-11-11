package name.hd.cloudmoney.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by AkiRuka on 2017/9/25.
 */

public class FindFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> beans;
    public FindFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setBeans(List<Fragment> beans) {
        this.beans = beans;
    }

    @Override
    public Fragment getItem(int position) {
        return beans.get(position);
    }

    @Override
    public int getCount() {
        return beans!=null?beans.size():0;
    }
}
