package com.example.ti.sampleapplication1.MyAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.ti.sampleapplication1.Fragment.Regist_Fragment;
import com.example.ti.sampleapplication1.Fragment.Category_Fragment;


/**
 * Created by TI on 2016/01/17.
 */
public class PagerAdapter extends FragmentStatePagerAdapter{
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new Category_Fragment();
            case 1:
                return new Regist_Fragment();
            default:
                return new Category_Fragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //return "Page" + position;
        switch (position) {
            case 0:
                return "カテゴリー";
            default:
                return "登録";
        }
    }
}
