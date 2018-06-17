package com.tonkia.juda;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class RecommendFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyViewPagerAdapter adapter;
    private List<Integer> images;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recommend, container, false);
        init();
        mTabLayout = root.findViewById(R.id.tab_layout);
        mViewPager = root.findViewById(R.id.view_pager);
        adapter = new MyViewPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

        //轮播图
        Banner banner = root.findViewById(R.id.banner);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        return root;
    }

    private void init() {
        //轮播图数据加载
        images = new ArrayList<>();
        images.add(R.drawable.lunbo1);
        images.add(R.drawable.lunbo2);
        images.add(R.drawable.lunbo3);
        images.add(R.drawable.lunbo4);
    }

    class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            Glide.with(getActivity()).load(path).into(imageView);
        }
    }


    class MyViewPagerAdapter extends FragmentPagerAdapter {
        private final String[] title = new String[]{
                "今日", "全部", "筛选"};
        private Fragment[] fragments = new Fragment[]{new TodayCommendFragment(), new
                AllCommendFragment(), new SelectedCommendFragment()};

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
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
