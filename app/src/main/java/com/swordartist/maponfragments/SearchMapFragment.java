package com.swordartist.maponfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchMapFragment extends Fragment implements OnMapReadyCallback, PlaceSelectionListener {

    private View rootView;
    private MapView mapView;
    private SupportPlaceAutocompleteFragment autocompleteFragment;

    public SearchMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SearchMapFragment.
     */
    public static SearchMapFragment newInstance() {
        SearchMapFragment fragment = new SearchMapFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_search_map, container, false);
        mapView = (MapView) rootView.findViewById(R.id.search_map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        // Initialize the map
        MapsInitializer.initialize(this.getActivity());

        // 
        autocompleteFragment = new SupportPlaceAutocompleteFragment();
        autocompleteFragment.setOnPlaceSelectedListener(this);

        updateAutoCompleteFragmentPlaceHolder(autocompleteFragment);

        return rootView;
    }

    private void updateAutoCompleteFragmentPlaceHolder(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.autocomplete_fragment_place_holder, fragment).commit();
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        getActivity().getSupportFragmentManager().beginTransaction().remove(autocompleteFragment).commit();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void onPlaceSelected(Place place) {
        
    }

    @Override
    public void onError(Status status) {

    }
}
