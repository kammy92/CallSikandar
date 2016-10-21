package com.actiknow.callsikandar.activity;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.utils.Utils;
import com.bumptech.glide.Glide;
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
    private AccountHeader headerResult;
    private Drawer result;

    private Toolbar toolbar;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        this.savedInstanceState = savedInstanceState;
        initData ();
        initView ();
        setUpNavigationDrawer ();
        initDrawer ();

    }

    private void initView () {
        toolbar = (Toolbar) findViewById (R.id.toolbar1);
    }

    private void initData () {
        new DrawerBuilder ().withActivity (this).build ();
        client = new GoogleApiClient.Builder (this).addApi (AppIndex.API).build ();

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
                        new PrimaryDrawerItem ().withName (R.string.drawer_item_appointments).withIcon (FontAwesome.Icon.faw_calendar).withIdentifier (1),
                        new PrimaryDrawerItem ().withName (R.string.drawer_item_history).withIcon (FontAwesome.Icon.faw_history).withIdentifier (2),
                        new PrimaryDrawerItem ().withName (R.string.drawer_item_service_providers).withIcon (FontAwesome.Icon.faw_home).withIdentifier (3),
                        new PrimaryDrawerItem ().withName (R.string.drawer_item_rate_callsikandar).withIcon (FontAwesome.Icon.faw_home).withIdentifier (4),
                        new PrimaryDrawerItem ().withName (R.string.drawer_item_settings).withIcon (FontAwesome.Icon.faw_home).withIdentifier (5),
                        new PrimaryDrawerItem ().withName (R.string.drawer_item_support).withIcon (FontAwesome.Icon.faw_home).withIdentifier (6),
                        new PrimaryDrawerItem ().withName (R.string.drawer_item_log_out).withIcon (FontAwesome.Icon.faw_home).withIdentifier (7)
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
                        switch ((int) drawerItem.getIdentifier ()) {
                            case 5:
                                Utils.showLog (Log.ERROR, "position ", "" + position, true);
                                break;
                        }
                        return false;
                    }
                })
                .build ();
//        result.getActionBarDrawerToggle ().setDrawerIndicatorEnabled (false);


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
        setSupportActionBar (toolbar);
        ActionBar actionBar = getSupportActionBar ();
        try {
            assert actionBar != null;
            actionBar.setDisplayHomeAsUpEnabled (false);
            actionBar.setHomeButtonEnabled (false);
            actionBar.setTitle (getResources ().getString (R.string.app_name));
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
