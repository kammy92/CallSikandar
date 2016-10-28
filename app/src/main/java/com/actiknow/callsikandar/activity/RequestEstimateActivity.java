package com.actiknow.callsikandar.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.utils.Constants;
import com.actiknow.callsikandar.utils.TypefaceSpan;
import com.actiknow.callsikandar.utils.Utils;

/**
 * Created by l on 20/10/2016.
 */
public class RequestEstimateActivity extends AppCompatActivity {

    TextInputLayout input_layout_service_description;
    TextInputLayout input_layout_km_reading;
    TextInputLayout input_layout_year_of_manufacture;
    TextInputLayout input_layout_last_service_date;

    EditText etServiceDescription;
    EditText etKmReading;
    EditText etYearOfManufacture;
    EditText etLastServiceDate;

    CheckBox cbAuthorized;
    CheckBox cbMultiBrands;
    RadioGroup rgLocationServices;
    RadioButton rbPickupDropup;
    RadioButton rbDoorstep;

    Toolbar toolbar;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_request_estimate);
        initView ();
        initData ();
        initListener ();
        setUpNavigationDrawer ();

    }

    private void initView () {

        input_layout_service_description = (TextInputLayout) findViewById (R.id.input_layout_service_description);
        input_layout_km_reading = (TextInputLayout) findViewById (R.id.input_layout_km_reading);
        input_layout_year_of_manufacture = (TextInputLayout) findViewById (R.id.input_layout_year_of_manufacture);
        input_layout_last_service_date = (TextInputLayout) findViewById (R.id.input_layout_last_service_date);

        etServiceDescription = (EditText) findViewById (R.id.etServiceDescription);
        etKmReading = (EditText) findViewById (R.id.etKmReading);
        etYearOfManufacture = (EditText) findViewById (R.id.etYearOfManufacture);
        etLastServiceDate = (EditText) findViewById (R.id.etLastServiceDate);
        cbAuthorized = (CheckBox) findViewById (R.id.cbAuthorized);
        cbMultiBrands = (CheckBox) findViewById (R.id.cbMultiBrands);
        rgLocationServices = (RadioGroup) findViewById (R.id.rgLocationServices);
        rbPickupDropup = (RadioButton) findViewById (R.id.rbPickupDropup);
        rbDoorstep = (RadioButton) findViewById (R.id.rbDoorstep);

    }

    private void initData () {

    }

    private void initListener () {
    }

    private void setUpNavigationDrawer () {
        toolbar = (Toolbar) findViewById (R.id.toolbar1);
        toolbar.showOverflowMenu ();
        SpannableString s = new SpannableString (getResources ().getString (R.string.activity_title_service_request));
        s.setSpan (new TypefaceSpan (RequestEstimateActivity.this, Constants.font_name), 0, s.length (), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setSupportActionBar (toolbar);
        ActionBar actionBar = getSupportActionBar ();
        try {
            assert actionBar != null;
            actionBar.setDisplayHomeAsUpEnabled (true);
            actionBar.setHomeButtonEnabled (false);
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
        Utils.hideSoftKeyboard (RequestEstimateActivity.this);
        return super.onOptionsItemSelected (item);
    }

}
