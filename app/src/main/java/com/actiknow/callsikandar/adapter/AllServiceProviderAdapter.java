package com.actiknow.callsikandar.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.model.ServiceProvider;
import com.actiknow.callsikandar.utils.SetTypeFace;

import java.util.ArrayList;
import java.util.List;

public class AllServiceProviderAdapter extends RecyclerView.Adapter<AllServiceProviderAdapter.ViewHolder> {
    OnItemClickListener mItemClickListener;
    private Activity activity;
    private List<ServiceProvider> serviceProviderList = new ArrayList<ServiceProvider> ();

    public AllServiceProviderAdapter (Activity activity, List<ServiceProvider> serviceProviderList) {
        this.activity = activity;
        this.serviceProviderList = serviceProviderList;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from (parent.getContext ());
        final View sView = mInflater.inflate (R.layout.listview_item_service_provider, parent, false);
        return new ViewHolder (sView);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {//        runEnterAnimation (holder.itemView);
        final ServiceProvider serviceProvider = serviceProviderList.get (position);
        holder.name.setTypeface (SetTypeFace.getTypeface (activity));
        holder.address.setTypeface (SetTypeFace.getTypeface (activity));
        holder.distance.setTypeface (SetTypeFace.getTypeface (activity));
        holder.name.setText (serviceProvider.getName ());
        holder.address.setText (serviceProvider.getAddress ());
        holder.distance.setText (serviceProvider.getDistance ());
        holder.rating.setRating (Float.parseFloat (serviceProvider.getRating ()));
    }

    @Override
    public int getItemCount () {
        return serviceProviderList.size ();
    }

    public void SetOnItemClickListener (final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick (View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView address;
        TextView distance;
        RatingBar rating;

        public ViewHolder (View view) {
            super (view);
            name = (TextView) view.findViewById (R.id.tvName);
            address = (TextView) view.findViewById (R.id.tvAddress);
            distance = (TextView) view.findViewById (R.id.tvDistance);
            rating = (RatingBar) view.findViewById (R.id.ratingBar);
            view.setOnClickListener (this);
        }

        @Override
        public void onClick (View v) {
//            final Atm atm = atmList.get (getLayoutPosition ());
//            Constants.report.setAtm_id (atm.getAtm_id ());
//            Constants.report.setAuditor_id (Constants.auditor_id_main);
//            Constants.report.setAgency_id (atm.getAtm_agency_id ());
//            Constants.report.setAtm_unique_id (atm.getAtm_unique_id ().toUpperCase ());
//            Constants.report.setLatitude (String.valueOf (Constants.latitude));
//            Constants.report.setLongitude (String.valueOf (Constants.longitude));
//
//            Intent intent = new Intent (activity, AtmDetailActivity.class);
//            activity.startActivity (intent);
//            activity.overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }
}