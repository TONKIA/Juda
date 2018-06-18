package com.tonkia.juda;

import android.os.Bundle;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.library.bubbleview.BubbleTextView;
import com.tonkia.juda.utils.ChatRobot;
import com.tonkia.juda.vo.ChatMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ChatActivity extends AppCompatActivity {

    private ArrayList<ChatMessage> list;
    private EditText et;
    private RecyclerView rv;
    private MsgListAdapt adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        list = new ArrayList<>();

        et = (EditText) findViewById(R.id.edt_msg);
        rv = (RecyclerView) findViewById(R.id.msgRecycleView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapt = new MsgListAdapt();
        rv.setAdapter(adapt);
    }


    public void sendMsg(View view) {
        String msg = et.getText().toString().trim();
        if (!TextUtils.isEmpty(msg)) {
            list.add(new ChatMessage(ChatMessage.MSG_SEND, msg));
            et.setText("");
            adapt.notifyDataSetChanged();
            rv.scrollToPosition(list.size() - 1);
            ChatRobot.sendMsg(msg, new MsgCallBack());
        }
    }

    public void exit(View view) {
        finish();
    }

    public void clearMsg(View view) {
        list.clear();
        adapt.notifyDataSetChanged();
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String edString = savedInstanceState.getString("editText");
        et.setText(edString);
        list = savedInstanceState.getParcelableArrayList("list");
        adapt.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("editText", et.getText().toString());
        outState.putParcelableArrayList("list", list);
    }

    class MsgCallBack implements Callback {

        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, final Response response) throws IOException {
            String json = response.body().string();
            try {
                JSONObject jo = new JSONObject(json);
                int code = jo.getInt("code");
                if (code == 100000) {
                    final String text = jo.getString("text");

                    runOnUiThread(new Runnable() {
                        public void run() {
                            list.add(new ChatMessage(ChatMessage.MSG_RECEIVE, text));
                            adapt.notifyDataSetChanged();
                            rv.scrollToPosition(list.size() - 1);
                        }
                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    class MsgListAdapt extends RecyclerView.Adapter<MsgListAdapt.MsgViewHolder> {
        @Override
        public MsgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(ChatActivity.this).inflate(R.layout.item_chat_msg,
                    parent, false);
            return new MsgViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MsgViewHolder holder, int position) {
            ChatMessage msg = list.get(position);
            if (msg.getMsgType() == ChatMessage.MSG_RECEIVE) {
                holder.mine.setVisibility(View.GONE);
                holder.target.setVisibility(View.VISIBLE);
                holder.target_msg.setText(msg.getMsg());
            } else {
                holder.target.setVisibility(View.GONE);
                holder.mine.setVisibility(View.VISIBLE);
                holder.mine_msg.setText(msg.getMsg());
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MsgViewHolder extends RecyclerView.ViewHolder {
            RelativeLayout mine;
            LinearLayout target;
            ImageView mine_image;
            ImageView target_iamge;
            BubbleTextView mine_msg;
            BubbleTextView target_msg;

            public MsgViewHolder(View itemView) {
                super(itemView);
                mine = itemView.findViewById(R.id.mine);
                target = itemView.findViewById(R.id.target);
                mine_image = itemView.findViewById(R.id.mine_image);
                target_iamge = itemView.findViewById(R.id.target_image);
                mine_msg = itemView.findViewById(R.id.mine_msg);
                target_msg = itemView.findViewById(R.id.target_msg);
            }
        }
    }

}
