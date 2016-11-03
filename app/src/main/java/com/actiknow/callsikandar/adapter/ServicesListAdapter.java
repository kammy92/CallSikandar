package com.actiknow.callsikandar.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.activity.ServiceDetailsListActivity;
import com.actiknow.callsikandar.model.Service;
import com.actiknow.callsikandar.utils.SetTypeFace;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by l on 27/10/2016.
 */

public class ServicesListAdapter extends RecyclerView.Adapter<ServicesListAdapter.ViewHolder> {
    OnItemClickListener mItemClickListener;
    private Activity activity;
    private List<Service> servicesList = new ArrayList<Service> ();

    public ServicesListAdapter (Activity activity, List<Service> servicesList) {
        this.activity = activity;
        this.servicesList = servicesList;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from (parent.getContext ());
        final View sView = mInflater.inflate (R.layout.listview_item_services, parent, false);
        return new ViewHolder (sView);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {//        runEnterAnimation (holder.itemView);
        final Service service = servicesList.get (position);
        holder.tvServiceName.setTypeface (SetTypeFace.getTypeface (activity));
        holder.tvServiceDescription.setTypeface (SetTypeFace.getTypeface (activity));

        holder.tvServiceName.setText (service.getName ());
        holder.tvServiceDescription.setText (service.getDescription ());

        Glide.with (activity).load (service.getIcon ()).placeholder (R.mipmap.ic_launcher).into (holder.ivServiceIcon);


//        holder.ivServiceIcon.setImageResource();

    }

    @Override
    public int getItemCount () {
        return servicesList.size ();
    }

    public void SetOnItemClickListener (final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick (View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvServiceName;
        TextView tvServiceDescription;
        ImageView ivServiceIcon;

        public ViewHolder (View view) {
            super (view);
            tvServiceName = (TextView) view.findViewById (R.id.tvServiceName);
            tvServiceDescription = (TextView) view.findViewById (R.id.tvServiceDescription);
            ivServiceIcon = (ImageView) view.findViewById (R.id.ivServiceIcon);
            view.setOnClickListener (this);
        }

        @Override
        public void onClick (View v) {

            final Service service = servicesList.get (getLayoutPosition ());


            Intent intent = new Intent (activity, ServiceDetailsListActivity.class);
            intent.putExtra ("service_id", service.getId ());
            intent.putExtra ("service_name", service.getName ());
            intent.putExtra ("service_type_id", service.getType_id ());
            intent.putExtra ("service_type_name", service.getType_name ());
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