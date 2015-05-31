package com.example.mac.exam;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    public class FragAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> mFragments;

        public FragAdapter(FragmentManager fm,ArrayList<Fragment> fragments) {
            super(fm);
            mFragments = fragments;
        }
        public Fragment getItem(int arg0) {

            return mFragments.get(arg0);
        }


        public int getCount() {

            return mFragments.size();
        }

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //构造适配器
        ArrayList<Fragment> fragments=new ArrayList<Fragment>();
        fragments.add(new SisterFragment());
        fragments.add(new ContentFragment());
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), fragments);

        //设定适配器
        ViewPager vp = (ViewPager)findViewById(R.id.pager);
        vp.setAdapter(adapter);
    }

}




