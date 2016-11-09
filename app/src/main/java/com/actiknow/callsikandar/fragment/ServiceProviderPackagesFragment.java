package com.actiknow.callsikandar.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.adapter.ServiceProviderPackageAdapter;
import com.actiknow.callsikandar.model.ServiceProviderPackage;
import com.actiknow.callsikandar.utils.SetTypeFace;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;

/**
 * Created by Admin on 03-11-2016.
 */
public class ServiceProviderPackagesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView rvPackageList;
    SwipeRefreshLayout swipeRefreshLayout;
    ServiceProviderPackageAdapter adapter;
    List<ServiceProviderPackage> serviceProviderPackagesList = new ArrayList<ServiceProviderPackage> ();
    private int service_provider_id;

    public ServiceProviderPackagesFragment () {
        setHasOptionsMenu (true);
    }

    public static ServiceProviderPackagesFragment newInstance (int service_provider_id) {
        Bundle args = new Bundle ();
        args.putInt ("service_provider_id", service_provider_id);
        ServiceProviderPackagesFragment fragment = new ServiceProviderPackagesFragment ();
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
        View view = inflater.inflate (R.layout.fragment_service_provider_packages, container, false);

        initView (view);
        initData ();
        initListener ();

        return view;
    }

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
        menu.clear ();
        inflater.inflate (R.menu.menu_fragment_service_provider_packages, menu);
        SearchManager searchManager = (SearchManager) getActivity ().getSystemService (Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem (R.id.action_search).getActionView ();
        if (null != searchView) {
            searchView.setSearchableInfo (searchManager.getSearchableInfo (getActivity ().getComponentName ()));
        }


        searchView.setQueryHint ("Search Packages");
        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener () {
            public boolean onQueryTextChange (String newText) {
//                Utils.showToast (getActivity (), "search text packages " + newText);
                return true;
            }

            public boolean onQueryTextSubmit (String query) {
                //Here u can get the value "query" which is entered in the search box.
                return true;
            }
        };
        searchView.setOnQueryTextListener (queryTextListener);

        EditText et = (EditText) searchView.findViewById (R.id.search_src_text);
        et.setHintTextColor (getResources ().getColor (R.color.hint_color_white));
        et.setTypeface (SetTypeFace.getTypeface (getActivity ()));

        super.onCreateOptionsMenu (menu, inflater);
    }


    private void initView (View view) {
        rvPackageList = (RecyclerView) view.findViewById (R.id.rvPackageList);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById (R.id.swipe_refresh_layout);
    }

    private void initData () {
        serviceProviderPackagesList.clear ();

        serviceProviderPackagesList.add (new ServiceProviderPackage (1, "AC check up", "Complete AC check up and gas top up if required", "1699", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
        serviceProviderPackagesList.add (new ServiceProviderPackage (2, "Brake Overhaul", "Complete overhauling of brakes fluid and check up", "1399", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
        serviceProviderPackagesList.add (new ServiceProviderPackage (3, "Clutch Overhaul", "Complete overhauling of clutch and replacement if required", "2999", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));

        adapter = new ServiceProviderPackageAdapter (getActivity (), serviceProviderPackagesList);
        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter (adapter);
        alphaInAnimationAdapter.setDuration (700);
        rvPackageList.setAdapter (alphaInAnimationAdapter);

        rvPackageList.setHasFixedSize (true);
        rvPackageList.setLayoutManager (new LinearLayoutManager (getActivity ()));
        rvPackageList.setItemAnimator (new DefaultItemAnimator ());
        adapter.notifyDataSetChanged ();
    }

    private void initListener () {
        swipeRefreshLayout.setOnRefreshListener (new SwipeRefreshLayout.OnRefreshListener () {
            @Override
            public void onRefresh () {
                swipeRefreshLayout.setRefreshing (true);
                getAllServiceProviderPackages ();
            }
        });
    }


    private void getAllServiceProviderPackages () {
        Log.e ("karman", "in getallservicepackage");
//        vehicleList.clear ();
        final Handler handler = new Handler ();
        handler.postDelayed (new Runnable () {
            @Override
            public void run () {
                serviceProviderPackagesList.add (new ServiceProviderPackage (2, "Brake Overhaul", "Complete overhauling of brakes fluid and check up", "1399", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png"));
                adapter.notifyDataSetChanged ();
                swipeRefreshLayout.setRefreshing (false);
            }
        }, 1000);

    }

    @Override
    public void onRefresh () {

    }
}