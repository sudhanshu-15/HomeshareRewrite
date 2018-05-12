package edu.indiana.soic.homeshare.homeshare.data.model;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

public class LocationLiveData extends LiveData<Location> implements LocationListener{

    private Context context;
    private LocationManager locationManager;
    public static final String TAG="LocationLiveData";

    @Inject
    public LocationLiveData(Application context) {
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    @SuppressLint("MissingPermission")
    public void refreshLocation() {
        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, this, null);
        locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
    }

    @Override
    @SuppressLint("MissingPermission")
    protected void onInactive() {
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        setValue(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.d(TAG, "onStatusChanged: " + s + i);
    }

    @Override
    public void onProviderEnabled(String s) {
        Log.d(TAG, "onProviderEnabled: " + s);
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.d(TAG, "onProviderDisabled: " + s);
    }
}
