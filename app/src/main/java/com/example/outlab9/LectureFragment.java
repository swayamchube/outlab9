package com.example.outlab9;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LectureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LectureFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mRecyclerView = null;
    private ArrayList<String> mEvents = null;
    private Context mContext = null;
    private Button mAddItemButton = null;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LectureFragment() {
        // Required empty public constructor
    }

    public LectureFragment(Context context) {
        this.mEvents = new ArrayList<String>();
        this.mContext = context;
        mEvents.add("First Event");
        mEvents.add("Second Event");
        mEvents.add("Third Event");
        mEvents.add("Fourth Event");
        mEvents.add("Fifth Event");
        mEvents.add("Sixth Event");
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LectureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LectureFragment newInstance(String param1, String param2) {
        LectureFragment fragment = new LectureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lecture, container, false);
        mRecyclerView = view.findViewById(R.id.lectureRecyclerView);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(mContext, mEvents);
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mAddItemButton = view.findViewById(R.id.buttonAddLectureItem);
        mAddItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LectureFragment.AddLectureItemFragment addLectureItemFragment = new LectureFragment.AddLectureItemFragment(LectureFragment.this);
                addLectureItemFragment.show(getParentFragmentManager(), "Something");
            }
        });
        return view;
    }

    public void addToList(String s) {
        mEvents.add(s);
    }

    public static class AddLectureItemFragment extends DialogFragment {
        LectureFragment mLF;
        AddLectureItemFragment(LectureFragment lf) {
            super();
            this.mLF = lf;
        }
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            View view = inflater.inflate(R.layout.fragment_addentry, container, false);
            EditText itemEditText = view.findViewById(R.id.enterText);
            Button addToListButton = view.findViewById(R.id.addToListButton);
            addToListButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String item = itemEditText.getText().toString();
                    mLF.addToList(item);
                }
            });
            return view;
        }
    }
}