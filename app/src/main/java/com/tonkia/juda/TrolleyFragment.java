package com.tonkia.juda;

import android.app.Fragment;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tonkia.juda.vo.SelectedInfo;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;


public class TrolleyFragment extends Fragment {
    private List<SelectedInfo> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_trolley, container, false);
        RecyclerView rv = root.findViewById(R.id.recycle_view);
        init();
        MyAdapter myAdapter = new MyAdapter(list);
        rv.setAdapter(myAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        return root;
    }

    private void init() {
        list = new ArrayList<>();
        SelectedInfo si = new SelectedInfo(R.drawable.lunbo1, "XXXXXX", "XXXXXX", 123);
        si.setAccount(5);
        list.add(si);
        si = new SelectedInfo(R.drawable.lunbo4, "XXXXXX", "XXXXXX", 40);
        si.setAccount(2);
        list.add(si);
        si = new SelectedInfo(R.drawable.lunbo4, "XXXXXX", "XXXXXX", 40);
        si.setAccount(1);
        list.add(si);
        si = new SelectedInfo(R.drawable.lunbo1, "XXXXXX", "XXXXXX", 40);
        si.setAccount(3);
        list.add(si);
        si = new SelectedInfo(R.drawable.lunbo2, "XXXXXX", "XXXXXX", 40);
        si.setAccount(1);
        list.add(si);
        si = new SelectedInfo(R.drawable.lunbo4, "XXXXXX", "XXXXXX", 40);
        si.setAccount(1);
        list.add(si);
        si = new SelectedInfo(R.drawable.lunbo4, "XXXXXX", "XXXXXX", 40);
        si.setAccount(1);
        list.add(si);
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private List<SelectedInfo> mList;

        public MyAdapter(List<SelectedInfo> list) {
            mList = list;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.item_trolley, parent,
                    false);
            MyViewHolder mvh = new MyViewHolder(v);
            return mvh;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            SelectedInfo si = mList.get(position);
            holder.tv_account.setText("X " + si.getAccount());
            holder.tv_price.setText("￥ " + si.getPrice());
            holder.tv_name.setText(si.getName());
            Glide.with(getActivity()).load(si.getImg()).into(holder.iv);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            public RadioButton rb;
            public ImageView iv;
            public TextView tv_name;
            public TextView tv_price;
            public TextView tv_account;

            public MyViewHolder(View itemView) {
                super(itemView);
                rb = itemView.findViewById(R.id.radio_btn);
                //RadioButton有问题

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rb.setChecked(!rb.isChecked());
                    }
                });
                iv = itemView.findViewById(R.id.img_view);
                tv_name = itemView.findViewById(R.id.tv_name);
                tv_price = itemView.findViewById(R.id.tv_price);
                tv_account = itemView.findViewById(R.id.tv_account);
            }
        }
    }
}
