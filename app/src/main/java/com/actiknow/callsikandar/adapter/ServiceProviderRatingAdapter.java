package com.actiknow.callsikandar.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.model.ServiceProviderRating;
import com.actiknow.callsikandar.utils.SetTypeFace;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by l on 07/11/2016.
 */

public class ServiceProviderRatingAdapter extends RecyclerView.Adapter<ServiceProviderRatingAdapter.ViewHolder> {
    OnItemClickListener mItemClickListener;
    private Activity activity;
    private List<ServiceProviderRating> serviceProviderRatingList = new ArrayList<ServiceProviderRating> ();

    public ServiceProviderRatingAdapter (Activity activity, List<ServiceProviderRating> serviceProviderRatingList) {
        this.activity = activity;
        this.serviceProviderRatingList = serviceProviderRatingList;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from (parent.getContext ());
        final View sView = mInflater.inflate (R.layout.listview_item_service_provider_rating, parent, false);
        return new ViewHolder (sView);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {//        runEnterAnimation (holder.itemView);
        final ServiceProviderRating serviceRating = serviceProviderRatingList.get (position);
        holder.tvName.setTypeface (SetTypeFace.getTypeface (activity));
        holder.tvRatingDate.setTypeface (SetTypeFace.getTypeface (activity));
        holder.tvRatingTime.setTypeface (SetTypeFace.getTypeface (activity));
        holder.tvDescription.setTypeface (SetTypeFace.getTypeface (activity));
        // holder.rbRating.setTypeface (SetTypeFace.getTypeface (activity));

        holder.tvName.setText (serviceRating.getName ());
        holder.tvRatingDate.setText (serviceRating.getDate ());
        holder.tvRatingTime.setText (serviceRating.getTime ());
        holder.tvDescription.setText (serviceRating.getDescription ());
        holder.rbRating.setRating (Float.parseFloat ("" + serviceRating.getRating ()));

    }

    @Override
    public int getItemCount () {
        return serviceProviderRatingList.size ();
    }

    public void SetOnItemClickListener (final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick (View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        TextView tvRatingDate;
        TextView tvRatingTime;
        TextView tvDescription;
        RatingBar rbRating;

        public ViewHolder (View view) {
            super (view);
            tvName = (TextView) view.findViewById (R.id.tvUserName);
            rbRating = (RatingBar) view.findViewById (R.id.ratingBar);
            tvRatingDate = (TextView) view.findViewById (R.id.tvRatingDate);
            tvRatingTime = (TextView) view.findViewById (R.id.tvRatingTime);
            tvDescription = (TextView) view.findViewById (R.id.tvRatingDescription);


            view.setOnClickListener (this);
        }

        @Override
        public void onClick (View v) {
            final ServiceProviderRating serviceRating = serviceProviderRatingList.get (getLayoutPosition ());
            // Intent intent = new Intent (activity, ServiceDetailActivity.class);
            // activity.startActivity (intent);
            //  activity.overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);


        }
    }
}