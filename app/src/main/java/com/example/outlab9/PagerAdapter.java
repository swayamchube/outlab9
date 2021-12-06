package com.example.outlab9;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

public class PagerAdapter extends FragmentPagerAdapter  {
    private int numOfTabs;
    private TabLayout tabLayout;
    private Context mContext;
    public PagerAdapter(FragmentManager fm, int numOfTabs, Context context) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new StudyFragment(mContext);
            case 1:
                return new AssignmentFragment(mContext);
            case 2:
                return new ExamFragment(mContext);
            case 3:
                return new LectureFragment(mContext);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.numOfTabs;
    }
}
