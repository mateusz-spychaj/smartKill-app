package pl.pwr.smartkill.activities;


import android.R;
import android.os.Bundle;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.googlecode.androidannotations.annotations.EActivity;

public class MapsActivity extends MapActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout_activity_maps);
    }
 
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
	
}
