package com.actiknow.callsikandar.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.model.Vehicle;
import com.actiknow.callsikandar.utils.SetTypeFace;

import java.util.ArrayList;
import java.util.List;

public class AllVehicleAdapter extends RecyclerView.Adapter<AllVehicleAdapter.ViewHolder> {
    OnItemClickListener mItemClickListener;
    private Activity activity;
    private List<Vehicle> vehicleList = new ArrayList<Vehicle> ();

    public AllVehicleAdapter (Activity activity, List<Vehicle> vehicleList) {
        this.activity = activity;
        this.vehicleList = vehicleList;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from (parent.getContext ());
        final View sView = mInflater.inflate (R.layout.listview_item_vehicle, parent, false);
        return new ViewHolder (sView);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {//        runEnterAnimation (holder.itemView);
        final Vehicle vehicle = vehicleList.get (position);
        holder.model.setTypeface (SetTypeFace.getTypeface (activity));
        holder.registration_number.setTypeface (SetTypeFace.getTypeface (activity));
        holder.manufacturer.setTypeface (SetTypeFace.getTypeface (activity));
        holder.last_service_date.setTypeface (SetTypeFace.getTypeface (activity));
        holder.fuel_type.setTypeface (SetTypeFace.getTypeface (activity));
        holder.model.setText (vehicle.getModel ());
        holder.registration_number.setText (vehicle.getRegistration_number ());
        holder.manufacturer.setText (vehicle.getManufacturer ());
        holder.last_service_date.setText (vehicle.getLast_service_date ());
        holder.fuel_type.setText (vehicle.getFuel_type ());
    }

    @Override
    public int getItemCount () {
        return vehicleList.size ();
    }

    public void SetOnItemClickListener (final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick (View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView model;
        TextView registration_number;
        TextView manufacturer;
        TextView last_service_date;
        TextView fuel_type;

        public ViewHolder (View view) {
            super (view);
            model = (TextView) view.findViewById (R.id.tvModel);
            registration_number = (TextView) view.findViewById (R.id.tvRegistrationNumber);
            manufacturer = (TextView) view.findViewById (R.id.tvManufacturer);
            last_service_date = (TextView) view.findViewById (R.id.tvLastServiceDate);
            fuel_type = (TextView) view.findViewById (R.id.tvFuelType);

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