package pl.pwr.smartkill.activities;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import pl.pwr.smartkill.LocationService;
import pl.pwr.smartkill.R;
import pl.pwr.smartkill.SKApplication;
import pl.pwr.smartkill.obj.KillData;
import pl.pwr.smartkill.obj.Match;
import pl.pwr.smartkill.obj.MatchData;
import pl.pwr.smartkill.obj.Position;
import pl.pwr.smartkill.obj.Positions;
import pl.pwr.smartkill.obj.Profile;
import pl.pwr.smartkill.obj.StartData;
import pl.pwr.smartkill.tools.DistanceCalculator;
import pl.pwr.smartkill.tools.FixedMyLocationOverlay;
import pl.pwr.smartkill.tools.PlayersItemizedOverlay;
import pl.pwr.smartkill.tools.WebserviceHandler;
import pl.pwr.smartkill.tools.httpRequests.PostRequest;
import android.R.drawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidquery.AQuery;
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
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity
public class MapsActivity extends MapActivity {
	@Extra
	Match match;

	@App
	SKApplication app;

	@ViewById(R.id.startButton)
	Button startButton;

	@ViewById(R.id.endButton)
	Button endButton;

	HashMap<Integer, Integer> killChecker;
	HashMap<Integer, Profile> profiles;
	boolean sent = true;
	boolean profilesFetched = false;
	private MapView mapView;
	private MapController mc;
	private MyLocationOverlay myLocationOverlay;
	public static final double PRECISION = 1000000;
	private boolean alive = true;
	long delay = 4 * 1000; // delay in ms : 4 * 1000 ms = 4 sec.
	Timer timer = new Timer("TaskName");
	private HashSet<Integer> killed = new HashSet<Integer>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		profiles = new HashMap<Integer, Profile>();
		killChecker = new HashMap<Integer, Integer>();

		myLocationOverlay = new FixedMyLocationOverlay(this, mapView);
		myLocationOverlay.enableMyLocation();
		mapView.getOverlays().add(myLocationOverlay);
		mapView.postInvalidate();
		mc = mapView.getController();
		mc.setZoom(15);

		myLocationOverlay.runOnFirstFix(new Runnable() {
			public void run() {
				getData();
				start();
				mc.animateTo(myLocationOverlay.getMyLocation());
			}

		});

		startButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startGame();
			}
		});

		endButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				endGame();
			}
		});
		//match.setStatus("planed");
		Log.e("id", app.getMyProfile().getId().equals(match.getCreated_by())
				+ "");
		if (app.getMyProfile().getId().intValue() != match.getCreated_by()
				.intValue()) {
			startButton.setVisibility(View.GONE);
		} else {
			startButton.setVisibility(View.VISIBLE);
		}
		endButton.setVisibility(View.GONE);
		// startButton.setVisibility(View.VISIBLE);

	}

	@Background
	protected void startGame() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", app.getSessionId());
		params.put("match", match.getId() + "");
		StartData startGame = new WebserviceHandler<StartData>().getAndParse(
				app, new PostRequest(SKApplication.API_URL + "start", params),
				new StartData());
		match.setStatus("goingon");
		Log.e("startGame", match.getStatus() + "");
		hideStart();
	}

	@UiThread
	public void hideStart(){
		startButton.setVisibility(View.GONE);
		endButton.setVisibility(View.VISIBLE);
	}

	@Background
	protected void endGame() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", app.getSessionId());
		params.put("match", match.getId() + "");
		StartData startGame = new WebserviceHandler<StartData>().getAndParse(
				app, new PostRequest(SKApplication.API_URL + "finish", params),
				new StartData());
		match.setStatus("finished");
		Log.e("endGame", match.getStatus() + "");
	}

	public static double StrToDbl(String data) {
		return (Double.parseDouble(data));
	}

	public void start() {
		TimerTask task = new TimerTask() {
			public void run() {
				if (sent) {
					getData();
				}
				if (myLocationOverlay != null
						&& myLocationOverlay.getMyLocation() != null)
					mc.animateTo(myLocationOverlay.getMyLocation());
			}
		};
		Date executionDate = new Date();
		timer.schedule(task, executionDate, delay);
	}

	@AfterViews
	public void prepare() {
	}

	@Background
	public void getData() {
		sent = false;
		String lat, lng;
		Log.e("data", "getData working");
		if (myLocationOverlay != null
				&& myLocationOverlay.getMyLocation() != null) {
			lat = ((float) (myLocationOverlay.getMyLocation().getLatitudeE6()))
					/ 1000000 + "";
			lng = ((float) myLocationOverlay.getMyLocation().getLongitudeE6())
					/ 1000000 + "";
		} else {
			lat = match.getLat();
			lng = match.getLng();
		}
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", app.getSessionId());
		params.put("match", match.getId() + "");
		MatchData currentMatch = new WebserviceHandler<MatchData>()
				.getAndParse(this, new PostRequest(
						SKApplication.API_URL
						+ "killUser", params),
						new MatchData());

		//		if (currentMatch.getStatus() == "goingon") {
		if (true){
			params = new HashMap<String, String>();
			params.put("id", app.getSessionId());
			params.put("match", match.getId() + "");
			params.put("lat", lat);
			params.put("lng", lng);
			Positions m = new WebserviceHandler<Positions>()
					.getAndParse(this, new PostRequest(SKApplication.API_URL
							+ "position", params), new Positions());
			if (!profilesFetched && m.getPositions() != null) {
				for (Position p : m.getPositions()) {
					Profile prof = getUserProfile(p.getUser());
					if (prof != null)
						profiles.put(p.getUser(), prof);
				}
				profilesFetched = true;
			}
			if (m.getPositions() != null) {
				updateData(m);
			}
		}
	}

	@Background
	public void updateData(Positions m) {
		sent = true;
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable victim = getResources().getDrawable(R.drawable.victim);
		Drawable hunter = getResources().getDrawable(
				R.drawable.ic_launcher_icon);
		Drawable dead = getResources().getDrawable(R.drawable.dead);
		myLocationOverlay = (FixedMyLocationOverlay) mapOverlays.get(0);
		DistanceCalculator dc = new DistanceCalculator();
		String lat, lng;
		int i = 1;
		Log.e("positions", m.getPositions().size()+"");
		for (Position p : m.getPositions()) {
			//			String myType = m.getUser().getType();
			String myType = "prey";
			if (alive && !m.getUser().getIs_active()) {
				alive = false;
			}
			Profile profile = profiles.get(p.getUser());
			if (profile == null)
				Log.e("profile", p.getUser() + " " + profiles.get(0));
			if (p.getLat() != null && profile != null) {
				GeoPoint point = new GeoPoint((int) (Float.parseFloat(p
						.getLat()) * 1000000), (int) (Float.parseFloat(p
								.getLng()) * 1000000));
				PlayersItemizedOverlay itemizedoverlay;
				if (p.getType().equals("hunter")) {
					itemizedoverlay = new PlayersItemizedOverlay(hunter, this,
							point, profile.getUsername(),
							getString(R.string.hunter));
					if (myType.equals("prey")) {
						if (dc.CalculationByDistance(point,
								myLocationOverlay.getMyLocation()) < 0.5) {
							Log.e("number",
									killChecker.containsKey(p.getUser()) + "");
							if (killChecker.containsKey(p.getUser())
									&& killChecker.get(p.getUser()) > 2) {
								Log.e("number", killChecker.get(p.getUser())
										+ "");
								HashMap<String, String> params = new HashMap<String, String>();
								params.put("id", app.getSessionId());
								params.put("match", match.getId() + "");
								params.put("hunter", p.getUser() + "");
								lat = ((float) (myLocationOverlay
										.getMyLocation().getLatitudeE6()))
										/ 1000000 + "";
								lng = ((float) myLocationOverlay
										.getMyLocation().getLongitudeE6())
										/ 1000000 + "";
								params.put("lat", lat);
								params.put("lng", lng);
								KillData killed = new WebserviceHandler<KillData>()
										.getAndParse(this, new PostRequest(
												SKApplication.API_URL
												+ "killUser", params),
												new KillData());
								Log.e("killed", p.getUser() + "");
								showKilled(m.getUser().getUsername(), true);
								changeOverlay(m.getUser().getUsername());
								timer.cancel();
								getData();

							} else {
								if (!killChecker.containsKey(p.getUser())) {
									killChecker.put(p.getUser(), 1);
								} else {
									killChecker.put(p.getUser(),
											killChecker.get(p.getUser()) + 1);
								}
							}
						}
					}
				} else {
					//if (p.getIs_active())
					if (true)
						itemizedoverlay = new PlayersItemizedOverlay(victim,
								this, point, profile.getUsername(),
								getString(R.string.victim));
					else {
						if (!killed.contains(p.getUser())) {
							showKilled(profile.getUsername(), false);
							killed.add(p.getUser());
						}
						itemizedoverlay = new PlayersItemizedOverlay(dead,
								this, point, profile.getUsername(),
								getString(R.string.victim));
					}
				}
				if (mapOverlays.size() > 1 && mapOverlays.size() > i)
					mapOverlays.remove(i);
				mapOverlays.add(itemizedoverlay);
				i++;
			} else
				Log.e("nullowo", "profile albo miejsce");
		}
	}

	@Override
	protected void onPause() {
		try {
			myLocationOverlay.disableMyLocation();
		} catch (Exception e) {
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		try {
			myLocationOverlay.enableMyLocation();
		} catch (Exception e) {
		}
		super.onResume();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@UiThread
	public void changeOverlay(String name) {
		PlayersItemizedOverlay mine = new PlayersItemizedOverlay(getResources()
				.getDrawable(R.drawable.dead), this,
				myLocationOverlay.getMyLocation(), name,
				getString(R.string.victim));
		mapView.getOverlays().remove(0);
		mapView.getOverlays().add(0, mine);
		mapView.postInvalidate();
	}

	@UiThread
	public void showKilled(String name, boolean own) {
		if (own)
			Toast.makeText(app, name + " zostałeś zabity!", Toast.LENGTH_LONG)
			.show();
		else
			Toast.makeText(app, "Zabiłeś " + name, Toast.LENGTH_LONG).show();
	}

	public Profile getUserProfile(Number id) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", app.getSessionId());
		params.put("user", id + "");
		Profile m = new WebserviceHandler<Profile>().getAndParse(this,
				new PostRequest(SKApplication.API_URL + "profile", params),
				new Profile());
		return m;
	}

	// TODO
	public void downloadAvatar(String url) {
		AQuery aq = new AQuery(getParent());
		// aq.id(R.id.image1).image(url);//TODO gdzie R.id.image1 to ID obrazka
		// który ma byc ustawiony, zamiast tego obiekt ImageView można dać
	}
}
