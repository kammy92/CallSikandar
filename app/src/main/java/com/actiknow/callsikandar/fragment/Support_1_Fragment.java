package com.actiknow.callsikandar.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.actiknow.callsikandar.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by actiknow on 10/27/16.
 */

public class Support_1_Fragment extends Fragment {
    ListView lvSupportList;
    String status;
    List<String> SupportList = new ArrayList<String> ();


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate (R.layout.fragment_support_1, null);
        initView (v);
        initData ();
        initListener ();
        initAdapter ();
        return v;

    }

    private void initView (View v) {
        lvSupportList = (ListView) v.findViewById (R.id.lvSupportList);
    }

    private void initData () {
        SupportList.add ("Contact us");
        SupportList.add ("Pricing");
        SupportList.add ("Service Providers");
        SupportList.add ("Doorstep Services");
        SupportList.add ("Pickup and Drop");
        SupportList.add ("Car Cleaning");
        SupportList.add ("Wheels and Tyres");
        SupportList.add ("Regular Maintenance");
        SupportList.add ("Breakdown");
        SupportList.add ("Service Requests");
    }

    private void initListener () {

    }

    private void initAdapter () {
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String> (getActivity (), android.R.layout.simple_list_item_1, SupportList);
        lvSupportList.setAdapter (itemsAdapter);
        lvSupportList.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText (getActivity (), String.valueOf (position), Toast.LENGTH_LONG).show ();
                switch (position) {
                    case 0:
                        status = "1";
                        break;
                    case 1:
                        status = "2";
                        break;
                    case 2:
                        status = "3";
                        break;
                    case 3:
                        status = "4";
                        break;
                    case 4:
                        status = "5";
                        break;
                    case 5:
                        status = "6";
                        break;
                    case 6:
                        status = "7";
                        break;
                    case 7:
                        status = "8";
                        break;
                    case 8:
                        status = "9";
                        break;
                    case 9:
                        status = "10";
                        break;
                }
                Bundle bundle = new Bundle ();
                bundle.putString ("status", status);
                FragmentManager fragmentManager = getFragmentManager ();
                FragmentTransaction fragmentTransaction;
                fragmentTransaction = fragmentManager.beginTransaction ();
                Support_2_Fragment f2 = new Support_2_Fragment ();
                f2.setArguments (bundle);
                fragmentTransaction.replace (R.id.fragment_container, f2, "fragment2");
                fragmentTransaction.commit ();
            }
        });
    }


}


