package pl.pwr.smartkill.tools;

import com.google.android.maps.GeoPoint;

public class DistanceCalculator {

	   private double Radius = 6371;

	   // R = earth's radius (mean radius = 6,371km)
	   // Constructor
	   public DistanceCalculator() {
	      Radius = 6371;
	   }

	   public double CalculationByDistance(GeoPoint StartP, GeoPoint EndP) {
	      double lat1 = StartP.getLatitudeE6()/1E6;
	      double lat2 = EndP.getLatitudeE6()/1E6;
	      double lon1 = StartP.getLongitudeE6()/1E6;
	      double lon2 = EndP.getLongitudeE6()/1E6;
	      double dLat = Math.toRadians(lat2-lat1);
	      double dLon = Math.toRadians(lon2-lon1);
	      double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	         Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	         Math.sin(dLon/2) * Math.sin(dLon/2);
	      double c = 2 * Math.asin(Math.sqrt(a));
	      return Radius * c;
	   }
}
