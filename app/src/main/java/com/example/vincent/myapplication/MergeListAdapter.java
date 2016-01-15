package com.example.vincent.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vincent on 16/1/13.
 */
public class MergeListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflager;
    private List<DataHolder> mDataList = new ArrayList<>();
    private TextView delTv;
    private View.OnClickListener clickListener;
    private OnScrollListener mOnScrollListener;

    public MergeListAdapter(Context mContext, List<DataHolder> dataList, View.OnClickListener clickListener, OnScrollListener mOnScrollListener) {
        this.mContext = mContext;
        this.mInflager = LayoutInflater.from(mContext);
        if (dataList != null && dataList.size() > 0) {
            mDataList.addAll(dataList);
        }
        this.clickListener = clickListener;
        this.mOnScrollListener = mOnScrollListener;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void removeItem(int position) {
        mDataList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null || convertView.getTag() == null) {
            viewHolder = new ViewHolder();
            convertView = mInflager.inflate(R.layout.item_layout, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        DataHolder dataHolder = mDataList.get(position);
        viewHolder.title.setText(dataHolder.title);
        dataHolder.rootView = (MyLinearLayout) convertView.findViewById(R.id.lin_root);
        dataHolder.rootView.scrollTo(0, 0);
        dataHolder.rootView.setOnScrollListener(mOnScrollListener);
        TextView delTv = (TextView) convertView.findViewById(R.id.del);
        delTv.setOnClickListener(clickListener);
        return convertView;
    }

    private static class ViewHolder {
        public TextView title;
    }
}
