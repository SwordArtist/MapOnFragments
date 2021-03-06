package com.swordartist.maponfragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_simple_map:
                    updateFragmentPlaceHolder(SimpleMapFragment.newInstance());
                    return true;
                case R.id.navigation_search_map:
                    updateFragmentPlaceHolder(SearchMapFragment.newInstance());
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Set initial fragment on the fragment place holder
        updateFragmentPlaceHolder(SimpleMapFragment.newInstance());
    }


    private void updateFragmentPlaceHolder(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (SimpleMapFragment.class == fragment.getClass()) {
            fragmentTransaction.replace(R.id.fragment_place_holder, fragment).commit();
        } else if (SearchMapFragment.class == fragment.getClass()) {
            fragmentTransaction.replace(R.id.fragment_place_holder, fragment).commit();
        }
    }
}
