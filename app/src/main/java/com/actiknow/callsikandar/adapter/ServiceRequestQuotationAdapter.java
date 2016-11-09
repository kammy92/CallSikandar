package com.actiknow.callsikandar.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.model.ServiceQuotations;
import com.actiknow.callsikandar.utils.SetTypeFace;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by l on 08/11/2016.
 */

public class ServiceRequestQuotationAdapter extends RecyclerView.Adapter<ServiceRequestQuotationAdapter.ViewHolder> {
    OnItemClickListener mItemClickListener;
    private Activity activity;
    private List<ServiceQuotations> serviceQuotations = new ArrayList<ServiceQuotations> ();

    public ServiceRequestQuotationAdapter (Activity activity, List<ServiceQuotations> serviceQuotationsList) {
        this.activity = activity;
        this.serviceQuotations = serviceQuotationsList;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from (parent.getContext ());
        final View sView = mInflater.inflate (R.layout.listview_item_quotation, parent, false);
        return new ViewHolder (sView);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {//        runEnterAnimation (holder.itemView);
        final ServiceQuotations serviceQuotation = serviceQuotations.get (position);
        holder.tvName.setTypeface (SetTypeFace.getTypeface (activity));

        holder.tvName.setText (serviceQuotation.getName ());

    }

    @Override
    public int getItemCount () {
        return serviceQuotations.size ();
    }

    public void SetOnItemClickListener (final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick (View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;


        public ViewHolder (View view) {
            super (view);
            tvName = (TextView) view.findViewById (R.id.tvName);
            view.setOnClickListener (this);
        }

        @Override
        public void onClick (View v) {
//            Intent intent = new Intent(activity, ServiceRequestActivity.class);
//            activity.startActivity(intent);
//            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);


        }
    }
}