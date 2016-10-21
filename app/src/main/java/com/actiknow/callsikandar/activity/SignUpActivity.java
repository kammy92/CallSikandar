package com.actiknow.callsikandar.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actiknow.callsikandar.R;

public class SignUpActivity extends AppCompatActivity {
    EditText etPhoneNumber;
    EditText etOtpNumber;
    TextView tvResend;
    TextView tvSubmit;
    TextView tvSendSMSCode;
    LinearLayout llAlreadyAccount;
    Toolbar toolbar;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_signup);
        initView ();
        initData ();
        initListener ();
        setUpNavigationDrawer ();

    }

    private void initView () {
        //  tvLogIn=(TextView)findViewById(R.id.tvLogIn);
        etPhoneNumber = (EditText) findViewById (R.id.etMobileNumber);
        etOtpNumber = (EditText) findViewById (R.id.etOTP);
        tvResend = (TextView) findViewById (R.id.tvResend);
        tvSubmit = (TextView) findViewById (R.id.tvSubmit);
        tvSendSMSCode = (TextView) findViewById (R.id.tvSendSMSCode);
        llAlreadyAccount = (LinearLayout) findViewById (R.id.llAlreadyAccount);

    }

    private void setUpNavigationDrawer () {
        toolbar = (Toolbar) findViewById (R.id.toolbar1);
        toolbar.showOverflowMenu ();
        setSupportActionBar (toolbar);
        ActionBar actionBar = getSupportActionBar ();
        try {
            assert actionBar != null;
            actionBar.setDisplayHomeAsUpEnabled (false);
            actionBar.setHomeButtonEnabled (false);
            actionBar.setTitle (getResources ().getString (R.string.activity_title_sign_up));
            actionBar.setDisplayShowTitleEnabled (true);
        } catch (Exception ignored) {
        }
    }

    private void initData () {

    }

    private void initListener () {
    }

    @Override
    public void onBackPressed () {
        finish ();
        overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
