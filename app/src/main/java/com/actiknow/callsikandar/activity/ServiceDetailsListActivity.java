package com.actiknow.callsikandar.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.adapter.ServiceDetailAdapter;
import com.actiknow.callsikandar.model.ServiceDetail;
import com.actiknow.callsikandar.utils.Constants;
import com.actiknow.callsikandar.utils.TypefaceSpan;
import com.actiknow.callsikandar.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;


public class ServiceDetailsListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static List<ServiceDetail> serviceDetailList = new ArrayList<> ();
    RecyclerView rvDetails;
    SwipeRefreshLayout swipeRefreshLayout;
    ServiceDetailAdapter adapter;

    int service_id = 0;
    int service_type_id = 0;
    String service_type_name = "";
    String service_name = "";

    Toolbar toolbar;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_service_details_list);
        getExtras ();
        initView ();
        initData ();
        initListener ();
        setUpNavigationDrawer ();
    }

    private void getExtras () {
        Bundle bundle = getIntent ().getExtras ();
        service_id = bundle.getInt ("service_id", 0);
        service_type_id = bundle.getInt ("service_type_id", 0);
        service_type_name = bundle.getString ("service_type_name", "");
        service_name = bundle.getString ("service_name", "");
    }


    private void initView () {
        rvDetails = (RecyclerView) findViewById (R.id.rvServiceDetailList);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById (R.id.swipe_refresh_layout);
    }

    private void initData () {
        serviceDetailList.clear ();

        switch (service_type_id) {
            case 1:

                switch (service_id) {
                    case 1:
                        serviceDetailList.add (new ServiceDetail (1, "12.06", 1999, service_id, "Central Locking Fitting", "Eco Car Cleaning", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        break;
                    case 2:
                        serviceDetailList.add (new ServiceDetail (2, "12.06", 1999, service_id, "Accessories Fitting", "Eco Car Cleaning", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        break;
                    case 3:
                        serviceDetailList.add (new ServiceDetail (3, "12.06", 1999, service_id, "Light Fitting", "Eco Car Cleaning", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        break;
                    case 4:
                        serviceDetailList.add (new ServiceDetail (4, "12.06", 1999, service_id, "Sensors Fitting", "Eco Car Cleaning", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        break;
                    case 5:
                        serviceDetailList.add (new ServiceDetail (5, "12.06", 1999, service_id, "Sunfilm Installation", "Eco Car Cleaning", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        break;
                    case 6:
//                        serviceDetailList.add (new ServiceDetail(1, "12.06", 1999, service_id, "Floor Mats installation", "Eco Car Cleaning", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        break;
                    case 7:
//                        serviceDetailList.add (new ServiceDetail (1, "12.06", 1999, service_id, "Racing stripes intallation", "Eco Car Cleaning", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        break;

                }


                break;
            case 2:
                switch (service_id) {
                    case 8:
                        serviceDetailList.add (new ServiceDetail (6, "12.06", 1999, service_id, "Exterior Refine and Protect", "Eco Car Cleaning", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        serviceDetailList.add (new ServiceDetail (7, "12.06", 2699, service_id, "Paint restoration", "Eco Car Cleaning", "Galleria Tower, Gurgaon", "4.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        serviceDetailList.add (new ServiceDetail (8, "12.06", 899, service_id, "Windshield Nano tech coating", "Eco Car Cleaning", "Galleria Tower, Gurgaon", "5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        serviceDetailList.add (new ServiceDetail (9, "12.06", 699, service_id, "Exterior Dry Cleaning", "Eco Car Cleaning", "Galleria Tower, Gurgaon", "3.9", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        break;
                    case 9:
                        serviceDetailList.add (new ServiceDetail (10, "12.06", 1499, service_id, "Interior Germ-Protect Treatment", "Eco Car Cleaning", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        serviceDetailList.add (new ServiceDetail (11, "12.06", 899, service_id, "Interior Dry cleaning", "Eco Car Cleaning", "Galleria Tower, Gurgaon", "4.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        break;
                    case 10:
                        serviceDetailList.add (new ServiceDetail (12, "12.06", 1899, service_id, "Full Car Detailing with Germ-Protect", "Eco Car Cleaning", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        serviceDetailList.add (new ServiceDetail (13, "12.06", 1499, service_id, "Full Car Detailing", "Eco Car Cleaning", "Galleria Tower, Gurgaon", "4.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        break;
                    case 11:
                        serviceDetailList.add (new ServiceDetail (14, "12.06", 1999, service_id, "Full AC Sanitization Package", "Eco Car Cleaning", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        break;
                }
                break;
            case 3:
                switch (service_id) {
                    case 12:
                        serviceDetailList.add (new ServiceDetail (15, "12.06", 35000, service_id, "Complete Vehicle Painting", "Eco Car Cleaning", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        serviceDetailList.add (new ServiceDetail (16, "12.06", 6500, service_id, "Alloys painting", "Eco Car Cleaning", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        serviceDetailList.add (new ServiceDetail (17, "12.06", 2399, service_id, "Paint restoration", "Eco Car Cleaning", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        break;
                    case 13:
                        break;
                    case 14:
                        break;
                    case 15:
                        break;
                    case 16:
                        break;
                }
                break;
            case 4:
                switch (service_id) {
                    case 17:
                        serviceDetailList.add (new ServiceDetail (18, "12.06", 1000, service_id, "Full Service (every 10,000 km)", "Callsikandar Doorstep Services", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        break;
                    case 18:
                        serviceDetailList.add (new ServiceDetail (19, "12.06", 199, service_id, "Express check-up (every 5,000 km)", "Callsikandar Doorstep Services", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        break;
                    case 19:
                        break;
                    case 20:
                        serviceDetailList.add (new ServiceDetail (20, "12.06", 249, service_id, "Used Car Inspection", "Callsikandar Doorstep Services", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        break;
                }
                break;
            case 5:
                switch (service_id) {
                    case 21:
                        break;
                    case 22:
                        serviceDetailList.add (new ServiceDetail (21, "12.06", 799, service_id, "AC check up and gas top up", "Callsikandar Doorstep Services", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        serviceDetailList.add (new ServiceDetail (22, "12.06", 1299, service_id, "AC check up and Full Gas Recharge", "Callsikandar Doorstep Services", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        break;
                    case 23:
                        break;
                    case 24:
                        serviceDetailList.add (new ServiceDetail (23, "12.06", 5599, service_id, "Exide FM10-MIDIN60 (60 Ah)", "Callsikandar Doorstep Services", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        serviceDetailList.add (new ServiceDetail (24, "12.06", 4899, service_id, "Amaron AAM-FL-566112060 (60 Ah)", "Callsikandar Doorstep Services", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        break;
                    case 25:
                        serviceDetailList.add (new ServiceDetail (25, "12.06", 400, service_id, "Front Brake Overhaul", "Callsikandar Doorstep Services", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        serviceDetailList.add (new ServiceDetail (26, "12.06", 400, service_id, "Brake Disk Replacement", "Callsikandar Doorstep Services", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        break;
                    case 26:
                        serviceDetailList.add (new ServiceDetail (27, "12.06", 1600, service_id, "Clutch Overhaul", "Callsikandar Doorstep Services", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        break;
                    case 27:
                        break;
                    case 28:
                        serviceDetailList.add (new ServiceDetail (28, "12.06", 600, service_id, "Wheel bearing replacement", "Callsikandar Doorstep Services", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        serviceDetailList.add (new ServiceDetail (29, "12.06", 600, service_id, "Shock absorber replacement", "Callsikandar Doorstep Services", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        break;
                    case 29:
                        serviceDetailList.add (new ServiceDetail (30, "12.06", 249, service_id, "Used Car Inspection", "Callsikandar Doorstep Services", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                        break;
                    case 30:
                        break;
                    case 31:
                        break;
                    case 32:
                        break;
                    case 33:
                        break;
                    case 34:
                        break;
                    case 35:
                        break;
                }
                break;
        }


        adapter = new ServiceDetailAdapter (ServiceDetailsListActivity.this, serviceDetailList);

        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter (adapter);
        alphaAdapter.setDuration (700);
        rvDetails.setAdapter (alphaAdapter);
        rvDetails.setHasFixedSize (true);
        rvDetails.setLayoutManager (new LinearLayoutManager (ServiceDetailsListActivity.this));
        rvDetails.setItemAnimator (new DefaultItemAnimator ());
        swipeRefreshLayout.setColorSchemeResources (R.color.primary);
    }

    private void initListener () {
        swipeRefreshLayout.setOnRefreshListener (new SwipeRefreshLayout.OnRefreshListener () {
            @Override
            public void onRefresh () {
                swipeRefreshLayout.setRefreshing (true);
                getAllServiceDetails ();
            }
        });
    }


    private void getAllServiceDetails () {
//        vehicleList.clear ();
        final Handler handler = new Handler ();
        handler.postDelayed (new Runnable () {
            @Override
            public void run () {
                serviceDetailList.add (new ServiceDetail (1, "12.06", 249, service_id, "Dummy Service", "Callsikandar Doorstep Services", "Galleria Tower, Gurgaon", "3.5", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                adapter.notifyDataSetChanged ();
                swipeRefreshLayout.setRefreshing (false);
            }
        }, 1000);
    }


    @Override
    public void onRefresh () {

    }

    private void setUpNavigationDrawer () {
        toolbar = (Toolbar) findViewById (R.id.toolbar1);
        toolbar.showOverflowMenu ();
        SpannableString s;
        s = new SpannableString (service_name);
        s.setSpan (new TypefaceSpan (ServiceDetailsListActivity.this, Constants.font_name), 0, s.length (), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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
        Utils.hideSoftKeyboard (ServiceDetailsListActivity.this);
        return super.onOptionsItemSelected (item);
    }


}
