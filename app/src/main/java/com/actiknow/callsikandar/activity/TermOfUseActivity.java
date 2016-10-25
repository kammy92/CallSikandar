package com.actiknow.callsikandar.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.utils.Constants;
import com.actiknow.callsikandar.utils.TypefaceSpan;
import com.actiknow.callsikandar.utils.Utils;

/**
 * Created by l on 25/10/2016.
 */

public class TermOfUseActivity extends AppCompatActivity {

    Toolbar toolbar;
    private WebView wvTermOfUse;

    @SuppressLint("Recycle")
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_term_of_use);

        setUpNavigationDrawer ();
        wvTermOfUse = (WebView) findViewById (R.id.wvTermOfUse);

        // uri loding in webview
        //wvAboutUs.loadUrl("file:///android_asset/webview.html");

        // url loding in webview
        wvTermOfUse.loadUrl ("file:///android_asset/terms_of_use.html");
        wvTermOfUse.clearCache (true);
        wvTermOfUse.clearHistory ();
        wvTermOfUse.getSettings ().setJavaScriptEnabled (true);
        wvTermOfUse.getSettings ().setJavaScriptCanOpenWindowsAutomatically (true);
    }

    private void setUpNavigationDrawer () {
        toolbar = (Toolbar) findViewById (R.id.toolbar1);
        toolbar.showOverflowMenu ();
        SpannableString s = new SpannableString (getResources ().getString (R.string.activity_title_terms_of_use));
        s.setSpan (new TypefaceSpan (TermOfUseActivity.this, Constants.font_name), 0, s.length (), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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
        Utils.hideSoftKeyboard (TermOfUseActivity.this);
        return super.onOptionsItemSelected (item);
    }
}
