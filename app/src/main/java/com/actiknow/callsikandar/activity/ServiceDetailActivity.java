package com.actiknow.callsikandar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.utils.Constants;
import com.actiknow.callsikandar.utils.SetTypeFace;
import com.actiknow.callsikandar.utils.TypefaceSpan;
import com.actiknow.callsikandar.utils.Utils;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by l on 27/10/2016.
 */

public class ServiceDetailActivity extends AppCompatActivity {

    TextView tvServiceProvider;
    TextView tvServiceDescription;
    TextView tvBookNow;
    TextView tvTotalPrice;

    Toolbar toolbar;

    String service_provider = "";
    String total_price = "";
    String service_name = "";


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_service_detail);
        initView ();
        getExtras ();
        initData ();
        initListener ();
        setUpNavigationDrawer ();

    }

    private void initView () {
        tvServiceProvider = (TextView) findViewById (R.id.tvServiceProvider);
        tvServiceDescription = (TextView) findViewById (R.id.tvServiceDescription);
        tvBookNow = (TextView) findViewById (R.id.tvBookNow);
        tvTotalPrice = (TextView) findViewById (R.id.tvTotalPrice);
    }

    private void initData () {
        Utils.setTypefaceToAllViews (ServiceDetailActivity.this, tvBookNow);

        tvServiceDescription.setText ("Ultimate exterior paint restoration package using 3M/Meguiars compounds. " +
                "Exterior wash, claying to remove stubborn dirt, sanding to remove imperfections and minor scratches / swirls, " +
                "polishing and waxing. Alloys and tire dressing is also included\nDoes not include Engine bay area and underbody / chassis");

        tvServiceProvider.setText (service_provider);
        tvTotalPrice.setText (getResources ().getString (R.string.Rs) + total_price);
    }

    private void getExtras () {
        Bundle bundle = getIntent ().getExtras ();
        service_provider = bundle.getString ("service_provider_name", "");
        total_price = bundle.getString ("price", "");
        service_name = bundle.getString ("service_name", "");
    }

    private void initListener () {
        tvBookNow.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                showConfirmationDialog ();
            }
        });
    }


    private void showConfirmationDialog () {
        MaterialDialog dialog = new MaterialDialog.Builder (this)
                .iconRes (R.mipmap.ic_launcher)
                .limitIconToDefaultSize ()
                .title (R.string.dialog_booking_confirmation_title)
                .content ("Book " + service_name + " for your car?")
                .positiveText (R.string.dialog_booking_confirmation_positive)
                .negativeText (R.string.dialog_booking_confirmation_negative)
                .typeface (SetTypeFace.getTypeface (ServiceDetailActivity.this), SetTypeFace.getTypeface (ServiceDetailActivity.this))
                .onPositive (new MaterialDialog.SingleButtonCallback () {
                    @Override
                    public void onClick (@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss ();
                        Utils.showToast (ServiceDetailActivity.this, "Your booking has been confirmed");
                        Intent intent = new Intent (ServiceDetailActivity.this, MainActivity.class);
                        intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity (intent);
                        overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
                    }
                }).build ();
        dialog.show ();
    }


    private void setUpNavigationDrawer () {
        toolbar = (Toolbar) findViewById (R.id.toolbar1);
        toolbar.showOverflowMenu ();
        SpannableString s;
        s = new SpannableString (service_name);
        s.setSpan (new TypefaceSpan (ServiceDetailActivity.this, Constants.font_name), 0, s.length (), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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
        Utils.hideSoftKeyboard (ServiceDetailActivity.this);
        return super.onOptionsItemSelected (item);
    }

}
