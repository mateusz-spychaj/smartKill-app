package pl.pwr.smartkill.activities;



import pl.pwr.smartkill.R;
import pl.pwr.smartkill.tools.FixedMyLocationOverlay;
import pl.pwr.smartkill.tools.PlayerLocationOverlay;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.BassBoost.Settings;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.OverlayItem;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.res.StringRes;

public class MapsActivity extends MapActivity implements LocationListener {
	private MapView mapView;
	private MapController mc;
	private MyLocationOverlay myLocationOverlay;
	private boolean firstPositionFlag = true;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_maps);
    	mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);

		// create an overlay that shows our current location
		myLocationOverlay = new FixedMyLocationOverlay(this, mapView);
		// add this overlay to the MapView and refresh it
		mapView.getOverlays().add(myLocationOverlay);
		mapView.postInvalidate();
		mc = mapView.getController();
		mc.setZoom(15);
		// call convenience method that zooms map on our location
		//zoomToMyLocation();
		LocationManager locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 500.0f, this);
	    myLocationOverlay.runOnFirstFix(new Runnable() {
            public void run() {
                mc.setZoom(20);
                mc.animateTo(myLocationOverlay.getMyLocation());
            }
        });
    }
 
    @Override
	protected void onResume() {
		super.onResume();
		// when our activity resumes, we want to register for location updates
		myLocationOverlay.enableMyLocation();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// when our activity pauses, we want to remove listening for location updates
		myLocationOverlay.disableMyLocation();
	}
	
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

	@Override
	public void onLocationChanged(Location location) {
		Log.v("location", location.toString());
		GeoPoint p = null;
	    if(location!=null){
	        double lat=location.getLatitude();
	        double lng=location.getLongitude();
	        p= new GeoPoint((int)(lat * 1E6), (int)(lng * 1E6));
	        if (firstPositionFlag)
	        	Toast.makeText(this, R.string.prompt, Toast.LENGTH_SHORT).show();
	        firstPositionFlag = false;
            mc.animateTo(p);

	    }
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
}
