package pl.pwr.smartkill;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import pl.pwr.smartkill.activities.MapsActivity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.googlecode.androidannotations.annotations.EService;
@EService 
public class LocationService extends Service{
	
	private static final String TAG = "Owller Location";
	private LocationManager mLocationManager = null;
	private MapsActivity map;
	private static final int LOCATION_INTERVAL = 1000;
	private static final float LOCATION_DISTANCE = 10f;

	public static final int MODE_PRECISE=0;
	public static final int MODE_OPTIMAL=1;
	public static final int MODE_ECONOMICAL=2;
	public static final int MODE_CUSTOM=3;
	public static final int MODE_NONE=4;
	
	private class LocationListener implements android.location.LocationListener{
		Location mLastLocation;
		public LocationListener(String provider)
		{
			Log.e(TAG, "LocationListener " + provider);
			mLastLocation = new Location(provider);
		}
		@Override
		public void onLocationChanged(Location location)
		{
			Log.e(TAG, "onLocationChanged: " + location);
	       (new ReverseGeocodingTask(LocationService.this)).execute(new Location[] {location});
			mLastLocation.set(location);
			map.getData();
			
		}
		@Override
		public void onProviderDisabled(String provider)
		{
			Log.e(TAG, "onProviderDisabled: " + provider);            
		}
		@Override
		public void onProviderEnabled(String provider)
		{
			Log.e(TAG, "onProviderEnabled: " + provider);
		}
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras)
		{
			Log.e(TAG, "onStatusChanged: " + provider);
		}
	} 
	LocationListener[] mLocationListeners = new LocationListener[] {
			new LocationListener(LocationManager.GPS_PROVIDER),
			new LocationListener(LocationManager.NETWORK_PROVIDER),
			new LocationListener(LocationManager.PASSIVE_PROVIDER)
	};
	@Override
	public IBinder onBind(Intent arg0)
	{
		return null;
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		Log.e(TAG, "onStartCommand");
		super.onStartCommand(intent, flags, startId);       
		return START_STICKY;
	}
	@Override
	public void onCreate()
	{
		Log.e(TAG, "onCreate");
		initializeLocationManager();
		try {
			mLocationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
					mLocationListeners[1]);
		} catch (java.lang.SecurityException ex) {
			Log.i(TAG, "fail to request location update, ignore", ex);
		} catch (IllegalArgumentException ex) {
			Log.d(TAG, "network provider does not exist, " + ex.getMessage());
		}
		try {
			mLocationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
					mLocationListeners[0]);
		} catch (java.lang.SecurityException ex) {
			Log.i(TAG, "fail to request location update, ignore", ex);
		} catch (IllegalArgumentException ex) {
			Log.d(TAG, "gps provider does not exist " + ex.getMessage());
		}
		try {
			mLocationManager.requestLocationUpdates(
					LocationManager.PASSIVE_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
					mLocationListeners[2]);
		} catch (java.lang.SecurityException ex) {
			Log.i(TAG, "fail to request location update, ignore", ex);
		} catch (IllegalArgumentException ex) {
			Log.d(TAG, "gps provider does not exist " + ex.getMessage());
		}
	}
	@Override
	public void onDestroy()
	{
		Log.e(TAG, "onDestroy");
		super.onDestroy();
		if (mLocationManager != null) {
			for (int i = 0; i < mLocationListeners.length; i++) {
				try {
					mLocationManager.removeUpdates(mLocationListeners[i]);
				} catch (Exception ex) {
					Log.i(TAG, "fail to remove location listners, ignore", ex);
				}
			}
		}
	} 
	private void initializeLocationManager() {
		Log.e(TAG, "initializeLocationManager");
		if (mLocationManager == null) {
			mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
		}
	}
	
	private class ReverseGeocodingTask extends AsyncTask<Location, Void, Void> {
	    Context mContext;

	    public ReverseGeocodingTask(Context context) {
	        super();
	        mContext = context;
	    }

	    @Override
	    protected Void doInBackground(Location... params) {
	        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());

	        Location loc = params[0];
	        List<Address> addresses = null;
	        try {
	            addresses = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
	        } catch (IOException e) {
	        }
	        if (addresses != null && addresses.size() > 0) {
//	            Address address = addresses.get(0);
	            //TODO dane podaje z geocodera
	        }
	        return null;
	    }
	}
}