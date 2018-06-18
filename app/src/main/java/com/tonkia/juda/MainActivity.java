package com.tonkia.juda;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Transformation;


import com.tonkia.juda.utils.Utils;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private Fragment from;
    private HomeFragment homeF;
    private CommunicationFragment communicationF;
    private TrolleyFragment trolleyF;
    private MineFragment mineF;
    private RecommendFragment recommendF;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFragment(homeF, transaction);
                    return true;
                case R.id.navigation_communication:
                    if (communicationF == null) {
                        communicationF = new CommunicationFragment();
                        transaction.add(R.id.content, communicationF);
                    }
                    replaceFragment(communicationF, transaction);
                    return true;
                case R.id.navigation_trolley:
                    if (trolleyF == null) {
                        trolleyF = new TrolleyFragment();
                        transaction.add(R.id.content, trolleyF);
                    }
                    replaceFragment(trolleyF, transaction);
                    return true;
                case R.id.navigation_mine:
                    if (mineF == null) {
                        mineF = new MineFragment();
                        transaction.add(R.id.content, mineF);
                    }
                    replaceFragment(mineF, transaction);
                    return true;
                case R.id.navigation_recommend:
                    if (recommendF == null) {
                        recommendF = new RecommendFragment();
                        transaction.add(R.id.content, recommendF);
                    }
                    replaceFragment(recommendF, transaction);
                    return true;
            }
            return false;
        }

    };

    private void replaceFragment(Fragment to, FragmentTransaction transaction) {
        transaction.hide(from).show(to).commit();
        from = to;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Utils.disableShiftMode(navigation);
        fragmentManager = getFragmentManager();
        from = fragmentManager.findFragmentById(R.id.fragment_content);
        homeF = (HomeFragment) from;
    }

    public void startChatting(View view) {
        Intent i = new Intent(this, ChatActivity.class);
        startActivity(i);

    }

}
