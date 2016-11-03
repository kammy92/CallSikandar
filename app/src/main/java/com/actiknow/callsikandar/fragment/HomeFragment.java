package com.actiknow.callsikandar.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.model.Banner;
import com.actiknow.callsikandar.utils.Constants;
import com.actiknow.callsikandar.utils.SetTypeFace;
import com.actiknow.callsikandar.utils.TypefaceSpan;
import com.actiknow.callsikandar.utils.Utils;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 25-10-2016.
 */
public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    List<Banner> bannerList = new ArrayList<> ();
    private SliderLayout mDemoSlider;


    public HomeFragment () {
        setHasOptionsMenu (true);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate (R.layout.fragment_home, container, false);
        mDemoSlider = (SliderLayout) rootView.findViewById (R.id.slider);
        setHasOptionsMenu (true);
        getBanners ();
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
        menu.clear ();
        inflater.inflate (R.menu.menu_fragment_home, menu);
        SearchManager searchManager = (SearchManager) getActivity ().getSystemService (Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem (R.id.action_search).getActionView ();
        if (null != searchView) {
            searchView.setSearchableInfo (searchManager.getSearchableInfo (getActivity ().getComponentName ()));
        }

        searchView.setQueryHint ("Search Home");
        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener () {
            public boolean onQueryTextChange (String newText) {
//                Utils.showToast (getActivity (), "search text home " + newText);
                return true;
            }

            public boolean onQueryTextSubmit (String query) {
                //Here u can get the value "query" which is entered in the search box.
                return true;
            }
        };
        searchView.setOnQueryTextListener (queryTextListener);

        EditText et = (EditText) searchView.findViewById (R.id.search_src_text);
        et.setHintTextColor (getResources ().getColor (R.color.hint_color_white));
        et.setTypeface (SetTypeFace.getTypeface (getActivity ()));


        super.onCreateOptionsMenu (menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId ()) {
            case R.id.action_search:

//                if (etSearch.isShown ()) {
//                    etSearch.setVisibility (View.GONE);
//                    final Handler handler = new Handler ();
//                    handler.postDelayed (new Runnable () {
//                        @Override
//                        public void run () {
//                            etSearch.setText ("");
//                        }
//                    }, 1000);
//                } else {
//                    etSearch.setVisibility (View.VISIBLE);
//                }
                break;
        }
        Utils.hideSoftKeyboard (getActivity ());

        return super.onOptionsItemSelected (item);
    }


    private void getBanners () {
  /*
        if (NetworkConnection.isNetworkAvailable (getActivity ())) {
            Utils.showLog (Log.INFO, AppConfigTags.URL, AppConfigURL.URL_GETBANNERS, true);
            StringRequest strRequest = new StringRequest (Request.Method.GET, AppConfigURL.URL_GETBANNERS,
                    new Response.Listener<String> () {
                        @Override
                        public void onResponse (String response) {
                            Utils.showLog (Log.INFO, AppConfigTags.SERVER_RESPONSE, response, true);
                            if (response != null) {
                                try {
                                    JSONObject jsonObj = new JSONObject (response);
                                    boolean is_error = jsonObj.getBoolean (AppConfigTags.ERROR);
                                    String message = jsonObj.getString (AppConfigTags.MESSAGE);
                                    JSONArray jsonArrayBanner = jsonObj.getJSONArray (AppConfigTags.BANNERS);
                                    for (int i = 0; i < jsonArrayBanner.length (); i++) {
                                        JSONObject jsonObjectBanner = jsonArrayBanner.getJSONObject (i);
                                        Banner banner = new Banner (
                                                jsonObjectBanner.getInt (AppConfigTags.BANNER_ID),
                                                jsonObjectBanner.getInt (AppConfigTags.PRODUCT_ID),
                                                jsonObjectBanner.getInt (AppConfigTags.CATEGORY_ID),
                                                jsonObjectBanner.getString (AppConfigTags.BANNER_TEXT),
                                                jsonObjectBanner.getString (AppConfigTags.BANNER_IMAGE),
                                                jsonObjectBanner.getString (AppConfigTags.BANNER_TYPE)
                                        );
                                        bannerList.add (i, banner);
                                    }

                                    initSlider ();


                                } catch (JSONException e) {
                                    e.printStackTrace ();
                                }
                            } else {
                                Utils.showLog (Log.WARN, AppConfigTags.SERVER_RESPONSE, AppConfigTags.DIDNT_RECEIVE_ANY_DATA_FROM_SERVER, true);
                            }
                        }
                    },
                    new Response.ErrorListener () {
                        @Override
                        public void onErrorResponse (VolleyError error) {
                            Utils.showLog (Log.ERROR, AppConfigTags.VOLLEY_ERROR, error.toString (), true);
                            NetworkResponse response = error.networkResponse;
                            if (response != null && response.data != null) {
                                Utils.showLog (Log.ERROR, AppConfigTags.ERROR, new String (response.data), true);

//                                try {
//                                    JSONObject jsonObj = new JSONObject (new String (response.data));
//                                    boolean is_error = jsonObj.getBoolean (AppConfigTags.ERROR);
//                                    String message = jsonObj.getString (AppConfigTags.MESSAGE);
//                                    Utils.showLog (Log.ERROR, AppConfigTags.ERROR, "" + is_error, true);
//                                    Utils.showLog (Log.ERROR, AppConfigTags.MESSAGE, message, true);
//                                } catch (JSONException e) {
//                                    e.printStackTrace ();
//                                }

                            }
                        }
                    }) {

                @Override
                protected Map<String, String> getParams () throws AuthFailureError {
                    Map<String, String> params = new Hashtable<String, String> ();
                    Utils.showLog (Log.INFO, AppConfigTags.PARAMETERS_SENT_TO_THE_SERVER, "" + params, true);
                    return params;
                }

                @Override
                public Map<String, String> getHeaders () throws AuthFailureError {
                    Map<String, String> params = new HashMap<> ();
                    params.put ("api_key", Constants.api_key);
                    params.put ("user_login_key", Constants.user_login_key);
                    Utils.showLog (Log.INFO, AppConfigTags.HEADERS_SENT_TO_THE_SERVER, "" + params, false);
                    return params;
                }


            };
            Utils.sendRequest (strRequest, 30);
        } else {
        }
*/


        bannerList.add (new Banner (1, 1, 1, "Get 20% off on body dent paint. TnC apply", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/ads/47/Android/drawable-xhdpi/painting.jpg", "category"));
        bannerList.add (new Banner (2, 1, 1, "Regular service: get 15% off on Labour. TnC apply", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/ads/16/android/xhdpi.png", "category"));
        bannerList.add (new Banner (3, 1, 1, "20% off on interior dry cleaning. TnC apply", "https://s3-ap-southeast-1.amazonaws.com/xenonpublic/ads/three/android/xhdpi/wd_int_det.jpg", "category"));
        initSlider ();


    }


    private void initSlider () {
        for (int i = 0; i < bannerList.size (); i++) {
            Banner banner = bannerList.get (i);

            SpannableString s = new SpannableString (banner.getText ());
            s.setSpan (new TypefaceSpan (getActivity (), Constants.font_name), 0, s.length (), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            TextSliderView textSliderView = new TextSliderView (getActivity ());
            // initialize a SliderLayout
            textSliderView
                    .description (String.valueOf (s))
                    .image (banner.getImage_url ())
                    .setScaleType (BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener (this);
            //add your extra information
            textSliderView.bundle (new Bundle ());
            textSliderView.getBundle ().putString ("extra", String.valueOf (s));
            mDemoSlider.addSlider (textSliderView);
        }

        mDemoSlider.setIndicatorVisibility (PagerIndicator.IndicatorVisibility.Invisible);
        mDemoSlider.setPresetTransformer (SliderLayout.Transformer.Default);
//        mDemoSlider.setPresetIndicator (SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation (new DescriptionAnimation ());
        mDemoSlider.setDuration (6000);
        mDemoSlider.addOnPageChangeListener (this);
    }


    @Override
    public void onSliderClick (BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled (int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected (int position) {

    }

    @Override
    public void onPageScrollStateChanged (int state) {

    }
}
