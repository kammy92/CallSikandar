package com.actiknow.callsikandar.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.fragment.Support_1_Fragment;
import com.actiknow.callsikandar.utils.Constants;
import com.actiknow.callsikandar.utils.TypefaceSpan;
import com.actiknow.callsikandar.utils.Utils;

public class SupportActivity extends AppCompatActivity {
    TextView tvContactUs;
    Toolbar toolbar;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_support);
        initView ();
        initData ();
        initListener ();
        initAdapter ();
        setUpNavigationDrawer ();
    }

    private void initView () {
    }

    private void initData () {
        FragmentManager fragmentManager = getFragmentManager ();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ();
        Support_1_Fragment f1 = new Support_1_Fragment ();
        fragmentTransaction.add (R.id.fragment_container, f1, "Fragment_Support");
        fragmentTransaction.commit ();
    }

    private void initListener () {
    }

    private void initAdapter () {
    }

    private void setUpNavigationDrawer () {
        toolbar = (Toolbar) findViewById (R.id.toolbar1);
        toolbar.showOverflowMenu ();
        SpannableString s = new SpannableString ("Support");
        s.setSpan (new TypefaceSpan (SupportActivity.this, Constants.font_name), 0, s.length (), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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
        Utils.hideSoftKeyboard (SupportActivity.this);
        return super.onOptionsItemSelected (item);
    }

    @Override
    public void onBackPressed () {
        finish ();
        overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
