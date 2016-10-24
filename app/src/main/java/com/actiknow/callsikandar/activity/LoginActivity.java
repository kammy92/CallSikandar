package com.actiknow.callsikandar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.utils.Constants;
import com.actiknow.callsikandar.utils.LoginDetailsPref;
import com.actiknow.callsikandar.utils.TypefaceSpan;
import com.actiknow.callsikandar.utils.Utils;

/**
 * Created by l on 19/10/2016.
 */
public class LoginActivity  extends AppCompatActivity {

    EditText etMobile;
    EditText etPassword;
    LinearLayout llForgotPassword;
    LinearLayout llSignUp;
    TextView tvLogIn;

    Toolbar toolbar;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
        initView();
        initData();
        initListener();
        setUpNavigationDrawer ();

    }

    private void initView() {
        etMobile = (EditText) findViewById (R.id.etPhone);
        etPassword=(EditText)findViewById(R.id.etPassword);
        llForgotPassword=(LinearLayout)findViewById(R.id.llForgotPassword);
        tvLogIn = (TextView) findViewById (R.id.tvLogIn);
        llSignUp = (LinearLayout) findViewById (R.id.llNeedAccount);
    }

    private void setUpNavigationDrawer () {
        toolbar = (Toolbar) findViewById (R.id.toolbar1);
        toolbar.showOverflowMenu ();
        SpannableString s = new SpannableString (getResources ().getString (R.string.activity_title_log_in));
        s.setSpan (new TypefaceSpan (LoginActivity.this, Constants.font_name), 0, s.length (), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setSupportActionBar (toolbar);
        ActionBar actionBar = getSupportActionBar ();
        try {
            assert actionBar != null;
            actionBar.setDisplayHomeAsUpEnabled (false);
            actionBar.setHomeButtonEnabled (false);
            actionBar.setDisplayShowTitleEnabled (true);
            actionBar.setTitle (s);
        } catch (Exception ignored) {
        }
    }


    private void initData () {
        Utils.setTypefaceToAllViews (this, tvLogIn);
    }

    private void initListener() {
        tvLogIn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                setPreferences ();
            }
        });
        llSignUp.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent (LoginActivity.this, HomeActivity.class);
                intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity (intent);
                overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }

    private void setPreferences () {
        LoginDetailsPref loginDetailsPref = LoginDetailsPref.getInstance ();

        loginDetailsPref.putStringPref (LoginActivity.this, LoginDetailsPref.USER_NAME, "Karman Singh");
        loginDetailsPref.putStringPref (LoginActivity.this, LoginDetailsPref.USER_EMAIL, "karman.singh@actiknowbi.com");
        loginDetailsPref.putStringPref (LoginActivity.this, LoginDetailsPref.USER_MOBILE, "9873684678");
        loginDetailsPref.putIntPref (LoginActivity.this, LoginDetailsPref.USER_ID, 1);


        Intent intent = new Intent (this, MainActivity.class);
        intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity (intent);
        overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);


//        loginDetailsPref.putStringPref (LoginActivity.this, LoginDetailsPref.USER_NAME, Constants.user_name);
//        loginDetailsPref.putStringPref (LoginActivity.this, LoginDetailsPref.USER_EMAIL, Constants.user_email);
//        loginDetailsPref.putStringPref (LoginActivity.this, LoginDetailsPref.USER_MOBILE, Constants.user_mobile);
//        loginDetailsPref.putIntPref (LoginActivity.this, LoginDetailsPref.USER_ID, Constants.user_id_main);
    }

    @Override
    public void onBackPressed () {
        finish ();
        overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
