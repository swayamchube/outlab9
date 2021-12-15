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

import java.lang.reflect.Constructor;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudyFragment extends Fragment {

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

    public StudyFragment() {
        // Required empty public constructor
    }

    public StudyFragment(Context context) {
        this.mEvents = new ArrayList<String>();
        this.mContext = context;
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        ArrayList<EventModel> eventList = dbHelper.getWithType("study");
        if (eventList == null) return;
        for (EventModel event: eventList) {
            mEvents.add("Date: " + event.getDate() + "\n" + event.getDescription());
        }
    }

    public Context getContext() {
        return mContext;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudyFragment newInstance(String param1, String param2) {
        StudyFragment fragment = new StudyFragment();
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
        View view = inflater.inflate(R.layout.fragment_study, container, false);
        mRecyclerView = view.findViewById(R.id.studyRecyclerView);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(mContext, mEvents);
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mAddItemButton = view.findViewById(R.id.buttonAddStudyItem);
        mAddItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddStudyItemFragment addStudyItemFragment = new AddStudyItemFragment(StudyFragment.this);
                addStudyItemFragment.show(getParentFragmentManager(), "Something");
            }
        });
        return view;
    }

    public void addToList(String s) {
        mEvents.add(s);
    }

    public void refreshList() {
        mEvents.clear();
        DatabaseHelper dbHelper = new DatabaseHelper(this.mContext);
        ArrayList<EventModel> eventList = dbHelper.getWithType("study");
        if (eventList == null) return;
        for (EventModel event: eventList) {
            mEvents.add("Date: " + event.getDate() + "\n" + event.getDescription());
        }
    }

    public static class AddStudyItemFragment extends DialogFragment {
        StudyFragment mSF;
        AddStudyItemFragment(StudyFragment sf) {
            super();
            this.mSF = sf;
        }
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            View view = inflater.inflate(R.layout.fragment_addentry, container, false);
            EditText itemEditText = view.findViewById(R.id.enterText);
            EditText itemEditDate = view.findViewById(R.id.enterDate);
            Button addToListButton = view.findViewById(R.id.addToListButton);
            addToListButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String description = itemEditText.getText().toString();
                    String date = itemEditDate.getText().toString();
                    EventModel em = new EventModel("study", description, date);
                    DatabaseHelper dbHelper = new DatabaseHelper(mSF.getContext());
                    dbHelper.addEvent(em);
                    mSF.refreshList();
                }
            });
            return view;
        }
    }
}