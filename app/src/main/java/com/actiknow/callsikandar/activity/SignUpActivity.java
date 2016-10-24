package com.actiknow.callsikandar.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.utils.Constants;
import com.actiknow.callsikandar.utils.LoginDetailsPref;
import com.actiknow.callsikandar.utils.TypefaceSpan;
import com.actiknow.callsikandar.utils.Utils;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class SignUpActivity extends AppCompatActivity {
    TextInputLayout input_layout_mobile;
    TextInputLayout input_layout_otp;
    TextInputLayout input_layout_username;
    TextInputLayout input_layout_email;
    TextInputLayout input_layout_password;
    TextInputLayout input_layout_confirm_password;
    EditText etMobile;
    EditText etOTP;
    EditText etUsername;
    EditText etEmail;
    EditText etPassword;
    EditText etConfirmPassword;

    TextView tvResend;
    TextView tvVerify;
    TextView tvSubmit;
    TextView tvSendSMSCode;
    LinearLayout llAlreadyAccount;

    RelativeLayout rlOTP;
    RelativeLayout rlProfile;

    Toolbar toolbar;


    GoogleApiClient client;


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
        input_layout_mobile = (TextInputLayout) findViewById (R.id.input_layout_mobile);
        input_layout_otp = (TextInputLayout) findViewById (R.id.input_layout_otp);
        input_layout_username = (TextInputLayout) findViewById (R.id.input_layout_username);
        input_layout_email = (TextInputLayout) findViewById (R.id.input_layout_email);
        input_layout_password = (TextInputLayout) findViewById (R.id.input_layout_password);
        input_layout_confirm_password = (TextInputLayout) findViewById (R.id.input_layout_confirm_password);

        etMobile = (EditText) findViewById (R.id.etMobile);
        etOTP = (EditText) findViewById (R.id.etOTP);
        etUsername = (EditText) findViewById (R.id.etUsername);
        etEmail = (EditText) findViewById (R.id.etEmail);
        etPassword = (EditText) findViewById (R.id.etPassword);
        etConfirmPassword = (EditText) findViewById (R.id.etConfirmPassword);

        tvResend = (TextView) findViewById (R.id.tvResend);
        tvVerify = (TextView) findViewById (R.id.tvVerify);
        tvSubmit = (TextView) findViewById (R.id.tvSubmit);
        tvSendSMSCode = (TextView) findViewById (R.id.tvSendSMSCode);

        llAlreadyAccount = (LinearLayout) findViewById (R.id.llAlreadyAccount);
        rlOTP = (RelativeLayout) findViewById (R.id.rlOTP);
        rlProfile = (RelativeLayout) findViewById (R.id.rlProfile);
    }

    private void setUpNavigationDrawer () {
        toolbar = (Toolbar) findViewById (R.id.toolbar1);
        toolbar.showOverflowMenu ();
        SpannableString s = new SpannableString (getResources ().getString (R.string.activity_title_sign_up));
        s.setSpan (new TypefaceSpan (SignUpActivity.this, Constants.font_name), 0, s.length (), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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
        Utils.setTypefaceToAllViews (this, tvSendSMSCode);
        client = new GoogleApiClient.Builder (this).addApi (AppIndex.API).build ();
    }

    private void initListener () {
        tvSendSMSCode.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                getOTPFromServer (etMobile.getText ().toString ());
            }
        });

        tvVerify.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                verifyOTP (etMobile.getText ().toString (), etOTP.getText ().toString ());
            }
        });

        tvSubmit.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                setPreferences ();
            }
        });

        llAlreadyAccount.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent (SignUpActivity.this, HomeActivity.class);
                intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity (intent);
                overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }

    @Override
    public void onBackPressed () {
        finish ();
        overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
    }


    private void setPreferences () {
        LoginDetailsPref loginDetailsPref = LoginDetailsPref.getInstance ();

        loginDetailsPref.putStringPref (SignUpActivity.this, LoginDetailsPref.USER_NAME, "Karman Singh");
        loginDetailsPref.putStringPref (SignUpActivity.this, LoginDetailsPref.USER_EMAIL, "karman.singh@actiknowbi.com");
        loginDetailsPref.putStringPref (SignUpActivity.this, LoginDetailsPref.USER_MOBILE, "9873684678");
        loginDetailsPref.putIntPref (SignUpActivity.this, LoginDetailsPref.USER_ID, 1);


        Intent intent = new Intent (this, MainActivity.class);
        intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity (intent);
        overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);


//        loginDetailsPref.putStringPref (LoginActivity.this, LoginDetailsPref.USER_NAME, Constants.user_name);
//        loginDetailsPref.putStringPref (LoginActivity.this, LoginDetailsPref.USER_EMAIL, Constants.user_email);
//        loginDetailsPref.putStringPref (LoginActivity.this, LoginDetailsPref.USER_MOBILE, Constants.user_mobile);
//        loginDetailsPref.putIntPref (LoginActivity.this, LoginDetailsPref.USER_ID, Constants.user_id_main);
    }


    private void verifyOTP (String mobile, String otp) {
        rlOTP.setVisibility (View.GONE);
        rlProfile.setVisibility (View.VISIBLE);
        tvVerify.setVisibility (View.GONE);
        tvSubmit.setVisibility (View.VISIBLE);
    }

    private void getOTPFromServer (final String mobile) {



        /*
        if (NetworkConnection.isNetworkAvailable (this)) {
            Utils.showLog (Log.INFO, AppConfigTags.URL, AppConfigURL.URL_GETOTP, true);
            StringRequest strRequest2 = new StringRequest (Request.Method.POST, AppConfigURL.URL_GETOTP,
                    new Response.Listener<String> () {
                        @Override
                        public void onResponse (String response) {
                            Utils.showLog (Log.INFO, AppConfigTags.SERVER_RESPONSE, response, true);
                            if (response != null) {
                                try {
                                    JSONObject jsonObj = new JSONObject (response);
                                    boolean is_error = jsonObj.getBoolean (AppConfigTags.ERROR);
                                    String message = jsonObj.getString (AppConfigTags.MESSAGE);

                                } catch (JSONException e) {
                                    e.printStackTrace ();
                                }
                            } else {
                                Utils.showLog (Log.WARN, AppConfigTags.SERVER_RESPONSE, AppConfigTags.DIDNT_RECEIVE_ANY_DATA_FROM_SERVER, true);
                            }

                        }
                    },
                    new Response.ErrorListener () {
                        @Override
                        public void onErrorResponse (VolleyError error) {
                            Utils.showLog (Log.ERROR, AppConfigTags.VOLLEY_ERROR, error.toString (), true);
                            NetworkResponse response = error.networkResponse;
                            if (response != null && response.data != null) {
                                Utils.showLog (Log.ERROR, AppConfigTags.ERROR, new String (response.data), true);
//                                try {
//                                    JSONObject jsonObj = new JSONObject (new String (response.data));
//                                    boolean is_error = jsonObj.getBoolean (AppConfigTags.ERROR);
//                                    String message = jsonObj.getString (AppConfigTags.MESSAGE);
//                                    Utils.showLog (Log.ERROR, AppConfigTags.ERROR, "" + is_error, true);
//                                    Utils.showLog (Log.ERROR, AppConfigTags.MESSAGE, message, true);
//                                } catch (JSONException e) {
//                                    e.printStackTrace ();
//                                }
                            }
                        }
                    }) {

                @Override
                protected Map<String, String> getParams () throws AuthFailureError {
                    Map<String, String> params = new Hashtable<String, String> ();
                    params.put ("mobile", mobile);
                    Utils.showLog (Log.INFO, AppConfigTags.PARAMETERS_SENT_TO_THE_SERVER, "" + params, true);
                    return params;
                }

                @Override
                public Map<String, String> getHeaders (){
                    Map<String, String> headers = new HashMap<> ();
//                    Map<String, String> headers = super.getHeaders ();
//                    if (headers == null)
//                        headers = new HashMap<> ();

                    headers.put ("api_key", Constants.api_key);
                    headers.put ("user_login_key", Constants.user_login_key);
//                    headers.put ("Content-Type", "application/json; charset=utf-8");
//                    headers.put ("User-agent", "My useragent");
                    Utils.showLog (Log.INFO, AppConfigTags.HEADERS_SENT_TO_THE_SERVER, "" + headers, false);
                    return headers;
                }
            };
            Utils.sendRequest (strRequest2, 30);
        } else {
//            Utils.showOkDialog (.this, "Seems like there is no internet connection, the app will continue in Offline mode", false);
        }
*/

        input_layout_otp.setVisibility (View.VISIBLE);
        tvResend.setVisibility (View.VISIBLE);
        tvSendSMSCode.setVisibility (View.GONE);
        tvVerify.setVisibility (View.VISIBLE);

    }


    @Override
    public void onStart () {
        super.onStart ();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect ();
        Action viewAction = Action.newAction (
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse ("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse ("android-app://com.actiknow.callsikandar/http/host/path")
        );
        AppIndex.AppIndexApi.start (client, viewAction);
    }

    @Override
    public void onStop () {
        super.onStop ();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction (
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse ("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse ("android-app://com.actiknow.callsikandar/http/host/path")
        );
        AppIndex.AppIndexApi.end (client, viewAction);
        client.disconnect ();
    }

}
