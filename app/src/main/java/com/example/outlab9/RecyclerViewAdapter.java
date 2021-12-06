package com.example.outlab9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    LayoutInflater mInflater;
    ArrayList<String> mMessageList;

    public RecyclerViewAdapter(Context context, ArrayList<String> messageList) {
        this.mInflater = LayoutInflater.from(context);
        this.mMessageList = messageList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_view, parent, false);
        return new RecyclerViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        String mCurrent = mMessageList.get(position);
        holder.messageItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView messageItemView;
        public RecyclerViewAdapter mAdapter;

        public RecyclerViewHolder(View itemView, RecyclerViewAdapter adapter) {
            super(itemView);
            this.messageItemView = itemView.findViewById(R.id.message);
            this.mAdapter = adapter;
        }
    }
}
