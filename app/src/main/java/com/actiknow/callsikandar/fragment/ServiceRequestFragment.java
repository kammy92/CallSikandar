package com.actiknow.callsikandar.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
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
import com.actiknow.callsikandar.adapter.ServiceRequestAdapter;
import com.actiknow.callsikandar.model.ServiceRequest;
import com.actiknow.callsikandar.utils.SetTypeFace;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;

/**
 * Created by Admin on 24-10-2016.
 */


public class ServiceRequestFragment extends Fragment {

    RecyclerView rvServiceRequestList;
    SwipeRefreshLayout swipeRefreshLayout;

    List<ServiceRequest> serviceRequestList = new ArrayList<ServiceRequest> ();
    ServiceRequestAdapter serviceRequestAdapter;

    public ServiceRequestFragment () {
        setHasOptionsMenu (true);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate (R.layout.fragment_service_request, container, false);
        rvServiceRequestList = (RecyclerView) rootView.findViewById (R.id.rvServiceRequestList);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById (R.id.swipe_refresh_layout);

        initData ();
        setHasOptionsMenu (true);
        return rootView;
    }

    private void initData () {
        serviceRequestList.clear ();
        ServiceRequest serviceRequest1 = new ServiceRequest (201301, "MBA Motor Parts", "Services", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png");
        ServiceRequest serviceRequest2 = new ServiceRequest (202303, "Globel EnterPrices", "Break Repairing", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png");
        ServiceRequest serviceRequest3 = new ServiceRequest (205305, "MultiIcon Repair workshop", "Parts Services", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png");
        ServiceRequest serviceRequest4 = new ServiceRequest (207307, "Motor Parts Center", "Oil Services", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png");
        ServiceRequest serviceRequest5 = new ServiceRequest (209309, "Motor Parts", "Break Services", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/package+icons/repairs/android/xhdpi/ic_pi_repair_acsan.png");

        serviceRequestList.add (serviceRequest1);
        serviceRequestList.add (serviceRequest2);
        serviceRequestList.add (serviceRequest3);
        serviceRequestList.add (serviceRequest4);
        serviceRequestList.add (serviceRequest5);

        serviceRequestAdapter = new ServiceRequestAdapter (getActivity (), serviceRequestList);
        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter (serviceRequestAdapter);
        alphaInAnimationAdapter.setDuration (700);
        rvServiceRequestList.setAdapter (alphaInAnimationAdapter);
        rvServiceRequestList.setHasFixedSize (true);
        rvServiceRequestList.setLayoutManager (new LinearLayoutManager (getActivity ()));
        rvServiceRequestList.setItemAnimator (new DefaultItemAnimator ());


    }

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
        menu.clear ();
        inflater.inflate (R.menu.menu_fragment_service_request, menu);
        SearchManager searchManager = (SearchManager) getActivity ().getSystemService (Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem (R.id.action_search).getActionView ();
        if (null != searchView) {
            searchView.setSearchableInfo (searchManager.getSearchableInfo (getActivity ().getComponentName ()));
        }
        searchView.setQueryHint ("Search Requests");
        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener () {
            public boolean onQueryTextChange (String newText) {
//                Utils.showToast (getActivity (), "search text history " + newText);


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


}
