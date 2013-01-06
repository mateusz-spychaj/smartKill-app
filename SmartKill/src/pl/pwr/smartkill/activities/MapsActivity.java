package pl.pwr.smartkill.activities;



import java.util.HashMap;
import java.util.List;

import pl.pwr.smartkill.R;
import pl.pwr.smartkill.SKApplication;
import pl.pwr.smartkill.obj.LoginData;
import pl.pwr.smartkill.obj.Match;
import pl.pwr.smartkill.obj.Matches;
import pl.pwr.smartkill.obj.Position;
import pl.pwr.smartkill.obj.Positions;
import pl.pwr.smartkill.tools.FixedMyLocationOverlay;
import pl.pwr.smartkill.tools.MyLocationListener;
import pl.pwr.smartkill.tools.PlayersItemizedOverlay;
import pl.pwr.smartkill.tools.WebserviceHandler;
import pl.pwr.smartkill.tools.httpRequests.HttpRequest;
import pl.pwr.smartkill.tools.httpRequests.PostRequest;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.App;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.UiThread;
@EActivity 
public class MapsActivity extends MapActivity {
	@Extra
	Match match;
	
	@App
	SKApplication app;
	
	private MapView mapView;
	private MapController mc;
	private MyLocationOverlay myLocationOverlay;
	public LocationManager locManager;
	public LocationListener locListener;
	
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
		
		// call convenience method that zooms map on our location
		//zoomToMyLocation();
		locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		locListener = new MyLocationListener(this);
		locManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 1000, 2, locListener);
		
	    myLocationOverlay.runOnFirstFix(new Runnable() {
            public void run() {
                mc.animateTo(myLocationOverlay.getMyLocation());
            }

        });
	    
    }
    
    private void updatePositions(GeoPoint myLocation) {
		
		
	}
    @AfterViews
    public void prepare(){
    	getData();
    }
    
    @Background
	public void getData(){
		HashMap<String , String> params = new HashMap<String, String>();
		params.put("id", app.getSessionId());
		params.put("match", match.getId()+"");
		params.put("user", match.getCreated_by()+"");
		params.put("lat", match.getLat());
		params.put("lng", match.getLng());
		Log.e("params", match.getLat()+"");
		Positions m = new WebserviceHandler<Positions>().getAndParse(this, new PostRequest(SKApplication.API_URL+"position", params), new Positions());
		updateData(m);
	}
	
	@UiThread
	public void updateData(Positions m){
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = getResources().getDrawable(R.drawable.victim);
		for (Position p : m.getPositions()){
			if (p.getLat() != null){
			GeoPoint point = new GeoPoint((int)(Float.parseFloat(p.getLat())*1000000),(int)(Float.parseFloat(p.getLng())*1000000));
			PlayersItemizedOverlay itemizedoverlay = new PlayersItemizedOverlay(drawable, this, point, p.getType());
			mapOverlays.add(itemizedoverlay);
			
			Log.e("type", "Position: " + p.getType() );
			}
		}
		//TODO
	}
	
   public static void updateLocation(Location location){
	   
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
