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
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.adapter.AllServiceProviderAdapter;
import com.actiknow.callsikandar.model.ServiceProvider;
import com.actiknow.callsikandar.utils.SetTypeFace;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;

/**
 * Created by Admin on 24-10-2016.
 */

public class ServiceProviderFragment extends Fragment {
    RecyclerView rvServiceProviderList;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<ServiceProvider> serviceProviderList = new ArrayList<ServiceProvider> ();
    ArrayList<ServiceProvider> tempArrayList = new ArrayList<ServiceProvider> ();
    AllServiceProviderAdapter adapter;

    public ServiceProviderFragment () {
        setHasOptionsMenu (true);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate (R.layout.fragment_service_provider, container, false);
        setHasOptionsMenu (true);
        initView (rootView);
        initListener ();
        initData ();
        return rootView;
    }

    private void initData () {
        ServiceProvider serviceProvider1 = new ServiceProvider (1, "Carnation - SS Automibiles", "9.31 Km", "Sector 18", "3.7");
        ServiceProvider serviceProvider2 = new ServiceProvider (2, "Bosch Express (CNG Nation)", "9.95 Km", "DLF Phase 1", "4.3");
        serviceProviderList.add (serviceProvider1);
        serviceProviderList.add (serviceProvider2);
        adapter = new AllServiceProviderAdapter (getActivity (), serviceProviderList);

        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter (adapter);
        alphaAdapter.setDuration (700);
        rvServiceProviderList.setAdapter (alphaAdapter);
        rvServiceProviderList.setHasFixedSize (true);
        rvServiceProviderList.setLayoutManager (new LinearLayoutManager (getActivity ()));
        rvServiceProviderList.setItemAnimator (new DefaultItemAnimator ());
        swipeRefreshLayout.setColorSchemeResources (R.color.primary);
//        swipeRefreshLayout.setRefreshing (true);
//        getAllVehicles ();
    }

    private void initView (View v) {
        rvServiceProviderList = (RecyclerView) v.findViewById (R.id.rvServiceProviderList);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById (R.id.swipeRefreshLayout);
    }

    private void initListener () {
        swipeRefreshLayout.setOnRefreshListener (new SwipeRefreshLayout.OnRefreshListener () {
            @Override
            public void onRefresh () {
                swipeRefreshLayout.setRefreshing (true);
                getAllServiceProviders ();
            }
        });
    }

    private void getAllServiceProviders () {
        final Handler handler = new Handler ();
        handler.postDelayed (new Runnable () {
            @Override
            public void run () {
                ServiceProvider serviceProvider1 = new ServiceProvider (1, "Carnation - SS Automibiles", "9.31 Km", "Sector 18", "3.7");
                serviceProviderList.add (serviceProvider1);
                adapter.notifyDataSetChanged ();
                swipeRefreshLayout.setRefreshing (false);
            }
        }, 1000);
    }


    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
        menu.clear ();
        inflater.inflate (R.menu.menu_fragment_service_provider, menu);
        SearchManager searchManager = (SearchManager) getActivity ().getSystemService (Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem (R.id.action_search).getActionView ();
        if (null != searchView) {
            searchView.setSearchableInfo (searchManager.getSearchableInfo (getActivity ().getComponentName ()));
        }

        searchView.setQueryHint ("Search Service Provider");
        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener () {
            public boolean onQueryTextChange (String newText) {
                int textlength = newText.length ();
                tempArrayList.clear ();

                for (ServiceProvider serviceProvider : serviceProviderList) {
                    if (textlength <= serviceProvider.getName ().length () || textlength <= serviceProvider.getAddress ().length ()) {
                        if (serviceProvider.getName ().toLowerCase ().contains (newText.toLowerCase ()) || serviceProvider.getAddress ().toLowerCase ().contains (newText.toLowerCase ())) {
                            tempArrayList.add (serviceProvider);
                        }
                    }
                }

                adapter = new AllServiceProviderAdapter (getActivity (), tempArrayList);
                AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter (adapter);
                alphaAdapter.setDuration (700);
                alphaAdapter.setFirstOnly (true);
                alphaAdapter.setInterpolator (new OvershootInterpolator ());

//                SlideInLeftAnimationAdapter slideAdapter = new SlideInLeftAnimationAdapter (alphaAdapter);
//                slideAdapter.setDuration (500);
//                slideAdapter.setFirstOnly (true);
                rvServiceProviderList.setAdapter (alphaAdapter);

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

}
