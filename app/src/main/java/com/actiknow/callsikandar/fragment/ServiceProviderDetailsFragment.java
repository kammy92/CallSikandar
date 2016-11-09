package com.actiknow.callsikandar.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.utils.Utils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Admin on 03-11-2016.
 */
public class ServiceProviderDetailsFragment extends Fragment implements OnMapReadyCallback {
    private static View view;
    MapView mMapView;
    MapFragment mapFragment;
    TextView tvServiceProviderName;
    private int service_provider_id;
    private GoogleMap mMap;

    public static ServiceProviderDetailsFragment newInstance (int service_provider_id) {
        Bundle args = new Bundle ();
        args.putInt ("service_provider_id", service_provider_id);
        ServiceProviderDetailsFragment fragment = new ServiceProviderDetailsFragment ();
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
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent ();
            if (parent != null)
                parent.removeView (view);
        }
        try {
            view = inflater.inflate (R.layout.fragment_service_provider_detail, container, false);
            mapFragment = (MapFragment) getActivity ().getFragmentManager ().findFragmentById (R.id.googleMap);
            mapFragment.getMapAsync (this);
        } catch (InflateException e) {
        /* map is already there, just return view as it is */
        }
        tvServiceProviderName = (TextView) view.findViewById (R.id.tvServiceProviderName);
        Utils.setTypefaceToAllViews (getActivity (), tvServiceProviderName);

        return view;
    }


    @Override
    public void onMapReady (GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng latLng = new LatLng (28.457523, 77.026344);
        //  mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );

        //This goes up to 21
        mMap.moveCamera (CameraUpdateFactory.newLatLngZoom (latLng, 16.0f));
        mMap.addMarker (new MarkerOptions ().position (latLng).title ("Gurgaon"));
        mMap.getUiSettings ().setAllGesturesEnabled (false);

        mMap.moveCamera (CameraUpdateFactory.newLatLng (latLng));
    }
}