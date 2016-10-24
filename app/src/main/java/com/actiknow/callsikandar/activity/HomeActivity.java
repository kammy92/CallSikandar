package com.actiknow.callsikandar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.utils.Utils;

/**
 * Created by l on 19/10/2016.
 */
public class HomeActivity extends AppCompatActivity {
    TextView tvCreateAccount;
    TextView tvLogIn;
    ImageView ivLogo;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_home);
        initView ();
        initData ();
        initListener ();

    }

    private void initView () {
        tvCreateAccount = (TextView) findViewById (R.id.tvCreateAccount);
        tvLogIn = (TextView) findViewById (R.id.tvLogIn);
        ivLogo = (ImageView) findViewById (R.id.ivLogo);
    }

    private void initData () {
        Utils.setTypefaceToAllViews (this, tvCreateAccount);
        final Handler handler = new Handler ();
        handler.postDelayed (new Runnable () {
            @Override
            public void run () {
                ivLogo.setVisibility (View.VISIBLE);
            }
        }, 600);
    }

    private void initListener () {
        tvLogIn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent (HomeActivity.this, LoginActivity.class);
                startActivity (intent);
                overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        tvCreateAccount.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent (HomeActivity.this, SignUpActivity.class);
                startActivity (intent);
                overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

    }
}
