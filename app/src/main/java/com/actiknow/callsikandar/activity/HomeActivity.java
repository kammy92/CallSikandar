package com.actiknow.callsikandar.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.actiknow.callsikandar.R;

/**
 * Created by l on 19/10/2016.
 */
public class HomeActivity extends AppCompatActivity {
    Button btCreateAccount;
    TextView tvLogIn;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_home);
        initView();
        initData();
        initListener();

    }

    private void initView() {

    }

    private void initData() {

    }

    private void initListener() {
    }
}
