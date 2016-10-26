package com.actiknow.callsikandar.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.fragment.ManageVehicleFragment;
import com.actiknow.callsikandar.model.Vehicle;
import com.actiknow.callsikandar.utils.Constants;
import com.actiknow.callsikandar.utils.TypefaceSpan;
import com.actiknow.callsikandar.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddVehicleActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    AutoCompleteTextView etCarModel;
    EditText etRegistrationNumber;
    EditText etYearOfManufacture;
    EditText etLastServiceDate;
    EditText etKmReading;

    TextInputLayout input_layout_car_model;
    TextInputLayout input_layout_registration_number;
    TextInputLayout input_layout_year_of_manufacture;
    TextInputLayout input_layout_last_service_date;
    TextInputLayout input_layout_km_reading;

    Toolbar toolbar;

    RadioGroup rgFuelType;
    RadioButton rbPetrol;
    RadioButton rbDiesel;
    RadioButton rbCNG;

    TextView tvAddVehicle;
    TextView tvUpdateVehicle;

    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    boolean update_vehicle = false;
    int vehicle_id = 0;
    String CarURl = "https://api.edmunds.com/api/vehicle/v2/makes?fmt=json&api_key=8vc7bxdxztrjkjws3s9qj3a7";
    String carsmodel[] = {
            "Hyundai Elantra", "Hyundai Creta", "Hyundai Grand i10", "Hyundai Elite i20",
            "Hyundai Xcent", "Hyundai EON", "Hyundai Verna", "Hyundai Eon Facelift", "Hyundai Tucson", "Hyundai i10 MPV",
            "Hyundai genesis", "Hyundai i20 Active"
    };
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_add_vehicle);
        getExtras ();
        initView ();
        initData ();
        initListener ();
        initAdapter ();
        setUpNavigationDrawer ();
    }

    private void getExtras () {
        Bundle bundle = getIntent ().getExtras ();
        if (bundle.getBoolean ("update_vehicle")) {
            update_vehicle = true;
            vehicle_id = bundle.getInt ("vehicle_id", 0);
        }
    }

    private void initAdapter () {
        adapter = new ArrayAdapter<String> (this, android.R.layout.simple_dropdown_item_1line, carsmodel);
        etCarModel.setThreshold (1);
        etCarModel.setAdapter (adapter);
        etCarModel.setOnItemSelectedListener (this);
    }

    private void initView () {
        etCarModel = (AutoCompleteTextView) findViewById (R.id.etCarModel);
        etRegistrationNumber = (EditText) findViewById (R.id.etRegistrationNumber);
        etYearOfManufacture = (EditText) findViewById (R.id.etYearOfManufacture);
        etLastServiceDate = (EditText) findViewById (R.id.etLastServiceDate);
        etKmReading = (EditText) findViewById (R.id.etKmReading);

        input_layout_car_model = (TextInputLayout) findViewById (R.id.input_layout_car_model);
        input_layout_registration_number = (TextInputLayout) findViewById (R.id.input_layout_registration_number);
        input_layout_year_of_manufacture = (TextInputLayout) findViewById (R.id.input_layout_year_of_manufacture);
        input_layout_last_service_date = (TextInputLayout) findViewById (R.id.input_layout_last_service_date);
        input_layout_km_reading = (TextInputLayout) findViewById (R.id.input_layout_km_reading);

        rgFuelType = (RadioGroup) findViewById (R.id.rgFuelType);
        rbPetrol = (RadioButton) findViewById (R.id.rbPetrol);
        rbDiesel = (RadioButton) findViewById (R.id.rbDiesel);
        rbCNG = (RadioButton) findViewById (R.id.rbCNG);

        tvAddVehicle = (TextView) findViewById (R.id.tvAddVehicle);
        tvUpdateVehicle = (TextView) findViewById (R.id.tvUpdateVehicle);
    }

    private void initData () {
        if (! update_vehicle) {
            tvAddVehicle.setVisibility (View.VISIBLE);
            tvUpdateVehicle.setVisibility (View.GONE);
        } else {

            Vehicle vehicle = ManageVehicleFragment.vehicleList.get (vehicle_id - 1);
            etCarModel.setText (vehicle.getMake_and_model ());
            etRegistrationNumber.setText (vehicle.getRegistration_number ());
            etYearOfManufacture.setText (vehicle.getYear_of_manufacture ());
            etLastServiceDate.setText (vehicle.getLast_service_date ());
            etKmReading.setText (vehicle.getKm_reading ());
            switch (vehicle.getFuel_type ()) {
                case "Petrol":
                    rbPetrol.setSelected (true);
                    break;
                case "Diesel":
                    rbDiesel.setSelected (true);
                    break;
                case "CNG":
                    rbCNG.setSelected (true);
                    break;
            }

            tvAddVehicle.setVisibility (View.GONE);
            tvUpdateVehicle.setVisibility (View.VISIBLE);
        }
    }

    private void initListener () {
        myCalendar = Calendar.getInstance ();
        date = new DatePickerDialog.OnDateSetListener () {

            @Override
            public void onDateSet (DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set (Calendar.YEAR, year);
                myCalendar.set (Calendar.MONTH, monthOfYear);
                myCalendar.set (Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat (myFormat, Locale.US);
                etLastServiceDate.setText (sdf.format (myCalendar.getTime ()));
            }
        };
        etLastServiceDate.setOnClickListener (new View.OnClickListener () {

            @Override
            public void onClick (View v) {
                new DatePickerDialog (AddVehicleActivity.this, date, myCalendar
                        .get (Calendar.YEAR), myCalendar.get (Calendar.MONTH),
                        myCalendar.get (Calendar.DAY_OF_MONTH)).show ();
            }
        });
    }

    @Override
    public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected (AdapterView<?> parent) {
    }

    private void setUpNavigationDrawer () {
        toolbar = (Toolbar) findViewById (R.id.toolbar1);
        toolbar.showOverflowMenu ();
        SpannableString s;
        if (update_vehicle) {
            s = new SpannableString (getResources ().getString (R.string.activity_title_update_vehicle));
        } else {
            s = new SpannableString (getResources ().getString (R.string.activity_title_add_vehicle));
        }
        s.setSpan (new TypefaceSpan (AddVehicleActivity.this, Constants.font_name), 0, s.length (), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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
        Utils.hideSoftKeyboard (AddVehicleActivity.this);
        return super.onOptionsItemSelected (item);
    }

}

