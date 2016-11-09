package com.actiknow.callsikandar.activity;

/**
 * Created by actiknow on 11/7/16.
 */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.adapter.ServiceRequestDetailPagerAdapter;
import com.actiknow.callsikandar.utils.Constants;
import com.actiknow.callsikandar.utils.TypefaceSpan;
import com.actiknow.callsikandar.utils.Utils;


public class ServiceRequestDetailActivity extends AppCompatActivity {


    ViewPager viewPager;
    TabLayout tabLayout;


    Toolbar toolbar;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_service_request_detail);
        initView ();
        initData ();
        setUpNavigationDrawer ();

    }

    private void initData () {
        viewPager.setAdapter (new ServiceRequestDetailPagerAdapter (getSupportFragmentManager (), ServiceRequestDetailActivity.this, 1));
        tabLayout.setupWithViewPager (viewPager);
        Utils.setTypefaceToAllViews (ServiceRequestDetailActivity.this, tabLayout);
    }

    private void initView () {
        viewPager = (ViewPager) findViewById (R.id.viewpager);
        tabLayout = (TabLayout) findViewById (R.id.sliding_tabs);
    }

    private void getExtras () {
        Bundle bundle = getIntent ().getExtras ();
//        service_provider_name = bundle.getString ("service_provider_name", "");
//        service_provider_id = bundle.getInt ("service_provider_id", 0);
    }

    private void initListener () {
    }

    private void setUpNavigationDrawer () {
        toolbar = (Toolbar) findViewById (R.id.toolbar1);
        toolbar.showOverflowMenu ();
        SpannableString s;
        s = new SpannableString ("Request #57656");
        s.setSpan (new TypefaceSpan (ServiceRequestDetailActivity.this, Constants.font_name), 0, s.length (), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setSupportActionBar (toolbar);
        ActionBar actionBar = getSupportActionBar ();
        try {
            assert actionBar != null;
            actionBar.setDisplayHomeAsUpEnabled (true);
            actionBar.setHomeButtonEnabled (true);
            actionBar.setDisplayShowTitleEnabled (true);
            actionBar.setTitle (s);
        } catch (Exception ignored) {
        }
    }

    @Override
    public void onBackPressed () {
        finish ();
        overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater ().inflate (R.menu.menu_blank, menu);
        return super.onCreateOptionsMenu (menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId ()) {
            case android.R.id.home:
                finish ();
                overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
                break;
        }
        Utils.hideSoftKeyboard (ServiceRequestDetailActivity.this);
        return super.onOptionsItemSelected (item);
    }

}