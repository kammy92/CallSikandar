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
import com.actiknow.callsikandar.activity.ServiceRequestDetailActivity;
import com.actiknow.callsikandar.model.ServiceRequest;
import com.actiknow.callsikandar.utils.SetTypeFace;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahul jain l on 07/11/2016.
 */

public class ServiceRequestAdapter extends RecyclerView.Adapter<ServiceRequestAdapter.ViewHolder> {
    OnItemClickListener mItemClickListener;
    private Activity activity;
    private List<ServiceRequest> serviceRequests = new ArrayList<ServiceRequest> ();

    public ServiceRequestAdapter (Activity activity, List<ServiceRequest> serviceRequestList) {
        this.activity = activity;
        this.serviceRequests = serviceRequestList;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from (parent.getContext ());
        final View sView = mInflater.inflate (R.layout.listview_item_service_request, parent, false);
        return new ViewHolder (sView);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {//        runEnterAnimation (holder.itemView);
        final ServiceRequest serviceRequest = serviceRequests.get (position);
        holder.tvRepairName.setTypeface (SetTypeFace.getTypeface (activity));
        holder.tvServiceRequestId.setTypeface (SetTypeFace.getTypeface (activity));
        holder.tvRequestDescription.setTypeface (SetTypeFace.getTypeface (activity));


        holder.tvRepairName.setText (serviceRequest.getRepairName ());
        holder.tvRequestDescription.setText (serviceRequest.getRepairDescription ());
        holder.tvServiceRequestId.setText ("" + serviceRequest.getRequestId ());
        Glide.with (activity).load (serviceRequest.getRepairIcon ()).placeholder (R.mipmap.ic_launcher).into (holder.ivRepairProviderIcon);

    }

    @Override
    public int getItemCount () {
        return serviceRequests.size ();
    }

    public void SetOnItemClickListener (final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick (View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvRepairName;
        TextView tvServiceRequestId;
        ImageView ivRepairProviderIcon;
        TextView tvRequestDescription;

        public ViewHolder (View view) {
            super (view);
            tvRepairName = (TextView) view.findViewById (R.id.tvRepairsName);
            tvServiceRequestId = (TextView) view.findViewById (R.id.tvRequestId);
            tvRequestDescription = (TextView) view.findViewById (R.id.tvServiceRequest);
            ivRepairProviderIcon = (ImageView) view.findViewById (R.id.ivRepairProviderIcon);
            view.setOnClickListener (this);
        }

        @Override
        public void onClick (View v) {
            Intent intent = new Intent (activity, ServiceRequestDetailActivity.class);
            activity.startActivity (intent);
            activity.overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);


        }
    }
}