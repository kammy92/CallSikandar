package com.actiknow.callsikandar.fragment;

/**
 * Created by actiknow on 11/7/16.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actiknow.callsikandar.R;


//Our class extending fragment
public class ServiceRequestDetailsFragment extends Fragment {
    private int service_request_id;

    public static ServiceRequestDetailsFragment newInstance (int service_provider_id) {
        Bundle args = new Bundle ();
        args.putInt ("service_provider_id", service_provider_id);
        ServiceRequestDetailsFragment fragment = new ServiceRequestDetailsFragment ();
        fragment.setArguments (args);
        return fragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        service_request_id = getArguments ().getInt ("service_request_id");
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.fragment_service_details in you classes
        return inflater.inflate (R.layout.fragment_service_request_details, container, false);
    }
}