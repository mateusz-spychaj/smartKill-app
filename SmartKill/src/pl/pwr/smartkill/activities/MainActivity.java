package pl.pwr.smartkill.activities;

import java.util.HashMap;

import android.content.Intent;
import android.util.Log;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.App;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;

import pl.pwr.smartkill.R;
import pl.pwr.smartkill.SKApplication;
import pl.pwr.smartkill.adapters.GameListAdapter;
import pl.pwr.smartkill.obj.Matches;
import pl.pwr.smartkill.tools.WebserviceHandler;
import pl.pwr.smartkill.tools.httpRequests.PostRequest;


@EActivity(R.layout.activity_main)
public class MainActivity extends SherlockActivity {
	
	@App
	SKApplication app;
	
	@ViewById(R.id.main_list)
	ListView list;
	
//	@Click(R.id.game_button) 
//	public void openMap(){
//		startActivity(new Intent(this,MapsActivity.class));
//	}
	
	@AfterViews
	public void prepare(){
		getMatches();
	}
	
	@Background
	public void getMatches(){
		HashMap<String , String> params = new HashMap<String, String>();
		params.put("id", app.getSessionId());
		Matches m = new WebserviceHandler<Matches>().getAndParse(this, new PostRequest(SKApplication.API_URL+"matches", params), new Matches());
		updateList(m);
	}
	
	@UiThread
	public void updateList(Matches m){
		list.setAdapter(new GameListAdapter(this, m.getMatches())); 
	}
}
