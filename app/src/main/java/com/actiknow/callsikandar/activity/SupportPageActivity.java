package com.actiknow.callsikandar.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
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

public class SupportPageActivity extends AppCompatActivity {

    Toolbar toolbar;
    int flag;
    private WebView wvTermOfUse;

    @SuppressLint("Recycle")
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_support_pages);
        Intent intent = getIntent ();
        flag = intent.getIntExtra ("FLAG", 0);
        Log.e ("Flag", "" + flag);


        setUpNavigationDrawer ();

        // setUpNavigationDrawer ();
        wvTermOfUse = (WebView) findViewById (R.id.wvTermOfUse);

        // uri loding in webview
        //wvAboutUs.loadUrl("file:///android_asset/webview.html");

        // url loding in webview
        switch (flag) {
            case 0:
                wvTermOfUse.loadUrl ("file:///android_asset/terms_of_use.html");
                break;
            case 1:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/pricing1.html");
                break;
            case 2:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/pricing2.html");
                break;
            case 3:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/serviceproviders1.html");
                break;
            case 4:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/serviceproviders2.html");
                break;
            case 5:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/serviceproviders3.html");
                break;
            case 6:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/serviceproviders4.html");
                break;
            case 7:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/doorstep.html");
                break;
            case 8:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/pickdrop.html");
                break;
            case 9:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/carcleaning1.html");
                break;
            case 10:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/carcleaning2.html");
                break;
            case 11:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/carcleaning3.html");
                break;
            case 12:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/carcleaning4.html");
                break;
            case 13:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/carcleaning5.html");
                break;
            case 14:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/carcleaning6.html");
                break;
            case 15:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/wheels1.html");
                break;
            case 16:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/wheels2.html");
                break;
            case 17:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/regularmaint1.html");
                break;
            case 18:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/regularmaint2.html");
                break;
            case 19:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/regularmaint3.html");
                break;
            case 20:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/regularmaint4.html");
                break;
            case 21:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/breakdown.html");
                break;
            case 22:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/servicerequest.html");
                break;
            case 23:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/servicerequest1.html");
                break;
            case 24:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/servicerequest2.html");
                break;
            case 25:
                wvTermOfUse.loadUrl ("file:///android_asset/support_pages/servicerequest3.html");
                break;
        }
        //wvTermOfUse.loadUrl ("file:///android_asset/breakdown.html");
        wvTermOfUse.clearCache (true);
        wvTermOfUse.clearHistory ();
        wvTermOfUse.getSettings ().setJavaScriptEnabled (true);
        wvTermOfUse.getSettings ().setJavaScriptCanOpenWindowsAutomatically (true);
    }

    private void setUpNavigationDrawer () {
        toolbar = (Toolbar) findViewById (R.id.toolbar1);
        toolbar.showOverflowMenu ();
        SpannableString s = new SpannableString ("Support");
        s.setSpan (new TypefaceSpan (SupportPageActivity.this, Constants.font_name), 0, s.length (), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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
        Utils.hideSoftKeyboard (SupportPageActivity.this);
        return super.onOptionsItemSelected (item);
    }

    @Override
    public void onBackPressed () {
        finish ();
        overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
    }


}
