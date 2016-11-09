package com.actiknow.callsikandar.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.adapter.ServiceProviderRatingAdapter;
import com.actiknow.callsikandar.model.ServiceProviderRating;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;

/**
 * Created by Admin on 03-11-2016.
 */
public class ServiceProviderRatingFragment extends Fragment {
    RecyclerView rvRatingList;
    RatingBar rbRating;
    TextView tvRating;
    TextView tvRatingReview;
    ServiceProviderRatingAdapter adapter;
    List<ServiceProviderRating> serviceProviderRatingList = new ArrayList<ServiceProviderRating> ();
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
        initView (view);
        initData ();
        initAdapter ();
        return view;
    }

    private void initData () {

        serviceProviderRatingList.clear ();

        serviceProviderRatingList.add (new ServiceProviderRating (1, "ABC", "4.0", "07 Nov 2016", "12:19 AM", "Good Experience"));
        serviceProviderRatingList.add (new ServiceProviderRating (2, "XYZ", "3.0", "08 Nov 2016", "11:19 AM", "Average"));

        adapter = new ServiceProviderRatingAdapter (getActivity (), serviceProviderRatingList);

        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter (adapter);
        alphaAdapter.setDuration (700);
        rvRatingList.setAdapter (alphaAdapter);
        rvRatingList.setHasFixedSize (true);
        rvRatingList.setLayoutManager (new LinearLayoutManager (getActivity ()));
        rvRatingList.setItemAnimator (new DefaultItemAnimator ());
        adapter.notifyDataSetChanged ();

        rbRating.setRating (Float.parseFloat (String.valueOf (4.0)));

    }

    private void initView (View view) {
        rvRatingList = (RecyclerView) view.findViewById (R.id.rvRatingList);
        tvRating = (TextView) view.findViewById (R.id.tvRating);
        tvRatingReview = (TextView) view.findViewById (R.id.tvReview);
        rbRating = (RatingBar) view.findViewById (R.id.rbRating);

    }

    private void initAdapter () {

    }
}