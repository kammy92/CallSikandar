package com.actiknow.callsikandar.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.fragment.AppointmentFragment;
import com.actiknow.callsikandar.fragment.HomeFragment;
import com.actiknow.callsikandar.fragment.ManageVehicleFragment;
import com.actiknow.callsikandar.fragment.ServiceProviderFragment;
import com.actiknow.callsikandar.fragment.ServiceRequestFragment;
import com.actiknow.callsikandar.utils.Constants;
import com.actiknow.callsikandar.utils.LoginDetailsPref;
import com.actiknow.callsikandar.utils.SetTypeFace;
import com.actiknow.callsikandar.utils.TypefaceSpan;
import com.actiknow.callsikandar.utils.Utils;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;

public class MainActivity extends AppCompatActivity {

    GoogleApiClient client;

    Bundle savedInstanceState;
    Fragment fragment = null;
    FloatingActionMenu fabHomeMenu;
    FloatingActionButton fabRequestEstimate;
    FloatingActionButton fabRegularService;
    FloatingActionButton fabRepairs;
    FloatingActionButton fabDentsAndPaint;
    FloatingActionButton fabCarCleaning;
    FloatingActionButton fabAccessories;
    FloatingActionButton fabBreakdown;
    FloatingActionButton fabAddVehicle;
    private AccountHeader headerResult;
    private Drawer result;
    private Toolbar toolbar;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        this.savedInstanceState = savedInstanceState;
        initPref ();
        initData ();
        initView ();
        isLogin ();
        initListener ();
        setUpNavigationDrawer ();
        initDrawer ();

    }

    private void initView () {
        toolbar = (Toolbar) findViewById (R.id.toolbar1);
        fabHomeMenu = (FloatingActionMenu) findViewById (R.id.fabHomeMenu);
        fabRequestEstimate = (FloatingActionButton) findViewById (R.id.fabRequestEstimate);
        fabRegularService = (FloatingActionButton) findViewById (R.id.fabRegularService);
        fabRepairs = (FloatingActionButton) findViewById (R.id.fabRepairs);
        fabDentsAndPaint = (FloatingActionButton) findViewById (R.id.fabDentsAndPaint);
        fabCarCleaning = (FloatingActionButton) findViewById (R.id.fabCarCleaning);
        fabAccessories = (FloatingActionButton) findViewById (R.id.fabAccessories);
        fabBreakdown = (FloatingActionButton) findViewById (R.id.fabBreakdown);

        fabAddVehicle = (FloatingActionButton) findViewById (R.id.fabAddVehicle);
    }

    private void initData () {
        new DrawerBuilder ().withActivity (this).build ();
        client = new GoogleApiClient.Builder (this).addApi (AppIndex.API).build ();

        fragment = new HomeFragment ();
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager ();
            fragmentManager.beginTransaction ().replace (R.id.main_content, fragment).commit ();
        }
    }

    private void initPref () {
        LoginDetailsPref loginDetailsPref = LoginDetailsPref.getInstance ();
        Constants.user_name = loginDetailsPref.getStringPref (MainActivity.this, LoginDetailsPref.USER_NAME);
        Constants.user_email = loginDetailsPref.getStringPref (MainActivity.this, LoginDetailsPref.USER_EMAIL);
        Constants.user_mobile = loginDetailsPref.getStringPref (MainActivity.this, LoginDetailsPref.USER_MOBILE);
        Constants.user_id_main = loginDetailsPref.getIntPref (MainActivity.this, LoginDetailsPref.USER_ID);
    }

    private void isLogin () {
        if (Constants.user_name.equalsIgnoreCase ("") || Constants.user_email.equalsIgnoreCase ("") || Constants.user_mobile.equalsIgnoreCase ("") || Constants.user_id_main == 0) {
            Intent myIntent = new Intent (this, HomeActivity.class);
            startActivity (myIntent);
        }
        if (Constants.user_id_main == 0)
            finish ();
    }

    private void initDrawer () {
        DrawerImageLoader.init (new AbstractDrawerImageLoader () {
            @Override
            public void set (ImageView imageView, Uri uri, Drawable placeholder) {
                Glide.with (imageView.getContext ()).load (uri).placeholder (placeholder).into (imageView);
            }

            @Override
            public void cancel (ImageView imageView) {
                Glide.clear (imageView);
            }

            @Override
            public Drawable placeholder (Context ctx, String tag) {
                //define different placeholders for different imageView targets
                //default tags are accessible via the DrawerImageLoader.Tags
                //custom ones can be checked via string. see the CustomUrlBasePrimaryDrawerItem LINE 111
                if (DrawerImageLoader.Tags.PROFILE.name ().equals (tag)) {
                    return DrawerUIUtils.getPlaceHolder (ctx);
                } else if (DrawerImageLoader.Tags.ACCOUNT_HEADER.name ().equals (tag)) {
                    return new IconicsDrawable (ctx).iconText (" ").backgroundColorRes (com.mikepenz.materialdrawer.R.color.primary).sizeDp (56);
                } else if ("customUrlItem".equals (tag)) {
                    return new IconicsDrawable (ctx).iconText (" ").backgroundColorRes (R.color.md_white_1000).sizeDp (56);
                }

                //we use the default one for
                //DrawerImageLoader.Tags.PROFILE_DRAWER_ITEM.name()

                return super.placeholder (ctx, tag);
            }
        });
        headerResult = new AccountHeaderBuilder ()
                .withActivity (this)
                .withCompactStyle (true)
                .addProfiles (new ProfileDrawerItem ()
                        .withName ("Karman Singh"))
//                        .withEmail ("Volkswagen Polo"))
//                        .withIcon ("http://i.istockimg.com/file_thumbview_approve/64330137/3/stock-photo-64330137-a-icon-of-a-businessman-avatar-or-profile-pic.jpg"))
//                        .withIcon (R.drawable.ic_blank_profile_image))
                .withProfileImagesVisible (false)
                .withProfileImagesClickable (false)
                .withPaddingBelowHeader (false)
                .withSelectionListEnabledForSingleProfile (false)
                .withHeaderBackground (R.drawable.nav_header_bg)
                .withSavedInstance (savedInstanceState)
                .build ();

        result = new DrawerBuilder ()
                .withActivity (this)
                .withAccountHeader (headerResult)
                .withToolbar (toolbar)
//                .withItemAnimator (new AlphaCrossFadeAnimator ())
                .addDrawerItems (
                        new PrimaryDrawerItem ().withName (R.string.drawer_item_home).withIcon (FontAwesome.Icon.faw_home).withIdentifier (1).withSetSelected (true),
                        new PrimaryDrawerItem ().withName (R.string.drawer_item_appointments).withIcon (FontAwesome.Icon.faw_calendar).withIdentifier (2).withSetSelected (true),
                        new PrimaryDrawerItem ().withName (R.string.drawer_item_service_requests).withIcon (FontAwesome.Icon.faw_file_text_o).withIdentifier (3).withSetSelected (false),
                        new PrimaryDrawerItem ().withName (R.string.drawer_item_service_providers).withIcon (FontAwesome.Icon.faw_map_marker).withIdentifier (4).withSetSelected (false),
                        new PrimaryDrawerItem ().withName (R.string.drawer_item_rate_callsikandar).withIcon (FontAwesome.Icon.faw_star).withIdentifier (5).withSelectable (false).withSetSelected (false),
                        new PrimaryDrawerItem ().withName (R.string.drawer_item_manage_vehicles).withIcon (FontAwesome.Icon.faw_car).withIdentifier (6).withSetSelected (false),
                        new PrimaryDrawerItem ().withName (R.string.drawer_item_settings).withIcon (FontAwesome.Icon.faw_cog).withIdentifier (7).withSetSelected (false).withSelectable (false),
                        new PrimaryDrawerItem ().withName (R.string.drawer_item_support).withIcon (FontAwesome.Icon.faw_question_circle).withIdentifier (8).withSelectable (false).withSetSelected (false),
                        new PrimaryDrawerItem ().withName (R.string.drawer_item_log_out).withIcon (FontAwesome.Icon.faw_sign_out).withIdentifier (9).withSelectable (false).withSetSelected (false)
//                        new DividerDrawerItem (),
//                        new SecondaryDrawerItem ().withName (R.string.drawer_item_settings).withEnabled (false).withSelectable (false).withIdentifier (2),
//                        new SecondaryDrawerItem ().withName (R.string.drawer_item_help_and_feedback).withEnabled (false).withSelectable (false).withIdentifier (3),
//                        new SecondaryDrawerItem ().withName (R.string.drawer_item_about_ctaudit).withEnabled (false).withSelectable (false).withIdentifier (4),
//                        new SecondaryDrawerItem ().withName (R.string.drawer_item_logout).withSelectable (false).withIdentifier (5)
                )
                .withSavedInstance (savedInstanceState)
                .withOnDrawerItemClickListener (new Drawer.OnDrawerItemClickListener () {
                    @Override
                    public boolean onItemClick (View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D

                        if ((int) drawerItem.getIdentifier () != 1) {
                            fabHomeMenu.setVisibility (View.GONE);
                        } else {
                            fabHomeMenu.setVisibility (View.VISIBLE);
                        }

                        if ((int) drawerItem.getIdentifier () != 6) {
                            fabAddVehicle.setVisibility (View.GONE);
                        } else {
                            fabAddVehicle.setVisibility (View.VISIBLE);
                        }


                        switch ((int) drawerItem.getIdentifier ()) {
                            case 1:
                                fragment = new HomeFragment ();
                                break;
                            case 2:
                                fragment = new AppointmentFragment ();
                                break;
                            case 3:
                                fragment = new ServiceRequestFragment ();
                                break;
                            case 4:
                                fragment = new ServiceProviderFragment ();
                                break;
                            case 5:
//                                final String appPackageName = getPackageName (); // getPackageName() from Context or Activity object
                                final String appPackageName = "actiknow.callsikandar";
                                try {
                                    startActivity (new Intent (Intent.ACTION_VIEW, Uri.parse ("market://details?id=" + appPackageName)));
                                } catch (android.content.ActivityNotFoundException anfe) {
                                    startActivity (new Intent (Intent.ACTION_VIEW, Uri.parse ("https://play.google.com/store/apps/details?id=" + appPackageName)));
                                }

                                break;
                            case 6:
                                fragment = new ManageVehicleFragment ();
                                break;
                            case 7:
                                final Handler handler = new Handler ();
                                handler.postDelayed (new Runnable () {
                                    @Override
                                    public void run () {
                                        Intent intent = new Intent (MainActivity.this, SettingActivity.class);
                                        startActivity (intent);
                                        overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
                                    }
                                }, 150);
                                break;
                            case 8:
                                break;
                            case 9:
                                showLogOutDialog ();
                                break;
                        }

                        if (fragment != null) {
                            // update the main content by replacing fragments
                            FragmentManager fragmentManager = getSupportFragmentManager ();
                            fragmentManager.beginTransaction ()
                                    .replace (R.id.main_content, fragment)
                                    .commit ();
                        }


//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//                            if (! settingsCheck && getFragmentManager ().findFragmentByTag ("preference") != null) {
//                                getFragmentManager ().beginTransaction ().remove (getFragmentManager ().findFragmentByTag ("preference")).commit ();
//                            }
//                        }


                        return false;
                    }
                })
                .build ();
//        result.getActionBarDrawerToggle ().setDrawerIndicatorEnabled (false);


    }


    private void initListener () {
        fabAddVehicle.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent (MainActivity.this, AddVehicleActivity.class);
                intent.putExtra ("update_vehicle", false);
                startActivity (intent);
                overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }


    private void showLogOutDialog () {
        TextView tvMessage;
        MaterialDialog dialog = new MaterialDialog.Builder (this)
                .iconRes (R.mipmap.ic_launcher)
                .limitIconToDefaultSize ()
                .title (R.string.dialog_logout_title)
                .content (R.string.dialog_logout_content)
                .positiveText (R.string.dialog_logout_positive)
                .negativeText (R.string.dialog_logout_negative)
                .typeface (SetTypeFace.getTypeface (MainActivity.this), SetTypeFace.getTypeface (MainActivity.this))
                .onPositive (new MaterialDialog.SingleButtonCallback () {
                    @Override
                    public void onClick (@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss ();
                        LoginDetailsPref loginDetailsPref = LoginDetailsPref.getInstance ();
                        loginDetailsPref.putIntPref (MainActivity.this, LoginDetailsPref.USER_ID, 0);
                        loginDetailsPref.putStringPref (MainActivity.this, LoginDetailsPref.USER_NAME, "");
                        loginDetailsPref.putStringPref (MainActivity.this, LoginDetailsPref.USER_EMAIL, "");
                        loginDetailsPref.putStringPref (MainActivity.this, LoginDetailsPref.USER_MOBILE, "");
                        Intent intent = new Intent (MainActivity.this, HomeActivity.class);
                        Constants.user_email = "";
                        Constants.user_name = "";
                        Constants.user_mobile = "";
                        Constants.user_id_main = 0;
                        intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity (intent);
                        overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
                    }
                }).build ();
        dialog.show ();

        /*
        AlertDialog.Builder alert = new AlertDialog.Builder (MainActivity.this);
        alert.setMessage ("Are you sure you want to LOGOUT");
        alert.setPositiveButton ("YES", new DialogInterface.OnClickListener () {
            @Override
            public void onClick (DialogInterface dialog, int which) {
                dialog.dismiss ();
                LoginDetailsPref loginDetailsPref = LoginDetailsPref.getInstance ();
                loginDetailsPref.putIntPref (MainActivity.this, LoginDetailsPref.AUDITOR_ID, 0);
                loginDetailsPref.putStringPref (MainActivity.this, LoginDetailsPref.AUDITOR_NAME, "");
                loginDetailsPref.putStringPref (MainActivity.this, LoginDetailsPref.USERNAME, "");
                loginDetailsPref.putIntPref (MainActivity.this, LoginDetailsPref.AUDITOR_AGENCY_ID, 0);
                Intent intent = new Intent (MainActivity.this, LoginActivity.class);
                Constants.username = "";
                Constants.auditor_name = "";
                Constants.auditor_id_main = 0;
                intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity (intent);
                overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        alert.setNegativeButton ("NO", new DialogInterface.OnClickListener () {
            @Override
            public void onClick (DialogInterface dialog, int which) {
                dialog.dismiss ();
            }
        });
        alert.show ();
        */
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState (outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState (outState);
        super.onSaveInstanceState (outState);
    }

    @Override
    public void onStart () {
        super.onStart ();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect ();
        Action viewAction = Action.newAction (
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse ("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse ("android-app://com.actiknow.callsikandar/http/host/path")
        );
        AppIndex.AppIndexApi.start (client, viewAction);
    }

    @Override
    public void onStop () {
        super.onStop ();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction (
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse ("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse ("android-app://com.actiknow.callsikandar/http/host/path")
        );
        AppIndex.AppIndexApi.end (client, viewAction);
        client.disconnect ();
    }

    private void setUpNavigationDrawer () {
        toolbar = (Toolbar) findViewById (R.id.toolbar1);
        toolbar.showOverflowMenu ();
        SpannableString s = new SpannableString (getResources ().getString (R.string.app_name));
        s.setSpan (new TypefaceSpan (MainActivity.this, Constants.font_name), 0, s.length (), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setSupportActionBar (toolbar);
        ActionBar actionBar = getSupportActionBar ();
        try {
            assert actionBar != null;
            actionBar.setDisplayHomeAsUpEnabled (false);
            actionBar.setHomeButtonEnabled (false);
            actionBar.setTitle (s);
            actionBar.setDisplayShowTitleEnabled (false);
        } catch (Exception ignored) {
        }
    }

    @Override
    public void onBackPressed () {
        if (result != null && result.isDrawerOpen ()) {
            result.closeDrawer ();
        } else {
            finish ();
            overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater ().inflate (R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService (Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem (R.id.action_search).getActionView ();
        if (null != searchView) {
            searchView.setSearchableInfo (searchManager.getSearchableInfo (getComponentName ()));
//            searchView.setIconifiedByDefault (false);
        }

//        final int searchBarId = searchView.getContext ().getResources ().getIdentifier ("android:id/search_bar", null, null);
//        LinearLayout searchBar = (LinearLayout) searchView.findViewById (searchBarId);

        EditText et = (EditText) searchView.findViewById (R.id.search_src_text);
//        et.getBackground ().setColorFilter (R.color.text_color_grey_dark,null);
//        et.setBackgroundColor (getResources ().getColor (R.color.text_color_grey_light)); // ← If you just want a color
//        et.setBackground (getResources ().getDrawable (R.drawable.layout_search_edittext));

//        LinearLayout searchBar = (LinearLayout) searchView.findViewById (R.id.search_bar);
//        searchBar.setLayoutTransition (new LayoutTransition ());

        searchView.setQueryHint ("Search");
        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener () {
            public boolean onQueryTextChange (String newText) {
//                etSearch.setText (newText);
                return true;
            }

            public boolean onQueryTextSubmit (String query) {
                //Here u can get the value "query" which is entered in the search box.
                return true;
            }
        };
        searchView.setOnQueryTextListener (queryTextListener);
        return super.onCreateOptionsMenu (menu);
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
        Utils.hideSoftKeyboard (MainActivity.this);
/**
 if (item != null && item.getItemId () == android.R.id.home) {
 if (mDrawerLayout.isDrawerOpen (mDrawerPanel)) {
 } else {
 mDrawerLayout.openDrawer (mDrawerPanel);
 }
 return true;
 }
 */
        return super.onOptionsItemSelected (item);
    }


}
