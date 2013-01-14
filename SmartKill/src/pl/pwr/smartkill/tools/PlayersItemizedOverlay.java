package pl.pwr.smartkill.tools;

import java.util.ArrayList;

import pl.pwr.smartkill.R;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class PlayersItemizedOverlay extends ItemizedOverlay<OverlayItem> {
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mContext = null;

	public PlayersItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}

	protected OverlayItem createItem(int index) {
		return mOverlays.get(index);
	}

	public void addOverlay(OverlayItem overlay) {
		mOverlays.add(overlay);
		populate();
	}


	public int size() {
		return mOverlays.size();
	}

	public PlayersItemizedOverlay(Drawable defaultMarker, Context context, GeoPoint point, String name, String type) {

		super(boundCenterBottom(defaultMarker));
		mContext = context;
		OverlayItem overlayitem = new OverlayItem(point, type, name);
		this.addOverlay(overlayitem);
	}

	protected boolean onTap(int index) {
		OverlayItem item = mOverlays.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet());
		dialog.show();
		return true;
	}

}
