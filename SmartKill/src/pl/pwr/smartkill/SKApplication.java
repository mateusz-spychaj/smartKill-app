package pl.pwr.smartkill;

import pl.pwr.smartkill.db.UserHelper;
import pl.pwr.smartkill.obj.User;

import com.googlecode.androidannotations.annotations.EApplication;

import android.app.Application;
import android.util.Log;

@EApplication 
public class SKApplication extends Application{
	
	public static String API_URL="http://smartkill.pl/app_dev.php/api/";
	
	private String sessionId;
	private boolean online;
	private UserHelper uh;
	private User myProfile;
	
	public User getMyProfile() {
		return myProfile;
	}
	public void setMyProfile(User myProfile) {
		this.myProfile = myProfile;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		uh = new UserHelper(this);
	}


	public UserHelper getUh() {
		return uh;
	}

	public void setUh(UserHelper uh) {
		this.uh = uh;
	}


	public boolean isOnline() {
		return online;
	}


	public void setOnline(boolean online) {
		this.online = online;
	}


	public String getSessionId() {
		return sessionId;
	}


	public void setSessionId(String sessionId) {
		Log.e("session id", sessionId);
		this.sessionId = sessionId;
	}
	
}
