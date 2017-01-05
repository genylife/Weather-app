package top.genylife.weather;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import top.genylife.weather.m.location.Location;

/**
 * Created by wanqi on 2016/12/15.
 *
 * @since 1.0.0
 */

public class FragmentsPagerAdapter extends FragmentStatePagerAdapter {

    private List<String> mDistricts;
    private List<Location> mLocations;

    FragmentsPagerAdapter(FragmentManager fragmentManager, List<String> districts, List<Location> locations) {
        super(fragmentManager);
        mDistricts = districts;
        mLocations = locations;
    }

    @Override
    public Fragment getItem(int position) {
        return WeatherFragment.create(mLocations.get(position), mDistricts.get(position));
    }

    @Override
    public int getCount() {
        return Math.min(mLocations.size(), mDistricts.size());
    }

    public void addItem(String district, Location location) {
        mDistricts.add(district);
        mLocations.add(location);
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void removeItem(String district) {
        int i = mDistricts.indexOf(district);
        mDistricts.remove(i);
        mLocations.remove(i);
        notifyDataSetChanged();
    }
}
