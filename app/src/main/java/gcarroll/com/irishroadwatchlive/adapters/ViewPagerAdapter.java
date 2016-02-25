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

  // Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
  CharSequence Titles[];

  // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
  int NumbOfTabs;

  // Build a Constructor and assign the passed Values to appropriate values in the class
  public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabs) {
    super(fm);

    this.Titles = mTitles;
    this.NumbOfTabs = mNumbOfTabs;

  }

  // This method return the fragment for the every position in the View Pager
  @Override
  public Fragment getItem(int position) {

    switch (position) {
      case 0:
        return new TabMotorwayFragment();
      case 1:
        return new TabNorthCityFragment();
      default:
        return new TabSouthCityFragment();
    }
  }

  // This method return the titles for the Tabs in the Tab Strip
  @Override
  public CharSequence getPageTitle(int position) {
    return Titles[position];
  }

  // This method return the Number of tabs for the tabs Strip
  @Override
  public int getCount() {
    return NumbOfTabs;
  }

}
