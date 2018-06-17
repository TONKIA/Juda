package com.tonkia.juda;


import android.app.Fragment;
import android.content.Context;

import android.os.Bundle;

import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.tonkia.juda.vo.SelectedInfo;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class SelectedFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private List<Integer> images;
    private Context mContext;
    private List<SelectedInfo> list;
    private RecyclerView rv;
    private SwipeRefreshLayout srl;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_selected, container, false);
        //        用NestedScrollView替换ScrollView
        NestedScrollView nsv = root.findViewById(R.id.nested_scroll_view);
        nsv.setNestedScrollingEnabled(false);


        //        String title = getArguments().getString("title");
        //        TextView tv = root.findViewById(R.id.title);
        //        tv.setText(title);
        //数据初始化
        init();


        //轮播图
        Banner banner = (Banner) root.findViewById(R.id.banner);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        //recycleView
        rv = root.findViewById(R.id.recycle_view);
        //避免打开页面聚焦
        rv.setFocusable(false);
        rv.setLayoutManager(new GridLayoutManager(mContext, 2));
        rv.setAdapter(new MyRecycleAdapter(list));

        srl = root.findViewById(R.id.swipe_refresh);
        srl.setOnRefreshListener(this);
        srl.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        return root;
    }

    private void init() {
        //轮播图数据加载
        images = new ArrayList<>();
        images.add(R.drawable.lunbo1);
        images.add(R.drawable.lunbo2);
        images.add(R.drawable.lunbo3);
        images.add(R.drawable.lunbo4);

        //商品加载
        list = new ArrayList<>();
        list.add(new SelectedInfo(R.drawable.lunbo1, "XXXXXX", "XXXXXX", 120));
        list.add(new SelectedInfo(R.drawable.lunbo4, "XXXXXX", "XXXXXX", 40));
        list.add(new SelectedInfo(R.drawable.lunbo1, "XXXXXX", "XXXXXX", 12.5f));
        list.add(new SelectedInfo(R.drawable.lunbo2, "XXXXXX", "XXXXXX", 50));
        list.add(new SelectedInfo(R.drawable.lunbo3, "XXXXXX", "XXXXXX", 12.5f));
        list.add(new SelectedInfo(R.drawable.lunbo1, "XXXXXX", "XXXXXX", 80));
    }

    @Override
    public void onRefresh() {
        //上拉刷新逻辑代码
        new Handler().postDelayed(new Runnable() {
            public void run() {
                // 停止刷新
                srl.setRefreshing(false);
            }
        }, 2000); // 5秒后发送消息，停止刷新
    }

    class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            Glide.with(getActivity()).load(path).into(imageView);
        }
    }

    class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.Holder> {
        private List<SelectedInfo> mlist;

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected,
                    parent, false);
            return new Holder(v);
        }

        public MyRecycleAdapter(List<SelectedInfo> list) {
            mlist = list;
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            SelectedInfo si = mlist.get(position);
            holder.desc.setText(si.getDesc());

            Glide.with(getActivity()).load(si.getImg())
                    .into(holder.iv);
            holder.name.setText(si.getName());
            holder.price.setText("￥ " + si.getPrice());
        }

        @Override
        public int getItemCount() {
            return mlist.size();
        }

        class Holder extends RecyclerView.ViewHolder {
            public ImageView iv;
            public TextView price;
            public TextView name;
            public TextView desc;

            public Holder(View itemView) {
                super(itemView);
                iv = itemView.findViewById(R.id.img_view);
                price = itemView.findViewById(R.id.tv_price);
                name = itemView.findViewById(R.id.tv_name);
                desc = itemView.findViewById(R.id.tv_desc);
            }
        }

    }
}
