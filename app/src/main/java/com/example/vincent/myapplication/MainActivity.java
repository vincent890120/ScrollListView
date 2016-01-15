package com.example.vincent.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnScrollListener {
    private MyLinearLayout mLastScrollView;
    private MyListView listView;
    private MergeListAdapter mergeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (MyListView) findViewById(R.id.listview);

        List<DataHolder> items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            DataHolder dataHolder = new DataHolder();
            dataHolder.title = "第" + i + "项";
            items.add(dataHolder);
        }
        mergeListAdapter = new MergeListAdapter(this, items, this, this);
        listView.setAdapter(mergeListAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.del) {
            int position = listView.getPositionForView(v);
            mergeListAdapter.removeItem(position);
        }
    }

    @Override
    public void OnScroll(MyLinearLayout view) {
        if (mLastScrollView != null) {
            mLastScrollView.smoothScrollTo(0, 0);
        }
        mLastScrollView = view;
    }
}
