package i.iot_project_receiptapp;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import java.util.List;
/**
 * Created by tsaiyunyun on 2017/6/30.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    public PagerAdapter(FragmentManager fm, List<Fragment> fragments){
        super(fm);
        this.fragments = fragments;
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
    @Override
    public int getCount() {
        return fragments.size();
    }
}
