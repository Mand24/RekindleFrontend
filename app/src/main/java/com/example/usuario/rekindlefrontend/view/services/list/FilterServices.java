package com.example.usuario.rekindlefrontend.view.services.list;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.utils.SetDate;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FilterServices extends AppCompatActivity {

    private RatingBar mRatingBar;
    private EditText mStartDate, mEndDate, mDistance;
    private RadioButton mGPSLocation, mCustomLocation, mMeters, mKilometers;
    private AppCompatButton mReset, mSend, mResetOrderingList;
    private TextView mSortDistance, mSortRating, mSortDate, mOrderTextView;

    private List<String> listToSend = new ArrayList<>();
    private String stringToSend = "distance, rating, date";
    private List<String> mOrderList = new ArrayList<>();

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

        SetDate fromDate = new SetDate(mStartDate, FilterServices.this);
        SetDate toDate = new SetDate(mEndDate, FilterServices.this);
        mRatingBar.setRating(0.0f);

        mGPSLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(FilterServices.this, Manifest.permission
                        .ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(FilterServices.this, getString(R.string.no_permission), Toast
                            .LENGTH_LONG).show();
                } else {
                    try {
                        LocationManager locationManager = (LocationManager) getSystemService(Context
                                .LOCATION_SERVICE);

                        Location myLocation = locationManager.getLastKnownLocation(
                                LocationManager.GPS_PROVIDER);

                        Geocoder geo = new Geocoder(FilterServices.this);
                        if (myLocation != null) {
                            List<Address> addresses = geo.getFromLocation(myLocation.getLatitude(),
                                    myLocation
                                            .getLongitude(), 1);
                            if (addresses.size() > 0) {
                                mGPSLocation.setText(addresses.get(0).getFeatureName());
                            }
                        } else {
                            Toast.makeText(FilterServices.this,
                                    getResources().getString(R.string
                                            .error), Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(FilterServices.this, getString(R.string.noLocation), Toast
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
                            .MODE_OVERLAY).build(FilterServices.this);
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        mOrderTextView = findViewById(R.id.orderTextView);
        mOrderTextView.setText(
                String.format("%s%s%s", getString(R.string.distance), getString(R.string.rating),
                        getString(R.string.date)));

        mSortDistance = findViewById(R.id.sort_distance);
        mSortDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mOrderList.contains(mSortDistance.getText().toString())){
                    mOrderList.add(mSortDistance.getText().toString());
                    stringToSend = "";
                    listToSend.add("distance");
                    stringToSend = TextUtils.join(", ", listToSend);
                    String listString = TextUtils.join(", ", mOrderList);
                    mOrderTextView.setText(listString);
                }
            }
        });

        mSortRating = findViewById(R.id.sort_rating);
        mSortRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mOrderList.contains(mSortRating.getText().toString())){
                    mOrderList.add(mSortRating.getText().toString());
                    stringToSend = "";
                    listToSend.add("rating");
                    stringToSend = TextUtils.join(", ", listToSend);
                    String listString = TextUtils.join(", ", mOrderList);
                    mOrderTextView.setText(listString);
                }
            }
        });

        mSortDate = findViewById(R.id.sort_date);
        mSortDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mOrderList.contains(mSortDate.getText().toString())){
                    mOrderList.add(mSortDate.getText().toString());
                    stringToSend = "";
                    listToSend.add("date");
                    stringToSend = TextUtils.join(", ", listToSend);
                    String listString = TextUtils.join(", ", mOrderList);
                    mOrderTextView.setText(listString);
                }
            }
        });

        mResetOrderingList = findViewById(R.id.reset_order_list);
        mResetOrderingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOrderList = new ArrayList<>();
                listToSend = mOrderList;
                stringToSend = "distance, rating, date";
                stringToSend = TextUtils.join(", ", listToSend);
                String listString = TextUtils.join(", ", mOrderList);
                mOrderTextView.setText(listString);
            }
        });

        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        FilterServices.this);

                builder.setMessage(R.string
                        .reset_confirmation);
                builder.setCancelable(false);
                builder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,
                                    int which) {
                                resetOptions();

                            }
                        });

                builder.setNegativeButton(R.string.no,
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,
                                    int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(FilterServices.this, ListServices.class);

                if (mStartDate.getText().toString().isEmpty()) {
                    i.putExtra("startDate", "1900-01-01");
                } else {
                    i.putExtra("startDate", mStartDate.getText().toString());
                }

                if (mEndDate.getText().toString().isEmpty()) {
                    i.putExtra("endDate", "2100-01-01");
                } else {
                    i.putExtra("endDate", mEndDate.getText().toString());
                }

                i.putExtra("minimumRating", (double) mRatingBar.getRating());

                if (mGPSLocation.isChecked()) {
                    i.putExtra("location", mGPSLocation.getText().toString());
                } else {
                    i.putExtra("location", mCustomLocation.getText().toString());
                }

                if (mDistance.getText().toString().length() > 0) {
                    if (android.text.TextUtils.isDigitsOnly(mDistance.getText().toString())) {
                        double distanceValue = Double.parseDouble(mDistance.getText().toString());
                        if (distanceValue > (Double.MAX_VALUE / 1000)) {
                            mDistance.setError(getText(R.string.value_too_high));
                        }
                        if (mMeters.isChecked()) {
                            i.putExtra("distance", distanceValue);
                        } else if (mKilometers.isChecked()) {
                            i.putExtra("distance", distanceValue * 1000);
                        }
                    } else {
                        mDistance.setError(getText(R.string.distancia_numeros));
                    }
                } else {
                    i.putExtra("distance", Double.MAX_VALUE);
                }

                i.putExtra("order", TextUtils.join(", ", listToSend));

                startActivity(i);
            }
        });

    }

    private void resetOptions(){
        mRatingBar.setRating(0.0f);
        mStartDate.setText(null);
        mEndDate.setText(null);
        mGPSLocation.setChecked(false);
        mCustomLocation.setChecked(false);
        mKilometers.setChecked(false);
        mMeters.setChecked(false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(FilterServices.this, data);
                mCustomLocation.setText(place.getAddress());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(FilterServices.this, data);
                Log.i("==================", status.getStatusMessage());
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
