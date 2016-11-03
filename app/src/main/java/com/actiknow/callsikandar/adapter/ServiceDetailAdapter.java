package com.actiknow.callsikandar.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.activity.ServiceDetailActivity;
import com.actiknow.callsikandar.model.ServiceDetail;
import com.actiknow.callsikandar.utils.SetTypeFace;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by l on 27/10/2016.
 */

public class ServiceDetailAdapter extends RecyclerView.Adapter<ServiceDetailAdapter.ViewHolder> {
    OnItemClickListener mItemClickListener;
    private Activity activity;
    private List<ServiceDetail> serviceDetailList = new ArrayList<ServiceDetail> ();

    public ServiceDetailAdapter (Activity activity, List<ServiceDetail> serviceDetailList) {
        this.activity = activity;
        this.serviceDetailList = serviceDetailList;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from (parent.getContext ());
        final View sView = mInflater.inflate (R.layout.listview_item_service_detail, parent, false);
        return new ViewHolder (sView);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {//        runEnterAnimation (holder.itemView);
        final ServiceDetail serviceDetail = serviceDetailList.get (position);
        holder.tvName.setTypeface (SetTypeFace.getTypeface (activity));
        holder.tvAddressLine1.setTypeface (SetTypeFace.getTypeface (activity));
        holder.tvAddressLine2.setTypeface (SetTypeFace.getTypeface (activity));
        holder.tvDistance.setTypeface (SetTypeFace.getTypeface (activity));
        holder.tvCharges.setTypeface (SetTypeFace.getTypeface (activity));


        holder.tvName.setText (serviceDetail.getService_name ());
        holder.tvAddressLine1.setText (serviceDetail.getService_provider_name ());
        holder.tvAddressLine2.setText (serviceDetail.getAddress_2 ());
        holder.tvDistance.setText ("" + serviceDetail.getDistance () + "km");
        holder.tvCharges.setText (activity.getResources ().getString (R.string.Rs) + "" + serviceDetail.getCharges ());
        holder.ratingBar.setRating (Float.parseFloat ("" + serviceDetail.getRating ()));

        Glide.with (activity).load (serviceDetail.getIcon ()).placeholder (R.mipmap.ic_launcher).into (holder.ivServiceIcon);
    }

    @Override
    public int getItemCount () {
        return serviceDetailList.size ();
    }

    public void SetOnItemClickListener (final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick (View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        TextView tvAddressLine1;
        TextView tvAddressLine2;
        TextView tvDistance;
        TextView tvCharges;
        ImageView ivServiceIcon;
        RatingBar ratingBar;

        public ViewHolder (View view) {
            super (view);
            tvName = (TextView) view.findViewById (R.id.tvName);
            tvAddressLine1 = (TextView) view.findViewById (R.id.tvServiceProviderName);
            tvAddressLine2 = (TextView) view.findViewById (R.id.tvAddressLine);
            tvDistance = (TextView) view.findViewById (R.id.tvDistance);
            tvCharges = (TextView) view.findViewById (R.id.tvCharges);
            ivServiceIcon = (ImageView) view.findViewById (R.id.ivIcon);
            ratingBar = (RatingBar) view.findViewById (R.id.ratingBar);


            view.setOnClickListener (this);
        }

        @Override
        public void onClick (View v) {
            final ServiceDetail serviceDetail = serviceDetailList.get (getLayoutPosition ());


            Intent intent = new Intent (activity, ServiceDetailActivity.class);
            intent.putExtra ("service_id", serviceDetail.getService_id ());
            intent.putExtra ("service_name", serviceDetail.getService_name ());
            intent.putExtra ("price", "" + serviceDetail.getCharges ());
            intent.putExtra ("service_provider_name", serviceDetail.getService_provider_name ());
            activity.startActivity (intent);
            activity.overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);


            // final Vehicle vehicle = vehicleList.get (getLayoutPosition ());
//            Constants.report.setAtm_id (atm.getAtm_id ());
//            Constants.report.setAuditor_id (Constants.auditor_id_main);
//            Constants.report.setAgency_id (atm.getAtm_agency_id ());
//            Constants.report.setAtm_unique_id (atm.getAtm_unique_id ().toUpperCase ());
//            Constants.report.setLatitude (String.valueOf (Constants.latitude));
//            Constants.report.setLongitude (String.valueOf (Constants.longitude));
//

            //  Intent intent = new Intent (activity, AddVehicleActivity.class);
            //  intent.putExtra ("update_vehicle", true);
            // intent.putExtra ("vehicle_id", vehicle.getId ());
            //activity.startActivity (intent);
            //activity.overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }
}