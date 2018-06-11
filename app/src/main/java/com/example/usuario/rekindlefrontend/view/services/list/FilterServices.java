package com.example.usuario.rekindlefrontend.view.services.list;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.utils.SetDate;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;
import java.util.List;

public class FilterServices extends AppCompatActivity {

    RatingBar mRatingBar;
    EditText mStartDate, mEndDate, mDistance;
    RadioButton mGPSLocation, mCustomLocation, mMeters, mKilometers;
    AppCompatButton mReset, mSend;

    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtering);
        this.setFinishOnTouchOutside(false);

        mStartDate = findViewById(R.id.start_date);
        mEndDate = findViewById(R.id.end_date);

        mRatingBar = findViewById(R.id.rating_bar);

        mGPSLocation = findViewById(R.id.gps_location);
        mCustomLocation = findViewById(R.id.custom_location);

        mDistance = findViewById(R.id.distance);
        mMeters = findViewById(R.id.meters);
        mKilometers = findViewById(R.id.kilometers);

        mReset = findViewById(R.id.reset);
        mSend = findViewById(R.id.send);

        SetDate fromDate = new SetDate(mStartDate, getApplicationContext());
        SetDate toDate = new SetDate(mEndDate, getApplicationContext());

        mGPSLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(FilterServices.this.getApplicationContext(), Manifest.permission
                        .ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), getString(R.string.no_permission), Toast
                            .LENGTH_LONG).show();
                } else {
                    try {
                        LocationManager locationManager = (LocationManager) getSystemService(Context
                                .LOCATION_SERVICE);

                        Location myLocation = locationManager.getLastKnownLocation(
                                LocationManager.GPS_PROVIDER);

                        Geocoder geo = new Geocoder(getApplicationContext());
                        if(myLocation != null) {
                            List<Address> addresses = geo.getFromLocation(myLocation.getLatitude(),
                                    myLocation
                                            .getLongitude(), 1);
                            if (addresses.size() > 0) {
                                mGPSLocation.setText(addresses.get(0).getFeatureName());
                            }
                        }else{
                            Toast.makeText(FilterServices.this.getApplicationContext(),
                                    getResources().getString(R.string
                                    .error), Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), getString(R.string.noLocation), Toast
                                .LENGTH_LONG).show();
                    }
                }
            }
        });

        mCustomLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete
                            .MODE_OVERLAY).build((Activity) getApplicationContext());
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRatingBar.setNumStars(0);
                mStartDate.setText(null);
                mEndDate.setText(null);
                mGPSLocation.setChecked(true);
                mKilometers.setChecked(true);
            }
        });

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), ListServices.class);
                i.putExtra("startDate", mStartDate.getText().toString());
                i.putExtra("endDate", mEndDate.getText().toString());
                i.putExtra("minimumRating", (double) mRatingBar.getRating());
                if(mGPSLocation.isChecked()){
                    i.putExtra("location",mGPSLocation.getText().toString());
                }else{
                    i.putExtra("location",mCustomLocation.getText().toString());
                }
                if(mMeters.isChecked()){
                    i.putExtra("distance",mDistance.getText().toString());
                }else{
                    i.putExtra("distance",Double.toString(Double.parseDouble(mDistance.getText
                            ().toString()) *1000));
                }
                startActivity(i);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(getApplicationContext(), data);
                mCustomLocation.setText(place.getAddress());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getApplicationContext(), data);
                Log.i("==================", status.getStatusMessage());
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
