
package com.actiknow.callsikandar.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;

import com.actiknow.callsikandar.fragment.ServiceRequestDetailsFragment;
import com.actiknow.callsikandar.fragment.ServiceRequestQuotationsFragment;
import com.actiknow.callsikandar.utils.Constants;
import com.actiknow.callsikandar.utils.TypefaceSpan;

/**
 * Created by Admin on 03-11-2016.
 */
public class ServiceRequestDetailPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    int service_provider_id;
    private String tabTitles[] = new String[] {"REQUEST DETAILS", "QUOTATIONS"};
    private Context context;

    public ServiceRequestDetailPagerAdapter (FragmentManager fm, Context context, int service_provider_id) {
        super (fm);
        this.context = context;
        this.service_provider_id = service_provider_id;
    }

    @Override
    public int getCount () {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem (int position) {
        switch (position) {
            case 0:
                return ServiceRequestDetailsFragment.newInstance (service_provider_id);
            case 1:
                return ServiceRequestQuotationsFragment.newInstance (service_provider_id);
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle (int position) {
        // Generate title based on item position
        SpannableString s;
        s = new SpannableString (tabTitles[position]);
        s.setSpan (new TypefaceSpan (context, Constants.font_name), 0, s.length (), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return s;
    }


}