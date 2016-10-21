package com.actiknow.callsikandar.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomViewPager extends ViewPager {
    Context context;
    private int childId;

    public CustomViewPager (Context context, AttributeSet attrs) {
        super (context, attrs);
        this.context = context;
    }

    public void setChildId (int childId) {
        this.childId = childId;
    }

    @Override
    public boolean onInterceptTouchEvent (MotionEvent event) {
        // Never allow swiping to switch between pages
        return false;
    }

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        // Never allow swiping to switch between pages
        return false;
    }
}