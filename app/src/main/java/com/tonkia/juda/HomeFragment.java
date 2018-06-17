package com.tonkia.juda;

import android.app.Fragment;
import android.app.FragmentManager;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;

import android.support.v13.app.FragmentPagerAdapter;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class HomeFragment extends Fragment {
    private View root;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyViewPagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        mTabLayout = root.findViewById(R.id.tab_layout);
        mViewPager = root.findViewById(R.id.view_pager);
        adapter = new MyViewPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    class MyViewPagerAdapter extends FragmentPagerAdapter {
        private final String[] title = new String[]{
                "女士", "男士", "情侣", "儿童"};
        private Fragment[] fragments = new Fragment[title.length];

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            if (fragments[i] == null) {
                fragments[i] = new SelectedFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title", title[i]);
                fragments[i].setArguments(bundle);
            }
            return fragments[i];
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

    }


}
