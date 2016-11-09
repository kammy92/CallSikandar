package com.actiknow.callsikandar.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.model.ServiceProviderPackage;
import com.actiknow.callsikandar.utils.SetTypeFace;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by l on 07/11/2016.
 */

public class ServiceProviderPackageAdapter extends RecyclerView.Adapter<ServiceProviderPackageAdapter.ViewHolder> {
    OnItemClickListener mItemClickListener;
    private Activity activity;
    private List<ServiceProviderPackage> serviceProviderPackageList = new ArrayList<ServiceProviderPackage> ();

    public ServiceProviderPackageAdapter (Activity activity, List<ServiceProviderPackage> serviceProviderPackageList) {
        this.activity = activity;
        this.serviceProviderPackageList = serviceProviderPackageList;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from (parent.getContext ());
        final View sView = mInflater.inflate (R.layout.listview_item_service_provider_package, parent, false);
        return new ViewHolder (sView);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {
        //        runEnterAnimation (holder.itemView);
        final ServiceProviderPackage serviceProviderPackage = serviceProviderPackageList.get (position);
        holder.tvName.setTypeface (SetTypeFace.getTypeface (activity));
        holder.tvPrice.setTypeface (SetTypeFace.getTypeface (activity));
        holder.tvDescription.setTypeface (SetTypeFace.getTypeface (activity));

        holder.tvName.setText (serviceProviderPackage.getName ());
        holder.tvPrice.setText (serviceProviderPackage.getPrice ());
        holder.tvDescription.setText (serviceProviderPackage.getDescription ());

        Glide.with (activity).load (serviceProviderPackage.getImage ()).placeholder (R.mipmap.ic_launcher).into (holder.ivPackageIcon);
    }

    @Override
    public int getItemCount () {
        return serviceProviderPackageList.size ();
    }

    public void SetOnItemClickListener (final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick (View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        TextView tvDescription;
        TextView tvPrice;
        ImageView ivPackageIcon;


        public ViewHolder (View view) {
            super (view);
            tvName = (TextView) view.findViewById (R.id.tvName);
            tvDescription = (TextView) view.findViewById (R.id.tvDescription);
            tvPrice = (TextView) view.findViewById (R.id.tvCharges);
            ivPackageIcon = (ImageView) view.findViewById (R.id.ivIcon);
            view.setOnClickListener (this);
        }

        @Override
        public void onClick (View v) {
            final ServiceProviderPackage serviceProviderPackage = serviceProviderPackageList.get (getLayoutPosition ());
            // Intent intent = new Intent (activity, ServiceDetailActivity.class);
            // activity.startActivity (intent);
            //  activity.overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);


        }
    }
}