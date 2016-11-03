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
import android.widget.RelativeLayout;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.adapter.ServicesListAdapter;
import com.actiknow.callsikandar.model.Service;
import com.actiknow.callsikandar.utils.Constants;
import com.actiknow.callsikandar.utils.TypefaceSpan;
import com.actiknow.callsikandar.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;

/**
 * Created by l on 27/10/2016.
 */

public class ServicesListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView rvServicesList;
    SwipeRefreshLayout swipeRefreshLayout;
    RelativeLayout rlOtherServices;
    ServicesListAdapter adapter;

    Toolbar toolbar;

    List<Service> serviceList = new ArrayList<> ();

    int service_type_id = 0;
    String service_type_name = "";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_services_list);
        getExtras ();
        initView ();
        initData ();
        initListener ();
        setUpNavigationDrawer ();
    }

    private void getExtras () {
        Bundle bundle = getIntent ().getExtras ();
        service_type_id = bundle.getInt ("service_type_id", 0);
        service_type_name = bundle.getString ("service_type_name", "");
    }

    private void initView () {
        rvServicesList = (RecyclerView) findViewById (R.id.rvServiceList);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById (R.id.swipeRefreshLayout);
        rlOtherServices = (RelativeLayout) findViewById (R.id.rlOtherServices);
    }

    private void initData () {
        serviceList.clear ();
        switch (service_type_id) {
            case 1:
                serviceList.add (new Service (1, 1, "Central Locking", "Central locking system not working? Get them fixed at the convenience of your home or office", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Accessories"));
                serviceList.add (new Service (2, 1, "Accessory fitment", "Fitment of electronic and accessories at your doorstep", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Accessories"));
                serviceList.add (new Service (3, 1, "Lighting", "Lighting options for your car", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Accessories"));
                serviceList.add (new Service (4, 1, "Parking sensors and cameras", "Parking sensors and cameras", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Accessories"));
                serviceList.add (new Service (5, 1, "Sunfilm", "Block the sun with 3M Automotive films", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Accessories"));
                serviceList.add (new Service (6, 1, "Floor Mats", "Customized floor mats fitted at your doorstep", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Accessories"));
                serviceList.add (new Service (7, 1, "Racing stripes", "Make your car stand out from the crowd with unique 3M (TM) racing stripes", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Accessories"));
                break;
            case 2:
                serviceList.add (new Service (8, 2, "Exterior Treatment", "Complete car exterior cleaning and polishing, waxing", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Car Cleaning"));
                serviceList.add (new Service (9, 2, "Interior Treatment", "Intensive car interior cleaning packages", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Car Cleaning"));
                serviceList.add (new Service (10, 2, "Complete Car Treatment", "Full car detailing covering both exterior and interiors of your car", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Car Cleaning"));
                serviceList.add (new Service (11, 2, "AC Sanitization", "Cleaning and sanitizing AC ducts", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Car Cleaning"));
                break;
            case 3:
                serviceList.add (new Service (12, 3, "Paint Packages", "Paint protection and restoration packages for your car to shine like new!", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Dents & Paint"));
                serviceList.add (new Service (13, 3, "Paint Protection Film", "Scotchgard(TM) paint protection films that protect your car from paint scratches", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Dents & Paint"));
                serviceList.add (new Service (14, 3, "Body Wraps", "Make your car stand out from the crowd with unique 3M (TM) wraps", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Dents & Paint"));
                serviceList.add (new Service (15, 3, "Paint Protection", "Protect the paint finish of your new car or restore your old car to factory finish", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Dents & Paint"));
                serviceList.add (new Service (16, 3, "Windshield and Wipers", "Replacement of windshield or wipes at your doorstep", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Dents & Paint"));
                break;
            case 4:
                serviceList.add (new Service (17, 4, "Full Service", "Full Service done at every 10,000 km and recommended by the manufacturer", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Regular Service"));
                serviceList.add (new Service (18, 4, "Express Service", "Express Check up (Every 5,000 km)", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Regular Service"));
                serviceList.add (new Service (19, 4, "Monsoon Packages", "Get your car prepared for the monsoon", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Regular Service"));
                serviceList.add (new Service (20, 4, "Used Car Inspection", "Inspection and refurbishment of used cars", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Regular Service"));
                break;
            case 5:
                serviceList.add (new Service (21, 5, "Minor Repairs", "Minor fixes such as wiper blade replacement, horn malfunctioning fix, headlamp replacement etc", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Repairs"));
                serviceList.add (new Service (22, 5, "Air-conditioning", "Car air-conditioner not effective? Bad odour from the AC? Get all AC related issues fixed at your doorstep", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Repairs"));
                serviceList.add (new Service (23, 5, "Central Locking", "Central locking system not working? Get them fixed at the convenience of your home or office", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Repairs"));
                serviceList.add (new Service (24, 5, "Battery Replacement", "Looking for a new car battery? Genuine car batteries with warranty, replaced at your doorstep within 24 hours", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Repairs"));
                serviceList.add (new Service (25, 5, "Brakes", "Brake pads / discs need replacement? Get them replaced with genuine parts", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Repairs"));
                serviceList.add (new Service (26, 5, "Clutch", "Have trouble with your clutch? Get your clutch system diagnosed and overhauled at your doorstep", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Repairs"));
                serviceList.add (new Service (27, 5, "Electrical Repairs", "Covering a range of electrical fixes, repairs and replacements", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Repairs"));
                serviceList.add (new Service (28, 5, "Suspension", "Shock absorbers to be replaced? Suspension needing repairs? Get them fixed with genuine parts", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Repairs"));
                serviceList.add (new Service (29, 5, "Used Car Inspection", "Inspection and refurbishment of used cars", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Repairs"));
                serviceList.add (new Service (30, 5, "Accessory fitment", "Fitment of electronics and accessories at your doorstep", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Repairs"));
                serviceList.add (new Service (31, 5, "Tyre Replacement", "Replacement of tyres, fitment and wheel alignment/balancing", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Repairs"));
                serviceList.add (new Service (32, 5, "Wheels and Tyres", "Wheels and tyres services such as alignment, balancing and tyre rotation", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Repairs"));
                serviceList.add (new Service (33, 5, "Windshield and Wipers", "Replacement of windshield or wipers at your doorstep", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Repairs"));
                serviceList.add (new Service (34, 5, "Sunfilm", "Block the sun with 3M Automotive films", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Repairs"));
                serviceList.add (new Service (35, 5, "Floor Mats", "Customized floor mats from 3M fitted at your doorstep", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Repairs"));
                break;
        }

        adapter = new ServicesListAdapter (ServicesListActivity.this, serviceList);

        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter (adapter);
        alphaAdapter.setDuration (700);
        rvServicesList.setAdapter (alphaAdapter);
        rvServicesList.setHasFixedSize (true);
        rvServicesList.setLayoutManager (new LinearLayoutManager (this));
        rvServicesList.setItemAnimator (new DefaultItemAnimator ());
        swipeRefreshLayout.setColorSchemeResources (R.color.primary);
    }

    private void initListener () {
        swipeRefreshLayout.setOnRefreshListener (new SwipeRefreshLayout.OnRefreshListener () {
            @Override
            public void onRefresh () {
                swipeRefreshLayout.setRefreshing (true);
                getAllServices ();
            }
        });
    }

    private void getAllServices () {
//        vehicleList.clear ();
        final Handler handler = new Handler ();
        handler.postDelayed (new Runnable () {
            @Override
            public void run () {
                Service service1 = new Service (1, 1, "Dummy Service", "Dummy service", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png", "Accessories");
                serviceList.add (service1);
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
        s = new SpannableString (service_type_name);
        s.setSpan (new TypefaceSpan (ServicesListActivity.this, Constants.font_name), 0, s.length (), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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
        Utils.hideSoftKeyboard (ServicesListActivity.this);
        return super.onOptionsItemSelected (item);
    }


}