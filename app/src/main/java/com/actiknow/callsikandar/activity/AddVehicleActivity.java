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
            "Hyundai Elantra", "Hyundai Creta", "Hyundai Grand i10", "Hyundai Elite i20", "Hyundai Xcent", "Hyundai EON", "Hyundai Verna", "Hyundai Eon Facelift", "Hyundai Tucson", "Hyundai i10 MPV", "Hyundai genesis", "Hyundai i20 Active", "Maruti Suzuki Omni", "Maruti Suzuki Alto 800", "Maruti Suzuki Eeco",
            "Maruti Suzuki Celerio", "Maruti Suzuki Wagon R", "Maruti Suzuki Wagon R Stingray", "Maruti Suzuki Alto K10", "Maruti Suzuki Ritz", "Maruti Suzuki Swift", "Maruti Suzuki Swift Dezire", "Tata Tiago", "Tata Zest",
            "Tata Nabo", "Tata Bolt", "Tata Safari", "Tata Indica", "Tata Indigo",
            "Mercedes-Benz CLS-Class", "Mercedes-Benz E-Class", "Mercedes-Benz GLK-Class", "Mercedes-Benz AMG-Class", "Mercedes-Benz AMG CLA-Class", "Mercedes-Benz AMG C43-Class",
            "BMW 1", "BMW 3", "BMW Gran Turismo", "BMW 5", "BMW X3", "BMW X5",
            "Volkswagen Passat", "Volkswagen Polo", "Volkswagen Tiguan", "Volkswagen Polo GTI", "Volkswagen Ameo", "Volkswagen Vento",
            "Honda Brio", "Honda Amaze", "Honda Mobilio", "Honda City", "Honda BR-V", "Honda CR-V", "Honda Accord", "Honda Accord Hybrid", "Honda Jazz", "Honda City", "Honda Civic",
            "Audi A3", "Audi A4", "Audi A6", "Audi A7", "Audi A8", "Audi Q3", "Audi Q5", "Audi Q7", "Audi R8", "Audi TT", "Audi S6", "Audi S4", "Audi RS7", "Audi RS5",
            "Mahindra Supro", "Mahindra Verito", "Mahindra Scorpio", "Mahindra XUV", "Mahindra Logan", "Mahindra Ghibli", "Mahindra NuvoSport", "Mahindra Bolero", "Mahindra Quanto", "Mahindra Xylo",
            "Toyota Corolla Altis", "Toyota Camry", "Toyota Corolla", "Toyota Etios", "Toyota Fortuner", "Toyota Innova", "Toyota Land Cruiser", "Toyota Prius", "Toyota Qualis",
            "Chevrolet Spark", "Chevrolet Beat", "Chevrolet Sail", "Chevrolet Enjoy",
            "Chevrolet Tavera", "Chevrolet Cruize", "Fiat Punto", "Fiat Linea", "Fiat Avventura", "Fiat Urban Cross",
            "Fiat Abarth", "Fiat Panda", "Ford Figo", "Ford Aspire", "Ford EcoSport", "Ford Endeavour", "Ford Mustang", "Ford Fiesta", "Ford Classic",
            "Mitsubishi Outlander", "Mitsubishi Outlander", "Mitsubishi Lancer", "Mitsubishi Galant", "Mitsubishi Carisma", "Mitsubishi Pajero", "Mitsubishi Eclipse",
            "Nissan Terrano", "Nissan Micra", "Nissan GTR", "Nissan Sunny", "Nissan Evalia", "Nissan Patrol", "Nissan X Trail",
            "Renault Kwid", "Renault Pulse", "Renault Scala", "Renalut Duster", "Renault Lodgy", "Renault Fluence", "Renault Koleos",
            "Skoda Fabia", "Skoda Rapid", "Skoda Octavia", "Skoda Laura", "Skoda Yeti", "Skoda Superb"
    };

    /*
     String carsmodel[] = {
             "Hyundai Elantra", "Hyundai Creta", "Hyundai Grand i10", "Hyundai Elite i20",
             "Hyundai Xcent", "Hyundai EON", "Hyundai Verna", "Hyundai Eon Facelift", "Hyundai Tucson", "Hyundai i10 MPV",
             "Hyundai genesis", "Hyundai i20 Active"
     };

     */
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

