package com.usv.androidtestapp.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.usv.androidtestapp.activity.InsertActivity;
import com.usv.androidtestapp.activity.R;
import com.usv.androidtestapp.adapter.PostAdapter;
import com.usv.androidtestapp.model.Record;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {

    RecyclerView.Adapter adapter;
    private List<Record> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View postFragment = inflater.inflate(R.layout.fragment_posts, container, false);


        RecyclerView recyclerView = (RecyclerView) postFragment.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        list = new ArrayList<>();
        adapter = new PostAdapter(list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(itemAnimator);

        FloatingActionButton floatingActionButton = (FloatingActionButton)  postFragment.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InsertActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        return postFragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (data == null) {
                return;
            }
            Record record = data.getParcelableExtra("data");
            list.add(record);
            adapter.notifyDataSetChanged();
        }
    }

}
