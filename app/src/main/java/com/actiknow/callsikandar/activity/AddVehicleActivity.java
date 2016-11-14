package com.actiknow.callsikandar.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.Log;
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
import com.actiknow.callsikandar.model.Vehicle;
import com.actiknow.callsikandar.utils.AppConfigTags;
import com.actiknow.callsikandar.utils.AppConfigURL;
import com.actiknow.callsikandar.utils.Constants;
import com.actiknow.callsikandar.utils.NetworkConnection;
import com.actiknow.callsikandar.utils.TypefaceSpan;
import com.actiknow.callsikandar.utils.Utils;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.actiknow.callsikandar.fragment.ManageVehicleFragment.vehicleList;
import static com.actiknow.callsikandar.utils.Constants.api_key;

public class AddVehicleActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // old project

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
            Vehicle vehicle = vehicleList.get (vehicle_id - 1);
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
                String myFormat = "dd/MM/yyyy"; //In which you need put here
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
        tvAddVehicle.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                if (validate ()) {
                    addNewVehicle (etCarModel.getText ().toString (), etRegistrationNumber.getText ().toString (), Integer.parseInt (etYearOfManufacture.getText ().toString ()), Utils.convertTimeFormat (etLastServiceDate.getText ().toString (), "dd/MM/yyyy", "yyyy-MM-dd"), Integer.parseInt (etKmReading.getText ().toString ()), "Petrol");
                }
            }
        });

        etCarModel.addTextChangedListener (new TextWatcher () {
            @Override
            public void onTextChanged (CharSequence s, int start, int before, int count) {
                if (s.length () > 0) {
                    input_layout_car_model.setError (null);
                    input_layout_car_model.setErrorEnabled (false);
                }
            }

            @Override
            public void beforeTextChanged (CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged (Editable s) {
            }
        });
        etRegistrationNumber.addTextChangedListener (new TextWatcher () {
            @Override
            public void onTextChanged (CharSequence s, int start, int before, int count) {
                if (s.length () > 0) {
                    input_layout_registration_number.setError (null);
                    input_layout_registration_number.setErrorEnabled (false);
                }
            }

            @Override
            public void beforeTextChanged (CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged (Editable s) {
            }
        });
        etYearOfManufacture.addTextChangedListener (new TextWatcher () {
            @Override
            public void onTextChanged (CharSequence s, int start, int before, int count) {
                if (s.length () > 0) {
                    input_layout_year_of_manufacture.setError (null);
                    input_layout_year_of_manufacture.setErrorEnabled (false);
                }
            }

            @Override
            public void beforeTextChanged (CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged (Editable s) {
            }
        });
        etLastServiceDate.addTextChangedListener (new TextWatcher () {
            @Override
            public void onTextChanged (CharSequence s, int start, int before, int count) {
                if (s.length () > 0) {
                    input_layout_last_service_date.setError (null);
                    input_layout_last_service_date.setErrorEnabled (false);
                }
            }

            @Override
            public void beforeTextChanged (CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged (Editable s) {
            }
        });
        etKmReading.addTextChangedListener (new TextWatcher () {
            @Override
            public void onTextChanged (CharSequence s, int start, int before, int count) {
                if (s.length () > 0) {
                    input_layout_km_reading.setError (null);
                    input_layout_km_reading.setErrorEnabled (false);
                }
            }

            @Override
            public void beforeTextChanged (CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged (Editable s) {
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

    private boolean validate () {
        boolean validate = true;
        List<String> error = new ArrayList<> ();
        error.clear ();
        if (etCarModel.getText ().toString ().length () == 0) {
            input_layout_car_model.setError ("Select a car model");
            input_layout_car_model.setErrorEnabled (true);
            validate = false;
        }
        if (etRegistrationNumber.getText ().toString ().length () == 0) {
            input_layout_registration_number.setError ("Enter a registration number");
            input_layout_registration_number.setErrorEnabled (true);
            validate = false;
        }
        if (etYearOfManufacture.getText ().toString ().length () == 0) {
            input_layout_year_of_manufacture.setError ("Enter year of manufacture");
            input_layout_year_of_manufacture.setErrorEnabled (true);
            validate = false;
        }
        if (etKmReading.getText ().toString ().length () == 0) {
            input_layout_km_reading.setError ("Enter km reading");
            input_layout_km_reading.setErrorEnabled (true);
            validate = false;
        }
        if (etLastServiceDate.getText ().toString ().length () == 0) {
            input_layout_last_service_date.setError ("Enter last service date");
            input_layout_last_service_date.setErrorEnabled (true);
            validate = false;
        }


        if (rgFuelType.getCheckedRadioButtonId () == - 1) {
            Utils.showToast (AddVehicleActivity.this, "Please select a fuel type");
            validate = false;
        }

        return validate;

        /*
        switch (question.getQuestion_type ()) {
            case "Radio":
                if (optionSelected.length () == 0) {
                    error.add ("Select an option");
                    validate = false;
                }
                if (question.isImage_required () && Utils.bitmapToBase64 (bp1).length () == 0) {
                    error.add ("Select an image");
                    validate = false;
                }
                if (question.isComment_required () && question.getComment_required_for ().equalsIgnoreCase (optionSelected) && etComments.getText ().toString ().length () == 0) {
                    error.add ("Enter the value in comment");
                    validate = false;
                }
                if (question.getQuestion_id () == 14 && question.getComment_required_for ().equalsIgnoreCase (optionSelected) && etComments.getText ().toString ().length () < 6) {
                    error.add ("Enter a valid number (Atleast 6 digit)");
                    validate = false;
                }
//                if (question.getQuestion_id () == 19 && etComments.getText ().toString ().length () == 0) {
//                    error.add ("Enter AC Make in comments");
//                    validate = false;
//                }
//                if (question.getQuestion_id () == 20 && etComments.getText ().toString ().length () == 0) {
//                    error.add ("Enter UPS Make and Sr Number in comments");
//                    validate = false;
//                }


                if (question.isMake_serial_type () && ! question.getMandatory_comment_not_for ().equalsIgnoreCase (optionSelected)) {
                    if (etMake.getText ().toString ().length () == 0) {
                        error.add ("Please specify make in comments");
                        validate = false;
                    }
                    if (etSerial.getText ().toString ().length () == 0) {
                        error.add ("Please specify serial in comments");
                        validate = false;
                    }

                }


                if (error.size () > 0) {
                    Utils.showValidationErrorDialog (getActivity (), error);
                }
                break;
            case "Hybrid":
                if (optionSelected.length () == 0) {
                    error.add ("Select an option");
                    validate = false;
                }
                if (question.isImage_required () && Utils.bitmapToBase64 (bp1).length () == 0) {
                    error.add ("Select an image");
                    validate = false;
                }
                if (question.isComment_required () && question.getComment_required_for ().equalsIgnoreCase (optionSelected) && etComments.getText ().toString ().length () == 0) {
                    error.add ("Enter the value in comment");
                    validate = false;
                }
                if (optionSelected.equalsIgnoreCase (question.getExtra_option_required_for ()) && extraOptions.size () == 0) {
                    error.add ("Select atleast one value");
                    validate = false;
                }
                if (error.size () > 0) {
                    Utils.showValidationErrorDialog (getActivity (), error);
                }
                break;
            case "Blank":
                if (question.isComment_required () && question.getComment_required_for ().equalsIgnoreCase (optionSelected) && etComments.getText ().toString ().length () == 0) {
                    error.add ("Enter the value in comment");
                    validate = false;
                }
                if (question.isImage_required () && Utils.bitmapToBase64 (bp1).length () == 0) {
                    error.add ("Select an image");
                    validate = false;
                }
                if (error.size () > 0) {
                    Utils.showValidationErrorDialog (getActivity (), error);
                }
                break;
            case "Rating":
                if (question.isComment_required () && question.getComment_required_for ().equalsIgnoreCase (optionSelected) && etComments.getText ().toString ().length () == 0) {
                    error.add ("Enter the value in comment");
                    validate = false;
                }
                if (question.isImage_required () && Utils.bitmapToBase64 (bp1).length () == 0) {
                    error.add ("Select an Image");
                    validate = false;
                }
                if (error.size () > 0) {
                    Utils.showValidationErrorDialog (getActivity (), error);
                }
                break;
        }

        */
//        return validate;
    }

    private void addNewVehicle (final String make_and_model, final String registration_number, final int year_of_manufacture, final String last_service_date, final int km_reading, final String fuel_type) {
        if (NetworkConnection.isNetworkAvailable (AddVehicleActivity.this)) {
            Utils.showLog (Log.INFO, AppConfigTags.URL, AppConfigURL.URL_ADDNEWVEHICLE, true);
            StringRequest strRequest = new StringRequest (Request.Method.POST, AppConfigURL.URL_ADDNEWVEHICLE,
                    new Response.Listener<String> () {
                        @Override
                        public void onResponse (String response) {
                            Utils.showLog (Log.INFO, AppConfigTags.SERVER_RESPONSE, response, true);
                            if (response != null) {
                                try {
                                    JSONObject jsonObj = new JSONObject (response);
                                    boolean is_error = jsonObj.getBoolean (AppConfigTags.ERROR);
                                    String message = jsonObj.getString (AppConfigTags.MESSAGE);
                                    int status = jsonObj.getInt (AppConfigTags.STATUS);

                                    switch (status) {
                                        case 0:
                                            Utils.showToast (AddVehicleActivity.this, "Vehicle already exist in the list");
                                            finish ();
                                            break;
                                        case 1:
                                            Utils.showToast (AddVehicleActivity.this, "Vehicle added successfully");
                                            finish ();
                                            break;
//                                        case 2:
//                                            Utils.showToast (AddVehicleActivity.this, "Vehicle added successfully");
//                                            finish ();
//                                            break;
                                    }

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

                                /*
                                try {
                                    JSONObject jsonObj = new JSONObject (new String (response.data));
                                    boolean is_error = jsonObj.getBoolean (AppConfigTags.ERROR);
                                    String message = jsonObj.getString (AppConfigTags.MESSAGE);
                                    Utils.showLog (Log.ERROR, AppConfigTags.ERROR, "" + is_error, true);
                                    Utils.showLog (Log.ERROR, AppConfigTags.MESSAGE, message, true);
                                } catch (JSONException e) {
                                    e.printStackTrace ();
                                }
*/
                            }
                        }
                    }) {

                @Override
                protected Map<String, String> getParams () throws AuthFailureError {
                    Map<String, String> params = new Hashtable<String, String> ();
                    params.put (AppConfigTags.MAKE_AND_MODEL, make_and_model);
                    params.put (AppConfigTags.REGISTRATION_NUMBER, registration_number);
                    params.put (AppConfigTags.YEAR_OF_MANUFACTURE, String.valueOf (year_of_manufacture));
                    params.put (AppConfigTags.LAST_SERVICE_DATE, last_service_date);
                    params.put (AppConfigTags.KM_READING, String.valueOf (km_reading));
                    params.put (AppConfigTags.FUEL_TYPE, fuel_type);

                    Utils.showLog (Log.INFO, AppConfigTags.PARAMETERS_SENT_TO_THE_SERVER, "" + params, true);
                    return params;
                }

                @Override
                public Map<String, String> getHeaders () throws AuthFailureError {
                    Map<String, String> params = new HashMap<> ();
                    params.put ("api_key", api_key);
                    params.put ("user_login_key", Constants.user_login_key);
                    Utils.showLog (Log.INFO, AppConfigTags.HEADERS_SENT_TO_THE_SERVER, "" + params, false);
                    return params;
                }


            };
            Utils.sendRequest (strRequest, 30);
        } else {
        }
//        vehicleList.clear ();
//        final Handler handler = new Handler ();
//        handler.postDelayed (new Runnable () {
//            @Override
//            public void run () {
//                Vehicle vehicle1 = new Vehicle (1, "Volkswagen Polo", "DL 6SM 1234", "2010", "14000", "12/02/2016", "Petrol");
//                vehicleList.add (vehicle1);
//                adapter.notifyDataSetChanged ();
//                swipeRefreshLayout.setRefreshing (false);
//            }
//        }, 1000);
    }
}

