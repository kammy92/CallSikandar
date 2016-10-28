package com.actiknow.callsikandar.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.utils.SetTypeFace;
import com.actiknow.callsikandar.utils.Utils;

/**
 * Created by Admin on 24-10-2016.
 */


public class ServiceRequestFragment extends Fragment {

    public ServiceRequestFragment () {
        setHasOptionsMenu (true);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate (R.layout.fragment_service_request, container, false);
        setHasOptionsMenu (true);
        return rootView;
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
                Utils.showToast (getActivity (), "search text history " + newText);


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
