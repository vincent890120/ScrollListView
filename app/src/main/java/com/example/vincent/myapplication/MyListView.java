package com.example.vincent.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by vincent on 16/1/13.
 */
public class MyListView extends ListView {

    private MyLinearLayout mCurView;
    public MyListView(Context context) {
        super(context);

    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                //我们想知道当前点击了哪一行
                int position = pointToPosition(x, y);
                if (position != INVALID_POSITION) {
                    DataHolder data = (DataHolder) getItemAtPosition(position);
                    mCurView = data.rootView;
                }
            }
            break;
            default:
                break;
        }
        if (mCurView != null) {
            mCurView.dispatchEvent(event);
        }
        return super.onTouchEvent(event);
    }

}