package com.example.outlab9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.sqlite.*;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/* TODO: Implement all the other callbacks */

public class MainActivity extends AppCompatActivity {
    private static TabLayout tabLayout;
    public static TabItem tabStudy;
    public static TabItem tabAssignment;
    public static TabItem tabExam;
    public static TabItem tabLecture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        mRecyclerView = findViewById(R.id.recyclerView);
        ArrayList<String> myList = new ArrayList<String>();
        myList.add("Hi");
        myList.add("My");
        myList.add("Name");
        myList.add("Is");
        myList.add("Swayam");

        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, myList);
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        */

        tabLayout = findViewById(R.id.tabLayout);
        tabStudy = findViewById(R.id.tabStudy);
        tabAssignment = findViewById(R.id.tabAssignment);
        tabExam = findViewById(R.id.tabExam);
        tabLecture = findViewById(R.id.tabLecture);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), this);

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //Toast.makeText(MainActivity.this, "Reselected", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_main);
        System.out.println("Restarted");
    }
}