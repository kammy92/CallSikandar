package com.actiknow.callsikandar.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actiknow.callsikandar.R;

/**
 * Created by l on 19/10/2016.
 */
public class LoginActivity  extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    LinearLayout llForgotPassword;
    Button btLogIn;
    TextView tvSignup;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
        initView();
        initData();
        initListener();

    }

    private void initView() {
        etEmail=(EditText)findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);
        llForgotPassword=(LinearLayout)findViewById(R.id.llForgotPassword);
        btLogIn=(Button)findViewById(R.id.btLogIn);
        tvSignup=(TextView)findViewById(R.id.tvSignup);

    }

    private void initData() {

    }

    private void initListener() {
    }
}
