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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.adapter.AllVehicleAdapter;
import com.actiknow.callsikandar.model.Vehicle;
import com.actiknow.callsikandar.utils.Utils;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;

/**
 * Created by Admin on 24-10-2016.
 */


public class ManageVehicleFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView rvVehicleList;
    SwipeRefreshLayout swipeRefreshLayout;

    ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle> ();

    AllVehicleAdapter adapter;

    public ManageVehicleFragment () {
        setHasOptionsMenu (true);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate (R.layout.fragment_manage_vehicle, container, false);
        setHasOptionsMenu (true);
        initView (rootView);
        initListener ();
        initData ();
        return rootView;
    }

    private void initData () {
        Vehicle vehicle1 = new Vehicle ("Polo", "DL 6SM 1234", "Volkswagen", "14000", "12/02/2016", "Petrol");
        Vehicle vehicle2 = new Vehicle ("Ecosport", "DL 6SM 2345", "Ford", "15560", "20/06/2016", "Petrol");

        vehicleList.add (vehicle1);
        vehicleList.add (vehicle2);
        adapter = new AllVehicleAdapter (getActivity (), vehicleList);

        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter (adapter);
        alphaAdapter.setDuration (700);
        rvVehicleList.setAdapter (alphaAdapter);
        rvVehicleList.setHasFixedSize (true);
        rvVehicleList.setLayoutManager (new LinearLayoutManager (getActivity ()));
        rvVehicleList.setItemAnimator (new DefaultItemAnimator ());

//        swipeRefreshLayout.setRefreshing (true);
//        getAllVehicles ();

    }

    private void initView (View v) {
        rvVehicleList = (RecyclerView) v.findViewById (R.id.rvVehicleList);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById (R.id.swipeRefreshLayout);
    }

    private void initListener () {
        swipeRefreshLayout.setOnRefreshListener (new SwipeRefreshLayout.OnRefreshListener () {
            @Override
            public void onRefresh () {
                swipeRefreshLayout.setRefreshing (true);
                getAllVehicles ();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
        menu.clear ();
        inflater.inflate (R.menu.menu_fragment_manage_vehicle, menu);
        SearchManager searchManager = (SearchManager) getActivity ().getSystemService (Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem (R.id.action_search).getActionView ();
        if (null != searchView) {
            searchView.setSearchableInfo (searchManager.getSearchableInfo (getActivity ().getComponentName ()));
        }

        searchView.setQueryHint ("Search Appointment");
        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener () {
            public boolean onQueryTextChange (String newText) {
                Utils.showToast (getActivity (), "search text home " + newText);
                return true;
            }

            public boolean onQueryTextSubmit (String query) {
                //Here u can get the value "query" which is entered in the search box.
                return true;
            }
        };
        searchView.setOnQueryTextListener (queryTextListener);

        super.onCreateOptionsMenu (menu, inflater);
    }

    private void getAllVehicles () {
//        vehicleList.clear ();
        final Handler handler = new Handler ();
        handler.postDelayed (new Runnable () {
            @Override
            public void run () {
                Vehicle vehicle1 = new Vehicle ("Polo", "DL 6SM 1234", "Volkswagen", "14000", "12/02/2016", "Petrol");
                vehicleList.add (vehicle1);
                adapter.notifyDataSetChanged ();
                swipeRefreshLayout.setRefreshing (false);
            }
        }, 1000);

    }

    @Override
    public void onRefresh () {
    }
}
