package pl.pwr.smartkill;

import pl.pwr.smartkill.db.UserHelper;

import com.googlecode.androidannotations.annotations.EApplication;

import android.app.Application;

@EApplication 
public class SKApplication extends Application{
	
	private UserHelper uh;
	
	
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
}
