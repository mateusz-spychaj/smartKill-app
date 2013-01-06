package pl.pwr.smartkill.tools;

import pl.pwr.smartkill.activities.MapsActivity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class MyLocationListener implements LocationListener {

	private MapsActivity map;
	
	public MyLocationListener(MapsActivity map){
		super();
		this.map = map;
	}
	
	@Override
	public void onLocationChanged(Location location) {
		if (map != null)
			map.getData();

	}

	@Override
	public void onProviderDisabled(String arg0) {

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}

}
