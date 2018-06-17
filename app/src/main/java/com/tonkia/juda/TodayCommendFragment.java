package com.tonkia.juda;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tonkia.juda.vo.ShopInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TONKIA on 2018/4/22.
 */

public class TodayCommendFragment extends Fragment {
    private List<ShopInfo> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_commend_today, container, false);
        init();
        RecyclerView rv = root.findViewById(R.id.recycle_view);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rv.setAdapter(new MyAdapter(list));
        return root;
    }

    private void init() {
        list = new ArrayList<>();
        ShopInfo si = new ShopInfo();
        si.setImg(R.drawable.lunbo1);
        list.add(si);
        si = new ShopInfo();
        si.setImg(R.drawable.lunbo2);
        list.add(si);
        si = new ShopInfo();
        si.setImg(R.drawable.lunbo3);
        list.add(si);
        si = new ShopInfo();
        si.setImg(R.drawable.lunbo4);
        list.add(si);
        si = new ShopInfo();
        si.setImg(R.drawable.lunbo2);
        list.add(si);
        si = new ShopInfo();
        si.setImg(R.drawable.lunbo3);
        list.add(si);
        si = new ShopInfo();
        si.setImg(R.drawable.lunbo4);
        list.add(si);

    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
        List<ShopInfo> mList;

        public MyAdapter(List<ShopInfo> list) {
            mList = list;
        }

        @Override
        public MyAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.item_today_shop, parent,
                    false);
            return new MyHolder(v);
        }

        @Override
        public void onBindViewHolder(MyAdapter.MyHolder holder, int position) {
            ShopInfo si = mList.get(position);
            Glide.with(getActivity()).load(si.getImg()).into(holder.iv);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            public ImageView iv;

            public MyHolder(View itemView) {
                super(itemView);
                iv = itemView.findViewById(R.id.img_view);
            }
        }
    }


}
