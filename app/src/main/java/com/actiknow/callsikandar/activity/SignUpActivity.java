package com.actiknow.callsikandar.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actiknow.callsikandar.R;

public class SignUpActivity  extends AppCompatActivity {
    Button btCreateAccount;

    EditText etPhoneNumber;
    EditText etOtpNumber;
    TextView tvResend;
    Button btSubmit;
    Button btSendSMSCode;
    LinearLayout llAlreadyAccount;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_signup);
        initView();
        initData();
        initListener();

    }

    private void initView() {
      //  tvLogIn=(TextView)findViewById(R.id.tvLogIn);
        etPhoneNumber=(EditText)findViewById(R.id.etPhoneNumber);
        etOtpNumber=(EditText)findViewById(R.id.etOtpNumber);
        tvResend=(TextView)findViewById(R.id.tvResend);
        btSubmit=(Button)findViewById(R.id.btSubmit);
        btSendSMSCode=(Button)findViewById(R.id.btSendSMSCode);
        llAlreadyAccount=(LinearLayout)findViewById(R.id.llAlreadyAccount);

    }

    private void initData() {

    }

    private void initListener() {
    }
}
