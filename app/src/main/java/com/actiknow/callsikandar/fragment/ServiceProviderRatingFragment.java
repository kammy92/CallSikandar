package com.actiknow.callsikandar.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actiknow.callsikandar.R;

/**
 * Created by Admin on 03-11-2016.
 */
public class ServiceProviderRatingFragment extends Fragment {
    private int service_provider_id;

    public static ServiceProviderRatingFragment newInstance (int service_provider_id) {
        Bundle args = new Bundle ();
        args.putInt ("service_provider_id", service_provider_id);
        ServiceProviderRatingFragment fragment = new ServiceProviderRatingFragment ();
        fragment.setArguments (args);
        return fragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        service_provider_id = getArguments ().getInt ("service_provider_id");
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.fragment_service_provider_rating, container, false);
        TextView textView = (TextView) view.findViewById (R.id.tv1);
//        textView.setText ("Fragment Packages");
        return view;
    }
}