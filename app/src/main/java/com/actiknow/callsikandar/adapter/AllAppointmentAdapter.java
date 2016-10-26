package com.actiknow.callsikandar.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.activity.AddVehicleActivity;
import com.actiknow.callsikandar.model.Appointment;
import com.actiknow.callsikandar.model.Vehicle;
import com.actiknow.callsikandar.utils.SetTypeFace;

import java.util.ArrayList;
import java.util.List;

import static com.actiknow.callsikandar.fragment.ManageVehicleFragment.vehicleList;

public class AllAppointmentAdapter extends RecyclerView.Adapter<AllAppointmentAdapter.ViewHolder> {
    OnItemClickListener mItemClickListener;
    private Activity activity;
    private List<Appointment> appointmentList = new ArrayList<Appointment> ();

    public AllAppointmentAdapter (Activity activity, List<Appointment> appointmentList) {
        this.activity = activity;
        this.appointmentList = appointmentList;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from (parent.getContext ());
        final View sView = mInflater.inflate (R.layout.listview_item_vehicle, parent, false);
        return new ViewHolder (sView);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {//        runEnterAnimation (holder.itemView);
        final Appointment appointment = appointmentList.get (position);
        holder.appointment_id.setTypeface (SetTypeFace.getTypeface (activity));
        holder.service_type.setTypeface (SetTypeFace.getTypeface (activity));
        holder.status.setTypeface (SetTypeFace.getTypeface (activity));
        holder.car_registration_number.setTypeface (SetTypeFace.getTypeface (activity));
        holder.appointment_id.setText (appointment.getId ());
        holder.car_registration_number.setText (appointment.getCar_registration_number ());
        holder.status.setText (appointment.getStatus ());
        holder.service_type.setText (appointment.getService_type ());
    }

    @Override
    public int getItemCount () {
        return appointmentList.size ();
    }

    public void SetOnItemClickListener (final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick (View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView service_type;
        TextView status;
        TextView car_registration_number;
        TextView appointment_id;

        public ViewHolder (View view) {
            super (view);
            service_type = (TextView) view.findViewById (R.id.tvModel);
            status = (TextView) view.findViewById (R.id.tvRegistrationNumber);
            car_registration_number = (TextView) view.findViewById (R.id.tvManufacturer);
            appointment_id = (TextView) view.findViewById (R.id.tvLastServiceDate);
            view.setOnClickListener (this);
        }

        @Override
        public void onClick (View v) {
            final Vehicle vehicle = vehicleList.get (getLayoutPosition ());
//            Constants.report.setAtm_id (atm.getAtm_id ());
//            Constants.report.setAuditor_id (Constants.auditor_id_main);
//            Constants.report.setAgency_id (atm.getAtm_agency_id ());
//            Constants.report.setAtm_unique_id (atm.getAtm_unique_id ().toUpperCase ());
//            Constants.report.setLatitude (String.valueOf (Constants.latitude));
//            Constants.report.setLongitude (String.valueOf (Constants.longitude));
//

            Intent intent = new Intent (activity, AddVehicleActivity.class);
            intent.putExtra ("update_vehicle", true);
            intent.putExtra ("vehicle_id", vehicle.getId ());
            activity.startActivity (intent);
            activity.overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }
}