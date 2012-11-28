package pl.pwr.smartkill.activities;



import java.util.List;

import pl.pwr.smartkill.R;
import pl.pwr.smartkill.tools.FixedMyLocationOverlay;
import pl.pwr.smartkill.tools.PlayersItemizedOverlay;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

public class MapsActivity extends MapActivity {
	private MapView mapView;
	private MapController mc;
	private MyLocationOverlay myLocationOverlay;
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
		myLocationOverlay.enableMyLocation();
		// add this overlay to the MapView and refresh it
		mapView.getOverlays().add(myLocationOverlay);
		mapView.postInvalidate();
		mc = mapView.getController();
		mc.setZoom(15);
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.victim);
		GeoPoint point = new GeoPoint(51113869,17026062);
		PlayersItemizedOverlay itemizedoverlay = new PlayersItemizedOverlay(drawable, this, point);
		mapOverlays.add(itemizedoverlay);
		// call convenience method that zooms map on our location
		//zoomToMyLocation();
	    myLocationOverlay.runOnFirstFix(new Runnable() {
            public void run() {
                mc.setZoom(20);
                mc.animateTo(myLocationOverlay.getMyLocation());
            }
        });
    }
 
    @Override
	protected void onPause(){
		try {
			myLocationOverlay.disableMyLocation();
		} 
		catch (Exception e) {
		}
	    super.onPause();
	}
	
	@Override
	protected void onResume() {
		try {
			myLocationOverlay.enableMyLocation();
		} 
		catch (Exception e) {
		}
		super.onResume();
	}
	
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
