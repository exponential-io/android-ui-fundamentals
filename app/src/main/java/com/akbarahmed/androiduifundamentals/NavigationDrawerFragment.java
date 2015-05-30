package com.akbarahmed.androiduifundamentals;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class NavigationDrawerFragment extends Fragment {

    // TODO: NEXT 2 VARS ARE FOR FUNCTIONALITY I DON'T WANT
    private static final String PREFERENCES_FILE = "navigation_drawer_preferences";
    private static final String USER_LEARNED_DRAWER = "userlearneddrawer";

//    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;

    // TODO: NEXT 2 VARS ARE FOR FUNCTIONALITY I DON'T WANT
    // Indicates whether the user is aware of the drawer's existence
    private boolean userLearnedDrawer = false;
    // Indicates if the Fragment is being re-created from a prior instance
    private boolean isFromSavedInstanceState = false;

    private ActionBarDrawerToggle drawerToggle;

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    private Callbacks callbacks;

    public static NavigationDrawerFragment newInstance(String param1) {
        NavigationDrawerFragment fragment = new NavigationDrawerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }

        // TODO: BEGIN FUNCTIONALITY I DON'T WANT
        Activity activity = getActivity();

        userLearnedDrawer = Boolean.valueOf(getPreferences(activity, USER_LEARNED_DRAWER, "false"));
        if (savedInstanceState != null) {
            isFromSavedInstanceState = true;
        }
        // TODO: END FUNCTIONALITY I DON'T WANT
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.navigation_drawer, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callbacks = (Callbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement Callbacks");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

//    public void checkContext(Activity context) {
//        Activity activity = getActivity();
//        if (context == activity) {
//            // This is true
//            Log.v("XXX", "ActivityName.this == getActivity()");
//        }
//    }

    // TODO: Move this into newInstance() or into onCreate()
    public void setup(Activity context, int drawerContainer, DrawerLayout layout, Toolbar toolbar) {
//        final Activity activity = getActivity();
        final Activity activity = context;
        drawerLayout = layout;
        int open = R.string.open;
        int close = R.string.close;

        drawerToggle = new ActionBarDrawerToggle(activity, layout, toolbar, open, close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                // TODO: BEGIN FUNCTIONALITY I DON'T WANT
                if (userLearnedDrawer) {
                    userLearnedDrawer = true;
                    setPreferences(activity, USER_LEARNED_DRAWER, Boolean.toString(userLearnedDrawer));
                }
                // TODO: END FUNCTIONALITY I DON'T WANT

                // creates call to onPrepareOptionsMenu()
                activity.invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                // TODO: BEGIN FUNCTIONALITY I DON'T WANT

                // TODO: END FUNCTIONALITY I DON'T WANT

                // creates call to onPrepareOptionsMenu()
                activity.invalidateOptionsMenu();
            }

        };

        // TODO: BEGIN FUNCTIONALITY I DON'T WANT
        if (!userLearnedDrawer && !isFromSavedInstanceState) {
            View container = activity.findViewById(drawerContainer);
            drawerLayout.openDrawer(container);
        }
        // TODO: END FUNCTIONALITY I DON'T WANT

        drawerLayout.setDrawerListener(drawerToggle);

        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });
    }

    // TODO: BEGIN FUNCTIONALITY I DON'T WANT
    public static void setPreferences(Context context, String key, String value) {
        SharedPreferences preferences = context
                .getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();

        // Shorter form of the above without single use variables
        /*
        context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
                .edit()
                .putString(key, value)
                .apply();
        */
    }

    public static String getPreferences(Context context, String key, String defaultValue) {
        SharedPreferences preferences = context
                .getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        return preferences.getString(key, defaultValue);
    }
    // TODO: END FUNCTIONALITY I DON'T WANT

    public interface Callbacks {
        public void onFragmentInteraction(Uri uri);
    }

}
