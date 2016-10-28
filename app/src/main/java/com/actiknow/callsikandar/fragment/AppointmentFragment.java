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
import android.widget.EditText;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.adapter.AllAppointmentAdapter;
import com.actiknow.callsikandar.model.Appointment;
import com.actiknow.callsikandar.utils.SetTypeFace;
import com.actiknow.callsikandar.utils.Utils;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;

/**
 * Created by Admin on 24-10-2016.
 */


public class AppointmentFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    public static ArrayList<Appointment> appointmentList = new ArrayList<Appointment> ();
    RecyclerView rvAppointmentList;
    SwipeRefreshLayout swipeRefreshLayout;
    AllAppointmentAdapter adapter;

    public AppointmentFragment () {
        setHasOptionsMenu (true);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate (R.layout.fragment_appointment, container, false);
        setHasOptionsMenu (true);
        initView (rootView);
        initData ();
        initListener ();
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
        menu.clear ();
        inflater.inflate (R.menu.menu_fragment_appointment, menu);
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

        EditText et = (EditText) searchView.findViewById (R.id.search_src_text);
        et.setHintTextColor (getResources ().getColor (R.color.text_color_white));
        et.setTypeface (SetTypeFace.getTypeface (getActivity ()));

        super.onCreateOptionsMenu (menu, inflater);
    }

    private void initData () {
        appointmentList.clear ();
        Appointment appointment1 = new Appointment (1, "Appointment 1", "DL 6SM 1234", "Paint", "Pending");
        Appointment appointment2 = new Appointment (2, "Appointment 2", "DL 6AS 2342", "Car Cleaning", "Approved");
        appointmentList.add (appointment1);
        appointmentList.add (appointment2);
        adapter = new AllAppointmentAdapter (getActivity (), appointmentList);

        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter (adapter);
        alphaAdapter.setDuration (700);
        rvAppointmentList.setAdapter (alphaAdapter);
        rvAppointmentList.setHasFixedSize (true);
        rvAppointmentList.setLayoutManager (new LinearLayoutManager (getActivity ()));
        rvAppointmentList.setItemAnimator (new DefaultItemAnimator ());

        swipeRefreshLayout.setColorSchemeResources (R.color.primary);
//        swipeRefreshLayout.setColorSchemeColors (Color.BLUE, Color.YELLOW, Color.GREEN, Color.BLUE);
//        swipeRefreshLayout.setRefreshing (true);
//        getAllVehicles ();
    }

    private void initView (View v) {
        rvAppointmentList = (RecyclerView) v.findViewById (R.id.rvAppointmentList);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById (R.id.swipeRefreshLayout);
    }

    private void initListener () {
        swipeRefreshLayout.setOnRefreshListener (new SwipeRefreshLayout.OnRefreshListener () {
            @Override
            public void onRefresh () {
                swipeRefreshLayout.setRefreshing (true);
                getAllAppointments ();
            }
        });
    }

    private void getAllAppointments () {
//        vehicleList.clear ();
        final Handler handler = new Handler ();
        handler.postDelayed (new Runnable () {
            @Override
            public void run () {
                Appointment appointment1 = new Appointment (1, "Appointment 1", "DL 6SM 1234", "Paint", "Pending");
                appointmentList.add (appointment1);
                adapter.notifyDataSetChanged ();
                swipeRefreshLayout.setRefreshing (false);
            }
        }, 1000);

    }

    @Override
    public void onRefresh () {
    }


}
