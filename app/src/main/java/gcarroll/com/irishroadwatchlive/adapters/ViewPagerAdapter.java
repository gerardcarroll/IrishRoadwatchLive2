package gcarroll.com.irishroadwatchlive.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import gcarroll.com.irishroadwatchlive.fragments.TabMotorwayFragment;
import gcarroll.com.irishroadwatchlive.fragments.TabNorthCityFragment;
import gcarroll.com.irishroadwatchlive.fragments.TabSouthCityFragment;

/**
 * Created by gcarroll on 24/02/2016. Need a ViewPager adapter to provide the views for every page i.e every Tab.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

  private final String LOG_TAG = ViewPagerAdapter.class.getSimpleName();

  public ViewPagerAdapter(final FragmentManager fm) {
    super(fm);
  }

  @Override
  public Fragment getItem(final int position) {

    switch (position) {
      case 0:
        return new TabMotorwayFragment(null);
      case 1:
        return new TabNorthCityFragment();
      default:
        return new TabSouthCityFragment();
    }
  }

  @Override
  public int getCount() {
    return 3; // As there are only 3 Tabs
  }
}
