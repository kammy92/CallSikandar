package com.actiknow.callsikandar.fragment;

/**
 * Created by actiknow on 11/7/16.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.adapter.ServiceRequestQuotationAdapter;
import com.actiknow.callsikandar.model.ServiceQuotations;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;


//Our class extending fragment
public class ServiceRequestQuotationsFragment extends Fragment {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView rvQuotationList;

    ServiceQuotations serviceQuotation;
    List<ServiceQuotations> serviceQuotationList = new ArrayList<ServiceQuotations> ();
    ServiceRequestQuotationAdapter serviceQuotationAdapter;


    public static ServiceRequestQuotationsFragment newInstance (int service_provider_id) {
        Bundle args = new Bundle ();
        args.putInt ("service_provider_id", service_provider_id);
        ServiceRequestQuotationsFragment fragment = new ServiceRequestQuotationsFragment ();
        fragment.setArguments (args);
        return fragment;
    }


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate (R.layout.fragment_service_request_quotations, container, false);

        initView (rootView);
        initData ();
        setHasOptionsMenu (true);
        return rootView;
    }

    private void initView (View rootView) {
        rvQuotationList = (RecyclerView) rootView.findViewById (R.id.rvQuotationsList);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById (R.id.swipe_refresh_layout);
    }

    private void initData () {
        serviceQuotationList.clear ();
        ServiceQuotations serviceQuotation1 = new ServiceQuotations ("Price 200");
        ServiceQuotations serviceQuotation2 = new ServiceQuotations ("Price 500");
        ServiceQuotations serviceQuotation3 = new ServiceQuotations ("Price 700");
        ServiceQuotations serviceQuotation4 = new ServiceQuotations ("Price 1200");
        ServiceQuotations serviceQuotation5 = new ServiceQuotations ("Price 2200");
        ServiceQuotations serviceQuotation6 = new ServiceQuotations ("Price 3200");

        serviceQuotationList.add (serviceQuotation1);
        serviceQuotationList.add (serviceQuotation2);
        serviceQuotationList.add (serviceQuotation3);
        serviceQuotationList.add (serviceQuotation4);
        serviceQuotationList.add (serviceQuotation5);
        serviceQuotationList.add (serviceQuotation6);

        serviceQuotationAdapter = new ServiceRequestQuotationAdapter (getActivity (), serviceQuotationList);
        AlphaInAnimationAdapter adapter = new AlphaInAnimationAdapter (serviceQuotationAdapter);
        rvQuotationList.setAdapter (adapter);
        adapter.setDuration (700);
        rvQuotationList.setAdapter (adapter);
        rvQuotationList.setHasFixedSize (true);
        rvQuotationList.setLayoutManager (new LinearLayoutManager (getActivity ()));
        rvQuotationList.setItemAnimator (new DefaultItemAnimator ());


    }

}